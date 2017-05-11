package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ColaboradorListController extends
		CRUDListController<ColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ColaboradorFormController colaboradorFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "pessoa","tipoColaborador","cargo","setor","sindicato","nivelFormacao","matricula", "codigoTurmaPonto",
				"dataCadastro", "dataAdmissao", "vencimentoFerias",
				"dataTransferencia" };
	}

	@Override
	public Class<? super ColaboradorEntity> getEntityClass() {
		return ColaboradorEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected CRUDFormController<ColaboradorEntity> getFormController() {
		return colaboradorFormController;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<ColaboradorEntity> pesquisa(String valor) {
		try {
			List<ColaboradorEntity> auxLista = (List<ColaboradorEntity>) this.colaboradorFormController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<ColaboradorEntity> pesquisaDefault() {
		try {
			@SuppressWarnings("unchecked")
			List<ColaboradorEntity> auxLista = (List<ColaboradorEntity>) this.colaboradorFormController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}