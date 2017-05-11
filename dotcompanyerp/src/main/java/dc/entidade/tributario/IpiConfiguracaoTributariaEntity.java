package dc.entidade.tributario;


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

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "tribut_ipi_dipi")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class IpiConfiguracaoTributariaEntity extends AbstractMultiEmpresaModel<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ipi")
	@SequenceGenerator(name = "ipi", sequenceName = "tribut_ipi_dipi_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name="cst_ipi")
	String cst;
	
	@Column(name="modalidade_base_calculo")
	String modalidadeBc;
	
	@Column(name="porcento_base_calculo")
	BigDecimal porcentoBc;
	
	@Column(name="aliquota_porcento")
	BigDecimal aliquotaPorcento;
	
	@Column(name="aliquota_unidade")
	BigDecimal aliquotaUnidade;
	
	@Column(name="valor_preco_maximo")
	BigDecimal valorPrecoMaximo;
	
	@Column(name="valor_pauta_fiscal")
	BigDecimal valorPautaFiscal;
		
	@Column(name="id_tipo_receita_dipi")
	Integer tipoReceitaDipi; 
		
	@ManyToOne
	@JoinColumn(name="id_tribut_configura_of_gt")
	ConfiguracaoTributariaEntity configuracaoTributaria;
	
	/*@ManyToOne
	@JoinColumn(name="id_empresa")
	Empresa empresa;*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public ConfiguracaoTributariaEntity getConfiguracaoTributaria() {
		return configuracaoTributaria;
	}

	public void setConfiguracaoTributaria(
			ConfiguracaoTributariaEntity configuracaoTributaria) {
		this.configuracaoTributaria = configuracaoTributaria;
	}

	public String getModalidadeBc() {
		return modalidadeBc;
	}

	public void setModalidadeBc(String modalidadeBc) {
		this.modalidadeBc = modalidadeBc;
	}

	public BigDecimal getPorcentoBc() {
		return porcentoBc;
	}

	public void setPorcentoBc(BigDecimal porcentoBc) {
		this.porcentoBc = porcentoBc;
	}

	public BigDecimal getAliquotaPorcento() {
		return aliquotaPorcento;
	}

	public void setAliquotaPorcento(BigDecimal aliquotaPorcento) {
		this.aliquotaPorcento = aliquotaPorcento;
	}

	public BigDecimal getAliquotaUnidade() {
		return aliquotaUnidade;
	}

	public void setAliquotaUnidade(BigDecimal aliquotaUnidade) {
		this.aliquotaUnidade = aliquotaUnidade;
	}

	public BigDecimal getValorPrecoMaximo() {
		return valorPrecoMaximo;
	}

	public void setValorPrecoMaximo(BigDecimal valorPrecoMaximo) {
		this.valorPrecoMaximo = valorPrecoMaximo;
	}

	public BigDecimal getValorPautaFiscal() {
		return valorPautaFiscal;
	}

	public void setValorPautaFiscal(BigDecimal valorPautaFiscal) {
		this.valorPautaFiscal = valorPautaFiscal;
	}
	

	public Integer getTipoReceitaDipi() {
		return tipoReceitaDipi;
	}

	public void setTipoReceitaDipi(Integer tipoReceitaDipi) {
		this.tipoReceitaDipi = tipoReceitaDipi;
	}

	/*public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}*/
}

