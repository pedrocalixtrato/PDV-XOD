package dc.entidade.geral.ged;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "GED_TIPO_DOCUMENTO")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class TipoDocumento extends AbstractMultiEmpresaModel<Integer> implements Serializable{
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "ID")
	    private Integer id;
	    
	    @Column(name = "NOME")
	    @Analyzer(definition= "dc_combo_analyzer")
	    @Field
	    @Caption("Nome")
	    private String nome;
	    
	    @Field
	    @Column(name = "TAMANHO_MAXIMO")
	    @Caption("Tamanho Maximo")
	    private BigDecimal tamanhoMaximo;

	    public TipoDocumento() {
	    }

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

	    public BigDecimal getTamanhoMaximo() {
	        return tamanhoMaximo;
	    }

	    public void setTamanhoMaximo(BigDecimal tamanhoMaximo) {
	        this.tamanhoMaximo = tamanhoMaximo;
	    }
	    
	    @Override
	    public String toString() {
	    	return this.nome;
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
			result = prime * result
					+ ((tamanhoMaximo == null) ? 0 : tamanhoMaximo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TipoDocumento other = (TipoDocumento) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (tamanhoMaximo == null) {
				if (other.tamanhoMaximo != null)
					return false;
			} else if (!tamanhoMaximo.equals(other.tamanhoMaximo))
				return false;
			return true;
		}
	    
}
