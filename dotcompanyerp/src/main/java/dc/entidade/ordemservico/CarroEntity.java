package dc.entidade.ordemservico;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;

@Entity
@Table(name = "os_carro")
@Indexed
@XmlRootElement
@Analyzer(impl=BrazilianAnalyzer.class)
public class CarroEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("Placa")
	@Column(name = "placa")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@NotNull(message = "Placa é Obrigatório!")
	private String placa;

	@Field
	@Caption("Data Cadastro")
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	@Generated(GenerationTime.INSERT)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Caption("Motorização")
	@Column(name = "motorizacao")
	private String motorizacao;

	@Field
	@Caption("Nº Chassis")
	@Column(name = "chassi")
	private String chassi;
	
	@Field
	@Caption("Ano")
	@Column(name = "ano")
	@NotNull(message = "Ano é Obrigatório!")
	private Integer ano;

	@Caption("Cliente")
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@NotNull(message = "Cliente é Obrigatório!")
	private ClienteEntity cliente;

	@Caption("Marca")
	@JoinColumn(name = "id_marca", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@NotNull(message = "Marca é Obrigatório!")
	private MarcaOsEntity marca;

	@Caption("Cor")
	@JoinColumn(name = "id_cor", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@NotNull(message = "Cor é Obrigatório")
	private CorEntity cor;
 
	@Caption("Modelo")
	@JoinColumn(name = "id_modelo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@NotNull(message = "Modelo é Obrigatório!")
	private ModeloOsEntity modelo;

	@Caption("Combustível")
	@JoinColumn(name = "id_combustivel", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@NotNull(message = "Combustível é Obrigatório!")
	private CombustivelEntity combustivel;

	@Field 
	@Caption("Observacao")
	@Lob
	@Column(name = "observacao")
	private String observacao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public MarcaOsEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaOsEntity marca) {
		this.marca = marca;
	}

	public CorEntity getCor() {
		return cor;
	}

	public void setCor(CorEntity cor) {
		this.cor = cor;
	}

	public ModeloOsEntity getModelo() {
		return modelo;
	}

	public void setModelo(ModeloOsEntity modelo) {
		this.modelo = modelo;
	}

	public String getMotorizacao() {
		return motorizacao;
	}

	public void setMotorizacao(String motorizacao) {
		this.motorizacao = motorizacao;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public CombustivelEntity getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(CombustivelEntity combustivel) {
		this.combustivel = combustivel;
	}

	/*public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}*/

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		
		return placa;
		
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CarroEntity)) {
            return false;
        }

        CarroEntity that = (CarroEntity) obj;
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
