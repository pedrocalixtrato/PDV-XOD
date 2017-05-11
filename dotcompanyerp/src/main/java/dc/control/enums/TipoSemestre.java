package dc.control.enums;

public enum TipoSemestre {

	P("PRIMEIRO SEMESTRE", "1"),

	S("SEGUNDO SEMESTRE", "2");

	private String label;

	private String codigo;

	private TipoSemestre(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}
	
	public static TipoSemestre getEnum(String valor) {
		if (valor.equals("1")) {
			return P;
		}

		if (valor.equals("2")) {
			return S;
		}

		return null;
	}

	public static TipoSemestre getEn(String value) {
		if (value.equals("PRIMEIRO SEMESTRE")) {
			return P;
		}

		if (value.equals("SEGUNDO SEMESTRE")) {
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