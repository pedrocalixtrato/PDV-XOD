package dc.control.enums;

public enum CsosnEn {

	_101 {
		@Override
		public String toString() {
			return "101 - TRIBUTADA PELO SIMPLES NACIONAL COM PERMISSÃO DE CRÉDITO";
		}
	}, // - Tributada pelo Simples Nacional com permissão de crédito,

	_102 {
		@Override
		public String toString() {
			return "102 - TRIBUTADA PELO SIMPLES NACIONAL SEM PERMISSÃO DE CRÉDITO";
		}
	}, // - Tributada pelo Simples Nacional sem permissão de crédito,

	_103 {
		@Override
		public String toString() {
			return "103 - ISENÇÃO DO ICMS NO SIMPLES NACIONAL PARA FAIXA DE RECEITA BRUTA";
		}
	}, // - Isenção do ICMS no Simples Nacional para faixa de receita bruta,

	_201 {
		@Override
		public String toString() {
			return "201 - TRIBUTADA PELO SIMPLES NACIONAL COM PERMISSÃO DE CRÉDITO E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	}, // - Tributada pelo Simples Nacional com permissão de crédito e com
		// cobrança do ICMS por substituição tributária,

	
	_202 {
		@Override
		public String toString() {
			return "202 - TRIBUTADA PELO SIMPLES NACIONAL SEM PERMISSÃO DE CRÉDITO E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	}, // - Tributada pelo Simples Nacional sem permissão de crédito e com
		// cobrança do ICMS por substituição tributária,

	_203 {
		@Override
		public String toString() {
			return "203 - ISENÇÃO DO ICMS NO SIMPLES NACIONAL PARA FAIXA DE RECEITA BRUTA E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	}, // - Isenção do ICMS no Simples Nacional para faixa de receita bruta e
		// com cobrança do ICMS por substituição tributária,

	_300 {
		@Override
		public String toString() {
			return "300 - IMUNE";
		}
	}, // - Imune,

	_400 {
		@Override
		public String toString() {
			return "400 - NÃO TRIBUTADA PELO SIMPLES NACIONAL";
		}
	}, // - Não tributada pelo Simples Nacional,

	_500 {
		@Override
		public String toString() {
			return "500 - ICMS COBRADO ANTERIORMENTE POR SUBSTITUIÇÃO TRIBUTÁRIA (SUBSTITUÍDO) OU POR ANTECIPAÇÃO";
		}
	}, // - ICMS cobrado anteriormente por substituição tributária (substituído)
		// ou por antecipação,

	_900 {
		@Override
		public String toString() {
			return "900 - OUTROS";
		} // - Outros
	}

}