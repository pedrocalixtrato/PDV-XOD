package dc.visao.ordemservico;


public enum SimNaoEn {

	SIM("Sim", true), NAO("Não", false);

	private SimNaoEn(String label, Boolean valor) {
		this.label = label;
		this.valor = valor;
	}

	private String label;
	private Boolean valor;

	public static SimNaoEn getSimNao(Boolean valor) {
		for (SimNaoEn e : SimNaoEn.values()) {
			if (e.getValor().equals(valor)) {
				return e;
			}
		}

		return null;
	}

	public Boolean getValor() {
		return valor;
	}

	public String getLabel() {
		return label;
	}

	
	@Override
	public String toString() {
		return label;
	}
}
