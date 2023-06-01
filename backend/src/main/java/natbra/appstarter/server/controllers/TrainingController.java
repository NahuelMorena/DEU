package natbra.appstarter.server.controllers;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.model.Training;
import natbra.appstarter.server.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TrainingController {
	private final String baseUrl = "/admin/trainings";
	
	@Autowired
	TrainingRepository trainingRepository;
	@Autowired Utils utils;
	
	@GetMapping(baseUrl)
	public HttpEntity<List<Training>> getTrainings(){
		return ResponseEntity.ok(trainingRepository.findAll());
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
		return ResponseEntity.ok(trainingRepository.save(training));
	}
}
