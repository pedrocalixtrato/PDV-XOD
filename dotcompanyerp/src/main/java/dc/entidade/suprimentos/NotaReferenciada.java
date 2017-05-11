package dc.entidade.suprimentos;

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

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.suprimentos.estoque.NotaFiscal;

@Entity
@Table(name = "nfe_nf_referenciada")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NotaReferenciada extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Caption("Id")
	// private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rje")
	@SequenceGenerator(name = "rje", sequenceName = "nfe_nf_referenciada_id_seq", allocationSize = 1)
	private Integer id;

	@Caption("Código UF")
	@Column(name = "codigo_uf")
	Integer uf;

	@Caption("Ano/Mês")
	@Column(name = "ano_mes")
	Integer anoMes;

	@Caption("CNPJ")
	String cnpj;

	@Caption("Modelo")
	String modelo;

	@Caption("Série")
	String serie;

	@Caption("N�mero NF")
	@Column(name = "numero_nf")
	String numero;

	@ManyToOne
	@JoinColumn(name = "id_nfe_cabecalho")
	NotaFiscal notaFiscal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUf() {
		return uf;
	}

	public void setUf(Integer uf) {
		this.uf = uf;
	}

	public Integer getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}