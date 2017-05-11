package dc.entidade.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "orcamento_fluxo_caixa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FluxoCaixaEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	    private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "ID")
	    private Integer id;
	    
	    @Field
	    @Caption("Nome")
	    @NotNull(message = "Nome é Obrigatório!")
	    @Column(name = "NOME")
	    private String nome;
	    
	    @Lob
	    @Field
	    @Caption("Descrição")
	    @Column(name = "DESCRICAO")
	    private String descricao;
	    
	    @Field(analyze=Analyze.NO)
		@FieldBridge(impl = DCDateBridge.class )
	    @Caption("Data Inicial")
	    @Column(name = "DATA_INICIAL")
	    @Temporal(TemporalType.DATE)
	    private Date dataInicial;
	    
	    @Field
	    @Caption("Número de Períodos")
	    @Column(name = "NUMERO_PERIODOS")
	    private Integer numeroPeriodos;
	    
	    @Field(analyze=Analyze.NO)
		@FieldBridge(impl = DCDateBridge.class )
	    @Caption("Data Base")
	    @Column(name = "DATA_BASE")
	    @Temporal(TemporalType.DATE)
	    private Date dataBase;
	    
	    @Caption("Fluxo Caixa de Período")
	    @JoinColumn(name = "id_orc_fluxo_caixa_periodo", referencedColumnName = "ID")
	    @ManyToOne(optional = false)
	    @NotNull(message = "Fluxo Caixa Período é Obrigatório")
	    private FluxoCaixaPeriodoEntity fluxoCaixaPeriodo;
	    
	    @OneToMany(mappedBy = "fluxoCaixa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		@Fetch(FetchMode.SUBSELECT)
		private List<FluxoCaixaDetalheEntity> fluxoCaixas = new ArrayList<>();

	    public FluxoCaixaEntity() {
	    }

	    public FluxoCaixaEntity(Integer id) {
	        this.id = id;
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

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public Date getDataInicial() {
	        return dataInicial;
	    }

	    public void setDataInicial(Date dataInicial) {
	        this.dataInicial = dataInicial;
	    }

	    public Integer getNumeroPeriodos() {
	        return numeroPeriodos;
	    }

	    public void setNumeroPeriodos(Integer numeroPeriodos) {
	        this.numeroPeriodos = numeroPeriodos;
	    }

	    public Date getDataBase() {
	        return dataBase;
	    }

	    public void setDataBase(Date dataBase) {
	        this.dataBase = dataBase;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }

	        if (!(obj instanceof FluxoCaixaEntity)) {
	            return false;
	        }

	        FluxoCaixaEntity that = (FluxoCaixaEntity) obj;
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
	    
	    public List<FluxoCaixaDetalheEntity> getFluxoCaixas() {
			return fluxoCaixas;
		}

		public void setFluxoCaixas(List<FluxoCaixaDetalheEntity> fluxoCaixas) {
			this.fluxoCaixas = fluxoCaixas;
		}

		public void removeFluxo(FluxoCaixaDetalheEntity value) {
			this.fluxoCaixas.remove(value);
			value.setFluxoCaixa(null);
		}
		
		public FluxoCaixaDetalheEntity addFluxo(FluxoCaixaDetalheEntity fluxo) {
			getFluxoCaixas().add(fluxo);
			fluxo.setFluxoCaixa(this);;

			return fluxo;
		}

	    @Override
	    public String toString() {
	        return nome;
	    }

	    /**
	     * @return the fluxoCaixaPeriodo
	     */
	    public FluxoCaixaPeriodoEntity getFluxoCaixaPeriodo() {
	        return fluxoCaixaPeriodo;
	    }

	    /**
	     * @param fluxoCaixaPeriodo the fluxoCaixaPeriodo to set
	     */
	    public void setFluxoCaixaPeriodo(FluxoCaixaPeriodoEntity fluxoCaixaPeriodo) {
	        this.fluxoCaixaPeriodo = fluxoCaixaPeriodo;
	    }

}
