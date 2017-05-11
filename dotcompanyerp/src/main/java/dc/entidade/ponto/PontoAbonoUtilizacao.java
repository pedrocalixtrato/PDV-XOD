package dc.entidade.ponto;

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

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "PONTO_ABONO_UTILIZACAO")
public class PontoAbonoUtilizacao  extends AbstractMultiEmpresaModel<Integer>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_UTILIZACAO")
	private Date dataUtilizacao;
	@Column(name = "OBSERVACAO")
	private String observacao;
	@JoinColumn(name = "ID_PONTO_ABONO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private PontoAbono pontoAbono;

	public PontoAbonoUtilizacao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataUtilizacao() {
		return dataUtilizacao;
	}

	public void setDataUtilizacao(Date dataUtilizacao) {
		this.dataUtilizacao = dataUtilizacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PontoAbono getPontoAbono() {
		return pontoAbono;
	}

	public void setPontoAbono(PontoAbono pontoAbono) {
		this.pontoAbono = pontoAbono;
	}

	@Override
	public String toString() {
		return "com.t2tierp.ponto.java.PontoAbonoUtilizacao[id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataUtilizacao == null) ? 0 : dataUtilizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
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
		PontoAbonoUtilizacao other = (PontoAbonoUtilizacao) obj;
		if (dataUtilizacao == null) {
			if (other.dataUtilizacao != null)
				return false;
		} else if (!dataUtilizacao.equals(other.dataUtilizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		return true;
	}

}
