package dc.control.enums;

public enum TipoComissaoProdutoEn {

	R("Comissão / Produto R$", "R"),

	P("Comissão / Produto %", "P");

	private String key;

	private String value;

	private TipoComissaoProdutoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoComissaoProdutoEn getEn(String value) {
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