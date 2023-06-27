package natbra.appstarter.server.controllers;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.controllers.requests.AddCalification;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.model.train.*;
import natbra.appstarter.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserPlanificationController {

    private final String baseUrl = "/admin/users/planifications";

    @Autowired
    UserPlanificationRepository userPlanificationRepository;

    @Autowired
    CalificationsRepository calificationsRepository;
    @Autowired
    TrainerPlanificationRepository trainerPlanificationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    Utils utils;

    @GetMapping(baseUrl)
    public HttpEntity<List<UserPlanification>> getUserPlanifications(){
        return ResponseEntity.ok(userPlanificationRepository.findAll());
    }

    @PostMapping(baseUrl + "/retrieve")
    public HttpEntity<Set<UserPlanification>> getUserPlanificationsById(@RequestBody Planification planification){
        return ResponseEntity.ok(userPlanificationRepository.findAllByPlanificationId(planification.getId()));
    }

    @PostMapping(baseUrl + "/retrieve-by-user")
    public HttpEntity<Set<UserPlanification>> getUserPlanificationsByUserId(@RequestBody User user){
        return ResponseEntity.ok(userPlanificationRepository.findAllByUserId(user.getId()));
    }

    @PutMapping(baseUrl + "/calification")
    public HttpEntity<Calification> getUserPlanificationsByUserId(@RequestBody AddCalification addCalification){
        Calification calification = new Calification();
        User user = userRepository.findById(addCalification.getUser_id()).orElseThrow(NoSuchElementException::new);
        Training training = trainingRepository.findById(addCalification.getTraining_id()).orElseThrow(NoSuchElementException::new);
        TrainerPlanification trainerPlanification = trainerPlanificationRepository.findById(addCalification.getTrainer_planification_id()).orElseThrow(NoSuchElementException::new);
        calification.setNote(addCalification.getNote());
        calification.setUser(user);
        calification.setTrainerPlanification(trainerPlanification);
        calification.setTraining(training);

        if(calificationsRepository.existsByUserAndTrainingAndTrainerPlanification(calification.getUser(),calification.getTraining(),calification.getTrainerPlanification())){
            Calification calificationToReplace = calificationsRepository.findByUserAndTrainingAndTrainerPlanification(calification.getUser(),calification.getTraining(),calification.getTrainerPlanification());
            calificationToReplace.setNote(addCalification.getNote());
            return ResponseEntity.ok(calificationsRepository.save(calificationToReplace));
        }else{
            return ResponseEntity.ok(calificationsRepository.save(calification));
        }
    }

    @PostMapping(baseUrl + "/calification/get-by-players")
    public HttpEntity<Set<Calification>> getCalificationsByPlayers(@RequestBody Set<User> users){
        Set<Calification> califications = new HashSet<Calification>();
        Calification calification;
        for (User user : users) {
            califications.addAll(calificationsRepository.findAllByUserId(user.getId()));
//            for(Calification calification1: calificationsRepository.findAllByUserId(user.getId())){
//                califications.add(calification1);
//            }
        }
        return ResponseEntity.ok(califications);
    }

    @PostMapping(baseUrl + "/calification/get-by-player")
    public HttpEntity<Set<Calification>> getCalificationsByPlayer(@RequestBody User user){

        return ResponseEntity.ok(calificationsRepository.findAllByUserId(user.getId()));
    }
    @PostMapping(baseUrl + "/retrieve-by-trainer")
    public HttpEntity<Set<UserPlanification>> getUserPlanificationsByTrainerId(@RequestBody User user){
        List<UserPlanification> userPlanifications = userPlanificationRepository.findAll();
        Set<UserPlanification> userPlanificationsOfTrainer = new HashSet<UserPlanification>();
        for (UserPlanification userPlanification : userPlanifications) {
            if(userPlanification.getUser().getTrainer().getId() == user.getId()){
                userPlanificationsOfTrainer.add(userPlanification);
            }
        }
        return ResponseEntity.ok(userPlanificationsOfTrainer);
    }

    @DeleteMapping(baseUrl)
    public HttpEntity<List<UserPlanification>> deleteUserPlanification(@RequestBody List<UserPlanification> userPlanifications){

        //aca me llegan los userPlanifications que quiero eliminar, del otro lado elegire cuales son los que saco y creo un array con esos
        userPlanificationRepository.deleteAll(userPlanifications);
        return ResponseEntity.ok(userPlanifications);
    }



    @PostMapping(baseUrl)
    public HttpEntity<List<UserPlanification>> addUserPlanification(@RequestBody List<UserPlanification> userPlanifications){
        return ResponseEntity.ok(userPlanificationRepository.saveAll(userPlanifications));

    }

    @PutMapping(baseUrl)
    public HttpEntity<List<UserPlanification>> editUserPlanification(@RequestBody List<UserPlanification> userPlanifications){
        return ResponseEntity.ok(userPlanificationRepository.saveAll(userPlanifications));

    }

}
