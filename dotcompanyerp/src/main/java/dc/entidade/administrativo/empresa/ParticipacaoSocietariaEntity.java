package dc.entidade.administrativo.empresa;


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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.visao.administrativo.empresa.SocioFormView.DIRIGENTE;


@Entity
@Table(name = "socio_participacao_societaria")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
@XmlRootElement
public class ParticipacaoSocietariaEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "socio_participacao_societaria_id_seq")
	@SequenceGenerator(name = "socio_participacao_societaria_id_seq", sequenceName = "socio_participacao_societaria_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
		
	@Caption("cnpj")
	String cnpj;
	
	@Column(name="razao_social")
	String razaoSocial;
	
	@Column(name="data_entrada")
	@Temporal(TemporalType.DATE)
	Date dataEntrada;
	
	@Column(name="data_saida")
	@Temporal(TemporalType.DATE)
	Date dataSaida;
			
	@ManyToOne
	@JoinColumn(name="id_socio", referencedColumnName = "ID")
	private SocioEntity socio;
	
	BigDecimal participacao;

	String dirigente;//0-Não 1-Sim
	
	@Transient
	DIRIGENTE dirigenteEnum;
	
	@Transient
	String dirigenteStr;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public SocioEntity getSocio() {
		return socio;
	}

	public void setSocio(SocioEntity socio) {
	    	this.socio = socio;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public BigDecimal getParticipacao() {
		return participacao;
	}

	public void setParticipacao(BigDecimal participacao) {
		this.participacao = participacao;
	}

	public String getDirigente() {
		return dirigente;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public String getDirigenteStr() {
		return dirigenteStr;
	}

	public void setDirigenteStr(String dirigenteStr) {
		this.dirigenteStr = dirigenteStr;
	}

	public DIRIGENTE getDirigenteEnum() {
		return dirigenteEnum;
	}

	public void setDirigenteEnum(DIRIGENTE dirigenteEnum) {
		this.dirigenteEnum = dirigenteEnum;
	}

	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ParticipacaoSocietariaEntity)) {
            return false;
        }

        ParticipacaoSocietariaEntity that = (ParticipacaoSocietariaEntity) obj;
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
