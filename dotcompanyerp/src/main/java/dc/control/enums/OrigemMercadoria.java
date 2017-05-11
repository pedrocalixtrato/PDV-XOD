package dc.control.enums;

public enum OrigemMercadoria {

	N("NACIONAL", "N"),

	E("ESTRANGEIRA", "E");

	private String key;

	private String value;

	private OrigemMercadoria(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static OrigemMercadoria getEn(String key) {
		if (key.equals("N")) {
			return N;
		}

		if (key.equals("E")) {
			return E;
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