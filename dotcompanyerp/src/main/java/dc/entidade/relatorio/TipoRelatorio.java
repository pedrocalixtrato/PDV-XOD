package dc.entidade.relatorio;

public enum TipoRelatorio {

	LISTAGEM(1, "Listagem"), FORMULARIO(2, "Formulário"), LISTAGEM_FORMULARIO(3, "Listagem/Formuláio");
	private TipoRelatorio(Integer tipo, String label) {
		this.tipo = tipo;
		this.label = label;
	}

	private Integer tipo;
	private String label;

	public static TipoRelatorio getEnum(Integer tipo) {
		for (TipoRelatorio e : TipoRelatorio.values()) {
			if (e.getTipo().equals(tipo)) {
				return e;
			}
		}

		return null;
	}

	public boolean isListagem() {
		return this.tipo.equals(LISTAGEM.tipo);
	}

	public boolean isFormulario() {
		return this.tipo.equals(FORMULARIO.tipo);
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}

}
