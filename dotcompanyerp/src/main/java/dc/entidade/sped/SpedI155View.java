package dc.entidade.sped;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

/**
 * 
 * 
 */

@Entity
@Table(name = "view_sped_i155")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SpedI155View implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "MES_ANO")
	private String mesAno;

	@Basic(optional = false)
	@Column(name = "ID_CONTABIL_CONTA")
	private int idContabilConta;

	@Column(name = "CLASSIFICACAO")
	private String classificacao;

	@Column(name = "TIPO")
	private Character tipo;

	@Column(name = "SOMA_VALOR")
	private BigDecimal somaValor;

	public SpedI155View() {

	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public int getIdContabilConta() {
		return idContabilConta;
	}

	public void setIdContabilConta(int idContabilConta) {
		this.idContabilConta = idContabilConta;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getSomaValor() {
		return somaValor;
	}

	public void setSomaValor(BigDecimal somaValor) {
		this.somaValor = somaValor;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}