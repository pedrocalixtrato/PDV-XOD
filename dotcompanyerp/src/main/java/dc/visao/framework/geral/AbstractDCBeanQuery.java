package dc.visao.framework.geral;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

public abstract class AbstractDCBeanQuery extends AbstractBeanQuery<Serializable> {
	protected List<Filter> filters = new ArrayList<Filter>();
	protected String[] sortingFields = new String[0];
	protected boolean[] sortingStates = new boolean[0];
	private Logger logger = Logger.getLogger(AbstractDCBeanQuery.class);

	public AbstractDCBeanQuery(QueryDefinition definition, Map<String, Object> queryConfiguration, Object[] sortPropertyIds, boolean[] sortStates) {
		super(definition, queryConfiguration, sortPropertyIds, sortStates);

		filters = definition.getFilters();

		logger.info("sort properties");

		this.sortingFields = new String[sortPropertyIds.length];

		for (int i = 0; i < sortPropertyIds.length; i++) {
			logger.info(String.valueOf(sortPropertyIds[i]));
			this.sortingFields[i] = String.valueOf(sortPropertyIds[i]);
		}

		logger.info("sort states");

		for (int i = 0; i < sortStates.length; i++) {
			logger.info(String.valueOf(sortStates[i]));
		}

		this.sortingStates = sortStates;

		logger.info("DCBeanQueryMultiEmpresa, instatiated");
	}

	@Override
	protected Serializable constructBean() {
		Class pojoClass = (Class) getQueryConfiguration().get("pojoClass");
		Object instance = null;

		try {
			instance = pojoClass.getConstructor().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (Serializable) instance;
	}

	@Override
	protected void saveBeans(List<Serializable> arg0, List<Serializable> arg1, List<Serializable> arg2) {
		// TODO Auto-generated method stub
	}

	protected boolean isSeach(String searchTerm) {
		return searchTerm != null && !searchTerm.trim().isEmpty() || (filters != null && filters.size() > 0);
	}
}
