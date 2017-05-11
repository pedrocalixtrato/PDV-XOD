
package dc.entidade.tributario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "tribut_configura_of_gt")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class ConfiguracaoTributariaEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cfg")
	@SequenceGenerator(name = "cfg", sequenceName = "tribut_configura_of_gt_id_seq", allocationSize = 1)
	private Integer id;
//
//	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
//	@ManyToOne(optional = false)
//	private Empresa empresa;

	@JoinColumn(name = "id_tribut_grupo_tributario", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Grupo Tributário")
	private GrupoTributarioEntity grupoTributario;

	@JoinColumn(name = "id_tribut_operacao_fiscal", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	@Caption("Operação Fiscal")
	private OperacaoFiscalEntity operacaoFiscal;

	@OneToMany(mappedBy="configuracaoTributaria",fetch=FetchType.EAGER)
	private List<IcmsConfiguracaoTributariaEntity> listaIcms = new ArrayList<IcmsConfiguracaoTributariaEntity>();

	@OneToOne(mappedBy="configuracaoTributaria")
	private PisConfiguracaoTributariaEntity pis ;
	
	@OneToOne(mappedBy="configuracaoTributaria")
	private CofinsConfiguracaoTributariaEntity cofins ;
	
	@OneToOne(mappedBy="configuracaoTributaria")
	private IpiConfiguracaoTributariaEntity ipi ;
		
	public ConfiguracaoTributariaEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}

	public GrupoTributarioEntity getGrupoTributario() {
		return grupoTributario;
	}

	public void setGrupoTributario(GrupoTributarioEntity grupoTributario) {
		this.grupoTributario = grupoTributario;
	}

	public OperacaoFiscalEntity getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscalEntity operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
	}

	public List<IcmsConfiguracaoTributariaEntity> getListaIcms() {
		return listaIcms;
	}

	public void setListaIcms(List<IcmsConfiguracaoTributariaEntity> listaIcms) {
		this.listaIcms = listaIcms;
	}

	public void adicionarIcms(IcmsConfiguracaoTributariaEntity icms){
		getListaIcms().add(icms);
		icms.setConfiguracaoTributaria(this);
	}

	public PisConfiguracaoTributariaEntity getPis() {
		return pis;
	}

	public void setPis(PisConfiguracaoTributariaEntity pis) {
		this.pis = pis;
	}

	public CofinsConfiguracaoTributariaEntity getCofins() {
		return cofins;
	}

	public void setCofins(CofinsConfiguracaoTributariaEntity cofins) {
		this.cofins = cofins;
	}

	public IpiConfiguracaoTributariaEntity getIpi() {
		return ipi;
	}

	public void setIpi(IpiConfiguracaoTributariaEntity ipi) {
		this.ipi = ipi;
	}
	
	

}
