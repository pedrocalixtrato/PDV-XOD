package dc.entidade.financeiro.type;

public enum EmpresaType {
	MATRIZ,
	FILIAL,
	DEPOSITO;
	
	public String getNome(){
		switch (this) {
		case MATRIZ: return "Matriz";
		case FILIAL: return "Filial";
		default: return "Deposito";
		}
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}
}