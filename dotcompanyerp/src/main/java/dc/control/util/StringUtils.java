package dc.control.util;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public synchronized static String removeSpecialCharacters(String string) {
		return string.replaceAll("\\W", "").trim();
	}

	/**
	 * Teste
	 */

	public static void main(String[] args) {
		System.out
				.println(removeSpecialCharacters("...KKK..;;;[[]]][[{{}}}lll++-eeeee-"));
	}

}