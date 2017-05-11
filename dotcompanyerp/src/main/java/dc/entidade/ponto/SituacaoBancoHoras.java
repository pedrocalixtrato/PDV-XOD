package dc.entidade.ponto;

public enum SituacaoBancoHoras {
	UTILIZADO_PARCIALMENTE("Utilizado Parcialmente", "P"), UTILIZADO("Utilizado", "U"), NAO_UTILIZADO("não Utilizado", "N");

	private SituacaoBancoHoras(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	private String label;
	private String codigo;

	public static SituacaoBancoHoras getSituacao(String codigo) {
		for (SituacaoBancoHoras e : SituacaoBancoHoras.values()) {
			if (e.getCodigo().equalsIgnoreCase(codigo)) {
				return e;
			}
		}

		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}
