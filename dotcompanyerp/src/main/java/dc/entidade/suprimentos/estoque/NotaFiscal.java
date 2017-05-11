package dc.entidade.suprimentos.estoque;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.CupomFiscalReferenciadoEntity;
import dc.entidade.suprimentos.NfeDuplicata;
import dc.entidade.suprimentos.NotaReferenciada;

@Entity
@Table(name = "nfe_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NotaFiscal extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_cabecalho_id_seq")
	@SequenceGenerator(name = "nfe_cabecalho_id_seq", sequenceName = "nfe_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	// ABA Dados da NF-E
	@Column(name = "status_nota")
	@Caption("Status")
	String status;

	@Column(name = "chave_acesso")
	@Caption("Chave de Acesso")
	String chaveAcesso;

	@Caption("Número")
	String numero;

	@Column(name = "codigo_modelo")
	@Caption("Modelo")
	String modelo;

	@Caption("Série")
	String serie;

	@Caption("Data de Emissão")
	@Column(name = "data_emissao")
	@Temporal(TemporalType.DATE)
	Date dataEmissao;

	@Caption("Data de Entrada Saida")
	@Column(name = "data_entrada_saida")
	@Temporal(TemporalType.DATE)
	Date dataEntradaSaida;

	@Caption("Hora de Entrada Saida")
	@Column(name = "hora_entrada_saida")
	String horaEntradaSaida;

	@Caption("Tipo de Operação")
	@Column(name = "tipo_operacao")
	String tipoOperacao;

	@Caption("Forma de Pagamento")
	@Column(name = "indicador_forma_pagamento")
	String formaPagamento;

	@Caption("Forma de Emissão")
	@Column(name = "tipo_emissao")
	String formaEmissao;

	@Caption("Finalidade de Emissão")
	@Column(name = "finalidade_emissao")
	String finalidadeEmissao;

	@Caption("Tipo de Impressão DANFE")
	@Column(name = "formato_impressao_danfe")
	String tipoImpressaoDanfe;

	@Caption("Natureza da Operacão")
	@Column(name = "natureza_operacao")
	String naturezaOperacao;

	@Caption("Base de Cálculo ICMS")
	@Column(name = "base_calculo_icms")
	BigDecimal baseCalculoIcms;

	@Caption("Valor do Cálculo ICMS")
	@Column(name = "valor_icms")
	BigDecimal valorIcms;

	@Caption("Base de Cálculo ICMS ST")
	@Column(name = "base_calculo_icms_st")
	BigDecimal baseCalculoIcmsSt;

	@Caption("Valor do Cálculo ICMS ST")
	@Column(name = "valor_icms_st")
	BigDecimal valorIcmsSt;

	@Caption("Valor do COFINS")
	@Column(name = "valor_cofins")
	BigDecimal valorCofins;

	@Caption("Valor do Frete")
	@Column(name = "valor_frete")
	BigDecimal valorFrete;

	@Caption("Valor do Seguro")
	@Column(name = "valor_seguro")
	BigDecimal valorSeguro;

	@Caption("Outras Desp.Acessórias")
	@Column(name = "valor_despesas_acessorias")
	BigDecimal outrasDespesasAcessorias;

	@Caption("Valor Total do PIS")
	@Column(name = "valor_pis")
	BigDecimal valorPis;

	@Caption("Descontos")
	@Column(name = "valor_desconto")
	BigDecimal valorDescontos;

	@Caption("Total Produtos")
	@Column(name = "valor_total_produtos")
	BigDecimal valorTotalProdutos;

	@Caption("Total Nota")
	@Column(name = "valor_total")
	BigDecimal valorTotalNota;

	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	List<NfeDuplicata> duplicatas = new ArrayList<NfeDuplicata>();

	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	List<CupomFiscalReferenciadoEntity> cuponsVinculados = new ArrayList<CupomFiscalReferenciadoEntity>();

	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
	List<NotaReferenciada> notasReferenciadas = new ArrayList<NotaReferenciada>();

	@Caption("Informações de Interesse do FISCO")
	@Column(name = "informacoes_add_fisco")
	String informacoesFisco;

	@Caption("Informações de Interesse do Contribuinte")
	@Column(name = "informacoes_add_contribuinte")
	String informacoesContribuinte;

	@Transient
	List<ProdutoEntity> produtos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<NfeDuplicata> getDuplicatas() {
		return duplicatas;
	}

	public void setDuplicatas(List<NfeDuplicata> duplicatas) {
		this.duplicatas = duplicatas;
	}

	public List<NotaReferenciada> getNotasReferenciadas() {
		return notasReferenciadas;
	}

	public void setNotasReferenciados(List<NotaReferenciada> notasReferenciadas) {
		this.notasReferenciadas = notasReferenciadas;
	}

	public List<CupomFiscalReferenciadoEntity> getCuponsVinculados() {
		return cuponsVinculados;
	}

	public void setCuponsVinculados(
			List<CupomFiscalReferenciadoEntity> cuponsVinculados) {
		this.cuponsVinculados = cuponsVinculados;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEntradaSaida() {
		return dataEntradaSaida;
	}

	public void setDataEntradaSaida(Date dataEntradaSaida) {
		this.dataEntradaSaida = dataEntradaSaida;
	}

	public String getHoraEntradaSaida() {
		return horaEntradaSaida;
	}

	public void setHoraEntradaSaida(String horaEntradaSaida) {
		this.horaEntradaSaida = horaEntradaSaida;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getFormaEmissao() {
		return formaEmissao;
	}

	public void setFormaEmissao(String formaEmissao) {
		this.formaEmissao = formaEmissao;
	}

	public String getFinalidadeEmissao() {
		return finalidadeEmissao;
	}

	public void setFinalidadeEmissao(String finalidadeEmissao) {
		this.finalidadeEmissao = finalidadeEmissao;
	}

	public String getTipoImpressaoDanfe() {
		return tipoImpressaoDanfe;
	}

	public void setTipoImpressaoDanfe(String tipoImpressaoDanfe) {
		this.tipoImpressaoDanfe = tipoImpressaoDanfe;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public BigDecimal getBaseCalculoIcmsSt() {
		return baseCalculoIcmsSt;
	}

	public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
		this.baseCalculoIcmsSt = baseCalculoIcmsSt;
	}

	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}

	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public BigDecimal getOutrasDespesasAcessorias() {
		return outrasDespesasAcessorias;
	}

	public void setOutrasDespesasAcessorias(BigDecimal outrasDespesasAcessorias) {
		this.outrasDespesasAcessorias = outrasDespesasAcessorias;
	}

	public BigDecimal getValorPis() {
		return valorPis;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = valorPis;
	}

	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}

	public void setValorDescontos(BigDecimal valorDescontos) {
		this.valorDescontos = valorDescontos;
	}

	public BigDecimal getValorTotalProdutos() {
		return valorTotalProdutos;

	}

	public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
		this.valorTotalProdutos = valorTotalProdutos;
	}

	public BigDecimal getValorTotalNota() {
		return valorTotalNota;
	}

	public void setValorTotalNota(BigDecimal valorTotalNota) {
		this.valorTotalNota = valorTotalNota;
	}

	public String getInformacoesFisco() {
		return informacoesFisco;
	}

	public void setInformacoesFisco(String informacoesFisco) {
		this.informacoesFisco = informacoesFisco;
	}

	public String getInformacoesContribuinte() {
		return informacoesContribuinte;
	}

	public void setInformacoesContribuinte(String informacoesContribuinte) {
		this.informacoesContribuinte = informacoesContribuinte;
	}

	public List<ProdutoEntity> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoEntity> produtos) {
		this.produtos = produtos;
	}

	public NfeDuplicata adicionarDuplicata(NfeDuplicata d) {
		getDuplicatas().add(d);
		d.setNotaFiscal(this);
		return d;
	}

	public CupomFiscalReferenciadoEntity adicionarCupom(
			CupomFiscalReferenciadoEntity cupom) {
		getCuponsVinculados().add(cupom);
		cupom.setNotaFiscal(this);
		return cupom;
	}

	public ProdutoEntity adicionarProduto(ProdutoEntity p) {
		getProdutos().add(p);
		return p;
	}

	public NotaReferenciada adicionarNota(NotaReferenciada notaReferenciada) {
		getNotasReferenciadas().add(notaReferenciada);
		notaReferenciada.setNotaFiscal(this);
		return notaReferenciada;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}