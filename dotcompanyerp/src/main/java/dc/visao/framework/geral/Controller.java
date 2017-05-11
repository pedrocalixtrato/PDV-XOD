package dc.visao.framework.geral;

import java.io.Serializable;

import com.vaadin.navigator.View;

/**
 *
 * @author Wesley Jr /* Interface tendo um Component, que utilizamos quase em
 *         todas as classes.
 * 
 */

public interface Controller extends Serializable {

	public View getView();

	public abstract String getViewIdentifier();

}