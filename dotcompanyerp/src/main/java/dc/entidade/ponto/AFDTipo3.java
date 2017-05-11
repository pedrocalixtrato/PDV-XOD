package dc.entidade.ponto;

import java.util.Date;

import dc.entidade.framework.AbstractModel;
import dc.entidade.geral.pessoal.ColaboradorEntity;


public class AFDTipo3 extends AbstractModel<Integer>{

    private static final long serialVersionUID = 1L;
    private Long sequencial;
    private Date dataMarcacao;
    private String horaMarcacao;
    private String pisEmpregado;
    private String numeroSerieRelogioPonto;
    private String situacaoRegistro;
    private ColaboradorEntity colaborador;
    private PontoHorario pontoHorario;
    private String parEntradaSaida;
    private Boolean desconsiderar;
    private String tipoRegistro;
    private String justificativa;

    /**
     * @return the sequencial
     */
    public Long getSequencial() {
        return sequencial;
    }

    /**
     * @param sequencial the sequencial to set
     */
    public void setSequencial(Long sequencial) {
        this.sequencial = sequencial;
    }

    /**
     * @return the dataMarcacao
     */
    public Date getDataMarcacao() {
        return dataMarcacao;
    }

    /**
     * @param dataMarcacao the dataMarcacao to set
     */
    public void setDataMarcacao(Date dataMarcacao) {
        this.dataMarcacao = dataMarcacao;
    }

    /**
     * @return the horaMarcacao
     */
    public String getHoraMarcacao() {
        return horaMarcacao;
    }

    /**
     * @param horaMarcacao the horaMarcacao to set
     */
    public void setHoraMarcacao(String horaMarcacao) {
        this.horaMarcacao = horaMarcacao;
    }

    /**
     * @return the pisEmpregado
     */
    public String getPisEmpregado() {
        return pisEmpregado;
    }

    /**
     * @param pisEmpregado the pisEmpregado to set
     */
    public void setPisEmpregado(String pisEmpregado) {
        this.pisEmpregado = pisEmpregado;
    }

    /**
     * @return the numeroSerieRelogioPonto
     */
    public String getNumeroSerieRelogioPonto() {
        return numeroSerieRelogioPonto;
    }

    /**
     * @param numeroSerieRelogioPonto the numeroSerieRelogioPonto to set
     */
    public void setNumeroSerieRelogioPonto(String numeroSerieRelogioPonto) {
        this.numeroSerieRelogioPonto = numeroSerieRelogioPonto;
    }

    /**
     * @return the situacaoRegistro
     */
    public String getSituacaoRegistro() {
        return situacaoRegistro;
    }

    /**
     * @param situacaoRegistro the situacaoRegistro to set
     */
    public void setSituacaoRegistro(String situacaoRegistro) {
        this.situacaoRegistro = situacaoRegistro;
    }

    /**
     * @return the colaborador
     */
    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    /**
     * @param colaborador the colaborador to set
     */
    public void setColaborador(ColaboradorEntity colaborador) {
        this.colaborador = colaborador;
    }

    /**
     * @return the pontoHorario
     */
    public PontoHorario getPontoHorario() {
        return pontoHorario;
    }

    /**
     * @param pontoHorario the pontoHorario to set
     */
    public void setPontoHorario(PontoHorario pontoHorario) {
        this.pontoHorario = pontoHorario;
    }

    /**
     * @return the parEntradaSaida
     */
    public String getParEntradaSaida() {
        return parEntradaSaida;
    }

    /**
     * @param parEntradaSaida the parEntradaSaida to set
     */
    public void setParEntradaSaida(String parEntradaSaida) {
        this.parEntradaSaida = parEntradaSaida;
    }

    /**
     * @return the desconsiderar
     */
    public Boolean getDesconsiderar() {
        return desconsiderar;
    }

    /**
     * @param desconsiderar the desconsiderar to set
     */
    public void setDesconsiderar(Boolean desconsiderar) {
        this.desconsiderar = desconsiderar;
    }

    /**
     * @return the tipoRegistro
     */
    public String getTipoRegistro() {
        return tipoRegistro;
    }

    /**
     * @param tipoRegistro the tipoRegistro to set
     */
    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    /**
     * @return the justificativa
     */
    public String getJustificativa() {
        return justificativa;
    }

    /**
     * @param justificativa the justificativa to set
     */
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return sequencial.intValue();
	}


}
