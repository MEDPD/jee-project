package com.gestion.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gestion.entities.Etudiant;
import com.gestion.entities.Filiere;
import com.gestion.metier.IMetier;

public class Dao implements IMetier{

	@Override
	public Etudiant getEtudiant(int code) {
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM etudiant WHERE code = ?");
			stmt.setInt(1, code);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				String prenom = result.getString("prenom");
				String nom = result.getString("nom");
				String birthday = result.getString("birthday");
				int code_filiere = result.getInt("code_filiere");
				Etudiant etudiant = new Etudiant(code, prenom, nom, birthday, code_filiere);
				return etudiant;
			}
			else {
				return null;
			}
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Etudiant> getAllEtudiants() {
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM etudiant");			
			List<Etudiant> listEtudiant = new ArrayList<Etudiant>();
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				int code = result.getInt("code");
				String prenom = result.getString("prenom");
				String nom = result.getString("nom");
				String birthday = result.getString("birthday");
				int code_filiere = result.getInt("code_filiere");
				Etudiant etudiant = new Etudiant(code, prenom, nom, birthday, code_filiere);
				listEtudiant.add(etudiant);	
			}
			return listEtudiant;
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
		return null;
	}

	@Override
	public void addEtudiant(Etudiant etudiant) {
		Filiere fil = getFiliere(etudiant.getCode_filiere());
		fil.setNombre_etudiant(fil.getNombre_etudiant() + 1);
		
		try {
			
			System.out.println("******* " + etudiant.getPrenom() + " " + etudiant.getBirthday() + " " + etudiant.getPrenom() + " " + etudiant.getCode_filiere());
			Connection conn = ConfDB.getUnique().getConnectin();
			System.out.println("******* " + etudiant.getPrenom() + " " + etudiant.getBirthday());
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO etudiant VALUES(NULL, ?, ?, ?, ?)");
			stmt.setString(1, etudiant.getPrenom());
			stmt.setString(2, etudiant.getNom());
			stmt.setString(3, etudiant.getBirthday());
			stmt.setInt(4, etudiant.getCode_filiere());
			stmt.execute();
			
			
			
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
		
		updateFiliere(fil);
		
	}

	@Override
	public void updateEtudiant(Etudiant etudiant) {
		
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("UPDATE etudiant SET prenom=?, nom=?, birthday=? WHERE code=?");
			stmt.setString(1, etudiant.getPrenom());
			stmt.setString(2, etudiant.getNom());
			stmt.setString(3, etudiant.getBirthday());
			stmt.setInt(4, etudiant.getCode());
			stmt.execute();
			
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
 		}
		
	}

	@Override
	public void deleteEtudiant(int code) {
		Etudiant etudiant = getEtudiant(code);
		Filiere fil = getFiliere(etudiant.getCode_filiere());
		fil.setNombre_etudiant(fil.getNombre_etudiant() - 1);
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM etudiant WHERE code=?");
			stmt.setInt(1, code);
			stmt.execute();
			System.out.println("delete one ->" + code);
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
		updateFiliere(fil);
	}

	@Override
	public Filiere getFiliere(int code) {
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM filiere WHERE code = ?");
			stmt.setInt(1, code);
			
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				
				Filiere filiere = new Filiere(code, result.getString("nom_filiere"), result.getInt("nombre_etudiant"),
						result.getInt("nombre_etudiant_max"));
				return filiere;
			}
			else {
				return null;
			}
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Filiere> getAllFilieres() {
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM filiere");			
			List<Filiere> listFilieres = new ArrayList<Filiere>();
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				int code = result.getInt("code");
				String nom_filiere = result.getString("nom_filiere");
				int nombre_etudiant = result.getInt("nombre_etudiant");
				int nombre_etudiant_max = result.getInt("nombre_etudiant_max");
				Filiere filiere = new Filiere(code, nom_filiere, nombre_etudiant, nombre_etudiant_max);
				listFilieres.add(filiere);	
			}
			return listFilieres;
		} catch (SQLException ex) {
			System.err.println("Erreur the Sql: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public void addFiliere(Filiere filiere) {
try {
			
			Connection conn = ConfDB.getUnique().getConnectin();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO filiere VALUES(NULL, ?, ?, ?)");
			stmt.setString(1, filiere.getNom_filiere());
			stmt.setInt(2, filiere.getNombre_etudiant());
			stmt.setInt(3, filiere.getNombre_etudiant_max());
			stmt.execute();
			
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
	}

	@Override
	public void updateFiliere(Filiere filiere) {
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("UPDATE filiere SET nom_filiere=?, nombre_etudiant=?, nombre_etudiant_max=? WHERE code=?");
			stmt.setString(1, filiere.getNom_filiere());
			stmt.setInt(2, filiere.getNombre_etudiant());
			stmt.setInt(3, filiere.getNombre_etudiant_max());
			stmt.setInt(4, filiere.getCode());
			stmt.execute();
			
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
 		}
	}

	@Override
	public void deleteFiliere(int code) {
		
		List<Etudiant> etudiants = getAllEtudiants();
		for(Etudiant etudiant : etudiants)
			if(etudiant.getCode_filiere() == code)
				deleteEtudiant(etudiant.getCode());
		
		
		try {
			Connection conn = ConfDB.getUnique().getConnectin();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM filiere WHERE code=?");
			stmt.setInt(1, code);
			stmt.execute();
			System.out.println("delete filiere ->" + code);
			
		} catch (SQLException ex) {
			System.err.println("Erreur : " + ex.getMessage());
		}
		
		
	}

}
