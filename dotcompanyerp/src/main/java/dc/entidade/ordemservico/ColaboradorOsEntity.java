package dc.entidade.ordemservico;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.PessoaEntity;

@Entity
@Table(name = "os_colaborador")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ColaboradorOsEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Filial")
	@Column(name = "filial")
	private Integer filial;

	@Field
	@Caption("Nome")
	@Column(name = "nome")
	private String nome;

	@JoinColumn(name = "id_pessoa", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Analyzer(definition = "dc_combo_analyzer")
	@IndexedEmbedded
	@ComboValue
	private PessoaEntity pessoa;

	@Caption("Tipo colaborador")
	@ManyToOne
	@JoinColumn(name = "id_tipo_colaborador", referencedColumnName = "id")
	private TipoColaboradorOsEntity tipoColaboradorOs;

	@Field
	@Caption("Data admissao")
	@Column(name = "data_admissao")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataAdmissao;

	@Column(name = "data_demissao")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataDemissao;

	@Field
	@Caption("Apelido")
	@Column(name = "apelido")
	private String apelido;

	@Field
	@Caption("Tipo sangue")
	@Column(name = "tipo_sangue")
	private String tipoSangue;

	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataNascimento;

	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@Column(name = "rg_insc_estadual")
	private String rgInscEstadual;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "celular")
	private String celular;

	@Column(name = "cep")
	private String cep;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "email")
	private String email;

	@Column(name = "salario_fixo")
	private BigDecimal salarioFixo;

	@Column(name = "tipo_comissao_servico")
	private String tipoComissaoServico;

	@Column(name = "valor_comissao_servico")
	private BigDecimal valorComissaoServico;

	@Column(name = "tipo_comissao_produto")
	private String tipoComissaoProduto;

	@Column(name = "valor_comissao_produto")
	private BigDecimal valorComissaoProduto;

	@Column(name = "priorizar_comissao")
	private Boolean priorizarComissao;

	@Column(name = "comissao_over")
	private Boolean comissaoOver;

	@Column(name = "pgto_comissao_sera")
	private Integer pgtoComissaoSera;

	@Column(name = "lcto_comissao")
	private Integer lctoComissao;

	@ManyToOne
	@JoinColumn(name = "id_plano_conta", referencedColumnName = "id")
	private PlanoContaEntity planoConta;

	@ManyToOne
	@JoinColumn(name = "id_conta_caixa", referencedColumnName = "id")
	private ContaCaixa contaCaixa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFilial() {
		return filial;
	}

	public void setFilial(Integer filial) {
		this.filial = filial;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public TipoColaboradorOsEntity getTipoColaboradorOs() {
		return tipoColaboradorOs;
	}

	public void setTipoColaboradorOs(TipoColaboradorOsEntity tipoColaboradorOs) {
		this.tipoColaboradorOs = tipoColaboradorOs;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRgInscEstadual() {
		return rgInscEstadual;
	}

	public void setRgInscEstadual(String rgInscEstadual) {
		this.rgInscEstadual = rgInscEstadual;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getSalarioFixo() {
		return salarioFixo;
	}

	public void setSalarioFixo(BigDecimal salarioFixo) {
		this.salarioFixo = salarioFixo;
	}

	public String getTipoComissaoServico() {
		return tipoComissaoServico;
	}

	public void setTipoComissaoServico(String tipoComissaoServico) {
		this.tipoComissaoServico = tipoComissaoServico;
	}

	public BigDecimal getValorComissaoServico() {
		return valorComissaoServico;
	}

	public void setValorComissaoServico(BigDecimal valorComissaoServico) {
		this.valorComissaoServico = valorComissaoServico;
	}

	public String getTipoComissaoProduto() {
		return tipoComissaoProduto;
	}

	public void setTipoComissaoProduto(String tipoComissaoProduto) {
		this.tipoComissaoProduto = tipoComissaoProduto;
	}

	public BigDecimal getValorComissaoProduto() {
		return valorComissaoProduto;
	}

	public void setValorComissaoProduto(BigDecimal valorComissaoProduto) {
		this.valorComissaoProduto = valorComissaoProduto;
	}

	public Boolean getPriorizarComissao() {
		return priorizarComissao;
	}

	public void setPriorizarComissao(Boolean priorizarComissao) {
		this.priorizarComissao = priorizarComissao;
	}

	public Boolean getComissaoOver() {
		return comissaoOver;
	}

	public void setComissaoOver(Boolean comissaoOver) {
		this.comissaoOver = comissaoOver;
	}

	public Integer getPgtoComissaoSera() {
		return pgtoComissaoSera;
	}

	public void setPgtoComissaoSera(Integer pgtoComissaoSera) {
		this.pgtoComissaoSera = pgtoComissaoSera;
	}

	public Integer getLctoComissao() {
		return lctoComissao;
	}

	public void setLctoComissao(Integer lctoComissao) {
		this.lctoComissao = lctoComissao;
	}

	public PlanoContaEntity getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoContaEntity planoConta) {
		this.planoConta = planoConta;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

}
