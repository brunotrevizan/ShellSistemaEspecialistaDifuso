package shell.entities;

public class Valor {

	private String nome;
	private int inicioSuporte, fimSuporte, inicioNucleo, fimNucleo;
	private double valorFuzzyObjetivo;

	public Valor() {
		this("", 0, 0, 0, 0, 0);
	}

	public Valor(String nome, int inicioSuporte, int fimSuporte,
			int inicioNucleo, int fimNucleo, double valorFuzzyObjetivo) {
		this.nome = nome;
		this.inicioSuporte = inicioSuporte;
		this.fimSuporte = fimSuporte;
		this.inicioNucleo = inicioNucleo;
		this.fimNucleo = fimNucleo;
		this.valorFuzzyObjetivo = valorFuzzyObjetivo;
	}

	public double getPertinencia(double x) {
		double y;
		if (inicioSuporte == inicioNucleo) {

			if (x <= fimNucleo) {
				y = 1;
			} else {
				if (x >= fimSuporte) {
					y = 0;
				} else {
					y = (fimSuporte - x) / (fimSuporte - fimNucleo);
				}
			}

		} else {
			if (fimSuporte == fimNucleo) {

				if (x >= inicioNucleo) {
					y = 1;
				} else {
					if (x <= inicioSuporte) {
						y = 0;
					} else {
						y = (x - inicioSuporte)
								/ (inicioNucleo - inicioSuporte);
					}
				}

			} else {

				if (x >= inicioNucleo && x <= fimNucleo) {
					y = 1;
				} else {
					if (x <= inicioSuporte || x >= fimSuporte) {
						y = 0;
					} else {
						if (x < inicioNucleo) {
							y = (x - inicioSuporte)
									/ (inicioNucleo - inicioSuporte);
						} else {
							y = (fimSuporte - x) / (fimSuporte - fimNucleo);
						}
					}
				}

			}
		}

		return y;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getInicioSuporte() {
		return inicioSuporte;
	}

	public void setInicioSuporte(int inicioSuporte) {
		this.inicioSuporte = inicioSuporte;
	}

	public int getFimSuporte() {
		return fimSuporte;
	}

	public void setFimSuporte(int fimSuporte) {
		this.fimSuporte = fimSuporte;
	}

	public int getInicioNucleo() {
		return inicioNucleo;
	}

	public void setInicioNucleo(int inicioNucleo) {
		this.inicioNucleo = inicioNucleo;
	}

	public int getFimNucleo() {
		return fimNucleo;
	}

	public void setFimNucleo(int fimNucleo) {
		this.fimNucleo = fimNucleo;
	}

	public double getValorFuzzyObjetivo() {
		return valorFuzzyObjetivo;
	}

	public void setValorFuzzyObjetivo(double valorFuzzyObjetivo) {
		this.valorFuzzyObjetivo = valorFuzzyObjetivo;
	}

}
