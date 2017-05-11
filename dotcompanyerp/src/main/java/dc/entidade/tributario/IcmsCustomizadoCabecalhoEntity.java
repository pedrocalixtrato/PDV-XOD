package dc.entidade.tributario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.OrigemMercadoriaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.produto.ProdutoEntity;

@Entity
@Table(name = "tribut_icms_custom_cab")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IcmsCustomizadoCabecalhoEntity extends	AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tribut_icms_custom_cab_id_seq")
	@SequenceGenerator(name = "tribut_icms_custom_cab_id_seq", sequenceName = "tribut_icms_custom_cab_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@NotNull
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Descrição")
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Descrição é Obrigatório!")
	private String descricao = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Origem da mercadoria")
	@Column(name = "origem_mercadoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Origem Mercadoria é Obrigatório!")
	private OrigemMercadoriaEn origemMercadoria;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "icmsCustomizado", fetch = FetchType.LAZY)
	private List<IcmsCustomizadoDetalheEntity> detalhes = new ArrayList<IcmsCustomizadoDetalheEntity>();
	
	@Fetch(FetchMode.SUBSELECT)
	//@OneToMany(mappedBy = "icmsCustomizado", fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "icmsCustomizado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProdutoEntity> produtoList = new ArrayList<ProdutoEntity>();

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public IcmsCustomizadoCabecalhoEntity() {
		// TODO Auto-generated constructor stub
	}

	@Override
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

	public OrigemMercadoriaEn getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(OrigemMercadoriaEn origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public List<IcmsCustomizadoDetalheEntity> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<IcmsCustomizadoDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public void adicionarDetalhe(IcmsCustomizadoDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setIcmsCustomizado(this);
	}
	
	public void removeDetalhe(IcmsCustomizadoDetalheEntity value) {
		this.detalhes.remove(value);
		value.setIcmsCustomizado(null);
	}

	public List<ProdutoEntity> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<ProdutoEntity> produtoList) {
		this.produtoList = produtoList;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IcmsCustomizadoCabecalhoEntity)) {
            return false;
        }

        IcmsCustomizadoCabecalhoEntity that = (IcmsCustomizadoCabecalhoEntity) obj;
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

}