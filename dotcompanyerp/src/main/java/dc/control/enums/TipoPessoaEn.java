package dc.control.enums;

public enum TipoPessoaEn {

	F("FÍSICA", "F"),

	J("JURÍDICA", "J");

	private String key;

	private String value;

	private TipoPessoaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoPessoaEn getEn(String value) {
		if (value.equals("F")) {
			return F;
		}

		if (value.equals("J")) {
			return J;
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