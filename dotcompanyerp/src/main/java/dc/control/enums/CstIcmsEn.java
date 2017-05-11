package dc.control.enums;

public enum CstIcmsEn {

	_00 {
		@Override
		public String toString() {
			return "00 - TRIBUTADA INTEGRALMENTE";
		}
	},
	// 00 - Tributada integralmente

	_10 {
		@Override
		public String toString() {
			return "10 - TRIBUTADA E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	},
	// 10 - Tributada e com cobrança do ICMS por substituição tributária

	_20 {
		@Override
		public String toString() {
			return "20 - COM REDUÇÃO DE BASE DE CÁLCULO";
		}
	},
	// 20 - Com redução de base de cálculo

	_30 {
		@Override
		public String toString() {
			return "30 - ISENTA OU NÃO TRIBUTADA E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	},
	// 30 - Isenta ou não tributada e com cobrança do ICMS por substituição
	// tributária

	_40 {
		@Override
		public String toString() {
			return "40 - ISENTA";
		}
	},
	// 40 - Isenta

	_41 {
		@Override
		public String toString() {
			return "41 - NÃO TRIBUTADA";
		}
	},
	// 41 - Não tributada

	_50 {
		@Override
		public String toString() {
			return "50 - SUSPENSÃO";
		}
	},
	// 50 - Suspensão

	_51 {
		@Override
		public String toString() {
			return "51 - DIFERIMENTO";
		}
	},
	// 51 - Diferimento

	_60 {
		@Override
		public String toString() {
			return "60 - ICMS COBRADO ANTERIORMENTE POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	},
	// 60 - ICMS cobrado anteriormente por substituição tributária

	_70 {
		@Override
		public String toString() {
			return "70 - COM REDUÇÃO DE BASE DE CÁLCULO E COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA";
		}
	},
	// 70 - Com redução de base de cálculo e cobrança do ICMS por substituição
	// tributária

	_90 {
		@Override
		public String toString() {
			return "90 - OUTRAS";
		}
	}
	// 90 - Outras

}