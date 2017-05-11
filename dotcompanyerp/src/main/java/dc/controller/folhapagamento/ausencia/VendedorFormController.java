package dc.controller.folhapagamento.ausencia;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.servicos.dao.folhapagamento.VendedorDAO;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class VendedorFormController extends CRUDFormController<VendedorEntity> {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Autowired
		private VendedorDAO pDAO;

		/** ENTITIES */

		private VendedorEntity pEntity;

		/** CONSTRUTOR */

		public VendedorFormController() {
			if (this.pEntity == null) {
				this.pEntity = new VendedorEntity();
			}
		}

		@Override
		protected String getNome() {
			return "Vendedor";
		}

		@Override
		protected Component getSubView() {
			return null;
		}

		@Override
		protected void actionSalvar() {
			try {
				this.pDAO.saveOrUpdate(this.pEntity);

				notifiyFrameworkSaveOK(this.pEntity);
			} catch (Exception e) {
				e.printStackTrace();

				mensagemErro(e.getMessage());
			}
		}

		@Override
		protected void carregar(Serializable id) {
			try {
				this.pEntity = this.pDAO.find(id);

			} catch (Exception e) {
				e.printStackTrace();

				mensagemErro(e.getMessage());
			}
		}

		@Override
		protected void initSubView() {
		}

		/*
		 * Deve sempre atribuir a current Bean uma nova instancia do bean do
		 * formulario.
		 */
		@Override
		protected void criarNovoBean() {
			try {
				this.pEntity = new VendedorEntity();


			} catch (Exception e) {
				e.printStackTrace();

				mensagemErro(e.getMessage());
			}
		}

		@Override
		protected void remover(List<Serializable> ids) {
			try {
				this.pDAO.deleteAllByIds(ids);

				mensagemRemovidoOK();
			} catch (Exception e) {
				e.printStackTrace();

				mensagemErro(e.getMessage());
			}
		}

		/* Implementar validacao de campos antes de salvar. */
		@Override
		protected boolean validaSalvar() {
			return true;
		}

		@Override
		protected void removerEmCascata(List<Serializable> ids) {

		}

		@Override
		public String getViewIdentifier() {
			// TODO Auto-generated method stub
			return ClassUtils.getUrl(this);
		}

		@Override
		protected boolean isFullSized() {
			return true;
		}

		@Override
		public VendedorEntity getModelBean() {
			return pEntity;
		}

}
