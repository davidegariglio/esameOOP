package didatticaonline;

import java.util.*;

public class Piattaforma {
	
	private Map<String, Corso> corsi = new HashMap<>();
	private Map<Integer, Docente> docenti = new HashMap<>();
	private int nDoc = 0;
	
	private Map<Corso, VC> vc = new HashMap<Corso, VC>();
	
	public String generaCodCorso(String titolo){
		String result = "";
		if(titolo.length()<=10){
			return titolo.toUpperCase();
		}
		else{	//Ho più di 10 lettere
			result += titolo.substring(0, 5).toUpperCase();
			result += titolo.substring(titolo.length()-5, titolo.length()).toUpperCase();
			return result;
		}
	}
	public Corso definisciCorso(String titolo, int anno, int periodoDidattico, int crediti) {
		if(this.corsi.containsKey(this.generaCodCorso(titolo))){
			return this.corsi.get(this.generaCodCorso(titolo));
		}
		else{ //corso da creare
			String cod = this.generaCodCorso(titolo);
			Corso c = new Corso(cod, titolo, anno, periodoDidattico, crediti);
			this.corsi.put(c.getCodice(), c);
			return c; 
		}
	}

	public Corso cercaCorso(String codice) {
		if(this.corsi.containsKey(codice)){
			return this.corsi.get(codice);
		}
		return null;
	}
	
	public Docente definisciDocente(String nome, String cognome, String dipartimento) {
		for(Docente d : this.docenti.values()){
			if(d.getNome().compareTo(nome) == 0
					&& d.getCognome().compareTo(cognome) == 0
					&& d.getDipartimento().compareTo(dipartimento) == 0){
				return d;
			}
		}
		//Docente non ancora presente
		Docente d = new Docente(1000+this.nDoc, nome, cognome, dipartimento);
		this.nDoc++;
		this.docenti.put(d.getId(), d);
		return d;
	}
	
	public Docente cercaDocente(int id) {
		if(this.docenti.containsKey(id)){
			return this.docenti.get(id);
		}
		return null;
	}

	public Collection<Corso> elencoCorsi(){
		List<Corso> result = new ArrayList<Corso>(this.corsi.values());
		Collections.sort(result, new ComparatoreCorsiCodiceCorso());
		return result;
	}

	public Collection<Docente> elencoDocenti(){
		List<Docente> result = new ArrayList<Docente>(this.docenti.values());
		Collections.sort(result, new ComparatoreDocentiCognomeNome());
		return result;
	}
	
	public void assegnaDocenteCorso(String codice, int id, int numeroOre, String tipoOre) {
		Corso c = this.corsi.get(codice);
		Docente d = this.cercaDocente(id);
		if(c != null && d != null){
			Lezioni l = new Lezioni(d, c, numeroOre, tipoOre);
			if(c.possoAggiungereLezione(l)){
				c.aggiungiLezione(l);
				d.aggiungiLezioneDocente(l);;
			}
		}
		
	}
	
	public String stampaDocentiCorso(String codice) {
		Corso c = this.cercaCorso(codice);
		List<Lezioni> result = new ArrayList<>();
		for(Lezioni l : c.getLezioni()){
			result.add(l);
		}
		
		Collections.sort(result, new ComparatoreLezioneOre());
		String stringa = "";
		
		for(int i = 0; i < result.size(); i++){
			if(i == result.size()-1){
				//Ultimo elemento senza \n
				stringa += result.get(i).getD().getId()+" "+result.get(i).getOre()+" "+result.get(i).getTipo();

			}
			else{
				stringa += result.get(i).getD().getId()+" "+result.get(i).getOre()+" "+result.get(i).getTipo()+"\n";
			}
		}
		return stringa;
	}
	/*
	public String stampaCorsiDocente(int id) {
		
		return null;
	}
	*/
	public VC creaVirtualClassroom(String codice, int id, String data, String oraInizio) throws EccezioneTrovataVCAperta {
		if(this.cercaCorso(codice) != null && this.cercaDocente(id) != null){
			Corso c = this.cercaCorso(codice);
			Docente d = this.cercaDocente(id);
			for(VC vc : this.vc.values()){
				if(vc.getCorso().equals(c) && vc.getOraFine()==""){
					throw new EccezioneTrovataVCAperta();
				}
			}
			VC nuova = new VCEsame(c, d, data, oraInizio);
			this.vc.put(c, nuova);
			return nuova;
		}
		return null;
	}

	public VC creaVirtualClassroom(String codice, int id, String data, String oraInizio, boolean abRegistrazione, boolean abMicWebcam) throws EccezioneTrovataVCAperta {
		if(this.cercaCorso(codice) != null && this.cercaDocente(id) !=null){
			Corso c = this.cercaCorso(codice);
			Docente d = this.cercaDocente(id);
			for(VC vc : this.vc.values()){
				if(vc.getCorso().equals(c) && vc.getOraFine()==""){
					throw new EccezioneTrovataVCAperta();
				}
			}
			VC nuova = new VCLezioneEsercitazione(c, d, data, oraInizio, abRegistrazione, abMicWebcam);
			this.vc.put(c, nuova);
			return nuova;
		}
		return null;
	}

	public void chiudiVirtualClassroom(String codice, String oraFine) throws EccezioneNessunaVCDaChiudere {
		VC trovata = this.vc.get(this.cercaCorso(codice));
		if(trovata == null || trovata.getOraFine().compareTo("")!=0){
			throw new EccezioneNessunaVCDaChiudere();
		}
		else{
			trovata.chiudiVC(oraFine);
		}
		
	}
	
	public Collection<VC> elencoVirtualClassroom() {
		List<VC> result = new ArrayList<VC>(this.vc.values())	;
		Collections.sort(result, new ComparatoreVCDataOraCreascenti());
		return result;
	}
	
	public Collection<VC> elencoVirtualClassroomChiuse() {
		List<VC> result = new ArrayList<VC>();
		for(VC v : this.vc.values()){
			if(v.getOraFine().compareTo("") != 0){
				result.add(v);
			}
		}
		Collections.sort(result, new ComparatoreVCDataOraCreascenti());

		return result;
	}
	
	public void collegamento(String codice, String matricola, String oraCollegamento, int minuti) {
		Corso c = this.cercaCorso(codice);
		if(c!= null){
			//for(VC v : this.vc.get(c)){
				
			//}

		}
	}

	public String stampaCollegamentiCorso(String codice) {
		return null;
	}
}
