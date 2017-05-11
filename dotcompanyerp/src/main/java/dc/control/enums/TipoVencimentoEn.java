package dc.control.enums;

public enum TipoVencimentoEn {

	M("MENSAL", "M"),

	D("DIÁRIO", "D");

	private String key;

	private String value;

	private TipoVencimentoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoVencimentoEn getEn(String value) {
		if (value.equals("M")) {
			return M;
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