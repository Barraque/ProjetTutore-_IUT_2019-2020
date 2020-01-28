package fr.iut.projet.projettutorearchetype.services;


import fr.iut.projet.projettutorearchetype.models.Etudiant;
import fr.iut.projet.projettutorearchetype.models.Tag;
import fr.iut.projet.projettutorearchetype.repositories.IEtudiantRepository;
import fr.iut.projet.projettutorearchetype.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EtudiantService {


    @Autowired
    IEtudiantRepository etudiantRepository;


    public Etudiant addEtudiant(final Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant getEtudiant(final Integer numero_etudiant) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(numero_etudiant);
        return etudiant.get();
    }

    /*
    public Etudiant getEtudiantInter(final Integer numero_etudiant_debut, final Integer numero_etudiant_fin) {
        Optional<Etudiant> etudiant = etudiantRepository.findAll().


        Optional<Etudiant> etudiant = etudiantRepository.findById(numero_etudiant);
        return etudiant.get();
    }

     */



}
