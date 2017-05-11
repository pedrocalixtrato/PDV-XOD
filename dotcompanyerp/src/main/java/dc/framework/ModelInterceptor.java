package dc.framework;

import java.io.Serializable;
import java.util.Arrays;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.sun.istack.logging.Logger;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.visao.spring.SecuritySessionProvider;

public class ModelInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 5903477031218867131L;

	public static Logger logger = Logger.getLogger(ModelInterceptor.class);

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		logger.info("A " + entity.getClass().getCanonicalName()
				+ " was created.");

		setMultiEmpresa(entity, state, propertyNames);

		return false;
	}

	private void setMultiEmpresa(Object entity, Object[] state,
			String[] properties) {
		String propertyName = "empresa";

		if (entity instanceof AbstractMultiEmpresaModel) {
			AbstractMultiEmpresaModel model = (AbstractMultiEmpresaModel) entity;

			if (SecuritySessionProvider.getUsuario() != null) {
				//if (model.getEmpresa() == null) { // Verifica se a entidade
													// possui empresa nula
					setValue(state, properties, propertyName,
							SecuritySessionProvider.getUsuario().getConta()
									.getEmpresa());
				//}
			}
		} else {
			logger.info("isn´t a multiEmpresa model...");
		}
	}

	private void setValue(Object[] currentState, String[] propertyNames,
			String propertyToSet, Object value) {
		int index = Arrays.asList(propertyNames).indexOf(propertyToSet);

		if (index >= 0) {
			currentState[index] = value;
		}
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		logger.info("A " + entity.getClass().getCanonicalName()
				+ " was updated.");

		setMultiEmpresa(entity, currentState, propertyNames);

		logger.info("updated properties");

		for (int i = 0; i < propertyNames.length; i++) {
			logger.info(propertyNames[i]);
		}

		return false;
	}

}