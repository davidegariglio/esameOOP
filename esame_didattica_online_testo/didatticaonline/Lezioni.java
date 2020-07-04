package didatticaonline;

public class Lezioni {

	private Docente d;
	private Corso c;
	private int ore;
	private String tipo;
	
	public Lezioni(Docente d, Corso c, int ore, String tipo) {
		super();
		this.d = d;
		this.c = c;
		this.ore = ore;
		this.tipo = tipo;
	}
	public Docente getD() {
		return d;
	}
	public void setD(Docente d) {
		this.d = d;
	}
	public Corso getC() {
		return c;
	}
	public void setC(Corso c) {
		this.c = c;
	}
	public int getOre() {
		return ore;
	}
	public void setOre(int ore) {
		this.ore = ore;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void aggiungiOreLezione(Lezioni l){
		if((this.ore + l.getOre())*10 <= this.c.getCrediti()*10){
			this.ore += l.getOre();
		}
	}
}
