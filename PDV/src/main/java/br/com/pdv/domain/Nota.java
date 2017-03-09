package br.com.pdv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.pdv.Enum.NotaFinalidade;

@Entity
@Table(name = "NOTA")
public class Nota implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	private Long not_codigo;
	private Integer not_nr;
	private Integer not_serie;
	private Date not_dataemissao;
	private Date not_datasaida;	
	private BigDecimal not_totitens;
	private BigDecimal not_totnota;
	@ManyToOne
	@Column(name = "TRA_CODIGO")	
	private Transportadora TRANSPORTADORA;
	private String not_cpfcnpj;
	private BigDecimal not_desconto;
	private BigDecimal not_acrecimo;
	private BigDecimal NOT_VALIPI;
	private BigDecimal NOT_ALIQIPI;
	private BigDecimal NOT_BASEIPI;
	private BigDecimal NOT_VALICM;
	private BigDecimal NOT_ALIQICM;
	private BigDecimal NOT_BASEICM;
	private Integer NOT_SITUACAO;
	private BigDecimal NOT_BASESUBS;
	private BigDecimal NOT_VALSUBS;
	private BigDecimal NOT_FRETE;
	private Integer FRETE;
	private Integer NOT_VOLUMES;
	private BigDecimal NOT_ALIQSUBS;
	private BigDecimal NOT_VALORISSQN;
	private Date DATA;
	private String NOT_FORMADEEMISSAO;
	private String NOTA_STATUS;
	private String NOT_FINALILDADEEMISSAO;
	private String NOT_TIPOIMPRESSAODANFE;
	private String NOT_STATUS_NFE;
	private String NOT_NUMERONFE;
	
	private NotaFinalidade NOT_NFEDEVOLUCAO;
	private Integer NFE_OPTANTESIMPLESNASCIONAL = 1;
	private BigDecimal TOTALIMPOSTOTRANSPARENCIA;
	@ManyToOne
	private Cliente CLI_CODIGO;
	@ManyToOne
	private CFOP CFOP_COF;
	
	private List<NotaItens> NOTA_ITENS = new ArrayList<>();
	
	
	
	
	@Id
	@GeneratedValue
	@Column(name = "NOT_CODIGO")
	public Long getNot_codigo() {
		return not_codigo;
	}

	public void setNot_codigo(Long not_codigo) {
		this.not_codigo = not_codigo;
	}

	public Integer getNot_nr() {
		return not_nr;
	}

	public void setNot_nr(Integer not_nr) {
		this.not_nr = not_nr;
	}

	public Integer getNot_serie() {
		return not_serie;
	}

	public void setNot_serie(Integer not_serie) {
		this.not_serie = not_serie;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getNot_dataemissao() {
		return not_dataemissao;
	}

	public void setNot_dataemissao(Date not_dataemissao) {
		this.not_dataemissao = not_dataemissao;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "")
	public Date getNot_datasaida() {
		return not_datasaida;
	}

	public void setNot_datasaida(Date not_datasaida) {
		this.not_datasaida = not_datasaida;
	}

	public BigDecimal getNot_totitens() {
		return not_totitens;
	}

	public void setNot_totitens(BigDecimal not_totitens) {
		this.not_totitens = not_totitens;
	}

	public BigDecimal getNot_totnota() {
		return not_totnota;
	}

	public void setNot_totnota(BigDecimal not_totnota) {
		this.not_totnota = not_totnota;
	}
	
		

	

	public String getNot_cpfcnpj() {
		return not_cpfcnpj;
	}

	public void setNot_cpfcnpj(String not_cpfcnpj) {
		this.not_cpfcnpj = not_cpfcnpj;
	}

	public BigDecimal getNot_desconto() {
		return not_desconto;
	}

	public void setNot_desconto(BigDecimal not_desconto) {
		this.not_desconto = not_desconto;
	}

	public BigDecimal getNot_acrecimo() {
		return not_acrecimo;
	}

	public void setNot_acrecimo(BigDecimal not_acrecimo) {
		this.not_acrecimo = not_acrecimo;
	}

	public BigDecimal getNOT_VALIPI() {
		return NOT_VALIPI;
	}

	public void setNOT_VALIPI(BigDecimal nOT_VALIPI) {
		NOT_VALIPI = nOT_VALIPI;
	}

	public BigDecimal getNOT_ALIQIPI() {
		return NOT_ALIQIPI;
	}

	public void setNOT_ALIQIPI(BigDecimal nOT_ALIQIPI) {
		NOT_ALIQIPI = nOT_ALIQIPI;
	}

	public BigDecimal getNOT_BASEIPI() {
		return NOT_BASEIPI;
	}

	public void setNOT_BASEIPI(BigDecimal nOT_BASEIPI) {
		NOT_BASEIPI = nOT_BASEIPI;
	}

	public BigDecimal getNOT_VALICM() {
		return NOT_VALICM;
	}

	public void setNOT_VALICM(BigDecimal nOT_VALICM) {
		NOT_VALICM = nOT_VALICM;
	}

	public BigDecimal getNOT_ALIQICM() {
		return NOT_ALIQICM;
	}

	public void setNOT_ALIQICM(BigDecimal nOT_ALIQICM) {
		NOT_ALIQICM = nOT_ALIQICM;
	}

	public BigDecimal getNOT_BASEICM() {
		return NOT_BASEICM;
	}

	public void setNOT_BASEICM(BigDecimal nOT_BASEICM) {
		NOT_BASEICM = nOT_BASEICM;
	}

	public Integer getNOT_SITUACAO() {
		return NOT_SITUACAO;
	}

	public void setNOT_SITUACAO(Integer nOT_SITUACAO) {
		NOT_SITUACAO = nOT_SITUACAO;
	}

	public BigDecimal getNOT_BASESUBS() {
		return NOT_BASESUBS;
	}

	public void setNOT_BASESUBS(BigDecimal nOT_BASESUBS) {
		NOT_BASESUBS = nOT_BASESUBS;
	}

	public BigDecimal getNOT_VALSUBS() {
		return NOT_VALSUBS;
	}

	public void setNOT_VALSUBS(BigDecimal nOT_VALSUBS) {
		NOT_VALSUBS = nOT_VALSUBS;
	}

	public BigDecimal getNOT_FRETE() {
		return NOT_FRETE;
	}

	public void setNOT_FRETE(BigDecimal nOT_FRETE) {
		NOT_FRETE = nOT_FRETE;
	}

	public Integer getFRETE() {
		return FRETE;
	}

	public void setFRETE(Integer fRETE) {
		FRETE = fRETE;
	}

	public Integer getNOT_VOLUMES() {
		return NOT_VOLUMES;
	}

	public void setNOT_VOLUMES(Integer nOT_VOLUMES) {
		NOT_VOLUMES = nOT_VOLUMES;
	}

	public BigDecimal getNOT_ALIQSUBS() {
		return NOT_ALIQSUBS;
	}

	public void setNOT_ALIQSUBS(BigDecimal nOT_ALIQSUBS) {
		NOT_ALIQSUBS = nOT_ALIQSUBS;
	}

	public BigDecimal getNOT_VALORISSQN() {
		return NOT_VALORISSQN;
	}

	public void setNOT_VALORISSQN(BigDecimal nOT_VALORISSQN) {
		NOT_VALORISSQN = nOT_VALORISSQN;
	}

	public Date getDATA() {
		return DATA;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public String getNOT_FORMADEEMISSAO() {
		return NOT_FORMADEEMISSAO;
	}

	public void setNOT_FORMADEEMISSAO(String nOT_FORMADEEMISSAO) {
		NOT_FORMADEEMISSAO = nOT_FORMADEEMISSAO;
	}

	public String getNOTA_STATUS() {
		return NOTA_STATUS;
	}

	public void setNOTA_STATUS(String nOTA_STATUS) {
		NOTA_STATUS = nOTA_STATUS;
	}

	public String getNOT_FINALILDADEEMISSAO() {
		return NOT_FINALILDADEEMISSAO;
	}

	public void setNOT_FINALILDADEEMISSAO(String nOT_FINALILDADEEMISSAO) {
		NOT_FINALILDADEEMISSAO = nOT_FINALILDADEEMISSAO;
	}

	public String getNOT_TIPOIMPRESSAODANFE() {
		return NOT_TIPOIMPRESSAODANFE;
	}

	public void setNOT_TIPOIMPRESSAODANFE(String nOT_TIPOIMPRESSAODANFE) {
		NOT_TIPOIMPRESSAODANFE = nOT_TIPOIMPRESSAODANFE;
	}

	public String getNOT_STATUS_NFE() {
		return NOT_STATUS_NFE;
	}

	public void setNOT_STATUS_NFE(String nOT_STATUS_NFE) {
		NOT_STATUS_NFE = nOT_STATUS_NFE;
	}

	public String getNOT_NUMERONFE() {
		return NOT_NUMERONFE;
	}

	public void setNOT_NUMERONFE(String nOT_NUMERONFE) {
		NOT_NUMERONFE = nOT_NUMERONFE;
	}

	@Enumerated(EnumType.STRING)
	public NotaFinalidade getNOT_NFEDEVOLUCAO() {
		return NOT_NFEDEVOLUCAO;
	}

	public void setNOT_NFEDEVOLUCAO(NotaFinalidade nOT_NFEDEVOLUCAO) {
		NOT_NFEDEVOLUCAO = nOT_NFEDEVOLUCAO;
	}

	public Integer getNFE_OPTANTESIMPLESNASCIONAL() {
		return NFE_OPTANTESIMPLESNASCIONAL;
	}

	public void setNFE_OPTANTESIMPLESNASCIONAL(Integer nFE_OPTANTESIMPLESNASCIONAL) {
		NFE_OPTANTESIMPLESNASCIONAL = nFE_OPTANTESIMPLESNASCIONAL;
	}

	public BigDecimal getTOTALIMPOSTOTRANSPARENCIA() {
		return TOTALIMPOSTOTRANSPARENCIA;
	}

	public void setTOTALIMPOSTOTRANSPARENCIA(BigDecimal tOTALIMPOSTOTRANSPARENCIA) {
		TOTALIMPOSTOTRANSPARENCIA = tOTALIMPOSTOTRANSPARENCIA;
	}

	public Cliente getCLI_CODIGO() {
		return CLI_CODIGO;
	}

	public void setCLI_CODIGO(Cliente cLI_CODIGO) {
		CLI_CODIGO = cLI_CODIGO;
	}

	public CFOP getCFOP_COF() {
		return CFOP_COF;
	}

	public void setCFOP_COF(CFOP cFOP_COF) {
		CFOP_COF = cFOP_COF;
	}

	@OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<NotaItens> getNOTA_ITENS() {
		return NOTA_ITENS;
	}

	public void setNOTA_ITENS(List<NotaItens> nOTA_ITENS) {
		NOTA_ITENS = nOTA_ITENS;
	}

	public Transportadora getTRANSPORTADORA() {
		return TRANSPORTADORA;
	}

	public void setTRANSPORTADORA(Transportadora tRANSPORTADORA) {
		TRANSPORTADORA = tRANSPORTADORA;
	}
	
	
	
	
	

}
