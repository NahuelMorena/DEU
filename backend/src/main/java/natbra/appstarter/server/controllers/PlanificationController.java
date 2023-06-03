package natbra.appstarter.server.controllers;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.model.train.Planification;
import natbra.appstarter.server.model.train.TrainerPlanification;
import natbra.appstarter.server.repository.PlanificationRepository;
import natbra.appstarter.server.controllers.requests.AddPlanification;
import natbra.appstarter.server.repository.TrainerPlanificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanificationController {

    private final String baseUrl = "/admin/planifications";

    @Autowired
    PlanificationRepository planificationRepository;

    @Autowired
    TrainerPlanificationRepository trainerPlanificationRepository;
    @Autowired
    Utils utils;

    @GetMapping(baseUrl)
    public HttpEntity<Set<AddPlanification>> getPlanifications(){

        //List<Planification> planificationsList = planificationRepository.findAll();

        //Set<Planification> planifications = new HashSet<>(planificationsList);

        //donde vamos a ir cargando en el for las planifications con los trainers planifications y la respectiva planificacion con su nombre
        Set<AddPlanification> fullPlanificationToAdd = new HashSet<>();
        AddPlanification planificationToAdd = new AddPlanification();

        for(Planification planification: planificationRepository.findAll()){
            //seteamos nombre y lista de trainer planifications

            planificationToAdd.setName(planification.getName());
            //planificationToAdd.setTrainerPlanificationList(trainerPlanificationRepository.findAllById(planification.getId()));
            planificationToAdd.setTrainerPlanificationList(trainerPlanificationRepository.findAllByPlanificationId(planification.getId()));

            //agregamos a la lista de todas las planificaciones
            fullPlanificationToAdd.add(planificationToAdd);
        }

        return ResponseEntity.ok(fullPlanificationToAdd);
    }

    @DeleteMapping(baseUrl)
    public HttpEntity<Planification> deletePlanification(@RequestBody Planification planification){
        //recorro los trainer planifications que agarro con el metodo creado y los borramos
        Set<TrainerPlanification> trainerPlanifications = getTrainerPlanificationList(planification.getId());
        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
            trainerPlanificationRepository.delete(trainerPlanification);
        }
        planificationRepository.delete(planification);
        return ResponseEntity.ok(planification);
    }

    @PostMapping(baseUrl)
    public HttpEntity<Planification> addPlanification(@ModelAttribute AddPlanification planification){
        //recorro los trainer planifications para este nuevo plan y los voy cargando a la BD
        Set<TrainerPlanification> trainerPlanifications = planification.getTrainerPlanificationList();
        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
            trainerPlanificationRepository.save(trainerPlanification);
        }
        Planification planification1 = new Planification();
        planification1.setName(planification.getName());
        return ResponseEntity.ok(planificationRepository.save(planification1));
    }

    @PutMapping(baseUrl)
    public HttpEntity<Planification> editPlanification(@ModelAttribute AddPlanification planification){
        //recorro los trainer planifications para este nuevo plan y los voy cargando a la BD
        Set<TrainerPlanification> trainerPlanifications = planification.getTrainerPlanificationList();
        for (TrainerPlanification trainerPlanification : trainerPlanifications) {
            trainerPlanificationRepository.save(trainerPlanification);
        }
        Planification planification1 = new Planification();
        planification1.setName(planification.getName());
        return ResponseEntity.ok(planificationRepository.save(planification1));
    }

    public Set<TrainerPlanification> getTrainerPlanificationList(Long id){
        //return trainerPlanificationRepository.findAllById(id);
        return trainerPlanificationRepository.findAllByPlanificationId(id);
    }
}
