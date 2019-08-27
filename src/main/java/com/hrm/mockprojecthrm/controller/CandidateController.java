package com.hrm.mockprojecthrm.controller;

import com.hrm.mockprojecthrm.dto.CandidateDTO;
import com.hrm.mockprojecthrm.entity.Candidate;
import com.hrm.mockprojecthrm.exception.ResourceNotFoundException;
import com.hrm.mockprojecthrm.mapper.CandidateMapper;
import com.hrm.mockprojecthrm.payload.ApiResponse;
import com.hrm.mockprojecthrm.repository.CandidateRepository;
import com.hrm.mockprojecthrm.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateMapper candidateMapper;
    @GetMapping("/candidate")
    public List<CandidateDTO> candidateList(){
        List<Candidate> candidates = candidateRepository.findAll();
        return candidateMapper.candidateToCandidateDTO(candidates);
    }
    @GetMapping("/candidate/{id}")
    public ResponseEntity<?> candidateById(@PathVariable Long id){
        return candidateRepository.findById(id).map(candidate -> ResponseEntity.ok().body(candidate))
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));
        /*Optional<Candidate> candidate = candidateRepository.findById(id);
        CandidateDTO result = (CandidateDTO) candidateMapper.candidateToCandidateDTO((List<Candidate>) candidate.get());
        return ResponseEntity.ok().body(result);*/
    }

    @PostMapping("/candidate")
    public ResponseEntity<?> createCandidate(@RequestBody CandidateDTO candidateDTO) throws URISyntaxException {
        Candidate result = candidateService.createCandidate(candidateDTO);
        return ResponseEntity.created(new URI("/api/candidate/" + result.getId() )).body(result);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id){
        return candidateRepository.findById(id).map(candidate -> {
            candidateRepository.delete(candidate);
            return ResponseEntity.ok().body(new ApiResponse(true, "User delete successfully"));
        }).orElseThrow(() -> new ResourceNotFoundException("User",  "id", id));
    }

    @PutMapping("/candidate/edit")
    public ResponseEntity<?> editCandidate(@Valid @RequestBody CandidateDTO candidateDTO){
        if(candidateDTO.getId() == null){
            return new ResponseEntity<>(new ApiResponse(false, "id not null!"),
                    HttpStatus.BAD_REQUEST);
        }
        Candidate candidate = candidateMapper.candidateDTOById(candidateDTO);
        Candidate resulf = candidateRepository.save(candidate);
        return ResponseEntity.ok().body(resulf);
    }

    /*@PutMapping("/candidate")
    public ResponseEntity<?> updateCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        if (candidateDTO.getId() == null) {
            return new ResponseEntity<>(new ApiResponse(false, "id not null!"),
                    HttpStatus.BAD_REQUEST);
        }
        Candidate candidate = candidateMapper.candidateDTOById(candidateDTO);
        candidate = candidateRepository.save(candidate);
        CandidateDTO resulf = (CandidateDTO) candidateMapper.candidateToCandidateDTO((List<Candidate>) candidate);
        return ResponseEntity.ok().body(resulf);
    }*/
}
