package com.example.demo.service.impl;

import com.example.demo.model.Candidat;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.service.CandidatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatServiceImpl implements CandidatService {
    private final CandidatRepository candidatRepository;

    public CandidatServiceImpl(CandidatRepository candidatRepository){
        this.candidatRepository = candidatRepository;
    }

    @Override
    public List<Candidat> getAllCandidats(){
        return candidatRepository.findAll();
    }

    @Override
    public Candidat getCandidatById(Long id){
        return candidatRepository.findById(id).orElse(null);
    }

    @Override
    public Candidat saveCandidat(Candidat candidat){
        return candidatRepository.save(candidat);
    }

    @Override
    public void deleteCandidat(Long id){
        candidatRepository.deleteById(id);
    }
}