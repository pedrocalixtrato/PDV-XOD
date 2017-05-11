package dc.visao.suprimento.estoque;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.suprimento.estoque.ReajusteEstoqueFormController;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.estoque.ReajusteCabecalhoEntity;
import dc.entidade.suprimentos.estoque.ReajusteDetalheEntity;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.visao.framework.component.SubFormComponent;
import dc.visao.framework.util.ComponentUtil;

public class ReajusteEstoqueFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private GridLayout fields;

	@AutoGenerated
	private PopupDateField dataReajuste;

	@AutoGenerated
	private TextField porcentagem;

	@AutoGenerated
	private ComboBox cmbTipoReajuste;

	ReajusteEstoqueFormController controller;

	ReajusteCabecalhoEntity currentBean;

	private SubFormComponent<ReajusteDetalheEntity, Integer> subForm;
	
	@AutoGenerated
	private TabSheet tsGeral;
	@AutoGenerated
	private VerticalLayout vlProduto;
	@AutoGenerated
	private Panel plProduto;

	@AutoGenerated
	private TabSheet subForms;

	@AutoGenerated
	private TabSheet produtos;

	@Autowired
	ProdutoDAO produtoDAO;

	public ReajusteEstoqueFormView(ReajusteEstoqueFormController controller) {
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private GridLayout buildFields() {
		// common part: create layout
		fields = new GridLayout(6, 1);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		// calDataRequisicao
		dataReajuste = new PopupDateField("Data Reajuste", new Date());
		dataReajuste.setImmediate(false);
		fields.addComponent(dataReajuste, 0, 0);

		porcentagem = ComponentUtil.buildPercentageField("Porcentagem");
		fields.addComponent(porcentagem, 1, 0);

		cmbTipoReajuste = ComponentUtil.buildComboBox("Tipo de Reajuste");
		fields.addComponent(cmbTipoReajuste, 2, 0);

		return fields;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// fields
		fields = buildFields();
		mainLayout.addComponent(fields);

		/*subForms = buildSubForms();
		mainLayout.addComponent(subForms);
		subForms.setHeight("200%");
		mainLayout.setExpandRatio(subForms, 1);*/
		
		tsGeral = new TabSheet();
		tsGeral.setImmediate(true);
		tsGeral.setSizeFull();

		tsGeral.addTab(bvlProduto(), 0);

		mainLayout.addComponent(tsGeral);
		mainLayout.setExpandRatio(tsGeral, 1);

		return mainLayout;
	}

	public PopupDateField getDataReajuste() {
		return dataReajuste;
	}

	public void setDataReajuste(PopupDateField dataReajuste) {
		this.dataReajuste = dataReajuste;
	}

	public TextField getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(TextField porcentagem) {
		this.porcentagem = porcentagem;
	}

	public void carregarTipoReajuste() {
		this.cmbTipoReajuste.removeAllItems();
		this.cmbTipoReajuste.addItem(TipoReajuste.AUMENTAR);
		this.cmbTipoReajuste.addItem(TipoReajuste.DIMINUIR);
	}

	public enum TipoReajuste {
		AUMENTAR("Aumentar", "1"), DIMINUIR("Diminuir", "2");

		private TipoReajuste(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TipoReajuste getTipoReajuste(String codigo) {
			if (codigo.equals("1")) {
				return AUMENTAR;
			} else if (codigo.equals("2")) {
				return DIMINUIR;
			}

			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public ComboBox getCmbTipoReajuste() {
		return cmbTipoReajuste;
	}

	public void setCmbTipoReajuste(ComboBox cmbTipoReajuste) {
		this.cmbTipoReajuste = cmbTipoReajuste;
	}

	public void carregarView(ReajusteCabecalhoEntity currentBean) {
		cmbTipoReajuste.setValue(TipoReajuste.getTipoReajuste(currentBean
				.getTipo()));
	}
	
	@AutoGenerated
	private VerticalLayout bvlProduto() {
		// common part: create layout
		vlProduto = new VerticalLayout();
		vlProduto.setImmediate(false);
		vlProduto.setWidth("100.0%");
		vlProduto.setHeight("100.0%");
		vlProduto.setMargin(true);
		vlProduto.setSpacing(true);
		vlProduto.setCaption("Produto");

		//
		vlProduto.addComponent(bplProduto());

		return vlProduto;
	}

	@AutoGenerated
	private Panel bplProduto() {
		// common part: create layout
		plProduto = new Panel();
		plProduto.setImmediate(false);
		plProduto.setSizeFull();

		plProduto.setContent(buildProdutoSubForm());

		return plProduto;
	}

	@AutoGenerated
	private SubFormComponent<ReajusteDetalheEntity, Integer> buildProdutoSubForm() {
		subForm = new SubFormComponent<ReajusteDetalheEntity, Integer>(
				ReajusteDetalheEntity.class, 
				new String[] { "produto","valorOriginal", "valorReajuste"  }, 
				new String[] {"Produto", "Valor Original", "Valor Reajuste" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {


						if ("produto".equals(propertyId)) {
							ComboBox comboBox = ComponentUtil.buildComboBox(null);
							BeanItemContainer<ProdutoEntity> PRODUTO = new BeanItemContainer<>(ProdutoEntity.class,
									controller.buscarProdutos());
							PRODUTO.addNestedContainerProperty("nome");
							comboBox.setContainerDataSource(PRODUTO);
							comboBox.setItemCaptionPropertyId("nome");
							return comboBox;
						
						} 
						if ("valorOriginal".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.setEnabled(false);
							textField.addBlurListener(getBlurListener(
									container, itemId, propertyId));
							return textField;
						}

						if ("valorReajuste".equals(propertyId)) {
							TextField textField = ComponentUtil
									.buildCurrencyField(null);
							textField.setEnabled(false);
							// textField.addBlurListener(getBlurListener(container,
							// itemId, propertyId));
							return textField;
						}
						return null;
					}

					private BlurListener getBlurListener(
							final Container container, final Object itemId,
							final Object propertyId) {
						return new BlurListener() {
							@Override
							public void blur(BlurEvent event) {
								Property<ProdutoEntity> produtoProperty = get("produto");
								ProdutoEntity produto = produtoProperty
										.getValue();
								Property<BigDecimal> valorOriginal = getBigDecimal("valorOriginal");
								Property<BigDecimal> valorReajuste = getBigDecimal("valorReajuste");
								Object tipoReajuste = cmbTipoReajuste
										.getValue();
								if (produto != null) {
									// valorOriginal.setValue(produto.getValorVenda());
								}
								if (getPorcentagem().getValue() != null
										&& valorOriginal != null
										&& tipoReajuste != null) {
									TipoReajuste tipo = (TipoReajuste) tipoReajuste;
									Integer acao = new Integer(tipo.getCodigo());
									BigDecimal valPorcentagem = new BigDecimal(
											getPorcentagem().getValue())
											.divide(new BigDecimal(100));
									BigDecimal reajustado = ReajusteCabecalhoEntity
											.valorAumentado(valorOriginal.getValue(),
													valPorcentagem, acao);
									valorReajuste.setValue(reajustado);
								}

							}

							@SuppressWarnings("unchecked")
							private Property<ProdutoEntity> get(String property) {
								Item item = container.getItem(itemId);
								return item.getItemProperty(property);
							}

							@SuppressWarnings("unchecked")
							private Property<BigDecimal> getBigDecimal(
									String property) {
								Item item = container.getItem(itemId);
								return item.getItemProperty(property);
							}
						};
					}
				};

			}

			protected ReajusteDetalheEntity getNovo() {
				ReajusteDetalheEntity detalhe = controller.novoDetalhe();
				return detalhe;
			}

			@Override
			public boolean validateItems(List<ReajusteDetalheEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}
		};

		return subForm;
	}

	public void preencherDetalhesSubForm(List<ReajusteDetalheEntity> detalhes) {
		subForm.fillWith(detalhes);
	}

}