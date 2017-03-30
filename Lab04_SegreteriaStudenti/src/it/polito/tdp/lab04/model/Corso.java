package it.polito.tdp.lab04.model;

import java.util.LinkedList;

public class Corso {

	private String codice;
	private int cfu;
	private String nome;
	private int pd;
	private LinkedList<Studente> studenti;


	public Corso(String codice, int cfu, String nome, int pd) {
		super();
		this.codice = codice;
		this.cfu = cfu;
		this.nome = nome;
		this.pd=pd;
		studenti= new LinkedList<Studente>();
	}


	public String getNome() {
		return nome;
	}


	@Override
	public String toString() {
		return "Corso: " +codice+" "+cfu+" "+ nome + "\n";
	}
	
	public LinkedList<Studente> getStudenti() {
		return studenti;
	}


	public void setStudenti(LinkedList<Studente> studenti) {
		this.studenti = studenti;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		Corso other = (Corso) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}


	public String getCodice() {
		return codice;
	}


	public int getCfu() {
		return cfu;
	}


	public void setCfu(int cfu) {
		this.cfu = cfu;
	}


	public int getPd() {
		return pd;
	}


	public void setPd(int pd) {
		this.pd = pd;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void aggiungiStudente(Studente s) {
		studenti.add(s);
		
	}
	
}
