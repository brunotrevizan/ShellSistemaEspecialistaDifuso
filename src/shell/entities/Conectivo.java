package shell.entities;

public enum Conectivo {
	E("E"), OU("OU");

	private String conectivo;

	private Conectivo(String conectivo) {
		this.setConectivo(conectivo);
	}

	public static Conectivo retornaConectivo(String nome) {
		if (nome.equals("E")) {
			return Conectivo.E;
		}
		if (nome.equals("OU")) {
			return Conectivo.OU;
		}
		return null;
	}
	public String getConectivo() {
		return conectivo;
	}

	public void setConectivo(String conectivo) {
		this.conectivo = conectivo;
	}

}
