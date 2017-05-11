package dc.servicos.dao.framework.geral;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.FetchType;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.TermTermination;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Operation;
import com.vaadin.data.util.filter.SimpleStringFilter;

import dc.anotacoes.AnotacoesUtil;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.FmMenu;
import dc.entidade.geral.ged.Documento;
import dc.model.dao.AbstractDAO;
import dc.visao.spring.BigDecimalNumericFieldBridge;
import dc.visao.spring.SecuritySessionProvider;

/**
 * @author Wesley Jr /* Classe onde é Abstract, temos nela alguns métodos do
 *         Save, do pesquisar também, onde aqui está um pouco da lógica do
 *         pesquisar que utilizamos dentro da Tela, para pegarmos informações!
 */

@SuppressWarnings({ "unchecked" })
public abstract class AbstractCrudDAO<T> implements AbstractDAO<T> {

	private static final int FIRST_ROW = 1;
	private static final int DEFAULT_PAGE_SIZE = 50;

	@Autowired
	protected SessionFactory sessionFactory;

	private String comboValue;
	private String comboCode;
	private String[] defaultSearchFields;

	public static Logger logger = Logger.getLogger(AbstractCrudDAO.class);

	@PostConstruct
	public void init() {
		configureDefaultComboFields();
		Class<T> entityClass = getEntityClass();

		if (entityClass != null) {
			configureComboFields(entityClass);
		}
	}

	private void configureDefaultComboFields() {
		String[] defaultSearchFields = getDefaultSearchFields();

		if (defaultSearchFields != null && defaultSearchFields.length != 0) {
			comboValue = defaultSearchFields[0];
			comboCode = defaultSearchFields[0];
		}
	}

	private void configureComboFields(Class<T> entityClass) {
		logger.info("combo config for class: " + entityClass);
		java.lang.reflect.Field[] entityFields = entityClass.getDeclaredFields();
		logger.info("fields.." + entityFields);
		logger.info("fields..length: " + entityFields.length);

		for (Field f : entityFields) {
			logger.info("Field: " + f);

			if (f.getAnnotation(ComboCode.class) != null) {
				comboCode = f.getName();
			}

			if (f.getAnnotation(ComboValue.class) != null) {
				comboValue = f.getName();
			}
		}

		logger.info("combo config code: " + comboCode);
		logger.info("combo config value: " + comboValue);
	}

	public abstract Class<T> getEntityClass();

	@Transactional
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Transactional
	public T find(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}

	@Transactional
	public T findInitialized(Serializable id) {
		T entity = (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
		Class<? extends Object> entityClass = entity.getClass();
		Field[] declaredFields = entityClass.getDeclaredFields();
		for (Field field : declaredFields) {
			Annotation[] annotations = field.getAnnotations();

			for (Annotation anotacao : annotations) {
				for (Method method : anotacao.annotationType().getDeclaredMethods()) {
					Object valor;
					try {
						valor = method.invoke(anotacao, (Object[]) null);

						if ("fetch".equals(method.getName()) && FetchType.LAZY.equals(valor)) {
							field.setAccessible(true);
							initialize(field.get(entity));
						}

					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}

		}
		return entity;
	}

	@Transactional
	public void save(T obj) {
		if (obj instanceof AbstractMultiEmpresaModel) {
			@SuppressWarnings("rawtypes")
			AbstractMultiEmpresaModel a = (AbstractMultiEmpresaModel) obj;
			a.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
		}

		sessionFactory.getCurrentSession().save(obj);
	}

	@Transactional
	public void delete(T obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Transactional
	public void deleteAllByIds(List<Serializable> ids) {
		String tableName = getEntityClass().getSimpleName();
		Query q = sessionFactory.getCurrentSession()
				.createQuery("delete from " + tableName + " where id in (:idList) ");
		q.setParameterList("idList", ids.toArray());
		q.executeUpdate();
	}

	@Transactional
	public void deleteAll(List<Serializable> objs) {
		for (Serializable s : objs) {
			sessionFactory.getCurrentSession().delete(s);
		}
	}

	@Transactional()
	public <E> void saveOrUpdate(final E o) {
		if (o instanceof AbstractMultiEmpresaModel) {
			@SuppressWarnings("rawtypes")
			AbstractMultiEmpresaModel a = (AbstractMultiEmpresaModel) o;

			if (a.getEmpresa() == null) {
				a.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			}
		}

		sessionFactory.getCurrentSession().saveOrUpdate(o);
	}

	@Transactional
	public List<T> getAll() {
		return getAll(getEntityClass());
	}

	@Transactional
	public <E> List<E> getAll(final Class<E> type) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);

		return crit.list();
	}

	@Transactional
	public List<T> getAllForCombo(final Class<T> type, int idEmpresa, FmMenu menu, Boolean getAll) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);

		if (getAll == null) {
			getAll = false;
		}

		if (!getAll && isConsultaMultiEmpresa(getEntityClass(), menu)) {
			crit.add(Restrictions.eq("empresa.id", idEmpresa));
		}

		return comboFilteredSearch(null, menu, getAll, null);
	}

