package com.gestion.metier;


import java.util.List;

import com.gestion.entities.Etudiant;
import com.gestion.entities.Filiere;


public interface IMetier {
	
	public Etudiant getEtudiant(int code);
	public List<Etudiant> getAllEtudiants();
	public void addEtudiant(Etudiant etudiant);
	public void updateEtudiant(Etudiant etudiant);
	public void deleteEtudiant(int code);
	

	public Filiere getFiliere(int code);
	public List<Filiere> getAllFilieres();
	public void addFiliere(Filiere filiere);
	public void updateFiliere(Filiere  Filiere);
	public void deleteFiliere(int code);
}
