package com.gestion.entities;

public class Filiere {
	
	int code;
	private String nom_filiere;	
	private int nombre_etudiant;
	private int nombre_etudiant_max;
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Filiere(String nom_filiere, int nombre_etudiant, int nombre_etudiant_max) {
		super();
		this.nom_filiere = nom_filiere;
		this.nombre_etudiant = nombre_etudiant;
		this.nombre_etudiant_max = nombre_etudiant_max;
	}

	public Filiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Filiere(int code, String nom_filiere, int nombre_etudiant, int nombre_etudiant_max) {
		super();
		this.code = code;
		this.nom_filiere = nom_filiere;
		this.nombre_etudiant = nombre_etudiant;
		this.nombre_etudiant_max = nombre_etudiant_max;
	}

	public String getNom_filiere() {
		return nom_filiere;
	}
	
	public void setNom_filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}
	public int getNombre_etudiant() {
		return nombre_etudiant;
	}
	public void setNombre_etudiant(int nombre_etudiant) {
		this.nombre_etudiant = nombre_etudiant;
	}
	public int getNombre_etudiant_max() {
		return nombre_etudiant_max;
	}
	public void setNombre_etudiant_max(int nombre_etudiant_max) {
		this.nombre_etudiant_max = nombre_etudiant_max;
	}
	
}
