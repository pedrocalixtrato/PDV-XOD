package dc.entidade.administrativo.empresa;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "quadro_societario")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class QuadroSocietarioEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "qdr")
	@SequenceGenerator(name = "qdr", sequenceName = "quadro_societario_id_seq", allocationSize = 1)
	@ComboCode
	@ComboValue
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_registro")
	@Caption("Data de Registro")
	@NotNull(message = "Data Registro é Obrigatório!")
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	private Date dataRegistro;
	
	@Field
	@Column(name="capital_social")
	@Caption("Capital Social")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal capitalSocial;
	
	@Field
	@Column(name="valor_quota")
	@Caption("Valor Quota")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorQuota;
	
	@Field
	@Column(name="quantidade_cotas")
	@Caption("Quantidade Cotas")
	@NotNull(message = "Quantidade de Cotas é Obrigatório!")
	private Integer quantidadeCotas;
	
//	@ManyToOne
//	@JoinColumn(name = "ID_EMPRESA", nullable = false)
//	private Empresa empresa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public BigDecimal getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(BigDecimal capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public BigDecimal getValorQuota() {
		return valorQuota;
	}

	public void setValorQuota(BigDecimal valorQuota) {
		this.valorQuota = valorQuota;
	}

	public Integer getQuantidadeCotas() {
		return quantidadeCotas;
	}

	public void setQuantidadeCotas(Integer quantidadeCotas) {
		this.quantidadeCotas = quantidadeCotas;
	}

	@Override
	public String toString() {
		return id.toString();
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}
	
	

	
	
	
}
