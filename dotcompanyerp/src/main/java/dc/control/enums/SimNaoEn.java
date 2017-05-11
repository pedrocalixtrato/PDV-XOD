package dc.control.enums;

public enum SimNaoEn {

	N("NÃO", "0"),

	S("SIM", "1");

	private String label;

	private String codigo;

	private SimNaoEn(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static SimNaoEn getEn(String codigo) {
		if (codigo.equals("0")) {
			return N;
		}

		if (codigo.equals("1")) {
			return S;
		}

		return null;
	}

	public static SimNaoEn getEnum(String valor) {
		if (valor.equals("NÃO")) {
			return N;
		}

		if (valor.equals("SIM")) {
			return S;
		}

		return null;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}

}