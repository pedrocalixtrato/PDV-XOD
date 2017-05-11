package dc.control.enums;

public enum TipoBaixaEn {
	
		T("TOTAL", "T"),

		P("PARCIAL", "P");

		private String key;

		private String value;

		private TipoBaixaEn(String value, String key) {
			this.key = key;
			this.value = value;
		}

		public static TipoBaixaEn getEn(String key) {
			if (key.equals("T")) {
				return T;
			}

			if (key.equals("P")) {
				return P;
			}

			return null;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value;
		}

}
