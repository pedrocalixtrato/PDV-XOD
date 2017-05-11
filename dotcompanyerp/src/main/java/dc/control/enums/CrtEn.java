package dc.control.enums;

public enum CrtEn {

	N("SIMPLES NACIONAL", "1"),

	E("SIMPLES NACIONAL - EXCESSO DE SUBLIMITE RECEITA BRUTA", "2"),

	R("REGIME NORMAL", "3");

	private String key;

	private String value;

	private CrtEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static CrtEn getTipoSped(String value) {
		if (value.equals("1")) {
			return N;
		}

		if (value.equals("2")) {
			return E;
		}

		if (value.equals("3")) {
			return R;
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