package didatticaonline;

import java.util.ArrayList;
import java.util.List;

public class VC{
	
	private Corso corso;
	private Docente docente;
	private String data;
	private String oraInizio;
	private String oraFine;

	private List<Connessione> connessioni;
	
	public VC(Corso corso, Docente docente, String data, String oraInizio) {
		super();
		this.corso = corso;
		this.docente = docente;
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = "";
		this.connessioni = new ArrayList<>();
	}

	public Corso getCorso() {
		return this.corso;
	}

	public Docente getDocente() {
		return this.docente;
	}

	public String getData() {
		return this.data;
	}

	public String getOraInizio() {
		return this.oraInizio;
	}

	public String getOraFine() {
		return this.oraFine;
	}

	public void chiudiVC(String oraFine2) {
		this.oraFine += oraFine2;
	}
	
	public void aggiungiConnessione(Connessione c){
		this.connessioni.add(c);
	}
	
	public List<Connessione> getConnssioni(){
		return this.connessioni;
	}
}
