package dc.entidade.suprimentos;

import java.util.Date;

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

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.suprimentos.estoque.NotaFiscal;

@Entity
@Table(name = "nfe_cupom_fiscal_referenciado")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CupomFiscalReferenciadoEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rje")
	@SequenceGenerator(name = "rje", sequenceName = "nfe_cupom_fiscal_referenciado_id_seq", allocationSize = 1)
	private Integer id;

	@Caption("Modelo")
	@Column(name = "modelo_documento_fiscal")
	String modelo;

	@Caption("Data de emissão")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao_cupom")
	Date dataEmissao;

	@Caption("COO")
	Integer coo;

	@Caption("N�mero Caixa")
	@Column(name = "numero_caixa")
	Integer numeroCaixa;

	@Caption("N�mero Série ECF")
	@Column(name = "numero_serie_ecf")
	String numeroSerieEcf;

	@ManyToOne
	@JoinColumn(name = "id_nfe_cabecalho")
	NotaFiscal notaFiscal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getCoo() {
		return coo;
	}

	public void setCoo(Integer coo) {
		this.coo = coo;
	}

	public Integer getNumeroCaixa() {
		return numeroCaixa;
	}

	public void setNumeroCaixa(Integer numeroCaixa) {
		this.numeroCaixa = numeroCaixa;
	}

	public String getNumeroSerieEcf() {
		return numeroSerieEcf;
	}

	public void setNumeroSerieEcf(String numeroSerieEcf) {
		this.numeroSerieEcf = numeroSerieEcf;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}