package didatticaonline;

import java.util.Comparator;

public class ComparatoreCorsiCodiceCorso implements Comparator<Corso> {

	@Override
	public int compare(Corso c1, Corso c2) {
		return c1.getCodice().compareTo(c2.getCodice());
	}

}
