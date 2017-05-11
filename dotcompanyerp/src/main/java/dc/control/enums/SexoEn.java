package dc.control.enums;

public enum SexoEn {

	F("FEMININO", "F"),

	M("MASCULINO", "M");

	private String key;

	private String value;

	private SexoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static SexoEn getEn(String value) {
		if (value.equals("F")) {
			return F;
		}

		if (value.equals("M")) {
			return M;
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