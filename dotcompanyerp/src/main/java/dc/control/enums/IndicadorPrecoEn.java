package dc.control.enums;

public enum IndicadorPrecoEn {

	T("TABELA", "1"),

	U("ÚLTIMO PEDIDO", "2");

	private String key;

	private String value;

	private IndicadorPrecoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static IndicadorPrecoEn getEn(String value) {
		if (value.equals("1")) {
			return T;
		}

		if (value.equals("2")) {
			return U;
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