package dc.control.enums;

public enum TipoSpedEn {

	MR("MERCADORIA P VENDA", "00"),

	MP("MATÉRIA PRIMA", "01"),

	EM("EMBALAGEM", "02"),

	PP("PRODUTO EM PROCESSO", "03"),

	PA("PRODUTO ACABADO", "04"),

	SP("SUBPRODUTO", "05"),

	PI("PRODUTO INTERMEDIÁRIO", "06"),

	MC("MATERIAL DE USO E CONSUMO", "07");

	private String key;

	private String value;

	private TipoSpedEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoSpedEn getEn(String value) {
		if (value.equals("00")) {
			return MR;
		}

		if (value.equals("01")) {
			return MP;
		}

		if (value.equals("02")) {
			return EM;
		}

		if (value.equals("03")) {
			return PP;
		}

		if (value.equals("04")) {
			return PA;
		}

		if (value.equals("05")) {
			return SP;
		}

		if (value.equals("06")) {
			return PI;
		}

		if (value.equals("07")) {
			return MC;
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