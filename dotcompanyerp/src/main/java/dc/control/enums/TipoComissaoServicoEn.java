package dc.control.enums;

public enum TipoComissaoServicoEn {

	R("Comissão / Serviço R$", "R"),

	P("Comissão / Serviço %", "P");

	private String key;

	private String value;

	private TipoComissaoServicoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoComissaoServicoEn getEn(String value) {
		if (value.equals("R")) {
			return R;
		}

		if (value.equals("P")) {
			return P;
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