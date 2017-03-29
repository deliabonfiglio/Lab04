package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	private LinkedList<Corso> corsi;
	private CorsoDAO corsoDAO;
	
	public Model(){
		corsoDAO = new CorsoDAO();
		corsi = corsoDAO.getTuttiICorsi();
	}
	
	public List<Corso> getCorsi(){
		if(corsi==null)
			corsi = corsoDAO.getTuttiICorsi();
		
		return corsi;
		
	}
	
	public Studente getStudenti(int mat){
		StudenteDAO dao = new StudenteDAO();
		
		return dao.getStudente(mat);
		
	}
	
	
	public List<Studente> getStudentiDelCorso(Corso corso){
			if (corsi.contains(corso)) {
				
				corsoDAO.getStudentiIscrittiAlCorso(corso);
				
			}
			return corso.getStudenti();
		}
	
	
}
