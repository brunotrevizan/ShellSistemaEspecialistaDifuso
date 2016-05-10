package shell.entities;

public class PertinenciaRegra {
	private Regra regra;
	private double valorPertinencia;

	public PertinenciaRegra() {
		this(new Regra(), 1);
	}

	public PertinenciaRegra(Regra regra, double valorPertinencia) {
		this.regra = regra;
		this.valorPertinencia = valorPertinencia;
	}

	public Regra getRegra() {
		return regra;
	}

	public void setRegra(Regra regra) {
		this.regra = regra;
	}

	public double getValorPertinencia() {
		return valorPertinencia;
	}

	public void setValorPertinencia(double valorPertinencia) {
		this.valorPertinencia = valorPertinencia;
	}

}
