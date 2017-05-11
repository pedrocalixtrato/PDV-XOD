package dc.control.enums;

public enum TipoRegimeEn {

	R("LUCRO REAL", "1"),

	P("SIMPLES PRESUMIDO", "2"),

	N("SIMPLES NACIONAL", "3");

	private String key;

	private String value;

	private TipoRegimeEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoRegimeEn getEn(String value) {
		if (value.equals("1")) {
			return R;
		}

		if (value.equals("2")) {
			return P;
		}

		if (value.equals("3")) {
			return N;
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