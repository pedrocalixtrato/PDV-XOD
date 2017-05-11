package dc.control.enums;

public enum CategoriaReservistaEn {

	U("1", "1"),

	D("2", "2"),

	T("3", "3");

	private String key;

	private String value;

	private CategoriaReservistaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static CategoriaReservistaEn getEn(String value) {
		if (value.equals("1")) {
			return U;
		}

		if (value.equals("2")) {
			return D;
		}

		if (value.equals("3")) {
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