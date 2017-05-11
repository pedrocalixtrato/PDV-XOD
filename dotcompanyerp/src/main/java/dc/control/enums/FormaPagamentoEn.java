package dc.control.enums;

public enum FormaPagamentoEn {

	D("DINHEIRO", "1"),

	Q("CHEQUE", "2"),

	C("CONTA", "3");

	private String key;

	private String value;

	private FormaPagamentoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static FormaPagamentoEn getEn(String value) {
		if (value.equals("1")) {
			return D;
		}

		if (value.equals("2")) {
			return Q;
		}

		if (value.equals("3")) {
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