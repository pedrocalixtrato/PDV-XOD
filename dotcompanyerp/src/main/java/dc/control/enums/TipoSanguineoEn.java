package dc.control.enums;

public enum TipoSanguineoEn {

	APO("A+", "A+"),

	ANE("A-", "A-"),

	BPO("B+", "B+"),

	BNE("B-", "B-"),

	OPO("O+", "O+"),

	ONE("O-", "O-"),

	ABP("AB+", "AB+"),

	ABN("AB-", "AB-");

	private String key;

	private String value;

	private TipoSanguineoEn(String value, String key) {
		this.key = key;
		this.value = value;
	}

	public static TipoSanguineoEn getEn(String value) {
		if (value.equals("A+")) {
			return APO;
		}

		if (value.equals("A-")) {
			return ANE;
		}

		if (value.equals("B+")) {
			return BPO;
		}

		if (value.equals("B-")) {
			return BNE;
		}

		if (value.equals("O+")) {
			return OPO;
		}

		if (value.equals("O-")) {
			return ONE;
		}

		if (value.equals("AB+")) {
			return ABP;
		}

		if (value.equals("AB-")) {
			return ABN;
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