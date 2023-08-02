package natbra.appstarter.server.controllers;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.model.train.Planification;
import natbra.appstarter.server.model.train.TrainerPlanification;
import natbra.appstarter.server.model.train.UserPlanification;
import natbra.appstarter.server.repository.PlanificationRepository;
import natbra.appstarter.server.controllers.requests.AddPlanification;
import natbra.appstarter.server.repository.TrainerPlanificationRepository;
import natbra.appstarter.server.repository.UserPlanificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class PlanificationController {

    private final String baseUrl = "/admin/planifications";

    @Autowired
    PlanificationRepository planificationRepository;

    @Autowired
    UserPlanificationRepository userPlanificationRepository;
    @Autowired
    TrainerPlanificationRepository trainerPlanificationRepository;
    @Autowired
    Utils utils;

    @GetMapping(baseUrl + "/basic")
    public HttpEntity<Set<Planification>> getPlanificationsBasic(){
        //
        return ResponseEntity.ok(planificationRepository.findAllByUserId(utils.getAuthUser().getId()));
    }

    @PostMapping(baseUrl + "/retrieve")
    public HttpEntity<Set<TrainerPlanification>> getPlanifications(@RequestBody Planification planification){
        return ResponseEntity.ok(trainerPlanificationRepository.findAllByPlanificationId(planification.getId()));
    }

    @PostMapping(baseUrl + "/retrieve-by-trainer")
    public HttpEntity<Set<TrainerPlanification>> getPlanificationsByTrainer(@RequestBody User user){

        List<TrainerPlanification> trainerPlanifications = trainerPlanificationRepository.findAll();
        Set<TrainerPlanification> trainerPlanificationsOfTrainer = new HashSet<TrainerPlanification>();
        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
            if(trainerPlanification.getTraining().getUser().getId() == user.getId()){
                trainerPlanificationsOfTrainer.add(trainerPlanification);
            }
        }
        return ResponseEntity.ok(trainerPlanificationsOfTrainer);
    }

    @DeleteMapping(baseUrl)
    public HttpEntity<Planification> deletePlanification(@RequestBody Planification planification){
        //recorro los trainer planifications que agarro con el metodo creado y los borramos
        Set<TrainerPlanification> trainerPlanifications = getTrainerPlanificationList(planification.getId());
//        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
//            trainerPlanificationRepository.delete(trainerPlanification);
//        }
        trainerPlanificationRepository.deleteAll(trainerPlanifications);

        //recorro los user planifications que agarro con el metodo creado y los borramos
        Set<UserPlanification> userPlanifications = getUserPlanificationsList(planification.getId());
        userPlanificationRepository.deleteAll(userPlanifications);

        planificationRepository.delete(planification);
        return ResponseEntity.ok(planification);
    }

    @DeleteMapping(baseUrl + "/trainers")
    public HttpEntity<Set<TrainerPlanification>> deletePlanificationTrainers(@RequestBody Set<TrainerPlanification> trainerPlanifications){

        trainerPlanificationRepository.deleteAll(trainerPlanifications);

        return ResponseEntity.ok(trainerPlanifications);
    }

    @PostMapping(baseUrl)
    public HttpEntity<Planification> addPlanification(@RequestBody Planification planification){
        return ResponseEntity.ok(planificationRepository.save(planification));

    }
    @PostMapping(baseUrl + "/trainers")
    public HttpEntity<Planification> addPlanificationTrainer(@RequestBody AddPlanification planification){
        //creo la planificacion con el nombre
        Planification planification1 = new Planification();
        planification1.setUser(utils.getAuthUser());
        planification1.setName(planification.getName());
        //la guardo y la agarro para cargarla en el trainer_planifications
//        planification1.setTrainer_planification(planification.getTrainerPlanificationList());
        Planification planificationSaved = planificationRepository.save(planification1);

        //recorro los trainer planifications para este nuevo plan y los voy cargando a la BD
        Set<TrainerPlanification> trainerPlanifications = planification.getTrainerPlanificationList();
        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
            trainerPlanification.setPlanification(planificationSaved);
            //trainerPlanificationRepository.save(trainerPlanification);
        }
        trainerPlanificationRepository.saveAll(trainerPlanifications);

        return ResponseEntity.ok(planificationSaved);
    }

    @PutMapping(baseUrl + "/trainers")
    public HttpEntity<Planification> editPlanificationTrainer(@RequestBody AddPlanification planification){

        Planification planification1 = planificationRepository.getReferenceById(planification.getId());
        planification1.setName(planification.getName());

        Set<TrainerPlanification> trainerPlanifications = planification.getTrainerPlanificationList();
        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
            trainerPlanification.setPlanification(planification1);
            //trainerPlanificationRepository.save(trainerPlanification);
        }

        trainerPlanificationRepository.saveAll(trainerPlanifications);


        return ResponseEntity.ok(planificationRepository.save(planification1));
    }

    @PutMapping(baseUrl)
    public HttpEntity<Set<TrainerPlanification>> editPlanification(@RequestBody Set<TrainerPlanification> trainerPlanifications){
        trainerPlanificationRepository.saveAll(trainerPlanifications);
        return ResponseEntity.ok(trainerPlanifications);
    }

    @PutMapping(baseUrl + "/basic")
    public HttpEntity<Planification> changePlanificationName(@RequestBody Planification planification){
        Planification existingPlanification = planificationRepository.findById(planification.getId())
                .orElseThrow(() -> new IllegalArgumentException("Planification not found"));

        // Update the necessary fields of existingPlanification using the values from the planification parameter
        existingPlanification.setName(planification.getName());

        // Save the updated Planification object
        planificationRepository.save(existingPlanification);

        return ResponseEntity.ok().build();
    }

    public Set<TrainerPlanification> getTrainerPlanificationList(Long id){
        //return trainerPlanificationRepository.findAllById(id);
        return trainerPlanificationRepository.findAllByPlanificationId(id);
    }

    public Set<UserPlanification> getUserPlanificationsList(Long id){
        //return trainerPlanificationRepository.findAllById(id);
        return userPlanificationRepository.findAllByPlanificationId(id);
    }

}
