package dc.entidade.framework;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.sun.istack.logging.Logger;
import com.vaadin.navigator.View;

import dc.anotacoes.Caption;
import dc.entidade.sistema.ConfiguracaoContaEmpresa;
import dc.framework.BlankModuleView;
import dc.framework.ModuleView;
import dc.visao.framework.geral.MainController;

@Entity
@Table(name = "fm_modulo", uniqueConstraints = @UniqueConstraint(columnNames = { "URL_ID" }))
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FmModulo extends AbstractModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ID_MODULO_ADM_DC = -1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Field
	@Caption("URL (identificador)")
	@Column(name = "URL_ID")
	private String urlID;

	@Field()
	@Caption("Caption")
	@Column(name = "CAPTION")
	private String caption;

	@Field
	@Caption("Nome da View")
	@Column(name = "VIEW_NAME")
	private String viewName;

	@OneToMany(mappedBy = "fmModulo", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FmMenu> menus;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "configuracao_conta_empresa_fm_modulo", joinColumns = { @JoinColumn(name = "modulos_id") }, inverseJoinColumns = { @JoinColumn(name = "configuracao_conta_empresa_id ") })
	private List<ConfiguracaoContaEmpresa> configuracaoContaEmpresa;

	private static Logger logger = Logger.getLogger(FmModulo.class);

	public FmModulo(String caption, int id, String url, String viewName) {
		this.caption = caption;
		this.viewName = viewName;
		this.id = id;
		this.urlID = url;
	}

	public FmModulo() {

	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlID() {
		return urlID;
	}

	public void setUrlID(String urlID) {
		this.urlID = urlID;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void loadRoute(HashMap<String, View> routes, MainController controller) {
		if (isBlank()) {
			BlankModuleView blank = new BlankModuleView(this, controller);
			routes.put(getUrlID(), blank);

			logger.info("loading route for module with blank view. module caption: " + this.getCaption());
		} else {
			try {
				logger.info("loading route for module with view name: " + this.getViewName());
				Class viewClass = Class.forName(this.getViewName());

				Object view = viewClass.getDeclaredConstructor(FmModulo.class, MainController.class).newInstance(this, controller);
				routes.put(this.getUrlID(), (View) view);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				Class viewClass;

				try {
					viewClass = Class.forName(this.getViewName());
					Object view = viewClass.newInstance();
					routes.put(this.getUrlID(), (View) view);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean isBlank() {
		return (this.viewName == ModuleView.BLANK_VIEW || this.viewName == null || this.viewName.trim().equals(""));
	}

	public List<FmMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<FmMenu> menus) {
		this.menus = menus;
	}

	public static FmModulo loadSystemInstance() {
		return new FmModulo("ADM DOTCOMPANY", FmModulo.ID_MODULO_ADM_DC, "adm_dotcompany", "dc.visao.modulos.AdmDotCompanyView");
	}

	/*
	 * public int hashCode() { return HashCodeBuilder.reflectionHashCode(this,
	 * new String[] { "id", "menus" }); }
	 * 
	 * @Override public boolean equals(Object object) { if (object instanceof
	 * FmModulo == false) return false; if (this == object) return true; final
	 * FmModulo other = (FmModulo) object; return
	 * EqualsBuilder.reflectionEquals(this, other, new String[] { "id", "urlID"
	 * }); }
	 */

	/*
	 * @Override public String toString() { return getCaption() + ": URL => " +
	 * getUrlID(); }
	 */

	/** HASHCODE E EQUALS */

	@Override
	public String toString() {
		return caption;
	}

	public List<ConfiguracaoContaEmpresa> getConfiguracaoContaEmpresa() {
		return configuracaoContaEmpresa;
	}

	public void setConfiguracaoContaEmpresa(List<ConfiguracaoContaEmpresa> configuracaoContaEmpresa) {
		this.configuracaoContaEmpresa = configuracaoContaEmpresa;
	}

}