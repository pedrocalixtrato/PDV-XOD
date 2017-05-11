package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.NivelFormacaoEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class NivelFormacaoListController extends
		CRUDListController<NivelFormacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private NivelFormacaoFormController nivelFormacaoFormController;

	/**
	 * CONSTRUTOR
	 */

	public NivelFormacaoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<NivelFormacaoEntity> getFormController() {
		return nivelFormacaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome","grauInstrucaoCaged","grauInstrucaoSefip","grauInstrucaoRais", "descricao" };
	}

	@Override
	public Class<? super NivelFormacaoEntity> getEntityClass() {
		return NivelFormacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<NivelFormacaoEntity> pesquisa(String valor) {
		try {
			List<NivelFormacaoEntity> auxLista = (List<NivelFormacaoEntity>) this.nivelFormacaoFormController
					.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<NivelFormacaoEntity> pesquisaDefault() {
		try {
			List<NivelFormacaoEntity> auxLista = (List<NivelFormacaoEntity>) this.nivelFormacaoFormController
					.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}