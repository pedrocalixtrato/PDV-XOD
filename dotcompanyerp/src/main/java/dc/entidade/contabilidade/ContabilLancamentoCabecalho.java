package dc.entidade.contabilidade;

import java.io.Serializable;
import java.math.BigDecimal;
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

import dc.entidade.administrativo.empresa.EmpresaEntity;

@Entity
@Table(name = "CONTABIL_LANCAMENTO_CABECALHO")
public class ContabilLancamentoCabecalho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_LANCAMENTO")
    private Date dataLancamento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INCLUSAO")
    private Date dataInclusao;
    
    //@Column(name = "TIPO")
    //private TipoType tipo;
    
    @Column(name = "LIBERADO")
    private String liberado;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @JoinColumn(name = "ID_CONTABIL_LOTE", referencedColumnName = "ID")
    @ManyToOne
    private ContabilLote contabilLote;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaEntity empresa;

    public ContabilLancamentoCabecalho() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

   /* public TipoType getTipo() {
        return tipo;
    }

    public void setTipo(TipoType tipo) {
        this.tipo = tipo;
    }*/

    public String getLiberado() {
        return liberado;
    }

    public void setLiberado(String liberado) {
        this.liberado = liberado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ContabilLote getContabilLote() {
        return contabilLote;
    }

    public void setContabilLote(ContabilLote contabilLote) {
        this.contabilLote = contabilLote;
    }

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contabilidade.java.ContabilLancamentoCabecalho[id=" + id + "]";
    }

}
