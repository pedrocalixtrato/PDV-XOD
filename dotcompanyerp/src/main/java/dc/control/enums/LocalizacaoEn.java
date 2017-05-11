package dc.control.enums;

public enum LocalizacaoEn {

	N("NACIONAL", "N"),

	I("INTERNACIONAL", "I");

	private String key;

	private String value;

	private LocalizacaoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static LocalizacaoEn getEn(String key) {
		if (key.equals("N")) {
			return N;
		}

		if (key.equals("I")) {
			return I;
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