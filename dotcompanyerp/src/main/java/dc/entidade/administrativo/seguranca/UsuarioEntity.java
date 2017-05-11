package dc.entidade.administrativo.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.sistema.ContaEmpresa;
import dc.visao.spring.DCDateBridge;

@Entity
@Table(name = "usuario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class UsuarioEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable, UserDetails {
	private static List<GrantedAuthority> grantedAuths = new ArrayList<>();
	
	static{
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usuario_id_seq")
	@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Login")
	@Column(name = "LOGIN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String login = "";

	@Field
	@Caption("Senha")
	@Column(name = "SENHA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String senha = "";

	@Temporal(TemporalType.DATE)
	@Field(analyze=Analyze.NO)
	@FieldBridge(impl = DCDateBridge.class )
	@Caption("Data de cadastro")
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;

	@Field
	@Caption("Administrador")
	@Column(name = "ADMINISTRADOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean administrador = Boolean.FALSE;

	@Field
	@Caption("Confirmado")
	@Column(name = "CONFIRMADO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private boolean confirmado;

	@Field
	@Caption("Nome")
	@Column(name = "usernome")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String usernome = "";
	
	@Field
	@Caption("Super Administrador")
	@Column(name = "SUPER_ADMINISTRADOR")
	private Boolean superAdministrador = Boolean.FALSE;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
	private ColaboradorEntity colaborador;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PAPEL", referencedColumnName = "ID")
	private PapelEntity papel;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.DETACH })
	private ContaEmpresa conta;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	private Integer consultaMultiempresa = new Integer(0);

	/**
	 * CONSTRUTOR
	 */

	public UsuarioEntity() {

	}

	public UsuarioEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = (login == null ? "".trim() : login.trim());
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = (senha == null ? "".trim() : senha.trim());
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = (administrador == null ? Boolean.FALSE
				: administrador);
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public String getUsernome() {
		return usernome;
	}

	public void setUsernome(String usernome) {
		this.usernome = (usernome == null ? "".trim() : usernome.trim());
	}

	/** @return the colaborador */
	public ColaboradorEntity getColaborador() {
		return colaborador;
	}

	/**
	 * @param colaborador
	 *            the colaborador to set
	 */
	public void setColaborador(ColaboradorEntity colaborador) {
		this.colaborador = colaborador;
	}

	/** @return the papel */
	public PapelEntity getPapel() {
		return papel;
	}

	/**
	 * @param papel
	 *            the papel to set
	 */
	public void setPapel(PapelEntity papel) {
		this.papel = papel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuths;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public ContaEmpresa getConta() {
		return conta;
	}

	public void setConta(ContaEmpresa conta) {
		this.conta = conta;
	}

	public Integer getConsultaMultiempresa() {
		return consultaMultiempresa;
	}

	public void setConsultaMultiempresa(Integer consultaMultiempresa) {
		this.consultaMultiempresa = (consultaMultiempresa == null ? new Integer(
				0) : consultaMultiempresa);
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Boolean getSuperAdministrador() {
		return superAdministrador;
	}

	public void setSuperAdministrador(Boolean superAdministrador) {
		this.superAdministrador = superAdministrador;
	}

}