package dc.entidade.geral;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "colaborador_relacionamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ColaboradorRelacionamentoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colaborador_relacionamento_id_seq")
	@SequenceGenerator(name = "colaborador_relacionamento_id_seq", sequenceName = "colaborador_relacionamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	private String nome;

	@Column(name = "DATA_NASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "CPF", length = 11)
	private String cpf;

	@Column(name = "REGISTRO_MATRICULA", length = 50)
	private String registroMatricula;

	@Column(name = "REGISTRO_CARTORIO", length = 50)
	private String registroCartorio;

	@Column(name = "REGISTRO_CARTORIO_NUMERO", length = 50)
	private String registroCartorioNumeor;

	@Column(name = "REGISTRO_NUMERO_LIVRO", length = 10)
	private String registroNumeroLivro;

	@Column(name = "REGISTRO_NUMERO_FOLHA", length = 10)
	private String registroNumeroFolha;

	@Column(name = "DATA_ENTREGA_DOCUMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataEntregaDocumento;

	@Column(name = "SALARIO_FAMILIA")
	private Character salarioFamilia;

	@Column(name = "SALARIO_FAMILIA_IDADE_LIMITE")
	private Integer salarioFamiliaIdadeLimite;

	@Column(name = "SALARIO_FAMILIA_DATA_FIM")
	@Temporal(TemporalType.DATE)
	private Date salarioFamiliaDataFim;

	@Column(name = "IMPOSTO_RENDA_DATA_FIM")
	private Integer impostoRendaDataFim;

	public ColaboradorRelacionamentoEntity() {

	}

	public ColaboradorRelacionamentoEntity(Integer id) {
		this.id = id;
	}

	@Override
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRegistroMatricula() {
		return registroMatricula;
	}

	public void setRegistroMatricula(String registroMatricula) {
		this.registroMatricula = registroMatricula;
	}

	public String getRegistroCartorio() {
		return registroCartorio;
	}

	public void setRegistroCartorio(String registroCartorio) {
		this.registroCartorio = registroCartorio;
	}

	public String getRegistroCartorioNumeor() {
		return registroCartorioNumeor;
	}

	public void setRegistroCartorioNumeor(String registroCartorioNumeor) {
		this.registroCartorioNumeor = registroCartorioNumeor;
	}

	public String getRegistroNumeroLivro() {
		return registroNumeroLivro;
	}

	public void setRegistroNumeroLivro(String registroNumeroLivro) {
		this.registroNumeroLivro = registroNumeroLivro;
	}

	public String getRegistroNumeroFolha() {
		return registroNumeroFolha;
	}

	public void setRegistroNumeroFolha(String registroNumeroFolha) {
		this.registroNumeroFolha = registroNumeroFolha;
	}

	public Date getDataEntregaDocumento() {
		return dataEntregaDocumento;
	}

	public void setDataEntregaDocumento(Date dataEntregaDocumento) {
		this.dataEntregaDocumento = dataEntregaDocumento;
	}

	public Character getSalarioFamilia() {
		return salarioFamilia;
	}

	public void setSalarioFamilia(Character salarioFamilia) {
		this.salarioFamilia = salarioFamilia;
	}

	public Integer getSalarioFamiliaIdadeLimite() {
		return salarioFamiliaIdadeLimite;
	}

	public void setSalarioFamiliaIdadeLimite(Integer salarioFamiliaIdadeLimite) {
		this.salarioFamiliaIdadeLimite = salarioFamiliaIdadeLimite;
	}

	public Date getSalarioFamiliaDataFim() {
		return salarioFamiliaDataFim;
	}

	public void setSalarioFamiliaDataFim(Date salarioFamiliaDataFim) {
		this.salarioFamiliaDataFim = salarioFamiliaDataFim;
	}

	public Integer getImpostoRendaDataFim() {
		return impostoRendaDataFim;
	}

	public void setImpostoRendaDataFim(Integer impostoRendaDataFim) {
		this.impostoRendaDataFim = impostoRendaDataFim;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}