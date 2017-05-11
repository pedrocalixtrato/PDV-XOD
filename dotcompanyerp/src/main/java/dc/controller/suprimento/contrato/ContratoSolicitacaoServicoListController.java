package dc.controller.suprimento.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.contrato.SolicitacaoServicoEntity;
import dc.servicos.dao.suprimentos.contrato.ISolicitacaoServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContratoSolicitacaoServicoListController extends
		CRUDListController<SolicitacaoServicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISolicitacaoServicoDAO dao;

	@Autowired
	ContratoSolicitacaoServicoFormController contratoSolicitacaoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "fornecedor","cliente","setor","colaborador","contratoTipoServico","dataDesejadaInicio",
				"dataSolicitacao", "urgente", "descricao", "statusSolicitacao"  };
	}

	@Override
	public Class<? super SolicitacaoServicoEntity> getEntityClass() {
		return SolicitacaoServicoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Solicitação de serviço";
	}

	@Override
	protected List<SolicitacaoServicoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SolicitacaoServicoEntity> getFormController() {
		return contratoSolicitacaoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<SolicitacaoServicoEntity> pesquisaDefault() {
		return (List<SolicitacaoServicoEntity>) dao.getAll(getEntityClass());
	}

}