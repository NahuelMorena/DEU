package natbra.appstarter.server.controllers;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.model.train.Training;
import natbra.appstarter.server.model.train.TrainingType;
import natbra.appstarter.server.repository.TrainingRepository;
import natbra.appstarter.server.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class TrainingController {
	private final String baseUrl = "/admin/trainings";
	
	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	TrainingTypeRepository trainingTypeRepository;
	@Autowired Utils utils;

	@GetMapping(baseUrl + "/get-types")
	public HttpEntity<List<TrainingType>> getTrainingTypes(){
		return ResponseEntity.ok(trainingTypeRepository.findAll());
	}
	@GetMapping(baseUrl)
	public HttpEntity<List<Training>> getTrainingsByUser(){
		return ResponseEntity.ok(trainingRepository.findAllByUserId(utils.getAuthUser().getId()));
	}

	@GetMapping(baseUrl + "/get-user")
	public HttpEntity<User> getTrainer(@RequestBody Training training){
		return ResponseEntity.ok(training.getUser());
	}

	@DeleteMapping(baseUrl)
	public HttpEntity<Training> deleteTraining(@RequestBody Training training){
		trainingRepository.delete(training);
		return ResponseEntity.ok(training);
	}
	
	@PostMapping(baseUrl)
	public HttpEntity<Training> addTraining(@RequestBody Training training){
		training.setUser(utils.getAuthUser());
		return ResponseEntity.ok(trainingRepository.save(training));
	}
	
	@PutMapping(baseUrl)
	public HttpEntity<Training> editTraining(@RequestBody Training training){
		training.setUser(utils.getAuthUser());
		return ResponseEntity.ok(trainingRepository.save(training));
	}
}
