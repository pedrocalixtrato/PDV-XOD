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
@Table(name = "os_acessorio_ordem_servico")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class AcessorioOsEntity extends AbstractMultiEmpresaModel<Integer> {

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

	@ManyToOne
	@JoinColumn(name = "id_acessorio", referencedColumnName = "id")
	private AcessorioEntity acessorio;
	
	@Field
	@Caption("QUANTIDADE")
	@Column(name = "qtd_acessorio")
	private Integer quantidadeAcessorio;
	
	@Field
	@Caption("OBS")
	@Column(name = "obs")
	private String observacao;
	
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

	public AcessorioEntity getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(AcessorioEntity acessorio) {
		this.acessorio = acessorio;
	}

	public Integer getQuantidadeAcessorio() {
		return quantidadeAcessorio;
	}

	public void setQuantidadeAcessorio(Integer quantidadeAcessorio) {
		this.quantidadeAcessorio = quantidadeAcessorio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
