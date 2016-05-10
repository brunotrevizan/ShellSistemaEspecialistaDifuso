package shell.entities;

import java.util.ArrayList;
import java.util.List;

public class SistemaEspecialistaDifuso {
	private List<Variavel> variaveis;
	private List<Regra> regras;

	public SistemaEspecialistaDifuso() {
		this(new ArrayList<Variavel>(), new ArrayList<Regra>());
	}

	public SistemaEspecialistaDifuso(List<Variavel> variaveis, List<Regra> regras) {
		this.variaveis = variaveis;
		this.regras = regras;
	}

	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

}
