package dc.entidade.ponto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dc.entidade.framework.AbstractMultiEmpresaModel;


@Entity
@Table(name = "PONTO_TURMA")
public class PontoTurma extends AbstractMultiEmpresaModel<Integer>  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "NOME")
    private String nome;
    @JoinColumn(name = "ID_PONTO_ESCALA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PontoEscala pontoEscala;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
    	if(codigo != null)
    	{
    		codigo.trim();
    	}
    	
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PontoEscala getPontoEscala() {
        return pontoEscala;
    }

    public void setPontoEscala(PontoEscala pontoEscala) {
        this.pontoEscala = pontoEscala;
    }


    @Override
    public String toString() {
        return "com.t2tierp.ponto.java.PontoTurma[id=" + id + "]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PontoTurma other = (PontoTurma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
