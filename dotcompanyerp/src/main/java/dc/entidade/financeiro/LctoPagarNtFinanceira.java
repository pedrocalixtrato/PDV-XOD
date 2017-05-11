package dc.entidade.financeiro;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilLancamentoDetalhe;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "LCTO_PAGAR_NT_FINANCEIRA")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LctoPagarNtFinanceira extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INCLUSAO")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataInclusao;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Caption(value = "Lançamento à Pagar")
	@JoinColumn(name = "ID_LANCAMENTO_PAGAR", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private LancamentoPagarEntity lancamentoPagar;
	
	@JoinColumn(name = "ID_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private NaturezaFinanceira naturezaFinanceira;
	
	@JoinColumn(name = "ID_CONTABIL_LANCAMENTO_DET", referencedColumnName = "ID")
	@ManyToOne
	private ContabilLancamentoDetalhe contabilLancamentoDetalhe;

	public LctoPagarNtFinanceira() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LancamentoPagarEntity getLancamentoPagar() {
		return lancamentoPagar;
	}

	public void setLancamentoPagar(LancamentoPagarEntity lancamentoPagar) {
		this.lancamentoPagar = lancamentoPagar;
	}

	public NaturezaFinanceira getNaturezaFinanceira() {
		return naturezaFinanceira;
	}

	public void setNaturezaFinanceira(NaturezaFinanceira naturezaFinanceira) {
		this.naturezaFinanceira = naturezaFinanceira;
	}

	public ContabilLancamentoDetalhe getContabilLancamentoDetalhe() {
		return contabilLancamentoDetalhe;
	}

	public void setContabilLancamentoDetalhe(ContabilLancamentoDetalhe contabilLancamentoDetalhe) {
		this.contabilLancamentoDetalhe = contabilLancamentoDetalhe;
	}

	@Override
	public String toString() {
		return "com.t2tierp.financeiro.java.FinLctoPagarNtFinanceira[id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((lancamentoPagar == null) ? 0 : lancamentoPagar.hashCode());
		result = prime * result + ((naturezaFinanceira == null) ? 0 : naturezaFinanceira.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LctoPagarNtFinanceira other = (LctoPagarNtFinanceira) obj;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (lancamentoPagar == null) {
			if (other.lancamentoPagar != null)
				return false;
		} else if (!lancamentoPagar.equals(other.lancamentoPagar))
			return false;
		if (naturezaFinanceira == null) {
			if (other.naturezaFinanceira != null)
				return false;
		} else if (!naturezaFinanceira.equals(other.naturezaFinanceira))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}