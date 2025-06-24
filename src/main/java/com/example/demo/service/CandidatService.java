package com.example.demo.service;

import com.example.demo.model.Candidat;
import java.util.List;

public interface CandidatService {
    List<Candidat> getAllCandidats();
    Candidat getCandidatById(Long id);
    Candidat saveCandidat(Candidat candidat);
    void deleteCandidat(Long id);
}