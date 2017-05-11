package dc.control.enums;

public enum IpptEn {

	P("PRÓPRIO", "1"),

	T("TERCEIRO", "2");

	private String key;

	private String value;

	private IpptEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static IpptEn getEn(String value) {
		if (value.equals("1")) {
			return P;
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