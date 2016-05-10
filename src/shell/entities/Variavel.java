package shell.entities;

import java.util.ArrayList;
import java.util.List;

public class Variavel {

	private String nome;
	private List<Valor> valores;
	private int inicioFaixa, fimFaixa;
	private Integer valorCrisp;
	private boolean objetivo;

	public Variavel() {
		this("", new ArrayList<Valor>(), Integer.MAX_VALUE, Integer.MIN_VALUE,
				0, false);
	}

	public Variavel(String nome, List<Valor> valores, int inicioFaixa,
			int fimFaixa, Integer valorCrisp, boolean objetivo) {
		this.nome = nome;
		this.valores = valores;
		this.setInicioFaixa(inicioFaixa);
		this.setFimFaixa(fimFaixa);
		this.objetivo = objetivo;
	}

	public void popularFaixa() {
		for (Valor valor : getValores()) {
			if (valor.getInicioSuporte() < getInicioFaixa()) {
				setInicioFaixa(valor.getInicioSuporte());
			}
			if (valor.getFimSuporte() > getFimFaixa()) {
				setFimFaixa(valor.getFimSuporte());
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Valor> getValores() {
		return valores;
	}

	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}

	public boolean isObjetivo() {
		return objetivo;
	}

	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}

	public int getInicioFaixa() {
		return inicioFaixa;
	}

	public void setInicioFaixa(int inicioFaixa) {
		this.inicioFaixa = inicioFaixa;
	}

	public int getFimFaixa() {
		return fimFaixa;
	}

	public void setFimFaixa(int fimFaixa) {
		this.fimFaixa = fimFaixa;
	}

	public Integer getValorCrisp() {
		return valorCrisp;
	}

	public void setValorCrisp(Integer valorCrisp) {
		this.valorCrisp = valorCrisp;
	}

}
