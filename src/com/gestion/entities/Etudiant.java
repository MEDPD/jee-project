package com.gestion.entities;



public class Etudiant {
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Etudiant(int code, String prenom, String nom, String birthday, int code_filiere) {
		super();
		this.code = code;
		this.prenom = prenom;
		this.nom = nom;
		this.birthday = birthday;
		this.code_filiere = code_filiere;
	}
	
	public Etudiant(String prenom, String nom, String birthday, int code_filiere) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.birthday = birthday;
		this.code_filiere = code_filiere;
	}

	private int code;
	private String prenom, nom;
	private String birthday;
	private int code_filiere;
	
	public int getCode_filiere() {
		return code_filiere;
	}

	public void setCode_filiere(int code_filiere) {
		this.code_filiere = code_filiere;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	
	

}
