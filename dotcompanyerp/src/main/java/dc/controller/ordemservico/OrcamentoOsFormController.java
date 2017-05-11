package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.entidade.ordemservico.OrcamentoOsItemEntity;
import dc.model.dao.ordemservico.ICorDAO;
import dc.model.dao.ordemservico.IOrcamentoItemOsDAO;
import dc.model.dao.ordemservico.IOrcamentoOsDAO;
import dc.servicos.dao.ordemservico.IMarcaOsDAO;
import dc.servicos.dao.ordemservico.IModeloDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.OrcamentoOsFormView;

@Controller
@Scope("prototype")
public class OrcamentoOsFormController extends CRUDFormController<OrcamentoOsEntity> {

	private static final long serialVersionUID = 1L;

	private OrcamentoOsFormView subView;

	@Autowired
	private IMarcaOsDAO marcaDAO;

	@Autowired
	private ICorDAO corDAO;

	@Autowired
	private IModeloDAO modeloDAO;

	@Autowired
	private IOrcamentoOsDAO orcamentoOsDAO;

	@Autowired
	private IOrcamentoItemOsDAO orcamentoItemOsDAO;

	private OrcamentoOsEntity currentBean;

	@Override
	protected String getNome() {
		return "O.S Simples";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			
			boolean valido = validaSalvar();
			if (valido) {

				currentBean.setNomeVendedor(subView.getTfNomeVendedor().getValue());
				currentBean.setNome(subView.getTfNome().getValue());
				currentBean.setFormaPagamento(subView.getTfFormaPagamento().getValue());
				currentBean.setEndereco(subView.getTfEndereco().getValue());
				currentBean.setFone(subView.getTfFone().getValue());
				currentBean.setMotor(subView.getTfCvMotor().getValue());
				currentBean.setMotorizacao(subView.getTfMotorizacao().getValue());
				currentBean.setPlaca(subView.getTfPlaca().getValue());
				currentBean.setMarca(subView.getTfMarca().getValue());
				currentBean.setCor(subView.getTfCor().getValue());
				if(!subView.getTfAno().getValue().equals("")){
					currentBean.setAno(Integer.parseInt(subView.getTfAno().getValue()));
				}else{
					currentBean.setAno(0);
				}
				currentBean.setModelo(subView.getTfModelo().getValue());
	
				orcamentoOsDAO.saveOrUpdate(currentBean);
				notifiyFrameworkSaveOK(this.currentBean);
			}
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getTfNome().getValue())) {
			valido = false;
			mensagemErro("O campo nome não pode ficar em branco.");
		}
		return valido;
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = orcamentoOsDAO.find((Integer) id);

		subView.getTfNomeVendedor().setValue(currentBean.getNomeVendedor());
		subView.getTfFormaPagamento().setValue(currentBean.getFormaPagamento());
		subView.getTfNome().setValue(currentBean.getNome());
		subView.getTfEndereco().setValue(currentBean.getEndereco());
		subView.getTfFone().setValue(currentBean.getFone());
		subView.getTfPlaca().setValue(currentBean.getPlaca());
		subView.getTfMarca().setValue(currentBean.getMarca());
		subView.getTfCor().setValue(currentBean.getCor());
		subView.getTfModelo().setValue(currentBean.getModelo());
		subView.getTfCvMotor().setValue(currentBean.getMotor());
		subView.getTfMotorizacao().setValue(currentBean.getMotorizacao());
		subView.getTfAno().setValue(currentBean.getAno().toString());

//		BigDecimal valorTotal = currentBean.getValorTotal();
//		BigDecimal valorDesconto = currentBean.getValorDesconto();

//		if(valorDesconto!=null ){
//			subView.getTfValorDesconto().setValue(valorTotal.toString());
//		}
//		if(valorTotal!=null ){
//			subView.getTfValorTotal().setValue(valorTotal.toString());
//		}

		List<OrcamentoOsItemEntity> itens = orcamentoItemOsDAO.findByOrcamentoOs(currentBean);

		subView.preencheSubForm(itens);

	}

	@Override
	protected void initSubView() {
		subView = new OrcamentoOsFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new OrcamentoOsEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		orcamentoOsDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			OrcamentoOsEntity orcamento = (OrcamentoOsEntity) id;

			try {
				orcamentoOsDAO.delete(orcamento);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
	}

	public OrcamentoOsItemEntity novoOrcamentoOsItem() {
		OrcamentoOsItemEntity item = new OrcamentoOsItemEntity();
		currentBean.addOrcamentoOsItem(item);
		return item;
	}

	public void removerOrcamentoOsItem(List<OrcamentoOsItemEntity> orcamentoOsItem) {
		for (OrcamentoOsItemEntity orcamentoItem : orcamentoOsItem) {
			currentBean.removeOrcamentoOsItem(orcamentoItem);
		}
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "orcamentoOsForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<OrcamentoOsEntity> getOrcamentoOsItens() {
		return orcamentoOsDAO.getAll();
	}

	@Override
	public OrcamentoOsEntity getModelBean() {
		return currentBean;
	}
}
