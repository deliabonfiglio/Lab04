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

	public List<Corso> getCorsiDelloStudente(Studente s) {
		int mat = s.getMatricola();
		
		if(this.getStudenti(mat)!=null){
			StudenteDAO dao = new StudenteDAO();
			return	dao.elencoCorsi( mat);
		}
				
		return null;
	}

	public String getStudenteIscrittoAlCorso(int mat, Corso c) {
		if(this.getStudenti(mat)!=null){
			StudenteDAO dao = new StudenteDAO();
			String res =dao.iscrittoAlCorso(mat, c);
			
				if( res!=null )
					return res;
				else
					return null;
		}
				
		return null;
	}

	public boolean iscriviStudenteAlCorso(Studente s, Corso c) {
		if(this.getStudenti(s.getMatricola())!=null){
			StudenteDAO dao = new StudenteDAO();
			boolean res =dao.iscriviAlCorso(s, c);
			
			return res;
		}
				
		return false;
	}
	
	
}
