package dc.servicos;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String s = "hello";
		
		String accessor = "class.name";
		String out = BeanUtils.getProperty(s, accessor);
		System.out.println(out);
	}

}
