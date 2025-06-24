package com.example.demo.controller;

import com.example.demo.model.Candidat;
import com.example.demo.service.CandidatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidats")
public class CandidatController {

    private final CandidatService candidatService;

    public CandidatController(CandidatService candidatService){
        this.candidatService = candidatService;
    }

    @GetMapping
    public String listCandidats(Model model){
        model.addAttribute("candidats", candidatService.getAllCandidats());
        return "candidats/list";
    }

    @GetMapping("/view/{id}")
    public String viewCandidat(@PathVariable Long id, Model model){
        Candidat candidat = candidatService.getCandidatById(id);
        if(candidat == null) return "redirect:/candidats";
        model.addAttribute("candidat", candidat);
        return "candidats/view";
    }

    @GetMapping("/add")
    public String addCandidatForm(Model model){
        model.addAttribute("candidat", new Candidat());
        return "candidats/add";
    }

    @PostMapping("/add")
    public String addCandidat(@ModelAttribute Candidat candidat){
        candidatService.saveCandidat(candidat);
        return "redirect:/candidats";
    }

    @GetMapping("/edit/{id}")
    public String editCandidatForm(@PathVariable Long id, Model model){
        Candidat candidat = candidatService.getCandidatById(id);
        if(candidat == null) return "redirect:/candidats";
        model.addAttribute("candidat", candidat);
        return "candidats/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCandidat(@PathVariable Long id, @ModelAttribute Candidat candidat){
        candidat.setId(id);
        candidatService.saveCandidat(candidat);
        return "redirect:/candidats/view/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteCandidat(@PathVariable Long id){
        candidatService.deleteCandidat(id);
        return "redirect:/candidats";
    }
}