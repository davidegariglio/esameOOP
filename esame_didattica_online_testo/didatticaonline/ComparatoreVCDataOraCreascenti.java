package didatticaonline;

import java.util.Comparator;

public class ComparatoreVCDataOraCreascenti implements Comparator<VC> {

	@Override
	public int compare(VC v1, VC v2) {
		String s1 = v1.getData()+v1.getOraInizio();
		String s2 = v2.getData()+v2.getOraInizio();

			return - s1.compareTo(s2);
	}

}
