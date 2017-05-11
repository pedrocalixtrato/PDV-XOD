package dc.entidade.ordemservico;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "os_servico")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ServicoOsEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("Nome")
	@Column(name = "nome")
	@NotNull(message = "Nome é Obrigatório!")
	private String nome;

	@Caption("Grupo")
	@ManyToOne
	@JoinColumn(name = "id_grupo", referencedColumnName = "id")
	@NotNull(message = "Grupo é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private GrupoOsEntity grupo;

	@Caption("Subgrupo")
	@ManyToOne
	@JoinColumn(name = "id_sub_grupo", referencedColumnName = "id")
	@NotNull(message = "Sub Grupo é Obrigatório!")
	@IndexedEmbedded(includePaths={"nome"})
	private SubGrupoOsEntity subGrupo;
	
	@Field
	@Caption("Aliquota ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "aliquota_issqn")
	private BigDecimal aliquotaIssqn;

	@Field
	@Caption("Valor Comissão Técnico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "valor_comissao_tecnico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorComissaoTecnico;

	@Field
	@Caption("Valor Comissão Vendedor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "valor_comissao_vendedor")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorComissaoVendedor;

	@Field
	@Caption("Tipo Comissão Técnico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "tipo_comissao_tecnico")
	@NotNull(message = "Tipo Comissão Técnico é Obrigatório!")
	private String tipoComissaoTecnico;

	@Field
	@Caption("Tipo Comissão Vendedor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "tipo_comissao_vendedor")
	@NotNull(message = "Tipo Comissão Vendedor é Obrigatório!")
	private String tipoComissaoVendedor;

	@Field
	@Caption("Valor Serviço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "valor_servico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorServico;

	@Field
	@Caption("Valor Mínimo Serviço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "valor_minimo_servico")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorMinimoServico;

	@Field
	@Caption("Garantia Dia")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "garantia_dia")
	private Integer garantiaDia;

	@Field
	@Caption("Retorno")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "retorno")
	private Integer retorno;

	@Field
	@Caption("Hora Gasta")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "hora_gasta")
	private BigDecimal horaGasta;

	@Field
	@Caption("Ativa")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "ativa")
	private Boolean ativa;

	@Field
	@Caption("Valor Promocional")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "valor_promocional")
	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorPromocional;

	@Field
	@Caption("Vencimento Promoção")
	@ComboValue
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	@Column(name = "vencimento_promocao")
	private Date vencimentoPromocao;
	
	@Field
	@Caption("Observacao")
	@Lob
	@Column(name = "OBSERVACAO")
	@Type(type = "text")
	private String observacao;
	
	@Field
	@Caption("Data Cadastro")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

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

	public GrupoOsEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoOsEntity grupo) {
		this.grupo = grupo;
	}

	public SubGrupoOsEntity getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupoOsEntity subGrupo) {
		this.subGrupo = subGrupo;
	}

	public String getTipoComissaoTecnico() {
		return tipoComissaoTecnico;
	}

	public void setTipoComissaoTecnico(String tipoComissaoTecnico) {
		this.tipoComissaoTecnico = tipoComissaoTecnico;
	}

	public String getTipoComissaoVendedor() {
		return tipoComissaoVendedor;
	}

	public void setTipoComissaoVendedor(String tipoComissaoVendedor) {
		this.tipoComissaoVendedor = tipoComissaoVendedor;
	}

	public Integer getGarantiaDia() {
		return garantiaDia;
	}

	public void setGarantiaDia(Integer garantiaDia) {
		this.garantiaDia = garantiaDia;
	}

	public Integer getRetorno() {
		return retorno;
	}

	public void setRetorno(Integer retorno) {
		this.retorno = retorno;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public Date getVencimentoPromocao() {
		return vencimentoPromocao;
	}

	public void setVencimentoPromocao(Date vencimentoPromocao) {
		this.vencimentoPromocao = vencimentoPromocao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = aliquotaIssqn;
	}

	public BigDecimal getValorComissaoTecnico() {
		return valorComissaoTecnico;
	}

	public void setValorComissaoTecnico(BigDecimal valorComissaoTecnico) {
		this.valorComissaoTecnico = valorComissaoTecnico;
	}

	public BigDecimal getValorComissaoVendedor() {
		return valorComissaoVendedor;
	}

	public void setValorComissaoVendedor(BigDecimal valorComissaoVendedor) {
		this.valorComissaoVendedor = valorComissaoVendedor;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public BigDecimal getValorMinimoServico() {
		return valorMinimoServico;
	}

	public void setValorMinimoServico(BigDecimal valorMinimoServico) {
		this.valorMinimoServico = valorMinimoServico;
	}

	public BigDecimal getHoraGasta() {
		return horaGasta;
	}

	public void setHoraGasta(BigDecimal horaGasta) {
		this.horaGasta = horaGasta;
	}

	public BigDecimal getValorPromocional() {
		return valorPromocional;
	}

	public void setValorPromocional(BigDecimal valorPromocional) {
		this.valorPromocional = valorPromocional;
	}
}
