package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.model.dao.geral.pessoal.IFluxoCaixaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FluxoCaixaListController extends CRUDListController<FluxoCaixaEntity> {
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Autowired
		private IFluxoCaixaDAO dao;

		@Autowired
		private FluxoCaixaFormController fluxoCaixaFormController;

		@Override
		public String[] getColunas() {
			
			return new String[] { "fluxoCaixaPeriodo","nome","dataInicial","dataBase","numeroPeriodos","descricao" };
		}

		@Override
		public Class<? super FluxoCaixaEntity> getEntityClass() {
			return FluxoCaixaEntity.class;
		}

		@Override
		protected String getTitulo() {
			return "Fluxo de Caixa";
		}

		@Override
		protected List<FluxoCaixaEntity> pesquisa(String valor) {
			return dao.fullTextSearch(valor);
		}

		@Override
		protected CRUDFormController<FluxoCaixaEntity> getFormController() {
			return fluxoCaixaFormController;
		}

		@Override
		public String getViewIdentifier() {
			return ClassUtils.getUrl(this);
		}
		
		@Override
		protected void actionRemoverSelecionados() {
			super.actionRemoverSelecionados();

		}

		@Override
		protected boolean deletaEmCascata() {
			return false;
		}

		@Override
		protected List<FluxoCaixaEntity> pesquisaDefault() {
			return (List<FluxoCaixaEntity>) dao.getAll(getEntityClass());
			
		}

}
