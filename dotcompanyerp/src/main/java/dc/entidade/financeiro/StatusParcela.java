package dc.entidade.financeiro;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */
@Entity
@Table(name = "status_parcela")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class StatusParcela extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DESCRICAO")
	@Caption(value = "Descrição")
	@Field
	@NotNull(message = "Descrição é obrigatório") 
	private String descricao;
	
	@Lob
	@Type(type = "text")
	@Column(name = "PROCEDIMENTO")
	@Caption(value = "Procedimento")
	@Field
	//@NotNull(message = "Procedimento é obrigatório do ${this.descricao}.") 
	@Basic(fetch = javax.persistence.FetchType.LAZY) 
	private String procedimento;

	@Caption(value = "Situação")
	@Column(name = "SITUACAO")
	@Field
	@NotNull(message = "Situação é obrigatório") 
	private String situacao;
	
	public StatusParcela() {
	}

	public StatusParcela(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StatusParcela)) {
            return false;
        }

        StatusParcela that = (StatusParcela) obj;
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

	@Override
	public String toString() {
		return descricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
