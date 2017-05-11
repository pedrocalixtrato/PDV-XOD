package dc.control.enums;

public enum VendedorAtendente {
	
	V("VENDEDOR", "0"),

	A("ATENDENTE", "1");

	private String label;

	private String codigo;

	private VendedorAtendente(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static VendedorAtendente getEn(String codigo) {
		if (codigo.equals("0")) {
			return V;
		}

		if (codigo.equals("1")) {
			return A;
		}

		return null;
	}

	public static VendedorAtendente getEnum(String valor) {
		if (valor.equals("VENDEDOR")) {
			return V;
		}

		if (valor.equals("ATENDENTE")) {
			return A;
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
