package dc.entidade.contabilidade;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CONTABIL_LANCAMENTO_DETALHE")
public class ContabilLancamentoDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "HISTORICO")
    private String historico;
    @Column(name = "VALOR")
    private BigDecimal valor;
    
   /* @Enumerated(EnumType.STRING)
	@Field
	@Caption()
	@Column(name = "TIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoType tipo;*/
    
    @JoinColumn(name = "ID_CONTABIL_LANCAMENTO_CAB", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContabilLancamentoCabecalho contabilLancamentoCabecalho;
    @JoinColumn(name = "ID_CONTABIL_HISTORICO", referencedColumnName = "ID")
    @ManyToOne
    private ContabilHistorico contabilHistorico;
    @JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContabilContaEntity contabilConta;

    public ContabilLancamentoDetalhe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

   /* public TipoType getTipo() {
        return tipo;
    }

    public void setTipo(TipoType tipo) {
        this.tipo = tipo;
    }*/

    public ContabilLancamentoCabecalho getContabilLancamentoCabecalho() {
        return contabilLancamentoCabecalho;
    }

    public void setContabilLancamentoCabecalho(ContabilLancamentoCabecalho contabilLancamentoCabecalho) {
        this.contabilLancamentoCabecalho = contabilLancamentoCabecalho;
    }

    public ContabilHistorico getContabilHistorico() {
        return contabilHistorico;
    }

    public void setContabilHistorico(ContabilHistorico contabilHistorico) {
        this.contabilHistorico = contabilHistorico;
    }

    public ContabilContaEntity getContabilConta() {
        return contabilConta;
    }

    public void setContabilConta(ContabilContaEntity contabilConta) {
        this.contabilConta = contabilConta;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contabilidade.java.ContabilLancamentoDetalhe[id=" + id + "]";
    }

}
