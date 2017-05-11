package dc.control.enums;

public enum TipoEmpresaEn {

	M("MATRIZ", "M"),

	F("FILIAL", "F"),

	D("DEPÓSITO", "D");

	private String key;

	private String value;

	private TipoEmpresaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoEmpresaEn getEn(String value) {
		if (value.equals("M")) {
			return M;
		}

		if (value.equals("F")) {
			return F;
		}

		if (value.equals("D")) {
			return D;
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