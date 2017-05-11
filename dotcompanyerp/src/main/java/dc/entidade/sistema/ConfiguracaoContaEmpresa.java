package dc.entidade.sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;

import com.sun.istack.logging.Logger;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.FmModulo;

@Entity
@Table(name = "configuracao_conta_empresa")
@XmlRootElement
public class ConfiguracaoContaEmpresa extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = -4401209508896666278L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@OneToOne()
	@JoinColumn(name = "conta_id")
	private ContaEmpresa conta;

	@Field()
	@Caption("Pergunta 1")
	@Column(name = "pergunta_1")
	private String pergunta1;

	@Field()
	@Caption("Pergunta 2")
	@Column(name = "pergunta_2")
	private String pergunta2;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "configuracao_conta_empresa_fm_modulo", inverseJoinColumns = { @JoinColumn(name = "modulos_id") }, joinColumns = { @JoinColumn(name = "configuracao_conta_empresa_id ") })
	private List<FmModulo> modulos;

	private static Logger logger = Logger.getLogger(ConfiguracaoContaEmpresa.class);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContaEmpresa getConta() {
		return conta;
	}

	public void setConta(ContaEmpresa conta) {
		this.conta = conta;
	}

	public String getPergunta1() {
		return pergunta1;
	}

	public void setPergunta1(String pergunta1) {
		this.pergunta1 = pergunta1;
	}

	public String getPergunta2() {
		return pergunta2;
	}

	public void setPergunta2(String pertegunta2) {
		this.pergunta2 = pergunta2;
	}

	public List<FmModulo> getModulos() {
		if(this.modulos != null){
			Set mSet = new HashSet(this.modulos);
			this.modulos = new ArrayList();
			this.modulos.addAll(mSet);
		}
		return this.modulos;
	}

	public void setModulos(List<FmModulo> modulos) {
		this.modulos = modulos;
	}

}
