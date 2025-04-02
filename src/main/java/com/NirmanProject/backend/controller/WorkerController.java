package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.dto.WorkerDTO;
import com.NirmanProject.backend.model.Worker;
import com.NirmanProject.backend.service.JobRequestService;
import com.NirmanProject.backend.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;
    private final JobRequestService jobRequestService;

    public WorkerController(WorkerService workerService, JobRequestService jobRequestService) {
        this.workerService = workerService;
        this.jobRequestService = jobRequestService;
    }

    // Endpoint to register a new worker (multipart/form-data)
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> registerWorker(@ModelAttribute WorkerDTO workerDTO) {
        try {
            Worker savedWorker = workerService.registerWorker(workerDTO);
            return new ResponseEntity<>(savedWorker, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error registering worker", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve worker details by ID (including profile photo, name, skills, and location)
    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorkerDetails(@PathVariable String id) {
        WorkerDTO workerDTO = workerService.getWorkerDetails(id);
        if (workerDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(workerDTO, HttpStatus.OK);
    }

    // Endpoint to search workers by name (returns full name, skill, and location)
    @GetMapping("/search")
    public ResponseEntity<List<WorkerDTO>> searchWorkers(@RequestParam String name) {
        List<WorkerDTO> workers = workerService.searchWorkersByName(name);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = workerService.getAllWorkers();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    // Endpoint to get the profile photo of a worker by ID
    @GetMapping("/{id}/profilePhoto")
    public ResponseEntity<String> getProfilePhoto(@PathVariable String id) {
        Worker worker = workerService.findWorkerById(id);
        if (worker == null || worker.getProfilePhoto() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worker.getProfilePhoto());
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateWorker(@PathVariable String id, @ModelAttribute WorkerDTO workerDTO) {
        try {
            Worker updatedWorker = workerService.updateWorker(id, workerDTO);
            return new ResponseEntity<>(updatedWorker, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating worker", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Worker endpoints
    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorker() {
        List<Worker> workers = workerService.findAll();
        return ResponseEntity.ok(workers);
    }

//    @GetMapping("/workers/available")
//    public ResponseEntity<List<Worker>> getAvailableWorkers() {
//        List<Worker> workers = workerService.findByAvailability(true);
//        return ResponseEntity.ok(workers);
//    }


}
