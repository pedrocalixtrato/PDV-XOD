package dc.entidade.financeiro;

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

import org.apache.commons.lang.builder.ToStringBuilder;

import dc.entidade.contabilidade.ContabilLancamentoDetalhe;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "LCTO_RECEBER_NT_FINANCEIRA")
public class LctoReceberNtFinanceiraEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@JoinColumn(name = "ID_LANCAMENTO_RECEBER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private LancamentoReceber lancamentoReceber;
	
	@JoinColumn(name = "ID_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private NaturezaFinanceira naturezaFinanceira;
	
	@JoinColumn(name = "ID_CONTABIL_LANCAMENTO_DET", referencedColumnName = "ID")
	@ManyToOne
	private ContabilLancamentoDetalhe contabilLancamentoDetalhe;

	public LctoReceberNtFinanceiraEntity() {
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

	public LancamentoReceber getLancamentoReceber() {
		return lancamentoReceber;
	}

	public void setLancamentoReceber(LancamentoReceber finLancamentoReceber) {
		this.lancamentoReceber = finLancamentoReceber;
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
		//return "com.t2tierp.financeiro.java.LctoReceberNtFinanceiraEntity[id=" + id + "]";
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((lancamentoReceber == null) ? 0 : lancamentoReceber.hashCode());
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
		LctoReceberNtFinanceiraEntity other = (LctoReceberNtFinanceiraEntity) obj;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (lancamentoReceber == null) {
			if (other.lancamentoReceber != null)
				return false;
		} else if (!lancamentoReceber.equals(other.lancamentoReceber))
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
