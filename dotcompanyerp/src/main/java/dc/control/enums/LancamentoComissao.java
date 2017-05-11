package dc.control.enums;

public enum LancamentoComissao {
	
	C("COMISSÃO A PAGAR", "0"),

	V("COMISSÃO SOBRE VENDAS", "1"),
	
	B("BANCO OU CAIXA", "2"),
	
	I("IMPOSTO DE RENDA RETIDO NA FONTE A PAGAR", "3"),
	
	P("PROVISÃO PARA COMISSÕES", "4");

	private String label;

	private String codigo;

	private LancamentoComissao(String label, String codigo) {
		this.label = label;
		this.codigo = codigo;
	}

	public static LancamentoComissao getEn(String codigo) {
		if (codigo.equals("0")) {
			return C;
		}

		if (codigo.equals("1")) {
			return V;
		}
		
		if (codigo.equals("2")) {
			return B;
		}
		
		if (codigo.equals("3")) {
			return I;
		}
		
		if (codigo.equals("4")) {
			return P;
		}

		return null;
	}

	public static LancamentoComissao getEnum(String valor) {
		if (valor.equals("COMISSÃO A PAGAR")) {
			return C;
		}

		if (valor.equals("COMISSÃO SOBRE VENDAS")) {
			return V;
		}
		
		if (valor.equals("BANCO OU CAIXA")) {
			return B;
		}
		
		if (valor.equals("IMPOSTO DE RENDA RETIDO NA FONTE A PAGAR")) {
			return I;
		}
		
		if (valor.equals("PROVISÃO PARA COMISSÕES")) {
			return P;
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
