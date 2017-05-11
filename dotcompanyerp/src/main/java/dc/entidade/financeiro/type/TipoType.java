package dc.entidade.financeiro.type;

public enum TipoType {
	
	CORRENTE,
	POUPANCA,
	INVESTIMENTO,
	CAIXAINTERNO;
	
	public String getNome(){
		switch (this) {
		case CORRENTE: return "Corrente";
		case POUPANCA: return "Poupança";
		case INVESTIMENTO: return "Investimento";
		case CAIXAINTERNO: return "Caixa Interno";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
