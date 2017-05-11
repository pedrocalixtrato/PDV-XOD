package dc.entidade.framework;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.sun.istack.logging.Logger;
import com.vaadin.ui.MenuBar.Command;

import dc.anotacoes.Caption;
import dc.entidade.relatorio.Relatorio;

@Entity
@Table(name = "fm_menu")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FmMenu extends AbstractModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fm_menu_id_seq")
	@SequenceGenerator(name = "fm_menu_id_seq", sequenceName = "fm_menu_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	private Integer id;

	@Field
	@Caption("URL (identificador)")
	@Column(name = "URL_ID")
	private String urlId = "";

	@Field()
	@Caption("Caption")
	@Column(name = "CAPTION")
	@ComboValue
	private String caption = "";

	@Field()
	@Caption("Classe do controller")
	@Column(name = "controller", nullable = false)
	private String controllerClass = "";

	@Field()
	@Caption("Permissão de operação")
	@Column(name = "sn_permissao_operacao")
	private Integer permissaoOperacao = new Integer(0);

	@Field()
	@Caption("Consulta por multiempresa")
	@Column(name = "sn_consulta_multiempresa")
	private Integer consultaMultiempresa = new Integer(0);

	@Field()
	@Caption("Tabela com colunas filtráveis")
	@Column(name = "sn_consulta_filter_table")
	private Integer consultaFilterTable = new Integer(0);

	/** REFERENCIA - FK */

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = FmModulo.class)
	private FmModulo fmModulo;

	@ManyToOne(fetch = FetchType.EAGER)
	private FmMenu parent;
	
	@Field()
	@Column(name = "only_superadmin")
	/**
	 *  Define se somente o administrador pode visualizar todos os registros da listagem.
	 */
	private Boolean superAdministradorOnly = false;

	/** REFERENCIA - LIST */

	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private List<FmMenu> menusFilho;

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<PapelMenu> papeisMenu;

	@OneToMany(mappedBy = "menu", orphanRemoval = true)
	private List<Relatorio> relatorios;

	/** TRANSIENT */

	@Transient
	private Integer parentId = new Integer(0);

	/** LOG */

	private static Logger logger = Logger.getLogger(FmMenu.class);

	/** CONSTRUTOR */
	public FmMenu() {

	}

	public FmMenu(Integer id) {
		this.id = id;
	}

	/** GET / SET */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = (urlId == null ? "" : urlId);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = (caption == null ? "" : caption);
	}

	public String getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(String controllerClass) {
		this.controllerClass = (controllerClass == null ? "" : controllerClass);
	}

	public Integer getPermissaoOperacao() {
		return permissaoOperacao;
	}

	public void setPermissaoOperacao(Integer permissaoOperacao) {
		this.permissaoOperacao = (permissaoOperacao == null ? new Integer(0) : permissaoOperacao);
	}

	public Integer getConsultaMultiempresa() {
		return consultaMultiempresa;
	}

	public boolean isConsultaMultiempresa() {
		return new Integer(0).equals(consultaMultiempresa);
	}

	public void setConsultaMultiempresa(Integer consultaMultiempresa) {
		this.consultaMultiempresa = (consultaMultiempresa == null ? new Integer(0) : consultaMultiempresa);
	}

	public FmModulo getFmModulo() {
		return fmModulo;
	}

	public void setFmModulo(FmModulo fmModulo) {
		this.fmModulo = fmModulo;
	}

	public FmMenu getParent() {
		return parent;
	}

	public void setParent(FmMenu parent) {
		this.parent = parent;
	}

	public List<FmMenu> getMenusFilho() {
		return menusFilho;
	}

	public void setMenusFilho(List<FmMenu> menusFilho) {
		this.menusFilho = menusFilho;
	}

	public List<PapelMenu> getPapeisMenu() {
		return papeisMenu;
	}

	public void setPapeisMenu(List<PapelMenu> papeisMenu) {
		this.papeisMenu = papeisMenu;
	}

	public Integer getParentId() {
		if (parent != null) {
			return parent.id;
		} else {
			return parentId;
		}
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Command getCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getConsultaFilterTable() {
		return consultaFilterTable;
	}

	public void setConsultaFilterTable(Integer consultaFilterTable) {
		this.consultaFilterTable = consultaFilterTable;
	}

	public boolean isConsultaFilterTable() {
		return new Integer(1).equals(consultaFilterTable);
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public Boolean getSuperAdministradorOnly() {
		return superAdministradorOnly;
	}

	public void setSuperAdministradorOnly(Boolean superAdministradorOnly) {
		this.superAdministradorOnly = superAdministradorOnly;
	}


	/** HASHCODE E EQUALS */

	// @Override
	// public String toString() {
	// return ToStringBuilder.reflectionToString(this);
	// }

}