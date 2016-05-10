package shell.entities;

public class Regra {
	private Variavel variavelUm, variavelDois, variavelTres;
	private Valor valorVariavelUm, valorVariavelDois, valorVariavelTres;
	private Conectivo conectivo;
	private int idUnico;

	public Regra() {
		this(new Variavel(), new Variavel(), new Variavel(), new Valor(), new Valor(), Conectivo.E, 0);
	}

	public Regra(Variavel variavelUm, Variavel variavelDois, Variavel variavelTres, Valor valorVariavelUm, Valor valorVariavelDois,
			Conectivo conectivo, int idUnico) {
		this.variavelUm = variavelUm;
		this.variavelDois = variavelDois;
		this.variavelTres = variavelTres;
		this.valorVariavelUm = valorVariavelUm;
		this.valorVariavelDois = valorVariavelDois;
		this.conectivo = conectivo;
		this.setIdUnico(idUnico);
	}

	public Variavel getVariavelUm() {
		return variavelUm;
	}

	public void setVariavelUm(Variavel variavelUm) {
		this.variavelUm = variavelUm;
	}

	public Variavel getVariavelDois() {
		return variavelDois;
	}

	public void setVariavelDois(Variavel variavelDois) {
		this.variavelDois = variavelDois;
	}

	public Conectivo getConectivo() {
		return conectivo;
	}

	public void setConectivo(Conectivo conectivo) {
		this.conectivo = conectivo;
	}

	public Valor getValorVariavelUm() {
		return valorVariavelUm;
	}

	public void setValorVariavelUm(Valor valorVariavelUm) {
		this.valorVariavelUm = valorVariavelUm;
	}

	public Valor getValorVariavelDois() {
		return valorVariavelDois;
	}

	public void setValorVariavelDois(Valor valorVariavelDois) {
		this.valorVariavelDois = valorVariavelDois;
	}

	public Variavel getVariavelTres() {
		return variavelTres;
	}

	public void setVariavelTres(Variavel variavelTres) {
		this.variavelTres = variavelTres;
	}

	public Valor getValorVariavelTres() {
		return valorVariavelTres;
	}

	public void setValorVariavelTres(Valor valorVariavelTres) {
		this.valorVariavelTres = valorVariavelTres;
	}

	public int getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}

}
