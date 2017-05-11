package dc.entidade.tributario;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.geral.tabela.CsosnbEntity;
import dc.entidade.geral.tabela.CstIcmsbEntity;

@Entity
@Table(name = "tribut_icms_custom_det")
@Indexed
@XmlRootElement
@Analyzer(impl=BrazilianAnalyzer.class)
public class IcmsCustomizadoDetalheEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tribut_icms_custom_det_id_seq")
	@SequenceGenerator(name = "tribut_icms_custom_det_id_seq", sequenceName = "tribut_icms_custom_det_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@NotNull
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption(value = "Uf Destino")
	@Column(name="uf_destino")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufDestino;

	@ManyToOne
	@JoinColumn(name="cfop")
	private CfopEntity cfop;

	@Field
	@Caption(value="Csosn B")
	@Column(name="csosn_b")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String csosnB;
	
	@Transient
	private CsosnbEntity csosn;

	@Field
	@Caption(value="Cst B")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name="cst_b")
	private String cstB;
	
	@Transient
	private CstIcmsbEntity cst;

	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name="modalidade_bc")
	private String modalidadeBc;

	private Integer aliquota;

	@Column(name="valor_pauta")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPauta;

	@Column(name="valor_preco_maximo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorPrecoMaximo;

	@ManyToOne()
	@Caption("ICMS Customizado Cabeçalho")
	@JoinColumn(name="id_tribut_icms_custom_cab")
	private IcmsCustomizadoCabecalhoEntity icmsCustomizado;

	@Transient
	private UfEntity uf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUfDestino() {
		return ufDestino;
	}

	public void setUfDestino(String ufDestino) {
		this.ufDestino = ufDestino;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public IcmsCustomizadoCabecalhoEntity getIcmsCustomizado() {
		return icmsCustomizado;
	}

	public void setIcmsCustomizado(IcmsCustomizadoCabecalhoEntity icmsCustomizado) {
		this.icmsCustomizado = icmsCustomizado;
	}

	
	
	public CfopEntity getCfop() {
		return cfop;
	}

	public void setCfop(CfopEntity cfop) {
		this.cfop = cfop;
	}

	
	public String getCsosnB() {
		return csosnB;
	}

	public void setCsosnB(String csosnB) {
		this.csosnB = csosnB;
	}

	public String getCstB() {
		return cstB;
	}

	public void setCstB(String cstB) {
		this.cstB = cstB;
	}

	public String getModalidadeBc() {
		return modalidadeBc;
	}

	public void setModalidadeBc(String modalidadeBc) {
		this.modalidadeBc = modalidadeBc;
	}

	public Integer getAliquota() {
		return aliquota;
	}

	public void setAliquota(Integer aliquota) {
		this.aliquota = aliquota;
	}

	public BigDecimal getValorPauta() {
		return valorPauta;
	}

	public void setValorPauta(BigDecimal valorPauta) {
		this.valorPauta = valorPauta;
	}

	public BigDecimal getValorPrecoMaximo() {
		return valorPrecoMaximo;
	}

	public void setValorPrecoMaximo(BigDecimal valorPrecoMaximo) {
		this.valorPrecoMaximo = valorPrecoMaximo;
	}

	public CsosnbEntity getCsosn() {
		return csosn;
	}

	public void setCsosn(CsosnbEntity csosn) {
		this.csosn = csosn;
	}

	public CstIcmsbEntity getCst() {
		return cst;
	}

	public void setCst(CstIcmsbEntity cst) {
		this.cst = cst;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IcmsCustomizadoDetalheEntity)) {
            return false;
        }

        IcmsCustomizadoDetalheEntity that = (IcmsCustomizadoDetalheEntity) obj;
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
