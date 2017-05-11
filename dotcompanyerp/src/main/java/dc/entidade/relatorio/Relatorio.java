package dc.entidade.relatorio;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.SeguimentoEntity;

@Entity
@Table(name = "relatorio")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Relatorio extends AbstractModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5030551518611792445L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "jasper_path")
	@Caption(value = "Caminho Relatório")
	private String jasperPath;
	@Column()
	@Caption(value = "Nome")
	@Field
	private String nome;
	@Column()
	@Caption(value = "Descrição")
	@Field
	private String descricao;

	@Column(name = "tela_parametros")
	@Caption(value = "Classe Tela de Parâmetros")
	private String telaParametros;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = FmMenu.class)
	@JoinColumn(name = "id_fm_menu")
	private FmMenu menu;

	@Column()
	@Field
	private Integer tipo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relatorio_papel", joinColumns = { @JoinColumn(name = "relatorio_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "papel_id", nullable = false, updatable = false) })
	private Set<PapelEntity> papeis = new HashSet<>();;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relatorio_seguimento", joinColumns = { @JoinColumn(name = "relatorio_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "seguimento_id", nullable = false, updatable = false) })
	private Set<SeguimentoEntity> seguimentos = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relatorio_empresa", joinColumns = { @JoinColumn(name = "relatorio_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "empresa_id", nullable = false, updatable = false) })
	private Set<EmpresaEntity> empresas = new HashSet<>();;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relatorio_usuario", joinColumns = { @JoinColumn(name = "relatorio_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "usuario_id", nullable = false, updatable = false) })
	private Set<UsuarioEntity> usuarios = new HashSet<>();;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	private Relatorio relatorioParent;

	@OneToMany(mappedBy = "relatorioParent")
	private Set<Relatorio> relatoriosChildren = new HashSet<Relatorio>();

	@Override
	public Integer getId() {
		return id;
	}

	public String getJasperPath() {
		return jasperPath;
	}

	public void setJasperPath(String jasperPath) {
		this.jasperPath = jasperPath;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FmMenu getMenu() {
		return menu;
	}

	public void setMenu(FmMenu menu) {
		this.menu = menu;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getTelaParametros() {
		return telaParametros;
	}

	public void setTelaParametros(String telaParametros) {
		this.telaParametros = telaParametros;
	}

	public Set<SeguimentoEntity> getSeguimentos() {
		return seguimentos;
	}

	public void setSeguimentos(Set<SeguimentoEntity> seguimentos) {
		this.seguimentos = seguimentos;
	}

	public Set<PapelEntity> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<PapelEntity> papeis) {
		this.papeis = papeis;
	}

	public Set<EmpresaEntity> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<EmpresaEntity> empresas) {
		this.empresas = empresas;
	}

	public Set<UsuarioEntity> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}

	public Relatorio getRelatorioParent() {
		return relatorioParent;
	}

	public void setRelatorioParent(Relatorio relatorioParent) {
		this.relatorioParent = relatorioParent;
	}

	public Set<Relatorio> getRelatoriosChildren() {
		return relatoriosChildren;
	}

	public void setRelatoriosChildren(Set<Relatorio> relatoriosChildren) {
		this.relatoriosChildren = relatoriosChildren;
	}

	@Override
	public String toString() {
		return this.getNome();
	}

}
