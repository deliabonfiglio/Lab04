package it.polito.tdp.lab04.controller;

import java.awt.event.InputMethodEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

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
	
    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

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
    	Corso c = comboCorso.getValue();
    	
			try{
				String matricola= txtMatricola.getText();
			//	if(matricola.matches("[a-zA-Z]+")){
					int mat = Integer.parseInt(matricola);
					Studente s = model.getStudenti(mat);
					
					if( s!= null){
						
						if((c!=null) ){
							
							if(model.getStudenteIscrittoAlCorso(mat, c) !=null){
								txtNome.setText(s.getNome());
								txtCognome.setText(s.getCognome());
								txtResult.appendText("Studente presente nel DB e già iscritto al corso selezionato.\n");
							
							} else{					
								txtNome.setText(s.getNome());
								txtCognome.setText(s.getCognome());
								txtResult.appendText("Studente presente nel DB ma NON iscritto al corso selezionato. "
										+ "Se si desidera iscrivere cliccare sul BOTTONE ISCRIVI\n");
							}
							
						} else{
							txtNome.setText(s.getNome());
							txtCognome.setText(s.getCognome());					
						}
					} else {
						txtResult.appendText("ATTENZIONE: Studente NON presente nel DB!\n");
						}
			/*	}else{
					txtResult.appendText("Inserire solo numeri nel campo della matricola!\n");
				}*/

				} catch (NumberFormatException e){
					txtResult.appendText("ATTENZIONE: Inserire la matricola con soli numeri!");
					} 
		} 

	/*
	 * Implementare la ricerca degli studenti iscritti ad un corso: selezionato un corso dal menù a tendina, 
	 * facendo click sul pulsante Cerca iscritti corso, vengono visualizzati tutti gli studenti iscritti a quel corso.
	 *  Se nessun corso è selezionato, avvisare l’utente con un messaggio di errore.
	 */
	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
	     Corso c = comboCorso.getValue();
	    
	     if(c!=null){
	    	 
		     String result=model.getStudentiDelCorso(c).toString();
		     txtResult.setText( result);
		     
	     } else {
	    	 txtResult.appendText("Attenzione! Selezionare un corso!\n");
	     }	     
	}

		
	/*
	 * Controllare se lo studente è presente nel database, ed in caso visualizzare tutti i corsi ai quali è iscritto. 
	 * Se la matricola non è presente, visualizzare un messaggio di errore.
	 */
	@FXML
	void doCercaCorsi(ActionEvent event) {
		try{
			String matricola= txtMatricola.getText();
	//if(matricola.matches("[a-zA-Z]*")){
			int mat = Integer.parseInt(matricola);
			Studente s = model.getStudenti(mat);
			
				if(s!=null){
					String res = model.getCorsiDelloStudente(s).toString();
					txtResult.appendText(res);				
				}else{
					txtResult.appendText("Studente con questa matricola non presente nel DB!\n");
				}
			/*} else {
				txtResult.appendText("Inserire solo numeri nel campo matricola!\n");
			}*/
				
	} catch (NumberFormatException e){
		txtResult.appendText("ATTENZIONE: Inserire la matricola con soli numeri!");
		}
	}

    @FXML
    void doIscrivi(ActionEvent event) {
    	Corso c = comboCorso.getValue();
    	
		try{
			String matricola= txtMatricola.getText();
		//	if(matricola.matches("[a-zA-Z]+")){
				int mat = Integer.parseInt(matricola);
				Studente s = model.getStudenti(mat);
				
				if( s!= null){
					
					if((c!=null) ){
						
						if(model.getStudenteIscrittoAlCorso(mat, c) !=null){
							txtNome.setText(s.getNome());
							txtCognome.setText(s.getCognome());
							
							txtResult.appendText("Studente presente nel DB e già iscritto al corso selezionato.\n");
						
						} else{					
							txtNome.setText(s.getNome());
							txtCognome.setText(s.getCognome());
							
							if(model.iscriviStudenteAlCorso(s, c)==true)
								txtResult.appendText("Lo studente e' stato iscritto con successo al corso selezionato.\n");
							else 
								txtResult.appendText("Errore");
						}
						
					} else{
						txtNome.setText(s.getNome());
						txtCognome.setText(s.getCognome());					
					}
				} else {
					txtResult.appendText("ATTENZIONE: Studente NON presente nel DB!\n");
					}
		/*	}else{
				txtResult.appendText("Inserire solo numeri nel campo della matricola!\n");
			}*/

			} catch (NumberFormatException e){
				txtResult.appendText("ATTENZIONE: Inserire la matricola in soli numeri!\n");
				} 
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
		assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

	public List<Corso> getCorsi(){
		
		corsi = new LinkedList<Corso>(model.getCorsi());		
		return corsi;
	}
	
	

}
