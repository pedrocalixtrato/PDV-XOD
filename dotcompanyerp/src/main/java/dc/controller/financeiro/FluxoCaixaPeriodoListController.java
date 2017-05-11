package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.FluxoCaixaPeriodoEntity;
import dc.servicos.dao.financeiro.IFluxoCaixaPeriodoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FluxoCaixaPeriodoListController extends CRUDListController<FluxoCaixaPeriodoEntity> {

	

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Autowired
			private IFluxoCaixaPeriodoDAO dao;

			@Autowired
			private FluxoCaixaPeriodoFormController fluxoCaixaFormController;

			@Override
			public String[] getColunas() {
				
				return new String[] {"contaCaixa", "nome","periodo" };
			}

			@Override
			public Class<? super FluxoCaixaPeriodoEntity> getEntityClass() {
				return FluxoCaixaPeriodoEntity.class;
			}

			@Override
			protected String getTitulo() {
				return "Fluxo Caixa de Período";
			}

			@Override
			protected List<FluxoCaixaPeriodoEntity> pesquisa(String valor) {
				return dao.fullTextSearch(valor);
			}

			@Override
			protected CRUDFormController<FluxoCaixaPeriodoEntity> getFormController() {
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
			protected List<FluxoCaixaPeriodoEntity> pesquisaDefault() {
				return (List<FluxoCaixaPeriodoEntity>) dao.getAll(getEntityClass());
				
			}
}
