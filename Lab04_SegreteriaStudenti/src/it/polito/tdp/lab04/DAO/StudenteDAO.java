package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int mat) {

		final String sql = "SELECT * FROM studente "+ "WHERE matricola=? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, mat);
			
			ResultSet rs = st.executeQuery();
			
			Studente stemp = null;
			
			while (rs.next()) {				
				stemp= new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
			}

			return stemp;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Corso> elencoCorsi(Integer mat) {
		//final String sql = "SELECT c.codins FROM corso AS c AND iscrizione AS i "+ "WHERE c.codins=i.codins AND matricola=? ";
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "+" FROM iscrizione as i, corso as c "+ "WHERE i.codins=c.codins && i.matricola= ? ";
		
		LinkedList<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, mat);
			
			ResultSet rs = st.executeQuery();
			
			Corso c = null;
			
			while (rs.next()) {				
				c= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public String iscrittoAlCorso(int m, Corso c) {
		final String sql = "SELECT DISTINCT c.codins, c.crediti, c.nome, c.pd "+
							"FROM iscrizione as i, corso as c "+
							"WHERE i.codins=c.codins AND i.matricola= ? AND i.codins= ? ";
				try {
					Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);

					st.setString(2, c.getCodice());
					st.setInt(1, m);
					
					ResultSet rs = st.executeQuery();
					
					Corso ctemp = null;
					
					while (rs.next()) {				
						ctemp= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
					}
					if(ctemp!=null)
						return ctemp.toString();
					
					return null;

				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("Errore Db");
				}
			}

	public boolean iscriviAlCorso(Studente s, Corso c) {
		
		final String sql = "INSERT INTO iscrizione "+
							"VALUES (?,'?') ";
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);

	   int ris=  st.executeUpdate(sql);
		
		st.setInt(1, s.getMatricola());
		st.setString(2, c.getCodice());
		
		
			c.aggiungiStudente(s);
			s.aggiungiCorsoAlloStudente(c);
		
			if(ris ==1)
				return true;
			return false;
			

	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
}

}
