package dc.control.enums;

public enum IatEn {

	A("ARREDONDAMENTO", "1"),

	T("TRUNCAMENTO", "2");

	private String key;

	private String value;

	private IatEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static IatEn getEn(String value) {
		if (value.equals("1")) {
			return A;
		}

		if (value.equals("2")) {
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