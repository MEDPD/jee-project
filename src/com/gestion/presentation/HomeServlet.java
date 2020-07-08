package com.gestion.presentation;

import java.io.IOException;
import java.util.List;

import com.gestion.dao.Dao;
import com.gestion.entities.Etudiant;
import com.gestion.entities.Filiere;
import com.gestion.metier.IMetier;
import com.google.gson.Gson;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns = {"/filieres", "/inscription", "/etudiants", "/updateEtudiant" , "/deleteEtudiant", "/deleteFiliere", "/updateFiliere", "/addFiliere"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IMetier metier = new Dao();
    
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("home serv");
		if(req.getRequestURI().equals(req.getContextPath() + "/filieres"))
			this.getAllFilieres(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/etudiants"))
			this.getEtudiants(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/inscription"))
			this.postInscription(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/deleteEtudiant"))
			this.deleteEtudiant(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/updateEtudiant"))
			this.updateEtudiant(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/updateFiliere"))
			this.updateFiliere(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/deleteFiliere"))
			this.deleteFiliere(req, resp);
		else if(req.getRequestURI().equals(req.getContextPath() + "/addFiliere"))
			this.addFiliere(req, resp);
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req,resp);
		
	}
	
	
	// Controllers
	
	public void getAllFilieres( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
		
		List<Filiere> filieres = metier.getAllFilieres();
		
		System.out.println("************get ALL FILIERES");
	    String json = new Gson().toJson(filieres);
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);

//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//		rd.forward(req, resp);
		
		
		
	}
	public void getEtudiants( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
		List<Etudiant> etudiants = metier.getAllEtudiants();
		
		System.out.println("************get ALL FILIERES");
	    String json = new Gson().toJson(etudiants);
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);

		
		
//		req.setAttribute("path", "Admin assat");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//		rd.forward(req, resp);
	}
	
	public void postInscription( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
	
		
	Etudiant etudiant = new Etudiant(req.getParameter("prenom"), 
			req.getParameter("nom"), req.getParameter("dateNaissance"),
			Integer.parseInt(req.getParameter("code_filiere")));
	
	System.out.println("etud -> " + etudiant.getBirthday() + " " + etudiant.getNom() + " " + etudiant.getPrenom());
	metier.addEtudiant(etudiant);
		
		
		
		String json = new Gson().toJson("Done");
		resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//		
//		rd.forward(req, resp);
	}
	public void updateEtudiant( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
		
		
		Etudiant etudiant = new Etudiant(Integer.parseInt(req.getParameter("code")),req.getParameter("prenom"), 
				req.getParameter("nom"), req.getParameter("dateNaissance"),
				Integer.parseInt(req.getParameter("code_filiere")));
		
		System.out.println("etud -> " + etudiant.getBirthday() + " " + etudiant.getNom() + " " + etudiant.getPrenom());
		metier.updateEtudiant(etudiant);
			
			
			
			String json = new Gson().toJson("Done");
			resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//			
//			rd.forward(req, resp);
		}
	
	public void deleteEtudiant( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
		
		metier.deleteEtudiant(Integer.parseInt(req.getParameter("etudiant_code")));
			
			
			String json = new Gson().toJson("Done");
			resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//			
//			rd.forward(req, resp);
		}
	
public void updateFiliere( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
		
		
		Filiere filiere = new Filiere(Integer.parseInt(req.getParameter("code")),req.getParameter("nom_filiere"), 
				Integer.parseInt(req.getParameter("nombre_etudiant")), Integer.parseInt(req.getParameter("nombre_etudiant_max")));
		
		System.out.println("etud -> " + filiere.getCode() + " " + filiere.getNom_filiere() + " " + filiere.getNombre_etudiant() + " " + filiere.getNombre_etudiant_max());
		metier.updateFiliere(filiere);
			
			
			
			String json = new Gson().toJson("Done");
			resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//			
//			rd.forward(req, resp);
		}
public void deleteFiliere( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
	
	metier.deleteFiliere(Integer.parseInt(req.getParameter("code_filiere")));
		
		
		String json = new Gson().toJson("Done");
		resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//		
//		rd.forward(req, resp);
	}
public void addFiliere( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException  {
	int nbr_etudiant_max;
	if(req.getParameter("nombre_etudiant_max").equals("")) nbr_etudiant_max = 0;
	else nbr_etudiant_max = Integer.parseInt(req.getParameter("nombre_etudiant_max"));
	
 	Filiere filiere = new Filiere(req.getParameter("nom_filiere"), 
			0, nbr_etudiant_max);
	
	System.out.println("add filiere -> " + filiere.getNom_filiere() + " " + filiere.getNombre_etudiant() + " " + filiere.getNombre_etudiant_max());
	metier.addFiliere(filiere);
		
		
		
		String json = new Gson().toJson("Done");
		resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(json);
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/index.jsp");
//		
//		rd.forward(req, resp);
	}
}
