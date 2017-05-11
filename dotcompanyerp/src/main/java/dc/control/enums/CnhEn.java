package dc.control.enums;

public enum CnhEn {

	A("A", "A"),

	B("B", "B"),

	C("C", "C"),

	D("D", "D"),

	E("E", "E");

	private String key;

	private String value;

	private CnhEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static CnhEn getEn(String value) {
		if (value.equals("A")) {
			return A;
		}

		if (value.equals("B")) {
			return B;
		}

		if (value.equals("C")) {
			return C;
		}

		if (value.equals("D")) {
			return D;
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