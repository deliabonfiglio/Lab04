package it.polito.tdp.lab04.model;

import java.util.LinkedList;

public class Studente {
	
	private int matricola;
	private String nome;
	private String cognome;
	private String cds;
	private LinkedList<Corso> corsi;
	

	public Studente(int matricola, String nome, String cognome, String cds) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
		corsi = new LinkedList<Corso >();
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCds() {
		return cds;
	}


	public void setCds(String cds) {
		this.cds = cds;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}


	public int getMatricola() {
		return matricola;
	}


	@Override
	public String toString() {
		return "Studente: " +matricola+" "+nome+" "+cognome+" "+cds+"\n";
	}
	
	public void aggiungiCorsoAlloStudente(Corso c){
		if(!corsi.contains(c))
		corsi.add(c);
	}


	public LinkedList<Corso> getCorsi() {
		return corsi;
	}
	
	
}
