package dc.entidade.ponto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;

@Entity
@Table(name = "PONTO_ABONO")
public class PontoAbono extends AbstractModel<Serializable> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "QUANTIDADE")
	@Caption(value = "Quantidade")
	private Integer quantidade;
	@Column(name = "UTILIZADO")
	@Caption(value = "Utilizado")
	private Integer utilizado;
	@Column(name = "SALDO")
	@Caption(value = "Saldo")
	private Integer saldo;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO")
	@Caption(value = "Data Cadastro")
	private Date dataCadastro;
	@Temporal(TemporalType.DATE)
	@Column(name = "INICIO_UTILIZACAO")
	@Caption(value = "Início Utilizaçãoo")
	private Date inicioUtilizacao;
	@Column(name = "OBSERVACAO")
	@Caption(value = "Observação")
	private String observacao;
	@JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption(value = "Colaborador")
	private ColaboradorEntity colaborador;

	@OneToMany(mappedBy = "pontoAbono", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PontoAbonoUtilizacao> pontoAbonoUtilizacoes = new ArrayList<>();

	public PontoAbono() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getUtilizado() {
		return utilizado;
	}

	public void setUtilizado(Integer utilizado) {
		this.utilizado = utilizado;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getInicioUtilizacao() {
		return inicioUtilizacao;
	}

	public void setInicioUtilizacao(Date inicioUtilizacao) {
		this.inicioUtilizacao = inicioUtilizacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	@Override
	public String toString() {
		return "com.t2tierp.ponto.java.PontoAbono[id=" + id + "]";
	}

	public PontoAbonoUtilizacao addPontoAbonoUtilizacao() {
		PontoAbonoUtilizacao ponto = new PontoAbonoUtilizacao();

		ponto.setPontoAbono(this);
		getPontoAbonoUtilizacoes().add(ponto);

		return ponto;
	}

	public void removePontoAbonoUtilizacao(PontoAbonoUtilizacao ponto) {
		getPontoAbonoUtilizacoes().remove(ponto);
		ponto.setPontoAbono(null);
	}

	public List<PontoAbonoUtilizacao> getPontoAbonoUtilizacoes() {
		return pontoAbonoUtilizacoes;
	}

	public void setPontoAbonoUtilizacoes(List<PontoAbonoUtilizacao> pontoAbonoUtilizacoes) {
		this.pontoAbonoUtilizacoes = pontoAbonoUtilizacoes;
	}

}
