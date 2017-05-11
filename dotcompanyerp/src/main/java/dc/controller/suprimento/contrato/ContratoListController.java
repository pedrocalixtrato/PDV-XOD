package dc.controller.suprimento.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.contrato.ContratoEntity;
import dc.servicos.dao.suprimentos.contrato.IContratoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContratoListController extends CRUDListController<ContratoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IContratoDAO dao;

	@Autowired
	ContratoFormController contratoFormController;

	@Override
	public String[] getColunas() {
		return new String[] {"pessoa","tipoContrato","contratoSolicitacaoServico","documento", "numero", "nome", "dataCadastro", "observacao",
				"dataInicioVigencia","dataFimVigencia","diaFaturamento","valor","quantidadeParcelas","intervaloEntreParcelas","descricao"};
	}

	@Override
	public Class<? super ContratoEntity> getEntityClass() {
		return ContratoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Contrato";
	}

	@Override
	protected List<ContratoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ContratoEntity> getFormController() {
		return contratoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ContratoEntity> pesquisaDefault() {
		return (List<ContratoEntity>) dao.getAll(getEntityClass());
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}