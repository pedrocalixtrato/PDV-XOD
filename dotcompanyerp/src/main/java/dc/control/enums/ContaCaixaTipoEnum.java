package dc.control.enums;

public enum ContaCaixaTipoEnum {

	C {
		@Override
		public String toString() {
			return "Corrente";
		}
	},

	P {
		@Override
		public String toString() {
			return "Poupança";
		}
	},

	I {
		@Override
		public String toString() {
			return "Investimento";
		}
	},

	X {
		@Override
		public String toString() {
			return "Caixa interno";
		}
	},

}