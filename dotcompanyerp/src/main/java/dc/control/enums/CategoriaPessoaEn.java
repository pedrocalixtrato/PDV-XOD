package dc.control.enums;

public enum CategoriaPessoaEn {

	C("CLIENTE", "1"),

	F("FORNECEDOR", "2"),

	O("COLABORADOR", "3"),

	T("TRANSPORTADORA", "4");

	private String key;

	private String value;

	private CategoriaPessoaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static CategoriaPessoaEn getEn(String value) {
		if (value.equals("1")) {
			return C;
		}

		if (value.equals("2")) {
			return F;
		}

		if (value.equals("3")) {
			return O;
		}

		if (value.equals("4")) {
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