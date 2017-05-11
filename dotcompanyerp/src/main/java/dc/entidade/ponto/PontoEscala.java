package dc.entidade.ponto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "PONTO_ESCALA")
public class PontoEscala extends AbstractMultiEmpresaModel<Integer>  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NOME")
	@Caption(value = "Nome")
	private String nome;
	@Caption(value = "Desconto Hora Dia")
	@Column(name = "DESCONTO_HORA_DIA")
	private String descontoHoraDia;
	@Caption(value = "Desconto DSR")
	@Column(name = "DESCONTO_DSR")
	private String descontoDsr;
	@Caption(value = "Código Horário Domingo")
	@Column(name = "CODIGO_HORARIO_DOMINGO")
	private String codigoHorarioDomingo;
	@Caption(value = "Código Horário Segunda")
	@Column(name = "CODIGO_HORARIO_SEGUNDA")
	private String codigoHorarioSegunda;
	@Caption(value = "Código Horário Terça")
	@Column(name = "CODIGO_HORARIO_TERCA")
	private String codigoHorarioTerca;
	@Caption(value = "Código Horário Quarta")
	@Column(name = "CODIGO_HORARIO_QUARTA")
	private String codigoHorarioQuarta;
	@Caption(value = "Código Horário Quinta")
	@Column(name = "CODIGO_HORARIO_QUINTA")
	private String codigoHorarioQuinta;
	@Caption(value = "Código Horário Sexta")
	@Column(name = "CODIGO_HORARIO_SEXTA")
	private String codigoHorarioSexta;
	@Caption(value = "Código Horário Sábado")
	@Column(name = "CODIGO_HORARIO_SABADO")
	private String codigoHorarioSabado;
	/*@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Empresa empresa;*/
	
	@OneToMany(mappedBy = "pontoEscala", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PontoTurma> pontoTurmas = new ArrayList<>();

	public PontoEscala() {
	}

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

	public String getDescontoHoraDia() {
		return descontoHoraDia;
	}

	public void setDescontoHoraDia(String descontoHoraDia) {
		this.descontoHoraDia = descontoHoraDia;
	}

	public String getDescontoDsr() {
		return descontoDsr;
	}

	public void setDescontoDsr(String descontoDsr) {
		this.descontoDsr = descontoDsr;
	}

	public String getCodigoHorarioDomingo() {
		return codigoHorarioDomingo;
	}

	public void setCodigoHorarioDomingo(String codigoHorarioDomingo) {
		this.codigoHorarioDomingo = codigoHorarioDomingo;
	}

	public String getCodigoHorarioSegunda() {
		return codigoHorarioSegunda;
	}

	public void setCodigoHorarioSegunda(String codigoHorarioSegunda) {
		this.codigoHorarioSegunda = codigoHorarioSegunda;
	}

	public String getCodigoHorarioTerca() {
		return codigoHorarioTerca;
	}

	public void setCodigoHorarioTerca(String codigoHorarioTerca) {
		this.codigoHorarioTerca = codigoHorarioTerca;
	}

	public String getCodigoHorarioQuarta() {
		return codigoHorarioQuarta;
	}

	public void setCodigoHorarioQuarta(String codigoHorarioQuarta) {
		this.codigoHorarioQuarta = codigoHorarioQuarta;
	}

	public String getCodigoHorarioQuinta() {
		return codigoHorarioQuinta;
	}

	public void setCodigoHorarioQuinta(String codigoHorarioQuinta) {
		this.codigoHorarioQuinta = codigoHorarioQuinta;
	}

	public String getCodigoHorarioSexta() {
		return codigoHorarioSexta;
	}

	public void setCodigoHorarioSexta(String codigoHorarioSexta) {
		this.codigoHorarioSexta = codigoHorarioSexta;
	}

	public String getCodigoHorarioSabado() {
		return codigoHorarioSabado;
	}

	public void setCodigoHorarioSabado(String codigoHorarioSabado) {
		this.codigoHorarioSabado = codigoHorarioSabado;
	}

	/*public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}*/

	@Override
	public String toString() {
		return "com.t2tierp.ponto.java.PontoEscala[id=" + id + "]";
	}

	public PontoTurma addPontoTurma() {
		PontoTurma pontoTurma = new PontoTurma();
		pontoTurma.setPontoEscala(this);
		getPontoTurmas().add(pontoTurma);
		return pontoTurma;
	}

	public void removePontoTurma(PontoTurma pontoTurma) {
		getPontoTurmas().remove(pontoTurma);
		pontoTurma.setPontoEscala(null);
		
	}

	public List<PontoTurma> getPontoTurmas() {
		return pontoTurmas;
	}

	public void setPontoTurmas(List<PontoTurma> pontoTurmas) {
		this.pontoTurmas = pontoTurmas;
	}

}
