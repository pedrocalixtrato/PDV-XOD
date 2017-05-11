package dc.entidade.financeiro.type;


public enum StatusChequeType {
	
	E("EM SER", "E"),

	B("BAIXADO", "B"),

	U("UTILIZADO", "U"),
	
	C("COMPENSADO","C"),
	
	X("CANCELADO","X");

	private String key;

	private String value;

	private StatusChequeType(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static StatusChequeType getEn(String value) {
		if (value.equals("E")) {
			return E;
		}

		if (value.equals("B")) {
			return B;
		}

		if (value.equals("U")) {
			return U;
		}
		
		if (value.equals("C")) {
			return C;
		}
		
		if (value.equals("X")) {
			return X;
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
