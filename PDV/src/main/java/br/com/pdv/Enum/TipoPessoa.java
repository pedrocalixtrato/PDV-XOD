package br.com.pdv.Enum;

public enum TipoPessoa {
	
	FISICA("Fisica"),
	JURIDICA("Juridica");
	
	
	private String descricao;
	
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
