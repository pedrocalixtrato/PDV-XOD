package dc.entidade.ponto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "PONTO_PARAMETRO")
public class PontoParametro extends AbstractMultiEmpresaModel<Integer>  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MES_ANO")
    @Caption(value = "Mês ano")
    private String mesAno;
    @Caption(value = "Dia Inicial Apuração")
    @Column(name = "DIA_INICIAL_APURACAO")
    private Integer diaInicialApuracao;
    @Caption(value = "Hora Noturna Início")
    @Column(name = "HORA_NOTURNA_INICIO")
    private String horaNoturnaInicio;
    @Caption(value = "Hora Noturna Fim")
    @Column(name = "HORA_NOTURNA_FIM")
    private String horaNoturnaFim;
    @Caption(value = "Periodo Mínimo Interjornada")
    @Column(name = "PERIODO_MINIMO_INTERJORNADA")
    private String periodoMinimoInterjornada;
    @Caption(value = "Percentual HE Diurna")
    @Column(name = "PERCENTUAL_HE_DIURNA")
    private BigDecimal percentualHeDiurna;
    @Caption(value = "Percentual HE Noturna")
    @Column(name = "PERCENTUAL_HE_NOTURNA")
    private BigDecimal percentualHeNoturna;
    @Caption(value = "Duração Hora Diurna")
    @Column(name = "DURACAO_HORA_NOTURNA")
    private String duracaoHoraNoturna;
    @Caption(value = "Tratamento Hora Mais")
    @Column(name = "TRATAMENTO_HORA_MAIS")
    private String tratamentoHoraMais;
    @Caption(value = "Tratamento Hora Menos")
    @Column(name = "TRATAMENTO_HORA_MENOS")
    private String tratamentoHoraMenos;
   /* @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Empresa empresa;*/

    public PontoParametro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public Integer getDiaInicialApuracao() {
        return diaInicialApuracao;
    }

    public void setDiaInicialApuracao(Integer diaInicialApuracao) {
        this.diaInicialApuracao = diaInicialApuracao;
    }

    public String getHoraNoturnaInicio() {
        return horaNoturnaInicio;
    }

    public void setHoraNoturnaInicio(String horaNoturnaInicio) {
        this.horaNoturnaInicio = horaNoturnaInicio;
    }

    public String getHoraNoturnaFim() {
        return horaNoturnaFim;
    }

    public void setHoraNoturnaFim(String horaNoturnaFim) {
        this.horaNoturnaFim = horaNoturnaFim;
    }

    public String getPeriodoMinimoInterjornada() {
        return periodoMinimoInterjornada;
    }

    public void setPeriodoMinimoInterjornada(String periodoMinimoInterjornada) {
        this.periodoMinimoInterjornada = periodoMinimoInterjornada;
    }

    public BigDecimal getPercentualHeDiurna() {
        return percentualHeDiurna;
    }

    public void setPercentualHeDiurna(BigDecimal percentualHeDiurna) {
        this.percentualHeDiurna = percentualHeDiurna;
    }

    public BigDecimal getPercentualHeNoturna() {
        return percentualHeNoturna;
    }

    public void setPercentualHeNoturna(BigDecimal percentualHeNoturna) {
        this.percentualHeNoturna = percentualHeNoturna;
    }

    public String getDuracaoHoraNoturna() {
        return duracaoHoraNoturna;
    }

    public void setDuracaoHoraNoturna(String duracaoHoraNoturna) {
        this.duracaoHoraNoturna = duracaoHoraNoturna;
    }

    public String getTratamentoHoraMais() {
        return tratamentoHoraMais;
    }

    public void setTratamentoHoraMais(String tratamentoHoraMais) {
        this.tratamentoHoraMais = tratamentoHoraMais;
    }

    public String getTratamentoHoraMenos() {
        return tratamentoHoraMenos;
    }

    public void setTratamentoHoraMenos(String tratamentoHoraMenos) {
        this.tratamentoHoraMenos = tratamentoHoraMenos;
    }

  /*  public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }*/


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoParametro[id=" + id + "]";
    }

}
