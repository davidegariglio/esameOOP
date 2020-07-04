package didatticaonline;

public class VCLezioneEsercitazione extends VC{

	private boolean abregistrazione;
	private boolean abMicWebcam;
	
	public VCLezioneEsercitazione(Corso corso, Docente docente, String data,
			String oraInizio, boolean abregistrazione, boolean abMicWebcam) {
		super(corso, docente, data, oraInizio);
		this.abregistrazione = abregistrazione;
		this.abMicWebcam = abMicWebcam;
	}

	public boolean isAbRegistrazione() {
		return this.abregistrazione;
	}

	public boolean isAbMicWebcam() {
		return this.abMicWebcam;
	}

}
