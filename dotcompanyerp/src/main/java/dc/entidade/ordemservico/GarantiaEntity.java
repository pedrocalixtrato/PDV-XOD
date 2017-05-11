package dc.entidade.ordemservico;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

@Entity
@Table(name = "os_garantia")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class GarantiaEntity extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "nome")
	private String nome;

	@JoinColumn(name = "id_revenda", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private RevendaEntity revenda;

	@Field
	@Caption("Nr. O.S Fabricante")
	@Column(name = "nr_os_fabricante")
	@Size(max = 30, message = "Tamanho inválido.")
	private String nrOsFabricante;
	
	@Field
	@Caption("Nr. Nota Fiscal")
	@Column(name = "nr_nota_fiscal")
	@Size(max = 30, message = "Tamanho inválido.")
	private String nrNotaFiscal;

	@Field
	@Caption("Data Nota Fiscal")
	@Column(name = "data_nota_fiscal")
	private Date dataCadastro;

	@JoinColumn(name = "id_equipamento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private EquipamentoEntity equipamento;

	@JoinColumn(name = "id_marca", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private MarcaOsEntity marca;

	@JoinColumn(name = "id_modelo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private ModeloEntity modelo;

	@JoinColumn(name = "id_cor", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private CorEntity cor;
 
	@Field
	@Caption("Apelido")
	@Column(name = "apelido")
	@Size(max = 40, message = "Tamanho inválido.")
	private String apelido;

	@Field
	@Caption("Nr. Serial")
	@Column(name = "nr_serial")
	@Size(max = 40, message = "Tamanho inválido.")
	private String nrSerial;

	@Field
	@Caption("Termo Garantia")
	@Column(name = "termo_garantia")
	@Size(max = 40, message = "Tamanho inválido.")
	private String termoGarantia;

	@Field
	@Caption("Dias")
	@Column(name = "dias_garantia")
	private Integer dias = new Integer(0);

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

	public RevendaEntity getRevenda() {
		return revenda;
	}

	public void setRevenda(RevendaEntity revenda) {
		this.revenda = revenda;
	}

	public String getNrOsFabricante() {
		return nrOsFabricante;
	}

	public void setNrOsFabricante(String nrOsFabricante) {
		this.nrOsFabricante = nrOsFabricante;
	}

	public String getNrNotaFiscal() {
		return nrNotaFiscal;
	}

	public void setNrNotaFiscal(String nrNotaFiscal) {
		this.nrNotaFiscal = nrNotaFiscal;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public EquipamentoEntity getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(EquipamentoEntity equipamento) {
		this.equipamento = equipamento;
	}

	public MarcaOsEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaOsEntity marca) {
		this.marca = marca;
	}

	public ModeloEntity getModelo() {
		return modelo;
	}

	public void setModelo(ModeloEntity modelo) {
		this.modelo = modelo;
	}

	public CorEntity getCor() {
		return cor;
	}

	public void setCor(CorEntity cor) {
		this.cor = cor;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getNrSerial() {
		return nrSerial;
	}

	public void setNrSerial(String nrSerial) {
		this.nrSerial = nrSerial;
	}

	public String getTermoGarantia() {
		return termoGarantia;
	}

	public void setTermoGarantia(String termoGarantia) {
		this.termoGarantia = termoGarantia;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}
}
