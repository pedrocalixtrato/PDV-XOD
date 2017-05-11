package dc.entidade.ordemservico;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "os_laudo_tecnico")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class LaudoTecnicoEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_ordem_servico", referencedColumnName = "id")
	private OrdemServicoEntity ordemServico;

	@Field
	@Caption("Observacao")
	@Lob
	@Column(name = "observacao_laudo_tecnico")
	@Type(type = "text")
	private String observacaoLaudoTecnico;

	@Field
	@Caption("Observacao")
	@Lob
	@Column(name = "observacao_laudo_ferr")
	@Type(type = "text")
	private String observacaoLaudoFerramentas;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServicoEntity getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServicoEntity ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getObservacaoLaudoTecnico() {
		return observacaoLaudoTecnico;
	}

	public void setObservacaoLaudoTecnico(String observacaoLaudoTecnico) {
		this.observacaoLaudoTecnico = observacaoLaudoTecnico;
	}

	public String getObservacaoLaudoFerramentas() {
		return observacaoLaudoFerramentas;
	}

	public void setObservacaoLaudoFerramentas(String observacaoLaudoFerramentas) {
		this.observacaoLaudoFerramentas = observacaoLaudoFerramentas;
	}
}
