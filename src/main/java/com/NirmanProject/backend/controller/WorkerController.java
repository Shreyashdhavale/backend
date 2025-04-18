package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.dto.WorkerDTO;
import com.NirmanProject.backend.model.Worker;
import com.NirmanProject.backend.service.JobRequestService;
import com.NirmanProject.backend.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;
    private final JobRequestService jobRequestService;

    public WorkerController(WorkerService workerService, JobRequestService jobRequestService) {
        this.workerService = workerService;
        this.jobRequestService = jobRequestService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> getWorkerDetails(@PathVariable String id) {
        WorkerDTO workerDTO = workerService.getWorkerDetails(id);
        if (workerDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(workerDTO, HttpStatus.OK);
    }

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

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorker() {
        List<Worker> workers = workerService.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/count-by-email/{email}")
    public ResponseEntity<Long> countByEmail(@PathVariable String email) {
        return ResponseEntity.ok(workerService.countWorkersByRegisteredEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginWorker(@RequestBody WorkerDTO workerDTO) {
        Optional<Worker> workerOptional = workerService.loginWorker(workerDTO.getWorkerId());

        if (workerOptional.isPresent()) {
            Worker worker = workerOptional.get();
            WorkerDTO responseDTO = workerService.convertToDTO(worker);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Worker ID");
        }
    }

    // ✅ NEW ENDPOINT ADDED HERE
    @GetMapping("/total")
    public ResponseEntity<Long> getTotalWorkers() {
        long total = workerService.getTotalWorkers();
        return ResponseEntity.ok(total);
    }



}
