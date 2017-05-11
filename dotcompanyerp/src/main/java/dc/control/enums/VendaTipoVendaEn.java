package dc.control.enums;

public enum VendaTipoVendaEn {

	O("DO ORÇAMENTO", "1"),

	V("VENDA DIRETA", "2");

	private String key;

	private String value;

	private VendaTipoVendaEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static VendaTipoVendaEn getEn(String value) {
		if (value.equals("0")) {
			return O;
		}

		if (value.equals("1")) {
			return V;
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