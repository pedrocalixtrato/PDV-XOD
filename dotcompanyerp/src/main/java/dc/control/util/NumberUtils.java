package dc.control.util;

import java.math.BigDecimal;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

	public static boolean isBlank(Integer number) {
		boolean b = isNull(number);

		if (b) {
			return b;
		}

		b = number.equals("");

		return b;
	}

	public static boolean isBlank(BigDecimal number) {
		boolean b = isNull(number);

		if (b) {
			return b;
		}

		b = number.equals("");

		return b;
	}

	public static boolean isBlank(Double number) {
		boolean b = isNull(number);

		if (b) {
			return b;
		}

		b = number.equals("");

		return b;
	}

	public static boolean isNotBlank(Integer number) {
		boolean b = isNotNull(number);

		if (!b) {
			return b;
		}

		b = number.equals("");

		return !b;
	}

	public static boolean isNotBlank(BigDecimal number) {
		boolean b = isNotNull(number);

		if (!b) {
			return b;
		}

		b = number.equals("");

		return !b;
	}

	public static boolean isNotBlank(Double number) {
		boolean b = isNotNull(number);

		if (!b) {
			return b;
		}

		b = number.equals("");

		return !b;
	}

	public static boolean isNotNull(Integer number) {
		return number != null;
	}

	public static boolean isNotNull(BigDecimal number) {
		return number != null;
	}

	public static boolean isNotNull(Double number) {
		return number != null;
	}

	public static boolean isNull(Integer number) {
		return number == null;
	}

	public static boolean isNull(BigDecimal number) {
		return number == null;
	}

	public static boolean isNull(Double number) {
		return number == null;
	}

	/**
	 * 
	 */

	public static BigDecimal createBigDecimal(Object obj) {
		return (BigDecimal) obj;
	}
	
	public static BigDecimal createBigDecimal(Number obj) {
		return new BigDecimal(obj.doubleValue());
	}


	public static int toInt(Object obj) {
		return (int) obj;
	}

}