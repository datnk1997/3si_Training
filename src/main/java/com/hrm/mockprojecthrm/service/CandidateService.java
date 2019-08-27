package com.hrm.mockprojecthrm.service;

import com.hrm.mockprojecthrm.dto.CandidateDTO;
import com.hrm.mockprojecthrm.entity.Candidate;
import com.hrm.mockprojecthrm.mapper.CandidateMapper;
import com.hrm.mockprojecthrm.repository.CandidateRepository;
import com.hrm.mockprojecthrm.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    public Candidate createCandidate(CandidateDTO candidateDTO) {
        return candidateRepository.save(candidateMapper.candidateDtoToCandidate(candidateDTO));
    }
   public List<CandidateDTO> getAllCandidates(List<Candidate> candidates) {
        return candidates.stream().map(candidate -> {
            CandidateDTO candidateDTO = new CandidateDTO();
            candidateDTO.setId(candidate.getId());
            return candidateDTO;
        }).collect(Collectors.toList());
    }


}
