package dc.control.util;

import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static boolean isBlank(Date date) {
		boolean b = isNull(date);

		if (b) {
			return b;
		}

		b = date.equals("");

		return b;
	}

	public static boolean isNotBlank(Date date) {
		boolean b = isNotNull(date);

		if (!b) {
			return b;
		}

		b = date.equals("");

		return !b;
	}

	public static boolean isNotNull(Date date) {
		return date != null;
	}

	public static boolean isNull(Date date) {
		return date == null;
	}

}