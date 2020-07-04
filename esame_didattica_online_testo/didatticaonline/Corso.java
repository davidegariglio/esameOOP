package didatticaonline;

import java.util.*;

public class Corso {
	
	private String codice;
	private String titolo;
	private int anno;
	private int periodoDidattico;
	private int crediti;
	
	private List<Lezioni> lezioni;

	public Corso(String codice, String titolo, int anno, int periodoDidattico,
			int crediti) {
		super();
		this.codice = codice;
		this.titolo = titolo;
		this.anno = anno;
		this.periodoDidattico = periodoDidattico;
		this.crediti = crediti;
		this.lezioni = new ArrayList<>();
	}

	public List<Lezioni> getLezioni() {
		return lezioni;
	}

	public boolean possoAggiungereLezione(Lezioni l){
		int result = 0;
		for(Lezioni lezione : this.lezioni){
			result += lezione.getOre();
		}
		if(result + l.getOre() <= this.crediti*10){
			return true;
		}
		return false;
	}
	public void aggiungiLezione(Lezioni l) {
		if(this.possoAggiungereLezione(l)){	
			for(Lezioni lezione : this.lezioni){
				if(lezione.getTipo().compareTo(l.getTipo()) == 0){
					lezione.aggiungiOreLezione(l);
				}
			}
			//Nessuna lezione di questo tipo
			this.lezioni.add(l);
		}
		
	}

	public String getCodice() {
		return this.codice;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public int getAnno() {
		return this.anno;
	}

	public int getPeriodoDidattico() {
		return this.periodoDidattico;
	}

	public int getCrediti() {
		return this.crediti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anno;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + crediti;
		result = prime * result + periodoDidattico;
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
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
		if (anno != other.anno)
			return false;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (crediti != other.crediti)
			return false;
		if (periodoDidattico != other.periodoDidattico)
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}

}
