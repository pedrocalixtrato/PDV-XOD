package dc.control.enums;

public enum CstIpiEn {

	_00 {
		@Override
		public String toString() {
			return "00 - ENTRADA COM RECUPERAÇÃO DE CRÉDITO";
		}
	},
	// Entrada com Recuperação de Crédito

	_01 {
		@Override
		public String toString() {
			return "01 - ENTRADA TRIBUTÁVEL COM ALÍQUOTA ZERO";
		}
	},
	// Entrada Tributável com Alíquota Zero

	_02 {
		@Override
		public String toString() {
			return "02 - ENTRADA ISENTA";
		}
	},
	// Entrada Isenta

	_03 {
		@Override
		public String toString() {
			return "03 - ENTRADA NÃO-TRIBUTADA";
		}
	},
	// Entrada Não-Tributada

	_04 {
		@Override
		public String toString() {
			return "04 - ENTRADA IMUNE";
		}
	},
	// Entrada Imune

	_05 {
		@Override
		public String toString() {
			return "05 - ENTRADA COM SUSPENSÃO";
		}
	},
	// Entrada com Suspensão

	_49 {
		@Override
		public String toString() {
			return "49 - OUTRAS ENTRADAS";
		}
	},
	// Outras Entradas

	_50 {
		@Override
		public String toString() {
			return "50 - SAÍDA TRIBUTADA";
		}
	},
	// Saída Tributada

	_51 {
		@Override
		public String toString() {
			return "51 - SAÍDA TRIBUTADA COM ALÍQUOTA ZERO";
		}
	},
	// Saída Tributável com Alíquota Zero

	_52 {
		@Override
		public String toString() {
			return "52 - SAÍDA ISENTA";
		}
	},
	// Saída Isenta

	_53 {
		@Override
		public String toString() {
			return "53 - SAÍDA NÃO-TRIBUTADA";
		}
	},
	// Saída Não-Tributada

	_54 {
		@Override
		public String toString() {
			return "54 - SAÍDA IMUNE";
		}
	},
	// Saída Imune

	_55 {
		@Override
		public String toString() {
			return "55 - SAÍDA COM SUSPENSÃO";
		}
	},
	// Saída com Suspensão

	_99 {
		@Override
		public String toString() {
			return "99 - OUTRAS SAÍDAS";
		}
	}
	// Outras Saídas

}