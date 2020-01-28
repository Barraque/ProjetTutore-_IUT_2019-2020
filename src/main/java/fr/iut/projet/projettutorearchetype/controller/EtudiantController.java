package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.models.Etudiant;
import fr.iut.projet.projettutorearchetype.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EtudiantController {


    @Autowired
    EtudiantService etudiantService;

    @PostMapping("etudiant")
    public Etudiant addEtudiant(
            @RequestBody Etudiant etudiant
    )
    {
        if(etudiant != null){}
            //System.out.println("nom de l'etudiant : " + etudiant.getInteger());
        return etudiantService.addEtudiant(etudiant);
    }

    @GetMapping("etudiant")
    public Etudiant getEtudiant(
            @RequestParam(name = "numero_etudiant") int numero_et
    )
    {
        return etudiantService.getEtudiant(numero_et);
    }
}
