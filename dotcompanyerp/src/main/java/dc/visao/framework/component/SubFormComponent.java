package dc.visao.framework.component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.ReflectionUtils;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.AbstractBeanContainer.BeanIdResolver;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.framework.AbstractModel;
import dc.framework.DcConstants;
import dc.visao.framework.geral.MainUI;

/**
 * @author Douglas
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class SubFormComponent<T extends AbstractModel<ID>, ID extends Serializable> extends VerticalLayout {

	private static final long serialVersionUID = 2294032614900261052L;

	
	/*
	 * 
	 * Selecionar (AJUSTES)
	 */
	private static final String CUSTOM_SELECT_ID = "";
	
	final CheckBox checkbox = new CheckBox();

	private Map<ID, T> selected = new HashMap<>();

	private Map<String, String> totalizador = new HashMap<>();

	private BeanContainer<Serializable, T> container;

	private final Table table;

	private HorizontalLayout botoes;

	private List<T> dados;

	private Class<T> containerClass;

	public SubFormComponent(Class<T> containerClass, final String[] props, final String[] propsCaption) {
		this(containerClass, props, propsCaption, new String[] {});
	}

	@SuppressWarnings("serial")
	public SubFormComponent(Class<T> containerClass, final String[] props, final String[] propsCaption, final String[] summaryBy) {
		setSizeFull();
		this.containerClass = containerClass;
		setMargin(false);

		container = new BeanContainer<Serializable, T>(containerClass) {
			@Override
			public Collection<String> getContainerPropertyIds() {
				return Arrays.asList(props);
			}
		};
		container.setBeanIdProperty("id");
		container.setBeanIdResolver(new BeanIdResolver<Serializable, T>() {

			@Override
			public Serializable getIdForBean(T bean) {
				ID id = bean.getId();
				if (id == null) {
					return (Serializable) bean.getUuid().getMostSignificantBits();
				}
				return id;
			}
		});

		for (String prop : props) {
			if (prop.contains(".")) {
				container.addNestedContainerProperty(prop);
			}
		}

		table = new Table() {
			@SuppressWarnings("rawtypes")
			@Override
			protected Object getPropertyValue(Object rowId, Object colId, Property property) {
				Object propertyValue = super.getPropertyValue(rowId, colId, property);
				if (propertyValue instanceof Field<?>) {
					((Field<?>) propertyValue).addValueChangeListener(new Property.ValueChangeListener() {
						@Override
						public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
							recalculateSummary();
						}
					});
				}
				return propertyValue;
			}
		};
		table.setEditable(true);
		table.setPageLength(0);
		table.setSizeFull();
		table.setContainerDataSource(container);
		table.addGeneratedColumn(CUSTOM_SELECT_ID, new ColumnGenerator() {

			@Override
			@SuppressWarnings("unchecked")
			public Component generateCell(final Table source, final Object itemId, final Object columnId) {

				/*
				 * @CheckBox
				 * Wesley Jr
				 * 
				 */
				
				final BeanItem<T> selectedBeanItem = (BeanItem<T>) source.getContainerDataSource().getItem(itemId);
				final CheckBox checkBox = new CheckBox();
				checkBox.setImmediate(true);
				checkBox.addValueChangeListener(new Property.ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {
						Boolean select = (Boolean) event.getProperty().getValue();
						if (select) {
							selected.put((ID) itemId, selectedBeanItem.getBean());
						} else {
							selected.remove(itemId);
						}

					}
				});

				checkBox.setValue(selected.containsKey(itemId));

				return checkBox;
			}
		});
		table.addItemClickListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				T item = container.getItem(event.getItemId()).getBean();
				onSelect(item);
			}
		});

		String[] cs = new String[] { CUSTOM_SELECT_ID };

		Map<String, ColumnGenerator> customColumns = generateCustomColumns();

		if (customColumns != null) {
			cs = (String[]) ArrayUtils.addAll(cs, customColumns.keySet().toArray());
			for (String key : customColumns.keySet()) {
				table.addGeneratedColumn(key, customColumns.get(key));
			}
		}

		table.setVisibleColumns(ArrayUtils.addAll(cs, props));
		table.setColumnHeaders((String[]) ArrayUtils.addAll(cs, propsCaption));

		table.setTableFieldFactory(getFieldFactory());

		for (String summary : summaryBy) {
			totalizador.put(summary, "");
		}

		recalculateSummary();

		if (!isReadOnly()) {
			adicionarBotoes(table);
		}

		addComponent(table);
		setExpandRatio(table, 1);
	}

	/**
	 * @author Douglas
	 * @return Retorna um Map com o header da coluna como chave e a coluna como
	 *         value;
	 */
	protected Map<String, ColumnGenerator> generateCustomColumns() {
		return null;
	}

	@SuppressWarnings("serial")
	protected void adicionarBotoes(final Table table) {
		botoes = new HorizontalLayout();
		botoes.setMargin(true);
		Button novo = new Button("Novo");
		novo.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				List<T> items = new ArrayList<>();
				for (Serializable id : container.getItemIds()) {
					items.add(container.getItem(id).getBean());
				}
				if (validateItems(items)) {
					T t = getNovo();
					container.addBeanAt(0, t);
					if (dados == null) {
						dados = new ArrayList<>();
					}
					dados.add(t);
				}
			}

		});
		botoes.addComponent(novo);

		Button remover = new Button("Remover selecionados");
		remover.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				final Collection<T> values = selected.values();
				if (values.isEmpty()) {
					mensagemErro("Nenhum registro selecionado para remoção");
				} else {
						ConfirmDialog
							.show(MainUI.getCurrent(),
									"Confirme a remoção",
									"Isso apagará os registros selecionados e Não poderá ser revertido.!\nDeseja continuar?",
									"Sim", "Não",
							new ConfirmDialog.Listener() {

								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										try {
											getRemoverSelecionados(new ArrayList<>(values));
											removeItens(values);
											selected.clear();
										} catch (Exception e) {
											mensagemErro("Houve um erro remover registro. Verifique se o mesmo Não tem dependência com outros registros.");
										}
									}
								}

							});
				}
			}

		});
		botoes.addComponent(remover);

		addComponent(botoes);
	}

	@SuppressWarnings("serial")
	protected TableFieldFactory getFieldFactory() {
		return new TableFieldFactory() {

			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				return new TextField(propertyId.toString());
			}
		};
	}

	protected T getNovo() {
		throw new UnsupportedOperationException("Implementar este Método!");
	}

	protected void getRemoverSelecionados(List<T> values) {
		throw new UnsupportedOperationException("Implementar este Método!");
	}

	public void fillWith(List<T> dados) {
		this.dados = dados;
		container.removeAllItems();
		container.addAll(dados);
		recalculateSummary();
	}

	protected void onSelect(T item) {
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		botoes.setVisible(false);
	}

	public void mensagemErro(String message) {
		new Notification(DcConstants.ERROR_TITLE, message, Type.ERROR_MESSAGE, true).show(Page.getCurrent());
	}

	public abstract boolean validateItems(List<T> items);

	private final void recalculateSummary() {
		if (totalizador.size() > 0) {
			table.setFooterVisible(true);
			table.setColumnFooter(CUSTOM_SELECT_ID, "Total");
		}

		for (String key : totalizador.keySet()) {
			BigDecimal total = new BigDecimal(0);
			if (dados != null) {
				for (T t : dados) {
					java.lang.reflect.Field field = ReflectionUtils.findField(containerClass, key);
					field.setAccessible(true);
					Object value = ReflectionUtils.getField(field, t);
					if (value != null) {
						total = total.add((BigDecimal) value);
					}
				}
			}
			table.setColumnFooter(key, "R$ " + getFormat().format(total.doubleValue()));
		}

	}

	public Double getTotalSumary(List<T> dados) {
		this.dados = dados;
		BigDecimal totalRetorno = new BigDecimal(0);
		for (String key : totalizador.keySet()) {
			BigDecimal total = new BigDecimal(0);
			if (dados != null) {
				for (T t : dados) {
					java.lang.reflect.Field field = ReflectionUtils.findField(containerClass, key);
					field.setAccessible(true);
					Object value = ReflectionUtils.getField(field, t);
					if (value != null && key.equals("valorTotal")) {
						totalRetorno = totalRetorno.add(total.add((BigDecimal) value));
					}
				}
			}
		}
		return totalRetorno.doubleValue();
	}
	
	public Double getTotalSumary(List<T> dados, String escolha) {
		this.dados = dados;
		BigDecimal totalRetorno = new BigDecimal(0);
		for (String key : totalizador.keySet()) {
			BigDecimal total = new BigDecimal(0);
			if (dados != null) {
				for (T t : dados) {
					java.lang.reflect.Field field = ReflectionUtils.findField(containerClass, key);
					field.setAccessible(true);
					Object value = ReflectionUtils.getField(field, t);
					if (value != null && key.equals(escolha)) {
						totalRetorno = totalRetorno.add(total.add((BigDecimal) value));
					}
				}
			}
		}
		return totalRetorno.doubleValue();
	}

	private NumberFormat getFormat() {
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
		return decimalFormat;
	}

	public List<T> getDados() {
		return dados;
	}

	public void removeAllItems() {
		container.removeAllItems();
	}

	public void removeItens(final Collection<T> values) {

		for (T t : values) {
			table.removeItem(t.getId());
			table.removeItem(t.getUuid().getMostSignificantBits());
			container.removeItem(t);
			dados.remove(t);
		}
	}

	public Collection<T> getSelectedItens() {
		final Collection<T> values = selected.values();

		return values;
	}

}
