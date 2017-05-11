package dc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.PessoaEntity;

@Entity
@Table(name = "CHEQUE_RECEBIDO")
public class ChequeRecebido extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "CPF_CNPJ")
	private String cpfCnpj;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "CODIGO_BANCO")
	private String codigoBanco;
	@Column(name = "CODIGO_AGENCIA")
	private String codigoAgencia;
	@Column(name = "CONTA")
	private String conta;
	@Column(name = "NUMERO")
	private Integer numero;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EMISSAO")
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	@Column(name = "BOM_PARA")
	private Date bomPara;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_COMPENSACAO")
	private Date dataCompensacao;
	@Column(name = "VALOR")
	private BigDecimal valor;
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne
	private PessoaEntity pessoa;
	@Transient
	private String historico;
	@Transient
	private ContaCaixa contaCaixa;

	public ChequeRecebido() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getBomPara() {
		return bomPara;
	}

	public void setBomPara(Date bomPara) {
		this.bomPara = bomPara;
	}

	public Date getDataCompensacao() {
		return dataCompensacao;
	}

	public void setDataCompensacao(Date dataCompensacao) {
		this.dataCompensacao = dataCompensacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.ChequeRecebido[id=" + id + "]";
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

}