	@Transactional
	public List<T> comboFilteredSearch(String value, FmMenu menu, Boolean getAll, List<Filter> filters) {
		String[] fields = { comboCode, comboValue };

		FullTextSession fullTextSession = getFullTextSession();
		List<T> resultSet = new ArrayList<T>();

		org.apache.lucene.search.Query query = createQuery(value, fields, menu, filters, fullTextSession, getAll);

		SortField[] sortingFields = new SortField[1];
		sortingFields[0] = new SortField(comboValue, SortField.Type.STRING, true);

		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, getEntityClass());
		configureSorting(sortingFields, fullTextQuery);

		resultSet = fullTextQuery.setFirstResult(0).list();

		return resultSet;
	}

	@Transactional
	public List<T> getAllForComboSelect(final Class<T> type, int idEmpresa, FmMenu menu, final String typeSelected,
			Integer idSelected) {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);

		crit.add(Restrictions.eq("empresa.id", idEmpresa));

		crit.add(Restrictions.eq(typeSelected.toLowerCase() + ".id", idSelected));
		String order = comboValue.contains(".") ? comboValue.split("\\.")[0] : comboValue;

		return crit.addOrder(Order.asc(order)).list();
	}

	public boolean isMultiEmpresa(@SuppressWarnings("rawtypes") Class c) {
		return AbstractMultiEmpresaModel.class.isAssignableFrom(c);
	}

	public boolean isConsultaMultiEmpresa(@SuppressWarnings("rawtypes") Class c, FmMenu menu, Boolean getAll) {

		UsuarioEntity usuarioEntity = (UsuarioEntity) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		boolean isAdm = usuarioEntity != null
				&& (usuarioEntity.getSuperAdministrador() == null ? false : usuarioEntity.getSuperAdministrador());

		if (menu != null && Boolean.TRUE.equals(menu.getSuperAdministradorOnly()) && !isAdm) {
			return true;
		}

		if (getAll != null && getAll) {
			return false;
		}

		if (menu != null) {
			return isMultiEmpresa(c) && menu.isConsultaMultiempresa();
		} else {
			return isMultiEmpresa(c);
		}
	}

	public boolean isConsultaMultiEmpresa(@SuppressWarnings("rawtypes") Class c, FmMenu ent) {
		return isConsultaMultiEmpresa(c, ent, false);
	}

	@Transactional
	// @FullTextSearch
	public List<T> fullTextSearch(String valor) {
		return fullTextSearch(valor, getSearchFields(), FIRST_ROW, DEFAULT_PAGE_SIZE, new String[0], new boolean[0],
				null, null);
	}

	@Transactional
	public List<T> fullTextSearch(String valor, int first, int pageSize, String[] sortingFields,
			boolean[] sortingStates, List<Filter> filters) {
		return fullTextSearch(valor, getSearchFields(), first, pageSize, sortingFields, sortingStates, null, filters);
	}

	@Transactional
	public List<T> fullTextSearch(String valor, String[] sortingFields, boolean[] states, List<Filter> filters) {
		return fullTextSearch(valor, getSearchFields(), FIRST_ROW, DEFAULT_PAGE_SIZE, sortingFields, states, null,
				filters);
	}

	@Transactional
	public int fullTextSearchCount(String searchValue, FmMenu menu, List<Filter> filters) {
		FullTextSession fullTextSession = getFullTextSession();

		org.apache.lucene.search.Query query = createQuery(searchValue, getSearchFields(), menu, filters,
				fullTextSession, null);

		return fullTextSession.createFullTextQuery(query, getEntityClass()).getResultSize();
	}

	@Transactional
	private List<T> fullTextSearch(String value, String[] searchFields, int first, int pageSize,
			String[] sortingFieldsStrings, boolean[] sortStates, FmMenu menu, List<Filter> filters) {
		FullTextSession fullTextSession = getFullTextSession();

		SortField[] sortingFields = new SortField[sortingFieldsStrings.length];

		for (int i = 0; i < sortingFieldsStrings.length; i++) {
			sortingFields[i] = new SortField(sortingFieldsStrings[i], SortField.Type.STRING, sortStates[i]);
		}

		org.apache.lucene.search.Query query = createQuery(value, searchFields, menu, filters, fullTextSession, null);

		FullTextQuery q = fullTextSession.createFullTextQuery(query, getEntityClass());

		configureSorting(sortingFields, q);
		// q.setFirstResult(first);
		// q.setMaxResults(pageSize);
		List<T> resultSet = q.list();

		logger.info("found for: " + value);
		logger.info("found: " + resultSet.size() + " entities...");

		return resultSet;
	}

	private org.apache.lucene.search.Query createQuery(String value, String[] searchFields, FmMenu menu,
			List<Filter> filters, FullTextSession fullTextSession, Boolean getAll) {
		org.apache.lucene.search.Query query;

		if (filters != null && filters.size() > 0) {
			searchFields = new String[0];
		}

		if (getAll == null) {
			getAll = false;
		}

		if (!getAll && isConsultaMultiEmpresa(getEntityClass(), menu)) {
			query = createMultiEmpresaQuery(value, searchFields, menu, filters);
		} else {
			query = createSimpleFullTextQuery(value, searchFields, fullTextSession, filters);
		}

		return query;
	}

	private org.apache.lucene.search.Query createSimpleFullTextQuery(String value, String[] searchFields,
			FullTextSession fullTextSession, List<Filter> filters) {
		BooleanQuery booleanQuery = createFieldQueryByValue(value, searchFields);

		org.apache.lucene.search.Query filtersQuery = generateFiltersQuery(filters);

		if (filtersQuery != null) {
			booleanQuery.add(filtersQuery, Occur.MUST);
		}

		if ("".equals(booleanQuery.toString())) {
			QueryParser parser = new QueryParser(Version.LUCENE_4_3, "id",
					fullTextSession.getSearchFactory().getAnalyzer("id_empresa_analyzer"));
			try {
				booleanQuery.add(parser.parse("[0 999999]"), Occur.SHOULD);
			} catch (ParseException e) {
			}
		}

		return booleanQuery;
	}

	private BooleanQuery createFieldQueryByValue(String value, String... searchFields) {
		BooleanQuery booleanQuery = new BooleanQuery();

		if (dc.control.validator.ObjectValidator.validateString(value)) {
			QueryBuilder qb = getFullTextSession().getSearchFactory().buildQueryBuilder().forEntity(getEntityClass())
					.get();

			List<String> searchList = new ArrayList<String>();
			for (int i = 0; i < searchFields.length; i++) {
				String search = searchFields[i];

				try {
					IndexedEmbedded anotacao = AnotacoesUtil.getAnotacao(IndexedEmbedded.class, getEntityClass(),
							search);
					if (anotacao != null) {
						for (Method method : anotacao.annotationType().getDeclaredMethods()) {
							Object valor;

							valor = method.invoke(anotacao, (Object[]) null);
							if ("includePaths".equals(method.getName()) && valor instanceof String[]) {
								String[] valores = (String[]) valor;
								searchList.add(search + "." + valores[0]);
							}
						}
					} else {
						ClassMetadata classMetadata = sessionFactory.getClassMetadata(getEntityClass());
						Type t = classMetadata.getPropertyType(search);

						if (t.getReturnedClass() == java.lang.String.class) {
							searchList.add(search);
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}

			org.apache.lucene.search.Query query = qb.keyword().fuzzy().withEditDistanceUpTo(1)
					.onFields(searchList.toArray(new String[0])).matching(value).createQuery();

			booleanQuery.add(query, Occur.SHOULD);
			for (String search : searchList) {

				query = qb.keyword().wildcard().onField(search).matching(value + "*").createQuery();

				booleanQuery.add(query, Occur.SHOULD);

			}
		}

		BooleanQuery mustQuery = new BooleanQuery();
		mustQuery.add(booleanQuery, Occur.MUST);
		return booleanQuery;
	}

	private org.apache.lucene.search.Query createMultiEmpresaQuery(String value, String[] searchFields, FmMenu menu,
			List<Filter> filters) {
		FullTextSession fullTextSession = getFullTextSession();
		org.apache.lucene.search.BooleanQuery booleanQuery = new BooleanQuery();

		if (dc.control.validator.ObjectValidator.validateString(value)) {
			org.apache.lucene.search.Query multiEmpresaQuery = buildMultiEmpresaQuery(value, searchFields,
					fullTextSession, menu);
			booleanQuery.add(multiEmpresaQuery, Occur.MUST);
		}

		org.apache.lucene.search.Query filterQuery = generateFiltersQuery(filters);
		booleanQuery.add(buildLuceneQueryForEmpresa(fullTextSession), Occur.MUST);

		if (filterQuery != null) {
			booleanQuery.add(filterQuery, Occur.MUST);
		}

		return booleanQuery;
	}

	private org.apache.lucene.search.Query generateFiltersQuery(Collection<Filter> collection) {
		BooleanQuery booleanQuery = null;

		if (collection != null && collection.size() > 0) {
			booleanQuery = new BooleanQuery();

			for (Filter filter : collection) {
				org.apache.lucene.search.Query query = null;

				if (filter instanceof And) {
					And castedFilter = ((And) filter);
					query = generateFiltersQuery(castedFilter.getFilters());

					if (query != null) {
						booleanQuery.add(query, Occur.MUST);
					}
				}

				try {
					if (filter instanceof Compare) {
						Compare castedFilter = ((Compare) filter);
						Object property = castedFilter.getPropertyId();
						Operation operation = castedFilter.getOperation();

						if (operation.equals(Compare.Operation.EQUAL)) {// EQUAL,
																		// GREATER,
																		// NumericRangeQuery
																		// LESS,
																		// GREATER_OR_EQUAL,
																		// LESS_OR_EQUAL
							//query = createEqualQuery(castedFilter.getValue(), property);
							 query = createGreaterLessQuery(castedFilter.getValue(),
							 castedFilter.getValue(), property, true);
						} else if (operation.equals(Compare.Operation.GREATER_OR_EQUAL)) {
							query = createGreaterLessQuery(castedFilter.getValue(), null, property);
						} else if (operation.equals(Compare.Operation.LESS_OR_EQUAL)) {
							query = createGreaterLessQuery(null, castedFilter.getValue(), property);
						} else if (operation.equals(Compare.Operation.GREATER)) {
							query = createGreaterLessQuery(castedFilter.getValue(), null, property);
						} else if (operation.equals(Compare.Operation.LESS)) {
							query = createGreaterLessQuery(null, castedFilter.getValue(), property);
						}
					} else if (filter instanceof Between) {
						Between castedFilter = ((Between) filter);
						Object property = castedFilter.getPropertyId();

						query = createGreaterLessQuery(castedFilter.getStartValue(), castedFilter.getEndValue(),
								property);
					} else if (filter instanceof SimpleStringFilter) {
						String property = ((SimpleStringFilter) filter).getPropertyId().toString();
						String search = ((SimpleStringFilter) filter).getFilterString();

						if (dc.control.validator.ObjectValidator.validateString(search)) {
							query = createFieldQueryByValue(search, property);
						}
					}

					if (query != null) {
						booleanQuery.add(query, Occur.MUST);
					}
				} catch (SecurityException e) {

					e.printStackTrace();
				}
			}
		}

		return booleanQuery;
	}

	private org.apache.lucene.search.Query createDateQuery(Object startValue, Object endValue, Object property) {
		org.apache.lucene.search.Query query;
		// 20131001030000000
		String start = "*";
		String end = "*";

		if (startValue instanceof Date) {
			start = DateTools.dateToString((Date) startValue, Resolution.SECOND);
		}

		if (endValue instanceof Date) {
			end = DateTools.dateToString((Date) endValue, Resolution.SECOND);
		}

		query = NumericRangeQuery.newIntRange(property.toString(), Integer.parseInt(start), Integer.parseInt(end), true,
				true);

		return query;
	}

	private org.apache.lucene.search.Query createGreaterLessQuery(Object startValue, Object endValue, Object property) {
		return createGreaterLessQuery(startValue, endValue, property, false);
	}

	private org.apache.lucene.search.Query createGreaterLessQuery(Object startValue, Object endValue, Object property,
			boolean equals) {
		org.apache.lucene.search.Query query = null;

		if (startValue instanceof Boolean || endValue instanceof Boolean) {

			FullTextSession fullTextSession = getFullTextSession();
			QueryBuilder qb = getFullTextSession().getSearchFactory().buildQueryBuilder().forEntity(getEntityClass())
					.get();

			org.apache.lucene.search.Query query2 = qb.keyword().onField(property.toString()).matching(startValue)
					.createQuery();
			// fuzzy().withEditDistanceUpTo(1).onFields(searchFields).matching(endValue).createQuery();

			SearchFactory searchFactory = fullTextSession.getSearchFactory();
			org.apache.lucene.search.Query luceneQuery = null;
			QueryParser parser = new QueryParser(Version.LATEST, property.toString(),
					searchFactory.getAnalyzer(Documento.class));
			try {
				luceneQuery = parser.parse("+" + property.toString() + ":" + startValue.toString());
			} catch (ParseException e) {
				// handle parsing failure
			}

			query = luceneQuery;

		} else if (startValue instanceof Integer || endValue instanceof Integer) {
			// 20131001030000000
			Integer startInt = Integer.MIN_VALUE;
			Integer endInt = Integer.MAX_VALUE;

			if (startValue instanceof Integer) {
				startInt = (Integer) startValue;
			}

			if (endValue instanceof Integer) {
				endInt = (Integer) endValue;
			}
			query = NumericRangeQuery.newIntRange(property.toString(), startInt, endInt, equals, equals);
		} else if (startValue instanceof Date || endValue instanceof Date) {

			String start = "-99999999999999999";
			String end = "99999999999999999";

			if (startValue instanceof Date) {
				start = DateTools.dateToString((Date) startValue, Resolution.SECOND);
			}

			if (endValue instanceof Date) {
				end = DateTools.dateToString((Date) endValue, Resolution.SECOND);
			}
			query = NumericRangeQuery.newLongRange(property.toString(), Long.parseLong(start), Long.parseLong(end),
					true, true);
		} else if (startValue instanceof Double || endValue instanceof Double) {

			Long startLong = Long.MIN_VALUE;
			Long endLong = Long.MAX_VALUE;

			if (startValue instanceof Double) {
				startLong = new BigDecimal((Double) startValue).multiply(BigDecimalNumericFieldBridge.storeFactor)
						.longValue();
			}

			if (endValue instanceof Double) {
				endLong = new BigDecimal((Double) endValue).multiply(BigDecimalNumericFieldBridge.storeFactor)
						.longValue();
			}
			query = NumericRangeQuery.newLongRange(property.toString(), startLong, endLong, equals, equals);

			/*
			 * Double startLong = new Double("-9999999999999"); Double endLong =
			 * new Double("9999999999999");
			 * 
			 * if (startValue instanceof Double) { startLong = (Double)
			 * startValue; }
			 * 
			 * if (endValue instanceof Double) { endLong = (Double) endValue; }
			 * 
			 * //query = TermRangeQuery.newStringRange(property.toString(),
			 * startLong.toString(), endLong.toString(), equals, equals);
			 * //valorTotal:{333.0 TO 1.7976931348623157E308} query =
			 * NumericRangeQuery.newDoubleRange(property.toString(), startLong,
			 * endLong, equals, equals);
			 */
		}

		else {
			Long startLong = Long.MIN_VALUE;
			Long endLong = Long.MAX_VALUE;

			if (startValue instanceof Long) {
				startLong = (Long) startValue;
			}

			if (endValue instanceof Integer) {
				endLong = (Long) endValue;
			}

			query = NumericRangeQuery.newLongRange(property.toString(), startLong, endLong, equals, equals);
		}

		return query;
	}

	private org.apache.lucene.search.Query createEqualQuery(Object value, Object property) {
		FullTextSession fullTextSession = getFullTextSession();

		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
		TermTermination matching = null;

		if (value instanceof Boolean) {
			matching = qb.keyword().onField(property.toString()).ignoreAnalyzer().matching(value);
		}else if (value instanceof Double) {
			Long longValue = new BigDecimal((Double) value).multiply(BigDecimalNumericFieldBridge.storeFactor)
					.longValue();
			
			matching = qb.keyword().onField(property.toString()).matching(longValue);
		}		
		else {
			matching = qb.keyword().onField(property.toString()).matching(value);
		}

		return matching.createQuery();
	}

	private void configureSorting(SortField[] sortingFields, FullTextQuery q) {
		if (sortingFields != null && sortingFields.length > 0) {
			org.apache.lucene.search.Sort sort = new Sort(sortingFields);
			q.setSort(sort);
		}
	}

	private org.apache.lucene.search.Query buildLuceneQueryForEmpresa(FullTextSession fullTextSession) {

		org.apache.lucene.search.Query luceneQueryForEmpresa = new org.apache.lucene.search.Query() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String toString(String arg0) {
				return null;
			}

		};

		Integer idEmpresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa().getId();

		String empresaField = "empresa.id";
		if (getEntityClass().equals(EmpresaEntity.class)) {
			empresaField = "id";
		}

		Analyzer an2 = fullTextSession.getSearchFactory().getAnalyzer("id_empresa_analyzer");
		QueryParser parser = new QueryParser(Version.LUCENE_4_3, empresaField, an2);
		try {
			luceneQueryForEmpresa = parser.parse(String.valueOf(idEmpresa));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return luceneQueryForEmpresa;
	}

	private org.apache.lucene.search.Query buildMultiEmpresaQuery(String value, String[] searchFields,
			FullTextSession fullTextSession, FmMenu menu) {

		return createFieldQueryByValue(value, searchFields);
	}

	public String[] getDefaultSearchFields() {
		return generateSearchFields();
	}

	@Transactional
	public FullTextSession getFullTextSession() {
		return Search.getFullTextSession(getSession());
	}

	@Transactional
	public List<T> fullTextSearch(String searchValue, int first, int pageSize, String[] sortingFields,
			boolean[] sortStates, FmMenu menu, List<Filter> filters) {
		return fullTextSearch(searchValue, getSearchFields(), first, pageSize, sortingFields, sortStates, menu,
				filters);
	}

	private String[] getSearchFields() {
		if (defaultSearchFields == null || defaultSearchFields.length > 0) {
			this.defaultSearchFields = getDefaultSearchFields();

			if (defaultSearchFields == null || defaultSearchFields.length == 0) {
				defaultSearchFields = generateSearchFields();
			}
		}

		return defaultSearchFields;
	}

	private String[] generateSearchFields() {
		Class<T> entityClass = getEntityClass();
		if (entityClass != null) {
			ClassMetadata classMetadata = sessionFactory.getClassMetadata(getEntityClass());
			String[] allProps = classMetadata.getPropertyNames();
			ArrayList<String> searchFields = new ArrayList<String>();

			for (int i = 0; i < allProps.length; i++) {
				Type t = classMetadata.getPropertyType(allProps[i]);

				if (t.getReturnedClass() == java.lang.String.class) {
					searchFields.add(allProps[i]);
				}
			}

			return searchFields.toArray(new String[searchFields.size()]);
		}
		return null;
	}

	@Override
	@Transactional
	public int count(Class<T> c) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(c);
		List<T> l = criteria.setProjection(Projections.rowCount()).list();

		return Integer.valueOf(l.get(0).toString());
	}

	@Override

	@Transactional
	public List<T> getAllPaged(Class<T> clazz, int start, int pageSize, String[] sortingFields, boolean[] states,
			List<Filter> filters) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		configureHQLOrder(sortingFields, states, criteria);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageSize);
		List<T> result = criteria.list();

		return result;
	}

	private void configureHQLOrder(String[] sortingFields, boolean[] states, Criteria criteria) {
		for (int i = 0; i < sortingFields.length; i++) {
			if (states[i]) {
				criteria.addOrder(Order.desc(sortingFields[i]).ignoreCase());
			} else {
				criteria.addOrder(Order.asc(sortingFields[i]).ignoreCase());
			}
		}
	}

	private String getHQLOrderByClause(String[] sortingFields, boolean[] states) {
		if (sortingFields.length > 0) {
			String clause = " ORDER BY ";

			for (int i = 0; i < sortingFields.length; i++) {
				if (states[i]) {
					clause = clause + " " + sortingFields[i] + " DESC ,";
				} else {
					clause = clause + " " + sortingFields[i] + " ASC ,";
				}
			}

			return clause.substring(0, clause.length() - 1);
		} else {
			return "";
		}
	}

	@Transactional
	public List<Serializable> getAllPagedByEmpresa(Class<T> pojoClass, Integer idEmpresa, int start, int pageSize,
			String[] sortingFields, boolean[] states) {
		Query query = sessionFactory.getCurrentSession().createQuery("from " + pojoClass.getName()
				+ " where empresa.id = :id_empresa " + getHQLOrderByClause(sortingFields, states));
		query.setParameter("id_empresa", idEmpresa);
		query.setFirstResult(start);

		query.setMaxResults(pageSize);

		List<Serializable> result = query.list();

		return result;
	}

	@Transactional
	public int countByEmpresa(Class<T> c, Integer idEmpresa) {
		List<T> l = sessionFactory.getCurrentSession().createCriteria(c).add(createRestrictionEmpresa(c, idEmpresa))
				.setProjection(Projections.rowCount()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return Integer.valueOf(l.get(0).toString());
	}

	private Criterion createRestrictionEmpresa(Class<T> c, Integer idEmpresa) {
		if (c.equals(EmpresaEntity.class)) {
			return Restrictions.eq("id", idEmpresa);
		}

		return Restrictions.eq("empresa.id", idEmpresa);
	}

	@Transactional
	public List<T> comboTextSearch(String value, FmMenu menu, Boolean getAll) {
		String[] fields = { comboCode, comboValue };

		FullTextSession fullTextSession = getFullTextSession();
		List<T> resultSet = new ArrayList<T>();
		org.apache.lucene.search.Query booleanQuery = createQuery(value, fields, menu, null, fullTextSession, getAll);
		resultSet = fullTextSession.createFullTextQuery(booleanQuery, getEntityClass()).setFirstResult(0).list();

		return comboFilteredSearch(value, menu, getAll, null);
	}

	@Transactional
	public FmMenu getMenu(String nomeClasse) {
		try {
			String sql = "FROM FmMenu ent WHERE (1 = 1) AND ent.controllerClass = :controller";

			Query query = getSession().createQuery(sql);
			query.setParameter("controller", nomeClasse);

			FmMenu ent = (FmMenu) query.uniqueResult();

			if (ent == null) {
				throw new Exception();
			}

			return ent;
		} catch (Exception e) {
			return new FmMenu();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void initialize(Object object) {
		if (object != null && !Hibernate.isInitialized(object)) {
			Hibernate.initialize(object);
		}
	}

	@Transactional
	public List<T> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = getSession().createQuery(sql);
			query.setParameter("q", value);
			List<T> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}