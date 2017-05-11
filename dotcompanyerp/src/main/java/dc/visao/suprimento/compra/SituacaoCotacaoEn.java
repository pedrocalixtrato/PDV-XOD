package dc.visao.suprimento.compra;


public enum SituacaoCotacaoEn {
	A("ABERTA", "ABERTA"),

	F("FECHADA", "FECHADA");

	private String key;

	private String value;

	private SituacaoCotacaoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static SituacaoCotacaoEn getEn(String value) {
		if (value.equals("ABERTA")) {
			return A;
		}

		if (value.equals("FECHADA")) {
			return F;
		}

		return null;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
