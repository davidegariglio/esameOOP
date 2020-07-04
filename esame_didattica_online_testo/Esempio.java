import java.util.*;

import didatticaonline.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneTrovataVCAperta, EccezioneNessunaVCDaChiudere {
			
		Piattaforma p = new Piattaforma();
		
		System.out.println("/***********************************/");
		System.out.println("/**       R1. CORSI E DOCENTI     **/");
		System.out.println("/***********************************/\n");
		
		System.out.println("Definito nuovo corso\n");
		
		Corso c1 = p.definisciCorso("Programmazione ad oggetti", 3, 1, 8);
		
		System.out.println("Informazioni corso:");
		
		System.out.println(" Codice assegnato: "+c1.getCodice());
		System.out.println(" Titolo: "+c1.getTitolo());
		System.out.println(" Anno: "+c1.getAnno());
		System.out.println(" Periodo didattico: "+c1.getPeriodoDidattico());
		System.out.println(" Crediti: "+c1.getCrediti());
		

		System.out.println("Definito nuovo docente\n");
		
		Docente d1 = p.definisciDocente("Fabio", "Garcea", "Dip. di Automatica e Informatica");
		
		System.out.println("Informazioni docente:");
		
		System.out.println(" Id assegnato: "+d1.getId());
		System.out.println(" Nome: "+d1.getNome());
		System.out.println(" Cognome: "+d1.getCognome());
		System.out.println(" Dipartimento: "+d1.getDipartimento());

		System.out.println("Definiti altri corsi\n");
		Corso c2 = p.definisciCorso("Piattaforme mobili e servizi cloud", 5, 2, 8);
		Corso c3 = p.definisciCorso("Informatica grafica", 4, 1, 6);
		
		
		System.out.println("Definiti altri docenti\n");
		Docente d2 = p.definisciDocente("Andrea", "Lamberti", "Dip. di Scienza Applicata e Tecnologia");
		Docente d3 = p.definisciDocente("Fabrizio", "Lamberti", "Dip. di Automatica e Informatica");
		Docente d4 = p.definisciDocente("Valentina", "Gatteschi", "Dip. di Automatica e Informatica");
		Docente d5 = p.definisciDocente("Alberto", "Cannavo'", "Dip. di Automatica e Informatica");
		
		System.out.println("\nElenco corsi (ordinati per codice):");
		LinkedList<Corso> listaCorsi = new LinkedList<Corso>(p.elencoCorsi());
		for(Corso c : listaCorsi)
			System.out.println(" "+c.getCodice()+", "+c.getTitolo()+", "+c.getAnno()+", "+c.getPeriodoDidattico()+", "+c.getCrediti()+"");
		
		System.out.println("\nElenco docenti (ordinati per cognome e nome):");
		LinkedList<Docente> listaDocenti = new LinkedList<Docente>(p.elencoDocenti());
		for(Docente d : listaDocenti)
			System.out.println(" "+d.getId()+", "+d.getCognome()+", "+d.getNome()+", "+d.getDipartimento());
		
		
		System.out.println("\n\n/*****************************************/");
		System.out.println("/**   R2. ASSEGNAZIONI CORSI - DOCENTI  **/");
		System.out.println("/*****************************************/\n");

		System.out.println("Assegna docente "+d1.getId()+" al corso "+c1.getCodice()+" per 20 ore di tipo ESE\n");
		
		p.assegnaDocenteCorso(c1.getCodice(), d1.getId(), 20, "ESE");

		System.out.println("Docenti assegnati al corso "+c1.getCodice()+" (ordinati per numero ore decrescente):");
		String stringaDocentiAssegnati = p.stampaDocentiCorso(c1.getCodice());
		System.out.println(stringaDocentiAssegnati);
		
		System.out.println("\nAssegna altri docenti al corso "+c1.getCodice()+"\n");

		p.assegnaDocenteCorso(c1.getCodice(), d3.getId(), 50, "ESE");
		p.assegnaDocenteCorso(c1.getCodice(), d4.getId(), 10, "LEZ");
		p.assegnaDocenteCorso(c1.getCodice(), d4.getId(), 10, "LEZ");

		System.out.println("Docenti assegnati al corso "+c1.getCodice()+" (ordinati per numero ore decrescente):");
		stringaDocentiAssegnati = p.stampaDocentiCorso(c1.getCodice());
		System.out.println(stringaDocentiAssegnati);
		
		System.out.println("\nAssegna docenti al corso "+c2.getCodice()+"");

		p.assegnaDocenteCorso(c3.getCodice(), d3.getId(), 30, "LEZ");
		p.assegnaDocenteCorso(c3.getCodice(), d5.getId(), 30, "ESE");
		
		//System.out.println("\nCorsi assegnati al docente "+d3.getId()+" (ordinati per numero ore decrescente):");
		//String stringaCorsiAssegnati = p.stampaCorsiDocente(d3.getId());
		//System.out.println(stringaCorsiAssegnati);
		
		
		System.out.println("\n\n/*****************************************/");
		System.out.println("/**         R3. VIRTUAL CLASSROOM       **/");
		System.out.println("/*****************************************/\n");

		System.out.println("Creazione Virtual Classroom corso "+c1.getCodice()+", docente "+d1.getId());

		VC vc1 = p.creaVirtualClassroom(c1.getCodice(), d1.getId(), "20200317", "14:30", true, false);

		System.out.println("\nInformazioni Virtual Classroom:");
		
		if(vc1 instanceof VCLezioneEsercitazione)
			System.out.println("VC lezione/eserc., "+vc1.getCorso().getCodice()+", "+vc1.getData()+", "+vc1.getOraInizio()+"-"+vc1.getOraFine()+", reg. "+((VCLezioneEsercitazione)vc1).isAbRegistrazione()+", mic./webcam "+((VCLezioneEsercitazione)vc1).isAbMicWebcam());
		else if(vc1 instanceof VCEsame)
			System.out.println("VC esame, "+vc1.getCorso().getCodice()+", "+vc1.getData()+", "+vc1.getOraInizio()+"-"+vc1.getOraFine());
			
		System.out.println("\nChiusura Virtual Classroom corso "+c1.getCodice());

		p.chiudiVirtualClassroom(c1.getCodice(), "16:30");

		System.out.println("\nInformazioni Virtual Classroom:");
		
		if(vc1 instanceof VCLezioneEsercitazione)
			System.out.println("VC l./es., "+vc1.getCorso().getCodice()+", "+vc1.getData()+", "+vc1.getOraInizio()+"-"+vc1.getOraFine()+", reg. "+((VCLezioneEsercitazione)vc1).isAbRegistrazione()+", mic./webcam "+((VCLezioneEsercitazione)vc1).isAbMicWebcam());
		else if(vc1 instanceof VCEsame)
			System.out.println("VC esame,  "+vc1.getCorso().getCodice()+", "+vc1.getData()+", "+vc1.getOraInizio()+"-"+vc1.getOraFine());
		
		
		System.out.println("\nCreazione altre Virtual Classroom (non chiuse)");
		p.creaVirtualClassroom(c1.getCodice(), d1.getId(), "20200317", "11:00", true, false);
		//p.creaVirtualClassroom(c1.getCodice(), d1.getId(), "20200220", "16:00");
		//p.creaVirtualClassroom(c1.getCodice(), d2.getId(), "20200621", "08:30");
		p.creaVirtualClassroom(c3.getCodice(), d3.getId(), "20200223", "08:30");
		
		System.out.println("\nElenco Virtual Classroom (ordinate per data e ora):");
		LinkedList<VC> listaVirtualClassroom = new LinkedList<VC>(p.elencoVirtualClassroom());
		for(VC vc : listaVirtualClassroom) {
			if(vc instanceof VCLezioneEsercitazione)
				System.out.println("VC l./es., "+vc.getCorso().getCodice()+", "+vc.getData()+", "+vc.getOraInizio()+"-"+vc.getOraFine()+", reg. "+((VCLezioneEsercitazione)vc).isAbRegistrazione()+", mic./webcam "+((VCLezioneEsercitazione)vc).isAbMicWebcam());
			else if(vc instanceof VCEsame)
				System.out.println("VC esame,  "+vc.getCorso().getCodice()+", "+vc.getData()+", "+vc.getOraInizio()+"-"+vc.getOraFine());
		}
		for(VC vc : listaVirtualClassroom) {
			vc.chiudiVC("16.00");
		}
		
		System.out.println("\nElenco virtual classroom chiuse (ordinate per data e ora):");
		LinkedList<VC> listaVirtualClassroomChiuse = new LinkedList<VC>(p.elencoVirtualClassroomChiuse());
		for(VC vc : listaVirtualClassroomChiuse) {
			if(vc instanceof VCLezioneEsercitazione)
				System.out.println("VC l./es., "+vc.getCorso().getCodice()+", "+vc.getData()+", "+vc.getOraInizio()+"-"+vc.getOraFine()+", reg. "+((VCLezioneEsercitazione)vc).isAbRegistrazione()+", mic./webcam "+((VCLezioneEsercitazione)vc).isAbMicWebcam());
			else if(vc instanceof VCEsame)
				System.out.println("VC esame,  "+vc.getCorso().getCodice()+", "+vc.getData()+", "+vc.getOraInizio()+"-"+vc.getOraFine());
		}
		
		System.out.println("\n\n/*****************************************/");
		System.out.println("/**    R4. COLLEGAMENTI E STATISTICHE   **/");
		System.out.println("/*****************************************/\n");

		System.out.println("Collegamenti alle Virtual Classroom per il corso "+c3.getCodice());
		p.collegamento(c3.getCodice(), "12345", "08:50", 20);
		p.collegamento(c3.getCodice(), "67890", "10:15", 10);
		p.collegamento(c3.getCodice(), "55555", "08:15", 30);
		
		System.out.println("\nNumero di collegamenti per matricola per il corso "+c3.getCodice()+" (ordinati per matricola, minuti cumulati):\n");
		String stringaCollegamentiCorso = p.stampaCollegamentiCorso(c3.getCodice());
		System.out.println(stringaCollegamentiCorso);
	}

}
