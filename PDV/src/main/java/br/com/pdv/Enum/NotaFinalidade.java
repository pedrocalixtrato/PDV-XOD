package br.com.pdv.Enum;

public enum NotaFinalidade {
	
	NFENORMAL("NF-E NORMAL"),
	DEVOLUCAORETORNO("DEVOLUÇÃO/RETORNO");
	
	
	private String descricao;
	
	private NotaFinalidade(String descricao) {		
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
