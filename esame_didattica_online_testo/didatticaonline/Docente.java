package didatticaonline;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class Docente {
	
	private int id;
	private String nome;
	private String cognome;
	private String dipartimento;
	
	private Map<Corso, List<Lezioni>> lezioniDocente;
	

	public Docente(int id, String nome, String cognome, String dipartimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dipartimento = dipartimento;
		this.lezioniDocente = new HashMap<>();
		
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public String getDipartimento() {
		return this.dipartimento;
	}

	public void aggiungiLezioneDocente(Lezioni l) {
		if(!this.lezioniDocente.containsKey(l.getC())){
			this.lezioniDocente.put(l.getC(), new ArrayList<>());
			if(l.getC().possoAggiungereLezione(l)){
				this.lezioniDocente.get(l.getC()).add(l);
			}
		}
		else{
			//Se già insegna in quel corso
			//Verifica che le ore siano sufficienti per aggiungerne altre
			if(l.getC().possoAggiungereLezione(l)){
				for(Lezioni lezione : this.lezioniDocente.get(l.getC())){
					if(lezione.getTipo().compareTo(l.getTipo())==0){
						//Sommo le ore a quel tipo di lezione
						lezione.aggiungiOreLezione(l);
					}
				}
			}
		}
	}

	public Map<Corso, List<Lezioni>> getLezioniDocente() {
		return lezioniDocente;
	}

	public void setLezioniDocente(Map<Corso, List<Lezioni>> lezioniDocente) {
		this.lezioniDocente = lezioniDocente;
	}
	


}
