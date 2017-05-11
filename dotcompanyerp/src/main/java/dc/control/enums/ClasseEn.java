package dc.control.enums;

public enum ClasseEn {

	A("A", "A"),

	B("B", "B"),

	C("C", "C");

	private String key;

	private String value;

	private ClasseEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static ClasseEn getEn(String value) {
		if (value.equals("A")) {
			return A;
		}

		if (value.equals("B")) {
			return B;
		}

		if (value.equals("C")) {
			return C;
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