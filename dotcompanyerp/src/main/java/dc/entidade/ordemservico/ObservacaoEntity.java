package dc.entidade.ordemservico;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "os_observacao")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ObservacaoEntity extends AbstractMultiEmpresaModel<Integer> {

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
	@Caption("OBS da O.S")
	@Column(name = "obs_os")
	private String observacaoOs;

	@Field
	@Caption("OBS da O.S")
	@Column(name = "obs_ficando")
	private String ficandoLocal;

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

	public String getObservacaoOs() {
		return observacaoOs;
	}

	public void setObservacaoOs(String observacaoOs) {
		this.observacaoOs = observacaoOs;
	}

	public String getFicandoLocal() {
		return ficandoLocal;
	}

	public void setFicandoLocal(String ficandoLocal) {
		this.ficandoLocal = ficandoLocal;
	}
}
