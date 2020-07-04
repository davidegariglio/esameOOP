package didatticaonline;

import java.util.Comparator;

public class ComparatoreLezioneOre implements Comparator<Lezioni> {

	@Override
	public int compare(Lezioni l1, Lezioni l2) {
		return -(l1.getOre()-l2.getOre());
	}

}
