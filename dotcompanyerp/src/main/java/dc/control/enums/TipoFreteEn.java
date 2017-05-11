package dc.control.enums;

public enum TipoFreteEn {

	E("EMITENTE", "1"),

	D("DESTINATÁRIO", "2"),

	S("SEM FRETE", "3"),

	T("TERCEIROS", "4");

	private String key;

	private String value;

	private TipoFreteEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoFreteEn getEn(String value) {
		if (value.equals("1")) {
			return E;
		}

		if (value.equals("2")) {
			return D;
		}

		if (value.equals("3")) {
			return S;
		}

		if (value.equals("4")) {
			return T;
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