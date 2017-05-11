package dc.control.enums;

public enum RacaEn {

	B("BRANCO", "1"),

	N("NEGRO", "2"),

	P("PARDO", "3"),

	I("ÍNDIO", "4");

	private String key;

	private String value;

	private RacaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static RacaEn getEn(String key) {
		if (key.equals("1")) {
			return B;
		}

		if (key.equals("2")) {
			return N;
		}

		if (key.equals("3")) {
			return P;
		}

		if (key.equals("4")) {
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