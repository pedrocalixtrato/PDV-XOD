package dc.entidade.comercial;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
import dc.entidade.geral.pessoal.TransportadoraEntity;

@Entity
@Table(name = "venda_frete")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Frete extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frt")
	@SequenceGenerator(name = "frt", sequenceName = "venda_frete_id_seq", allocationSize = 1)
	private Integer id;
	
	@Field
	@Caption("Conhecimento")
	@Column(name = "CONHECIMENTO")
	private Integer conhecimento;
	
    @Field
    @Caption("Responsável")
    @Column(name = "RESPONSAVEL")
    @NotNull(message = "Responsável é Obrigatório!")
	private String responsavel;
	
    @Field
    @Caption("Placa")
    @Column(name = "PLACA")
    @NotNull(message = "Placa é Obrigatório!")
	private String placa;
	
	@Column(name="uf_placa")
	private String ufPlaca;
	
	@Column(name="selo_fiscal")
	private Integer seloFiscal;
	
	@Column(name="quantidade_volume")
	private BigDecimal quantidadeVolume;
	
	@Column(name="marca_volume")
	private String marcaVolume;
	
	@Column(name="especie_volume")
	private String especieVolume;
	
	@Column(name="peso_bruto")
	private BigDecimal pesoBruto;
	
	@Column(name="peso_liquido")
	private BigDecimal pesoLiquido;
	
    @Caption("Venda")
    @JoinColumn(name = "ID_VENDA_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @NotNull(message = "Venda é Obrigatório!")
    private Venda vendaCabecalho;
    
    @Caption("Transportadora")
    @JoinColumn(name = "ID_TRANSPORTADORA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TransportadoraEntity transportadora;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TransportadoraEntity getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(TransportadoraEntity transportadora) {
		this.transportadora = transportadora;
	}
	
	public Venda getVendaCabecalho() {
		return vendaCabecalho;
	}

	public void setVendaCabecalho(Venda vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	public Integer getConhecimento() {
		return conhecimento;
	}

	public void setConhecimento(Integer conhecimento) {
		this.conhecimento = conhecimento;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUfPlaca() {
		return ufPlaca;
	}

	public void setUfPlaca(String ufPlaca) {
		this.ufPlaca = ufPlaca;
	}

	public Integer getSeloFiscal() {
		return seloFiscal;
	}

	public void setSeloFiscal(Integer seloFiscal) {
		this.seloFiscal = seloFiscal;
	}

	public BigDecimal getQuantidadeVolume() {
		return quantidadeVolume;
	}

	public void setQuantidadeVolume(BigDecimal quantidadeVolume) {
		this.quantidadeVolume = quantidadeVolume;
	}

	public String getMarcaVolume() {
		return marcaVolume;
	}

	public void setMarcaVolume(String marcaVolume) {
		this.marcaVolume = marcaVolume;
	}

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(BigDecimal pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public String getEspecieVolume() {
		return especieVolume;
	}

	public void setEspecieVolume(String especieVolume) {
		this.especieVolume = especieVolume;
	}
	
	@Override
	public String toString() {
		return responsavel;
	}

//@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this);
//	}

   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       }

       if (!(obj instanceof Frete)) {
           return false;
       }

       Frete that = (Frete) obj;
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
