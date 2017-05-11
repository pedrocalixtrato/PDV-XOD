package dc.controller.folhapagamento.ausencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class VendedorListController extends CRUDListController<VendedorEntity> {
	
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * DAO'S
		 */

		@Autowired
		private VendedorDAO pDAO;

		/**
		 * CONTROLLER'S
		 */

		@Autowired
		private VendedorFormController pController;

		@Override
		public String[] getColunas() {
			return new String[] { "" };
		}

		@Override
		public Class<? super VendedorEntity> getEntityClass() {
			return VendedorEntity.class;
		}

		@Override
		protected String getTitulo() {
			return super.getTitulo(this);
		}

		@Override
		protected List<VendedorEntity> pesquisa(String valor) {
			List<VendedorEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		}

		@Override
		protected CRUDFormController<VendedorEntity> getFormController() {
			return this.pController;
		}

		// Identificador da VIEW, para posterior uso nas urls de navegacao
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
		protected List<VendedorEntity> pesquisaDefault() {
			List<VendedorEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		}
		
		@Override
		protected void actionRemoverSelecionados() {
			super.actionRemoverSelecionados();

		}

}
