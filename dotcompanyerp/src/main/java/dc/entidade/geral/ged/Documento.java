package dc.entidade.geral.ged;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.suprimentos.contrato.ContratoEntity;

@Entity
@Table(name = "GED_DOCUMENTO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Documento extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Caption("Id")
	private Integer id;

	@Column(name = "NOME")
	@Field
	@Caption("Nome")
	private String nome;

	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO")
	private String descricao;

	@Field
	@Caption("Palavra-chave")
	@Column(name = "PALAVRA_CHAVE")
	private String palavraChave;

	@Field
	@Caption("Pode Excluir")
	@Column(name = "PODE_EXCLUIR")
	
	private Boolean podeExcluir;

	@Field
	@Caption("Pode Alterar")
	@Column(name = "PODE_ALTERAR")
	private Boolean podeAlterar;

	@Field
	@Caption("Assinado")
	@Column(name = "ASSINADO")
	private Boolean assinado;

	@Field
	@Caption("Data Vigência")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FIM_VIGENCIA")
	private Date dataFimVigencia;

	@Field
	@Caption("Data exclusão")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EXCLUSAO")
	private Date dataExclusao;

	@Caption("Tipo Documento")
	@JoinColumn(name = "ID_GED_TIPO_DOCUMENTO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoDocumento tipoDocumento;

	@Field()
	@Caption("Template")
	@Column(name = "template_contrato")
	@Analyzer(impl = org.apache.lucene.analysis.core.SimpleAnalyzer.class)
	private Boolean templateContrato;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DOCUMENTO")
	@Cascade({ CascadeType.ALL })
	private List<DocumentoArquivo> documentos;
	
	@OneToMany(mappedBy = "documento", orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<ContratoEntity> contratoList = new ArrayList<>();

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

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Boolean getPodeExcluir() {
		return podeExcluir;
	}

	public void setPodeExcluir(Boolean podeExcluir) {
		this.podeExcluir = podeExcluir;
	}

	public Boolean getPodeAlterar() {
		return podeAlterar;
	}

	public void setPodeAlterar(Boolean podeAlterar) {
		this.podeAlterar = podeAlterar;
	}

	public Boolean getAssinado() {
		return assinado;
	}

	public void setAssinado(Boolean assinado) {
		this.assinado = assinado;
	}

	public Date getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public List<DocumentoArquivo> getDocumentos() {
		if (documentos == null) {
			documentos = new ArrayList<DocumentoArquivo>();
		}

		return documentos;
	}

	public void setDocumentos(List<DocumentoArquivo> documentos) {
		this.documentos = documentos;
	}
	
	public List<ContratoEntity> getContratoList() {
		return contratoList;
	}

	public void setContratoList(List<ContratoEntity> contratoList) {
		this.contratoList = contratoList;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((palavraChave == null) ? 0 : palavraChave.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (palavraChave == null) {
			if (other.palavraChave != null)
				return false;
		} else if (!palavraChave.equals(other.palavraChave))
			return false;
		return true;
	}

	public void setTipoDocumento(TipoDocumento value) {
		this.tipoDocumento = value;
	}

	public Boolean getTemplateContrato() {
		return templateContrato;
	}

	public void setTemplateContrato(Boolean template) {
		this.templateContrato = template;
	}

}
