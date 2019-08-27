package com.hrm.mockprojecthrm.mapper;

import com.hrm.mockprojecthrm.dto.CandidateDTO;
import com.hrm.mockprojecthrm.entity.Candidate;
import com.hrm.mockprojecthrm.entity.Position;
import com.hrm.mockprojecthrm.entity.User;
import com.hrm.mockprojecthrm.repository.CandidateRepository;
import com.hrm.mockprojecthrm.repository.PositionRepository;
import com.hrm.mockprojecthrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateMapper {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public List<CandidateDTO> candidateToCandidateDTO(List<Candidate> candidate){
        return candidate.stream().map(candidates -> {
            CandidateDTO candidateDTO = new CandidateDTO();
            candidateDTO.setId(candidates.getId());
            candidateDTO.setName(candidates.getName());
            candidateDTO.setFileCv(candidates.getFileCv());
            candidateDTO.setComment(candidates.getComment());
            candidateDTO.setInterviewDate(candidates.getInterviewDate());
            candidateDTO.setStatus(candidates.getStatus());
            candidateDTO.setUserId(candidates.getUser().getId());
            candidateDTO.setPositionId(candidates.getPosition().getId());
            return candidateDTO;
        }).collect(Collectors.toList());
    }

    public Candidate candidateDtoToCandidate(CandidateDTO candidateDTO){
        Optional<Position> opPosition = positionRepository.findById(candidateDTO.getPositionId());
        Optional<User> opUser = userRepository.findById(candidateDTO.getUserId());
        Candidate candidate = new Candidate();
        candidateDTO.setId(candidateDTO.getId());
        candidateDTO.setName(candidateDTO.getName());
        candidate.setFileCv(candidateDTO.getFileCv());
        candidate.setInterviewDate(candidateDTO.getInterviewDate());
        candidate.setComment(candidateDTO.getComment());
        candidate.setStatus(candidateDTO.getStatus());
        candidate.setPosition(opPosition.get());
        candidate.setUser(opUser.get());
        return candidate;
    }

    public Candidate candidateDTOById(CandidateDTO candidateDTO){
        Optional<Position> opPosition = positionRepository.findById(candidateDTO.getPositionId());
        Optional<User> opUser = userRepository.findById(candidateDTO.getUserId());
        Optional<Candidate> opCandidate = candidateRepository.findById(candidateDTO.getId());
        opCandidate.ifPresent(candidate -> {
            candidate.setFileCv(candidateDTO.getFileCv());
            candidate.setInterviewDate(candidateDTO.getInterviewDate());
            candidate.setComment(candidateDTO.getComment());
            candidate.setStatus(candidateDTO.getStatus());
            if(opPosition.isPresent()){
                candidate.setPosition(opPosition.get());
            }else{
                candidate.setPosition(null);
            }
            if(opUser.isPresent()){
                candidate.setUser(opUser.get());
            }else{
                candidate.setUser(null);
            }
        });
        return opCandidate.get();
    }
}
