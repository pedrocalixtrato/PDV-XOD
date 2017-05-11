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

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "PONTO_BANCO_HORAS_UTILIZACAO")
public class PontoBancoHorasUtilizacao extends AbstractMultiEmpresaModel<Integer>  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_UTILIZACAO")
    private Date dataUtilizacao;
    @Column(name = "QUANTIDADE_UTILIZADA")
    private String quantidadeUtilizada;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @JoinColumn(name = "ID_PONTO_BANCO_HORAS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PontoBancoHoras pontoBancoHoras;

    public PontoBancoHorasUtilizacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataUtilizacao() {
        return dataUtilizacao;
    }

    public void setDataUtilizacao(Date dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    public String getQuantidadeUtilizada() {
        return quantidadeUtilizada;
    }

    public void setQuantidadeUtilizada(String quantidadeUtilizada) {
        this.quantidadeUtilizada = quantidadeUtilizada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public PontoBancoHoras getPontoBancoHoras() {
        return pontoBancoHoras;
    }

    public void setPontoBancoHoras(PontoBancoHoras pontoBancoHoras) {
        this.pontoBancoHoras = pontoBancoHoras;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoBancoHorasUtilizacao[id=" + id + "]";
    }

}
