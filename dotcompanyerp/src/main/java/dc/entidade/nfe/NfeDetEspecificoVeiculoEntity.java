package dc.entidade.nfe;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * 
 */

@Entity
@Table(name = "nfe_det_especifico_veiculo")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetEspecificoVeiculoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_det_especifico_veiculo_id_seq")
	@SequenceGenerator(name = "nfe_det_especifico_veiculo_id_seq", sequenceName = "nfe_det_especifico_veiculo_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "tipo_operacao")
	@Caption(value = "Tipo de operação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoOperacao = "";

	@Field
	@Column(name = "chassi")
	@Caption(value = "Chassi")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String chassi = "";

	@Field
	@Column(name = "cor")
	@Caption(value = "Cor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cor = "";

	@Field
	@Column(name = "descricao_cor")
	@Caption(value = "Descrição da cor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoCor = "";

	@Field
	@Column(name = "potencia_motor")
	@Caption(value = "Potência do motor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String potenciaMotor = "";

	@Field
	@Column(name = "cilindradas")
	@Caption(value = "Cilindradas")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cilindradas = "";

	@Field
	@Column(name = "peso_liquido")
	@Caption(value = "Peso líquido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pesoLiquido = "";

	@Field
	@Column(name = "peso_bruto")
	@Caption(value = "Peso bruto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pesoBruto = "";

	@Field
	@Column(name = "numero_serie")
	@Caption(value = "Número de série")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroSerie = "";

	@Field
	@Column(name = "tipo_combustivel")
	@Caption(value = "Tipo do combustível")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoCombustivel = "";

	@Field
	@Column(name = "numero_motor")
	@Caption(value = "Número do motor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroMotor = "";

	@Field
	@Column(name = "capacidade_maxima_tracao")
	@Caption(value = "Capacidade máxima da tração")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String capacidadeMaximaTracao = "";

	@Field
	@Column(name = "distancia_eixos")
	@Caption(value = "Distância dos eixos")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String distanciaEixos = "";

	@Field
	@Column(name = "ano_modelo")
	@Caption(value = "Ano do modelo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String anoModelo = "";

	@Field
	@Column(name = "ano_fabricacao")
	@Caption(value = "Ano de fabricação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String anoFabricacao = "";

	@Field
	@Column(name = "tipo_pintura")
	@Caption(value = "Tipo da pintura")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoPintura = "";

	@Field
	@Column(name = "tipo_veiculo")
	@Caption(value = "Tipo do veículo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoVeiculo = "";

	@Field
	@Column(name = "especie_veiculo")
	@Caption(value = "Espécie do veículo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String especieVeiculo = "";

	@Field
	@Column(name = "condicao_vin")
	@Caption(value = "Condição do VIN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String condicaoVin = "";

	@Field
	@Column(name = "condicao_veiculo")
	@Caption(value = "Condição do veículo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String condicaoVeiculo = "";

	@Field
	@Column(name = "codigo_marca_modelo")
	@Caption(value = "Código da marca e modelo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoMarcaModelo = "";

	@Field
	@Column(name = "codigo_cor")
	@Caption(value = "Código da cor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoCor = "";

	@Field
	@Column(name = "lotacao")
	@Caption(value = "Lotação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer lotacao = new Integer(0);

	@Field
	@Column(name = "restricao")
	@Caption(value = "Restrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String restricao = "";

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne
	@JoinColumn(name = "id_nfe_detalhe")
	private NfeDetalheEntity nfeDetalhe;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDetEspecificoVeiculoEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = (tipoOperacao == null ? "" : tipoOperacao
				.toUpperCase().trim());
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = (chassi == null ? "" : chassi.toUpperCase().trim());
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = (cor == null ? "" : cor.toUpperCase().trim());
	}

	public String getDescricaoCor() {
		return descricaoCor;
	}

	public void setDescricaoCor(String descricaoCor) {
		this.descricaoCor = (descricaoCor == null ? "" : descricaoCor
				.toUpperCase().trim());
	}

	public String getPotenciaMotor() {
		return potenciaMotor;
	}

	public void setPotenciaMotor(String potenciaMotor) {
		this.potenciaMotor = (potenciaMotor == null ? "" : potenciaMotor
				.toUpperCase().trim());
	}

	public String getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(String cilindradas) {
		this.cilindradas = (cilindradas == null ? "" : cilindradas
				.toUpperCase().trim());
	}

	public String getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(String pesoLiquido) {
		this.pesoLiquido = (pesoLiquido == null ? "" : pesoLiquido
				.toUpperCase().trim());
	}

	public String getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(String pesoBruto) {
		this.pesoBruto = (pesoBruto == null ? "" : pesoBruto.toUpperCase()
				.trim());
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = (numeroSerie == null ? "" : numeroSerie
				.toUpperCase().trim());
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = (tipoCombustivel == null ? "" : tipoCombustivel
				.toUpperCase().trim());
	}

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = (numeroMotor == null ? "" : numeroMotor
				.toUpperCase().trim());
	}

	public String getCapacidadeMaximaTracao() {
		return capacidadeMaximaTracao;
	}

	public void setCapacidadeMaximaTracao(String capacidadeMaximaTracao) {
		this.capacidadeMaximaTracao = (capacidadeMaximaTracao == null ? ""
				: capacidadeMaximaTracao.toUpperCase().trim());
	}

	public String getDistanciaEixos() {
		return distanciaEixos;
	}

	public void setDistanciaEixos(String distanciaEixos) {
		this.distanciaEixos = (distanciaEixos == null ? "" : distanciaEixos
				.toUpperCase().trim());
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = (anoModelo == null ? "" : anoModelo.toUpperCase()
				.trim());
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = (anoFabricacao == null ? "" : anoFabricacao
				.toUpperCase().trim());
	}

	public String getTipoPintura() {
		return tipoPintura;
	}

	public void setTipoPintura(String tipoPintura) {
		this.tipoPintura = (tipoPintura == null ? "" : tipoPintura
				.toUpperCase().trim());
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = (tipoVeiculo == null ? "" : tipoVeiculo
				.toUpperCase().trim());
	}

	public String getEspecieVeiculo() {
		return especieVeiculo;
	}

	public void setEspecieVeiculo(String especieVeiculo) {
		this.especieVeiculo = (especieVeiculo == null ? "" : especieVeiculo
				.toUpperCase().trim());
	}

	public String getCondicaoVin() {
		return condicaoVin;
	}

	public void setCondicaoVin(String condicaoVin) {
		this.condicaoVin = (condicaoVin == null ? "" : condicaoVin
				.toUpperCase().trim());
	}

	public String getCondicaoVeiculo() {
		return condicaoVeiculo;
	}

	public void setCondicaoVeiculo(String condicaoVeiculo) {
		this.condicaoVeiculo = (condicaoVeiculo == null ? "" : condicaoVeiculo
				.toUpperCase().trim());
	}

	public String getCodigoMarcaModelo() {
		return codigoMarcaModelo;
	}

	public void setCodigoMarcaModelo(String codigoMarcaModelo) {
		this.codigoMarcaModelo = (codigoMarcaModelo == null ? ""
				: codigoMarcaModelo.toUpperCase().trim());
	}

	public String getCodigoCor() {
		return codigoCor;
	}

	public void setCodigoCor(String codigoCor) {
		this.codigoCor = (codigoCor == null ? "" : codigoCor.toUpperCase()
				.trim());
	}

	public Integer getLotacao() {
		return lotacao;
	}

	public void setLotacao(Integer lotacao) {
		this.lotacao = (lotacao == null ? new Integer(0) : lotacao);
	}

	public String getRestricao() {
		return restricao;
	}

	public void setRestricao(String restricao) {
		this.restricao = (restricao == null ? "" : restricao.toUpperCase()
				.trim());
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}