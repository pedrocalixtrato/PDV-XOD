package dc.entidade.financeiro.type;

public enum StatusType {
	
	NORMAL,
	CANCELADO,
	EXTRAVIADO,
	UTILIZADO;
	
	public String getNome(){
		switch (this) {
		case NORMAL: return "Normal";
		case CANCELADO: return "Cancelado";
		case EXTRAVIADO: return "Extraviado";
		case UTILIZADO: return "Utilizado";
		}
		
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
