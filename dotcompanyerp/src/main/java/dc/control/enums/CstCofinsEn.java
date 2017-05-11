package dc.control.enums;

public enum CstCofinsEn {

	_01 {
		@Override
		public String toString() {
			return "01 - OPERAÇÃO TRIBUTÁVEL (BASE DE CÁLCULO = VALOR DA OPERAÇÃO (ALÍQUOTA NORMAL (CUMULATIVO / NÃO CUMULATIVO)))";
		}
	},
	// Operação Tributável (base de cálculo = valor da operação (alíquota normal
	// (cumulativo/não cumulativo)))

	_02 {
		@Override
		public String toString() {
			return "02 - OPERAÇÃO TRIBUTÁVEL (BASE DE CÁLCULO = VALOR DA OPERAÇÃO (ALÍQUOTA DIFERENCIADA))";
		}
	},
	// Operação Tributável (base de cálculo = valor da operação (alíquota
	// diferenciada))

	_03 {
		@Override
		public String toString() {
			return "03 - OPERAÇÃO TRIBUTÁVEL (BASE DE CÁLCULO = QUANTIDADE VENDIDA (ALÍQUOTA POR UNIDADE DE PRODUTO))";
		}
	},
	// Operação Tributável (base de cálculo = quantidade vendida (alíquota por
	// unidade de produto))

	_04 {
		@Override
		public String toString() {
			return "04 - OPERAÇÃO TRIBUTÁVEL (TRIBUTAÇÃO MONOFÁSICA (ALÍQUOTA ZERO))";
		}
	},
	// Operação Tributável (tributação monofásica (alíquota zero))

	_06 {
		@Override
		public String toString() {
			return "06 - OPERAÇÃO TRIBUTÁVEL (ALÍQUOTA ZERO)";
		}
	},
	// Operação Tributável (alíquota zero)

	_07 {
		@Override
		public String toString() {
			return "07 - OPERAÇÃO ISENTA DA CONTRIBUIÇÃO";
		}
	},
	// Operação Isenta da Contribuição

	_08 {
		@Override
		public String toString() {
			return "08 - OPERAÇÃO SEM INCIDÊNCIA DA CONTRIBUIÇÃO";
		}
	},
	// Operação Sem Incidência da Contribuição

	_09 {
		@Override
		public String toString() {
			return "09 - OPERAÇÃO COM SUSPENSÃO DA CONTRIBUIÇÃO";
		}
	},
	// Operação com Suspensão da Contribuição

	_99 {
		@Override
		public String toString() {
			return "99 - OUTRAS OPERAÇÕES";
		}
	},
	// Outras Operações

}