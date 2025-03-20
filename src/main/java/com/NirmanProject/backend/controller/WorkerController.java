package com.NirmanProject.backend.controller;

import com.NirmanProject.backend.dto.WorkerDTO;
import com.NirmanProject.backend.model.Worker;
import com.NirmanProject.backend.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> registerWorker(@ModelAttribute WorkerDTO workerDTO) {
        try {
            Worker savedWorker = workerService.registerWorker(workerDTO);
            return new ResponseEntity<>(savedWorker, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering worker", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/details")  // Renamed to avoid conflict
    public ResponseEntity<WorkerDTO> getWorkerDetails(@PathVariable String id) {
        WorkerDTO workerDTO = workerService.getWorkerDetails(id);
        return workerDTO != null ? ResponseEntity.ok(workerDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable String workerId) {
        return workerService.getWorkerById(workerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkerDTO>> searchWorkers(@RequestParam String name) {
        return ResponseEntity.ok(workerService.searchWorkersByName(name));
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

    @GetMapping("/{id}/profilePhoto")
    public ResponseEntity<String> getProfilePhoto(@PathVariable String id) {
        Worker worker = workerService.findWorkerById(id);
        return (worker == null || worker.getProfilePhoto() == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(worker.getProfilePhoto());
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateWorker(@PathVariable String id, @ModelAttribute WorkerDTO workerDTO) {
        try {
            return ResponseEntity.ok(workerService.updateWorker(id, workerDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating worker");
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalWorkers() {
        return ResponseEntity.ok(workerService.getTotalWorkers());
    }

    @GetMapping("/list-by-email/{email}")
    public ResponseEntity<List<Worker>> getWorkersByEmail(@PathVariable String email) {
        return ResponseEntity.ok(workerService.getWorkersByRegisteredEmail(email));
    }

    @GetMapping("/count-by-email/{email}")
    public ResponseEntity<Long> countWorkersByEmail(@PathVariable String email) {
        return ResponseEntity.ok(workerService.countWorkersByRegisteredEmail(email));
    }
}
