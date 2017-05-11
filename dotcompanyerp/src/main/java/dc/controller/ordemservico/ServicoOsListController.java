package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ServicoOsEntity;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ServicoOsListController extends CRUDListController<ServicoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ServicoOsFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"nome","grupo","subGrupo","aliquotaIssqn","valorComissaoTecnico","valorComissaoVendedor","valorServico","valorMinimoServico",
				"dataCadastro","observacao"};
	}

	@Override
	protected String getTitulo() {
		return "Serviço OS";
	}

	@Override
	protected List<ServicoOsEntity> pesquisa(String valor) {
		try {
			List<ServicoOsEntity> auxLista = (List<ServicoOsEntity>) this.formController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
		
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaServico";
	}

	@Override
	protected CRUDFormController<ServicoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ServicoOsEntity> getEntityClass() {
		return ServicoOsEntity.class;
	}

	@Override
	protected List<ServicoOsEntity> pesquisaDefault() {
		try {
			List<ServicoOsEntity> auxLista = (List<ServicoOsEntity>) this.formController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}
}
