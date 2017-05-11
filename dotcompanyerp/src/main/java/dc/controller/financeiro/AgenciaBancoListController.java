package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.model.dao.geral.pessoal.IAgenciaBancoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class AgenciaBancoListController extends
		CRUDListController<AgenciaBancoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IAgenciaBancoDAO dao;

	@Autowired
	private AgenciaBancoFormController agenciaBancoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "banco","codigo","nome", "logradouro","numero","bairro","cep","municipio",
				"uf","telefone","contato","gerente","observacao" };
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
	public Class<? super AgenciaBancoEntity> getEntityClass() {
		return AgenciaBancoEntity.class;
	}

	@Override
	protected CRUDFormController<AgenciaBancoEntity> getFormController() {
		return agenciaBancoFormController;
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}
	
	@Override
	protected List<AgenciaBancoEntity> pesquisa(String valor) {
		try {
			
			List<AgenciaBancoEntity> auxLista = this.dao.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<AgenciaBancoEntity> pesquisaDefault() {
		try {
			
			List<AgenciaBancoEntity> auxLista = (List<AgenciaBancoEntity>) this.dao.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}