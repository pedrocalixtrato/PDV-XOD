package dc.control.enums;

public enum TipoSindicatoEn {

	P("PATRONAL", "P"),

	E("EMPREGADOS", "E");

	private String key;

	private String value;

	private TipoSindicatoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoSindicatoEn getEn(String value) {
		if (value.equals("P")) {
			return P;
		}

		if (value.equals("E")) {
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