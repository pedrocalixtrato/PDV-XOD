package dc.entidade.ordemservico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "os_parametros")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ParametroOsEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Caption("Limpar BD")
	@Column(name = "limpar_bd")
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean limparBdAut;

	
	@Caption("Usar vendedor\\atendente")
	@Column(name = "vendedor_atendente")
	//@Enumerated(EnumType.STRING)
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean vendedorAtendente;

	@Caption("Usar vendedor no produto")
	@Column(name = "vendedor_produto")
	//@Enumerated(EnumType.STRING)
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean vendedorProduto;

	@Caption("Usar vendedor no serviço")
	@Column(name = "vendedor_servico")
	private Boolean vendedorServico;

	@Caption("Usar valo pago")
	@Column(name = "valor_pago_peca")
	private Boolean valorPagoPeca;

	@Caption("Usar desconto geral")
	@Column(name = "desconto_geral")
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean descontoGeral;

	@Caption("Usar técnico lançam. produto")
	@Column(name = "tecnico_produto")
	//@Enumerated(EnumType.STRING)
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean tecnicoProduto;

	@Caption("Usar O.S simplificada")
	@Column(name = "ordem_servico_simples")
	private Boolean ordemServicoSimples;

	@Caption("Baixar produto")
	@Column(name = "baixar_produto_estoque")
	private Character baixarProdutoEstoque;

	@Caption("Perguntar qtd dias próxima revisão")
	@Column(name = "qtd_dias_revisao")
	//@Enumerated(EnumType.STRING)
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean qtdDiasRevisao;

	@Caption("Qtde dias padrão revisão")
	@Column(name = "qtd_dias_padrao")
	private Integer qtdDiasPadrao;

	@Caption("Matricial total de linhas")
	@Column(name = "matricial_total_linhas")
	private Integer matricialTotalLinhas;

	@Caption("Matricial entrelinhas")
	@Column(name = "matricial_entrelinhas")
	private Integer matricialEntrelinhas;

	@Caption("Obs defeitos")
	@Column(name = "obs_defeito_padrao")
	private String obsDefeitoPadrao;
	
	@Caption("Obs padrão")
	@Column(name = "obs_padrao")
	private String obsPadrao;
	
	@Caption("Obs O.S simples")
	@Column(name = "obs_padrao_os_simples")
	private String obsPadraoOsSimples;
	
	@Field
	@Caption("Data Cadastro")
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getLimparBdAut() {
		return limparBdAut;
	}

	public void setLimparBdAut(Boolean limparBdAut) {
		this.limparBdAut = limparBdAut;
	}

	public Boolean getVendedorProduto() {
		return vendedorProduto;
	}

	public void setVendedorProduto(Boolean vendedorProduto) {
		this.vendedorProduto = vendedorProduto;
	}

	public Boolean getVendedorServico() {
		return vendedorServico;
	}

	public void setVendedorServico(Boolean vendedorServico) {
		this.vendedorServico = vendedorServico;
	}

	public Boolean getValorPagoPeca() {
		return valorPagoPeca;
	}

	public void setValorPagoPeca(Boolean valorPagoPeca) {
		this.valorPagoPeca = valorPagoPeca;
	}

	public Boolean getDescontoGeral() {
		return descontoGeral;
	}

	public void setDescontoGeral(Boolean descontoGeral) {
		this.descontoGeral = descontoGeral;
	}

	public Boolean getTecnicoProduto() {
		return tecnicoProduto;
	}

	public void setTecnicoProduto(Boolean tecnicoProduto) {
		this.tecnicoProduto = tecnicoProduto;
	}

	public Boolean getOrdemServicoSimples() {
		return ordemServicoSimples;
	}

	public void setOrdemServicoSimples(Boolean ordemServicoSimples) {
		this.ordemServicoSimples = ordemServicoSimples;
	}

	public Character getBaixarProdutoEstoque() {
		return baixarProdutoEstoque;
	}

	public void setBaixarProdutoEstoque(Character baixarProdutoEstoque) {
		this.baixarProdutoEstoque = baixarProdutoEstoque;
	}

	public Boolean getQtdDiasRevisao() {
		return qtdDiasRevisao;
	}

	public void setQtdDiasRevisao(Boolean qtdDiasRevisao) {
		this.qtdDiasRevisao = qtdDiasRevisao;
	}

	public Integer getQtdDiasPadrao() {
		return qtdDiasPadrao;
	}

	public void setQtdDiasPadrao(Integer qtdDiasPadrao) {
		this.qtdDiasPadrao = qtdDiasPadrao;
	}

	public Integer getMatricialTotalLinhas() {
		return matricialTotalLinhas;
	}

	public void setMatricialTotalLinhas(Integer matricialTotalLinhas) {
		this.matricialTotalLinhas = matricialTotalLinhas;
	}

	public Integer getMatricialEntrelinhas() {
		return matricialEntrelinhas;
	}

	public void setMatricialEntrelinhas(Integer matricialEntrelinhas) {
		this.matricialEntrelinhas = matricialEntrelinhas;
	}

	public String getObsDefeitoPadrao() {
		return obsDefeitoPadrao;
	}

	public void setObsDefeitoPadrao(String obsDefeitoPadrao) {
		this.obsDefeitoPadrao = obsDefeitoPadrao;
	}

	public String getObsPadrao() {
		return obsPadrao;
	}

	public void setObsPadrao(String obsPadrao) {
		this.obsPadrao = obsPadrao;
	}

	public String getObsPadraoOsSimples() {
		return obsPadraoOsSimples;
	}

	public void setObsPadraoOsSimples(String obsPadraoOsSimples) {
		this.obsPadraoOsSimples = obsPadraoOsSimples;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getVendedorAtendente() {
		return vendedorAtendente;
	}

	public void setVendedorAtendente(Boolean vendedorAtendente) {
		this.vendedorAtendente = vendedorAtendente;
	}

}
