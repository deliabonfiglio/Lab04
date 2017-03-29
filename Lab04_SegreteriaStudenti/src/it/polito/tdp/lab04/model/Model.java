package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	public Model(){
		
	}
	
	public List<Corso> getCorsi(){
		CorsoDAO dao = new CorsoDAO();
		
		return dao.getTuttiICorsi();
		
	}
	
	public Studente getStudenti(int mat){
		StudenteDAO dao = new StudenteDAO();
		
		return dao.getStudente(mat);
		
	}
	
	
	public List<Studente> getStudentiDelCorso(Corso corso){
		return corso.getStudenti();
			
	}
	
	
}
