package dc.entidade.administrativo.empresa;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;


@Entity
@Table(name = "socio_dependente")
@Indexed
@XmlRootElement
@Analyzer(impl=BrazilianAnalyzer.class)
public class DependenteEntity extends AbstractMultiEmpresaModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "socio_dependente_id_seq")
	@SequenceGenerator(name = "socio_dependente_id_seq", sequenceName = "socio_dependente_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_relacionamento", referencedColumnName = "ID")
	private TipoRelacionamentoEntity tipoRelacionamento;
	
	@Caption("nome")
	String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	Date dataNascimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio_depedencia")
	Date dataInicioDependencia;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_fim_dependencia")
	Date dataFimDependencia;
	
	String cpf;
	
	@ManyToOne
	@JoinColumn(name="id_socio", referencedColumnName = "ID")
	private SocioEntity socio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoRelacionamentoEntity getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(TipoRelacionamentoEntity tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataInicioDependencia() {
		return dataInicioDependencia;
	}

	public void setDataInicioDependencia(Date dataInicioDependencia) {
		this.dataInicioDependencia = dataInicioDependencia;
	}

	public Date getDataFimDependencia() {
		return dataFimDependencia;
	}

	public void setDataFimDependencia(Date dataFimDependencia) {
		this.dataFimDependencia = dataFimDependencia;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public SocioEntity getSocio() {
		return socio;
	}

	public void setSocio(SocioEntity socio) {
		this.socio = socio;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DependenteEntity)) {
            return false;
        }

        DependenteEntity that = (DependenteEntity) obj;
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
