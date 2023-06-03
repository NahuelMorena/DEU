package natbra.appstarter.server.controllers;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.controllers.requests.AddPlanification;
import natbra.appstarter.server.controllers.requests.AddUserPlanification;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.model.train.Planification;
import natbra.appstarter.server.model.train.TrainerPlanification;
import natbra.appstarter.server.model.train.UserPlanification;
import natbra.appstarter.server.repository.PlanificationRepository;
import natbra.appstarter.server.repository.TrainerPlanificationRepository;
import natbra.appstarter.server.repository.UserPlanificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserPlanificationController {

    private final String baseUrl = "/admin/user/planification";

    @Autowired
    PlanificationRepository planificationRepository;

    @Autowired
    UserPlanificationRepository userPlanificationRepository;

    @Autowired
    Utils utils;

    @GetMapping(baseUrl)
    public HttpEntity<List<UserPlanification>> getUserPlanifications(){
        return ResponseEntity.ok(userPlanificationRepository.findAll());
    }

    @DeleteMapping(baseUrl)
    public HttpEntity<Set<UserPlanification>> deleteUserPlanification(@RequestBody Set<UserPlanification> userPlanifications){

        for(UserPlanification userPlanification : userPlanifications){
            userPlanificationRepository.delete(userPlanification);
        }

        return ResponseEntity.ok(userPlanifications);
    }

    @PostMapping(baseUrl)
    public HttpEntity<AddUserPlanification> addUserPlanification(@ModelAttribute AddUserPlanification usersPlanifications){

        Planification planification = usersPlanifications.getPlanification();
        LocalDate date = usersPlanifications.getDate();

        UserPlanification userPlanification1 = new UserPlanification();
        for(User user : usersPlanifications.getUsers()){
            userPlanification1.setUser(user);
            userPlanification1.setDate(date);
            userPlanification1.setPlanification(planification);
            userPlanificationRepository.save(userPlanification1);
        }

        return ResponseEntity.ok(usersPlanifications);

    }

    @PutMapping(baseUrl)
    public HttpEntity<AddUserPlanification> editUserPlanification(@ModelAttribute AddUserPlanification usersPlanifications){

        //ESTO SOLO CAMBIARIA EL DATE POR AHORA
        LocalDate date = usersPlanifications.getDate();

        UserPlanification userPlanification1 = new UserPlanification();
        for(User user : usersPlanifications.getUsers()){
            userPlanification1.setDate(date);
            userPlanificationRepository.save(userPlanification1);
        }

        return ResponseEntity.ok(usersPlanifications);
    }

}
