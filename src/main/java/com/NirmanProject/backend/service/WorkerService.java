package com.NirmanProject.backend.service;

import com.NirmanProject.backend.dto.WorkerDTO;
import com.NirmanProject.backend.model.Worker;
import com.NirmanProject.backend.repositary.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    // Method to register a new worker
    public Worker registerWorker(WorkerDTO workerDTO) {
        Worker worker = new Worker();
        worker.setFullName(workerDTO.getFullName());
        worker.setAge(workerDTO.getAge());
        worker.setDateOfBirth(LocalDate.parse(workerDTO.getDateOfBirth()));
        worker.setGender(workerDTO.getGender());
        worker.setContact(workerDTO.getContact());
        worker.setEmergencyContact(workerDTO.getEmergencyContact());
        worker.setEmail(workerDTO.getEmail());
        worker.setStreetAddress(workerDTO.getStreetAddress());
        worker.setDistrict(workerDTO.getDistrict());
        worker.setState(workerDTO.getState());
        worker.setPincode(workerDTO.getPincode());
        worker.setMaritalStatus(workerDTO.getMaritalStatus());
        worker.setPhysicallyHandicapped(workerDTO.getPhysicallyHandicapped());
        worker.setCriticalIllness(workerDTO.getCriticalIllness());

        // Professional Details
        worker.setSkillSet(workerDTO.getSkillSet());
        worker.setSkillLevel(workerDTO.getSkillLevel());
        worker.setPreferredWorkLocation(workerDTO.getPreferredWorkLocation());
        worker.setAvailability(workerDTO.getAvailability());

        worker.setProfilePhoto(workerDTO.getProfilePhoto());
        worker.setAadhaarPhoto(workerDTO.getAadhaarPhoto());
        worker.setAlternateDoc(workerDTO.getAlternateDoc());


        // Consent
        worker.setConsent(workerDTO.getConsent());

        // Set other fields...
        return workerRepository.save(worker);
    }

    // Method to get worker details by ID
    public WorkerDTO getWorkerDetails(String id) {
        Worker worker = workerRepository.findById(id).orElse(null);
        if (worker == null) {
            return null;
        }
        return convertToDTO(worker);
    }

    // Method to search workers by name
    public List<WorkerDTO> searchWorkersByName(String name) {
        List<Worker> workers = workerRepository.findByFullNameContainingIgnoreCase(name);
        return workers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Method to find a worker by ID
    public Worker findWorkerById(String id) {
        return workerRepository.findById(id).orElse(null);
    }

    // Helper method to convert Worker to WorkerDTO
    private WorkerDTO convertToDTO(Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setWorkerId(worker.getWorkerId());
        workerDTO.setFullName(worker.getFullName());
        workerDTO.setAge(worker.getAge());
        workerDTO.setDateOfBirth(String.valueOf(worker.getDateOfBirth()));
        workerDTO.setGender(worker.getGender());
        workerDTO.setContact(worker.getContact());
        workerDTO.setEmergencyContact(worker.getEmergencyContact());
        workerDTO.setEmail(worker.getEmail());
        workerDTO.setStreetAddress(worker.getStreetAddress());
        workerDTO.setDistrict(worker.getDistrict());
        workerDTO.setState(worker.getState());
        workerDTO.setPincode(worker.getPincode());
        workerDTO.setMaritalStatus(worker.getMaritalStatus());
        workerDTO.setPhysicallyHandicapped(worker.getPhysicallyHandicapped());
        workerDTO.setCriticalIllness(worker.getCriticalIllness());

        // Professional Details
        workerDTO.setSkillSet(worker.getSkillSet());
        workerDTO.setSkillLevel(worker.getSkillLevel());
        workerDTO.setPreferredWorkLocation(worker.getPreferredWorkLocation());
        workerDTO.setAvailability(worker.getAvailability());

        // Photos
        workerDTO.setProfilePhoto(worker.getProfilePhoto());
        workerDTO.setAadhaarPhoto(worker.getAadhaarPhoto());
        workerDTO.setAlternateDoc(worker.getAlternateDoc());


        // Consent
        workerDTO.setConsent(worker.getConsent());

        return workerDTO;
    }

    public Worker updateWorker(String id, WorkerDTO workerDTO) {
        // Find the worker to be updated
        Worker existingWorker = findWorkerById(id);
        if (existingWorker == null) {
            throw new RuntimeException("Worker not found");
        }

        // Update fields
        existingWorker.setFullName(workerDTO.getFullName());
        existingWorker.setAge(workerDTO.getAge());
        existingWorker.setContact(workerDTO.getContact());
        existingWorker.setEmail(workerDTO.getEmail());
        existingWorker.setSkillSet(workerDTO.getSkillSet());
        existingWorker.setSkillLevel(workerDTO.getSkillLevel());
        existingWorker.setPreferredWorkLocation(workerDTO.getPreferredWorkLocation());
        existingWorker.setAvailability(workerDTO.getAvailability());

        // Professional Details
        existingWorker.setSkillSet(workerDTO.getSkillSet());
        existingWorker.setSkillLevel(workerDTO.getSkillLevel());
        existingWorker.setPreferredWorkLocation(workerDTO.getPreferredWorkLocation());
        existingWorker.setAvailability(workerDTO.getAvailability());

        // Consent
        existingWorker.setConsent(workerDTO.getConsent());

        // Update images only if new ones are provided
        if (workerDTO.getProfilePhoto() != null) {
            // Update the profile photo
            existingWorker.setProfilePhoto(workerDTO.getProfilePhoto());
        }
        if (workerDTO.getAadhaarPhoto() != null) {
            // Update the aadhaar photo
            existingWorker.setAadhaarPhoto(workerDTO.getAadhaarPhoto());
        }
        if (workerDTO.getAlternateDoc() != null) {
            // Update the alternate document
            existingWorker.setAlternateDoc(workerDTO.getAlternateDoc());
        }

        // Save the updated worker
        return workerRepository.save(existingWorker);
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }
//GET WORKER BY ID
    public Optional<Worker> getWorkerById(String workerId) {
        return workerRepository.findById(workerId);
    }

    // Method to get the total number of workers
    public long getTotalWorkers() {
        return workerRepository.count();
    }


}