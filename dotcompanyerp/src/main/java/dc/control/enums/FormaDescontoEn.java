package dc.control.enums;

public enum FormaDescontoEn {

	P("PRODUTO", "1"),

	F("FIM DO PRODUTO", "2");

	private String key;

	private String value;

	private FormaDescontoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static FormaDescontoEn getEn(String value) {
		if (value.equals("1")) {
			return P;
		}

		if (value.equals("2")) {
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