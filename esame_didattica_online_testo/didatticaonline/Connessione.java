package didatticaonline;

public class Connessione {
	
	private Corso c;
	private VC vc;
	private int studente;
	private String inizioColl;
	private int durata;
	public Connessione(Corso c, VC vc, int studente, String inizioColl,
			int durata) {
		super();
		this.c = c;
		this.vc = vc;
		this.studente = studente;
		this.inizioColl = inizioColl;
		this.durata = durata;
	}
	public Corso getC() {
		return c;
	}
	public void setC(Corso c) {
		this.c = c;
	}
	public VC getVc() {
		return vc;
	}
	public void setVc(VC vc) {
		this.vc = vc;
	}
	public int getStudente() {
		return studente;
	}
	public void setStudente(int studente) {
		this.studente = studente;
	}
	public String getInizioColl() {
		return inizioColl;
	}
	public void setInizioColl(String inizioColl) {
		this.inizioColl = inizioColl;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}

}
