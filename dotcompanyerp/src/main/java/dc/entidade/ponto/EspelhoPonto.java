package dc.entidade.ponto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EspelhoPonto implements Serializable{

    private static final long serialVersionUID = 1L;
    private String cnpjEmpregador;
    private String nomeEmpregador;
    private String ederecoEmpregador;
    private String pisEmpregado;
    private String nomeEmpregado;
    private Date dataAdmissao;
    private Date dataEmissaoRelatorio;
    private List<PontoHorario> listaHorarios;
    private List<EspelhoPontoPeriodo> listaPeriodo;

    /**
     * @return the cnpjEmpregador
     */
    public String getCnpjEmpregador() {
        return cnpjEmpregador;
    }

    /**
     * @param cnpjEmpregador the cnpjEmpregador to set
     */
    public void setCnpjEmpregador(String cnpjEmpregador) {
        this.cnpjEmpregador = cnpjEmpregador;
    }

    /**
     * @return the nomeEmpregador
     */
    public String getNomeEmpregador() {
        return nomeEmpregador;
    }

    /**
     * @param nomeEmpregador the nomeEmpregador to set
     */
    public void setNomeEmpregador(String nomeEmpregador) {
        this.nomeEmpregador = nomeEmpregador;
    }

    /**
     * @return the ederecoEmpregador
     */
    public String getEderecoEmpregador() {
        return ederecoEmpregador;
    }

    /**
     * @param ederecoEmpregador the ederecoEmpregador to set
     */
    public void setEderecoEmpregador(String ederecoEmpregador) {
        this.ederecoEmpregador = ederecoEmpregador;
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
     * @return the nomeEmpregado
     */
    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    /**
     * @param nomeEmpregado the nomeEmpregado to set
     */
    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    /**
     * @return the dataAdmissao
     */
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * @param dataAdmissao the dataAdmissao to set
     */
    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    /**
     * @return the dataEmissaoRelatorio
     */
    public Date getDataEmissaoRelatorio() {
        return dataEmissaoRelatorio;
    }

    /**
     * @param dataEmissaoRelatorio the dataEmissaoRelatorio to set
     */
    public void setDataEmissaoRelatorio(Date dataEmissaoRelatorio) {
        this.dataEmissaoRelatorio = dataEmissaoRelatorio;
    }

    /**
     * @return the listaHorarios
     */
    public List<PontoHorario> getListaHorarios() {
        return listaHorarios;
    }

    /**
     * @param listaHorarios the listaHorarios to set
     */
    public void setListaHorarios(List<PontoHorario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    /**
     * @return the listaPeriodo
     */
    public List<EspelhoPontoPeriodo> getListaPeriodo() {
        return listaPeriodo;
    }

    /**
     * @param listaPeriodo the listaPeriodo to set
     */
    public void setListaPeriodo(List<EspelhoPontoPeriodo> listaPeriodo) {
        this.listaPeriodo = listaPeriodo;
    }

}