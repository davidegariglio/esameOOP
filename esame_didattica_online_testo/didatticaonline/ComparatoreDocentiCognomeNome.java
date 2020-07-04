package didatticaonline;

import java.util.Comparator;

public class ComparatoreDocentiCognomeNome implements Comparator<Docente> {

	@Override
	public int compare(Docente d1, Docente d2) {
		if(d1.getCognome().compareTo(d2.getCognome())!=0){
			return d1.getCognome().compareTo(d2.getCognome()); 
		}
		else{	//stesso cognome, ritorno il nome
			return d1.getNome().compareTo(d2.getNome());
		}
	}

}
