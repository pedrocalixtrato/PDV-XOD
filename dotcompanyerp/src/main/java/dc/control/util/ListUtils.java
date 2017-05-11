package dc.control.util;

import java.util.List;

public class ListUtils {

	public static boolean isEmpty(List auxLista) {
		return auxLista.isEmpty();
	}

	public static boolean isNotEmpty(List auxLista) {
		return !auxLista.isEmpty();
	}

	public static boolean isNull(List auxLista) {
		return auxLista == null;
	}

	public static boolean isNotNull(List auxLista) {
		return auxLista != null;
	}

	public static boolean isNotNullAndNotEmpty(List auxLista) {
		boolean b = isNotNull(auxLista);

		if (!b) {
			return b;
		}

		b = isNotEmpty(auxLista);

		return b;
	}

	public static boolean isNullOrEmpty(List auxLista) {
		boolean b = isNull(auxLista);

		if (b) {
			return b;
		}

		b = isEmpty(auxLista);

		return b;
	}

}