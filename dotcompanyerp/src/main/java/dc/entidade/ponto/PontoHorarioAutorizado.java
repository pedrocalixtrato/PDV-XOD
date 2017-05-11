package dc.entidade.ponto;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;

@Entity
@Table(name = "PONTO_HORARIO_AUTORIZADO")
public class PontoHorarioAutorizado extends AbstractMultiEmpresaModel<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_HORARIO")
    @Caption(value = "Data Horário")
    private Date dataHorario;
    @Column(name = "TIPO")
    @Caption(value = "Tipo")
    private String tipo;
    @Caption(value = "Carga Horária")
    @Column(name = "CARGA_HORARIA")
    private String cargaHoraria;
    @Caption(value = "Entrada1")
    @Column(name = "ENTRADA01")
    private String entrada01;
    @Caption(value = "Saida1")
    @Column(name = "SAIDA01")
    private String saida01;
    @Column(name = "ENTRADA02")
    @Caption(value = "Entrada2")
    private String entrada02;
    @Column(name = "SAIDA02")
    @Caption(value = "Saída2")
    private String saida02;
    @Column(name = "ENTRADA03")
    @Caption(value = "Entrada3")
    private String entrada03;
    @Column(name = "SAIDA03")
    @Caption(value = "Saída3")
    private String saida03;
    @Column(name = "ENTRADA04")
    @Caption(value = "Entrada4")
    private String entrada04;
    @Column(name = "SAIDA04")
    @Caption(value = "Saída4")
    private String saida04;
    @Column(name = "ENTRADA05")
    @Caption(value = "Entrada5")
    private String entrada05;
    @Column(name = "SAIDA05")
    @Caption(value = "Saída5")
    private String saida05;
    @Column(name = "HORA_FECHAMENTO_DIA")
    @Caption(value = "Hora Fechamento Dia")
    private String horaFechamentoDia;
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @Caption(value = "Colaborador")
    private ColaboradorEntity colaborador;

    public PontoHorarioAutorizado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(Date dataHorario) {
        this.dataHorario = dataHorario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getEntrada01() {
        return entrada01;
    }

    public void setEntrada01(String entrada01) {
        this.entrada01 = entrada01;
    }

    public String getSaida01() {
        return saida01;
    }

    public void setSaida01(String saida01) {
        this.saida01 = saida01;
    }

    public String getEntrada02() {
        return entrada02;
    }

    public void setEntrada02(String entrada02) {
        this.entrada02 = entrada02;
    }

    public String getSaida02() {
        return saida02;
    }

    public void setSaida02(String saida02) {
        this.saida02 = saida02;
    }

    public String getEntrada03() {
        return entrada03;
    }

    public void setEntrada03(String entrada03) {
        this.entrada03 = entrada03;
    }

    public String getSaida03() {
        return saida03;
    }

    public void setSaida03(String saida03) {
        this.saida03 = saida03;
    }

    public String getEntrada04() {
        return entrada04;
    }

    public void setEntrada04(String entrada04) {
        this.entrada04 = entrada04;
    }

    public String getSaida04() {
        return saida04;
    }

    public void setSaida04(String saida04) {
        this.saida04 = saida04;
    }

    public String getEntrada05() {
        return entrada05;
    }

    public void setEntrada05(String entrada05) {
        this.entrada05 = entrada05;
    }

    public String getSaida05() {
        return saida05;
    }

    public void setSaida05(String saida05) {
        this.saida05 = saida05;
    }

    public String getHoraFechamentoDia() {
        return horaFechamentoDia;
    }

    public void setHoraFechamentoDia(String horaFechamentoDia) {
        this.horaFechamentoDia = horaFechamentoDia;
    }

    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorEntity colaborador) {
        this.colaborador = colaborador;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoHorarioAutorizado[id=" + id + "]";
    }

}
