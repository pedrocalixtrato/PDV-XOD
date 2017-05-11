package dc.entidade.suprimentos.estoque;

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
import javax.persistence.Lob;
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
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.nfe.NfeDetalheEntity;

@Entity
@Table(name = "LOTE_PRODUTO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LoteProdutoEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "ID")
	    private Integer id;
	    
	    @Field
		@Caption(value = "Código")
	    @Column(name = "CODIGO")
	    private String codigo;
	    
	    @Field
		@Caption(value = "Nome")
	    @Column(name = "NOME")
	    @NotNull(message = "Nome é Obrigatório!")
	    private String nome;
	    
	    @Field
		@Caption(value = "Descrição")
	    @Column(name = "DESCRICAO")
	    private String descricao;
	    
	    @Field
		@Caption(value = "Data Cadastro")
	    @Column(name = "DATA_CADASTRO")
	    @Temporal(TemporalType.DATE)
	    @NotNull(message = "Data Cadastro é Obrigatório!")
	    private Date dataCadastro;
	    
	    @Field
		@Caption(value = "Data Compra")
	    @Column(name = "DATA_COMPRA")
	    @Temporal(TemporalType.DATE)
	    private Date dataCompra;
	    
	    @Field
		@Caption(value = "Data Fabricação")
	    @Column(name = "DATA_FABRICACAO")
	    @Temporal(TemporalType.DATE)
	    private Date dataFabricacao;
	    
	    @Field
		@Caption(value = "Data Vencimento")
	    @Column(name = "DATA_VENCIMENTO")
	    @Temporal(TemporalType.DATE)
	    private Date dataVencimento;
	    
	    @Lob
	    @Field
		@Caption(value = "Observação")
	    @Column(name = "OBSERVACAO")
	    private String observacao;
	    
	    @OneToMany(mappedBy = "loteProduto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	   // @OneToMany(mappedBy="loteProduto", fetch = FetchType.LAZY)
		@Fetch(FetchMode.SUBSELECT)
		private List<NfeDetalheEntity> NfeDetalhe = new ArrayList<>();

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(String codigo) {
	        this.codigo = codigo;
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

	    public Date getDataCadastro() {
	        return dataCadastro;
	    }

	    public void setDataCadastro(Date dataCadastro) {
	        this.dataCadastro = dataCadastro;
	    }

	    public Date getDataCompra() {
	        return dataCompra;
	    }

	    public void setDataCompra(Date dataCompra) {
	        this.dataCompra = dataCompra;
	    }

	    public Date getDataFabricacao() {
	        return dataFabricacao;
	    }

	    public void setDataFabricacao(Date dataFabricacao) {
	        this.dataFabricacao = dataFabricacao;
	    }

	    public Date getDataVencimento() {
	        return dataVencimento;
	    }

	    public void setDataVencimento(Date dataVencimento) {
	        this.dataVencimento = dataVencimento;
	    }

	    public String getObservacao() {
	        return observacao;
	    }

	    public void setObservacao(String observacao) {
	        this.observacao = observacao;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }

	        if (!(obj instanceof LoteProdutoEntity)) {
	            return false;
	        }

	        LoteProdutoEntity that = (LoteProdutoEntity) obj;
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
	    	return nome;
	    }
	    
	    public List<NfeDetalheEntity> getNfeDetalhe() {
			return NfeDetalhe;
		}

		public void setNfeDetalhe(List<NfeDetalheEntity> nfeDetalhe) {
			NfeDetalhe = nfeDetalhe;
		}

		public void removeNfe(NfeDetalheEntity value) {
	    	value.setLoteProduto(null);
			this.NfeDetalhe.remove(value);
			
		}

		public void addParcelaPagar(NfeDetalheEntity nfe) {
			nfe.setLoteProduto(this);
			this.NfeDetalhe.add(nfe);
		}


}
