package dc.visao.framework.component;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;

public class ComboItemValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object bean;
	private String caption;
	
	public String getCaption() {
		try {
			return BeanUtils.getProperty(bean, "nome").toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	

   
}