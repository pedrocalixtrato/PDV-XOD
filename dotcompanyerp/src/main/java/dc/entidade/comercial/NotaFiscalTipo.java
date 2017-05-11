package dc.entidade.comercial;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "nota_fiscal_tipo")
@Indexed
@XmlRootElement
@Analyzer(impl=BrazilianAnalyzer.class)
public class NotaFiscalTipo extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_tipo_id_seq")
	@SequenceGenerator(name = "nota_fiscal_tipo_id_seq", sequenceName = "nota_fiscal_tipo_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Caption("Série")
	String serie;
	
	@Caption("Série Scan")
	String serieScan;
	
	@Caption("Nome")
	@NotNull(message = "Nome é Obrigatório!")
	String nome;
	
	@Caption("Descrição")
	String descricao;
	
	@Column(name="ultimo_numero")
	Integer ultimoNumero;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSerieScan() {
		return serieScan;
	}

	public void setSerieScan(String serieScan) {
		this.serieScan = serieScan;
	}

	public Integer getUltimoNumero() {
		return ultimoNumero;
	}

	public void setUltimoNumero(Integer ultimoNumero) {
		this.ultimoNumero = ultimoNumero;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	   public boolean equals(Object obj) {
	       if (this == obj) {
	           return true;
	       }

	       if (!(obj instanceof NotaFiscalTipo)) {
	           return false;
	       }

	       NotaFiscalTipo that = (NotaFiscalTipo) obj;
	       EqualsBuilder eb = new EqualsBuilder();
	       eb.append(getId(), that.getId());
	       return eb.isEquals();
	   }

	   @Override
	   public int hashCode() {
	       if (getId() == null) {
	           return super.hashCode();
	       } else {
	           return new HashCodeBuilder()
	                   .append(id)
	                   .toHashCode();
	       }
	   }
	
	
}
