package dc.control.enums;

public enum TipoVendaEn {

	V("VENDA", "1"),

	C("COMPOSIÇÃO", "2"),

	P("PRODUÇÃO", "3"),

	I("INSUMO", "4"),

	U("USO PRÓPRIO", "5");

	private String key;

	private String value;

	private TipoVendaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoVendaEn getEn(String value) {
		if (value.equals("1")) {
			return V;
		}

		if (value.equals("2")) {
			return C;
		}

		if (value.equals("3")) {
			return P;
		}

		if (value.equals("4")) {
			return I;
		}

		if (value.equals("5")) {
			return U;
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