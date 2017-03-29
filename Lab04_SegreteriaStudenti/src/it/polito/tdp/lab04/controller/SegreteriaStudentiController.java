package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model= model;
		comboCorso.getItems().addAll(getCorsi());

	}

	@FXML
	void doReset(ActionEvent event) {
		txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		txtResult.clear();
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		
		String matricola= txtMatricola.getText();
		int mat = Integer.parseInt(matricola);
		
		Studente s = model.getStudenti(mat);
		
		if(s!=null){
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
		}
		else
			txtResult.appendText("Studente non presente nel database!\n");
	}

	
	/*
	 * Implementare la ricerca degli studenti iscritti ad un corso: selezionato un corso dal menù a tendina, 
	 * facendo click sul pulsante Cerca iscritti corso, vengono visualizzati tutti gli studenti iscritti a quel corso.
	 *  Se nessun corso è selezionato, avvisare l’utente con un messaggio di errore.
	 */
	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		
		Corso corso = comboCorso.getValue();
		
		LinkedList<Studente> st = new LinkedList<Studente>(model.getStudentiDelCorso(corso));
		
		if(st!=null){
			for(Studente s: st)
				txtResult.appendText(s.toString()+"\n");			
		}else{
			txtResult.appendText("Corso senza iscritti! \n");
		}
		
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		
	}

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	
		
		
	}

	public List<Corso> getCorsi(){
		
		corsi = new LinkedList<Corso>(model.getCorsi());
		
		return corsi;
	}
	
	

}
