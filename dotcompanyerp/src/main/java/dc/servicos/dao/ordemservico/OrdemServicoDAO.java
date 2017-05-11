package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.ordemservico.IOrdemServicoDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrdemServicoDAO extends AbstractCrudDAO<OrdemServicoEntity> implements IOrdemServicoDAO{

	@Override
	public Class<OrdemServicoEntity> getEntityClass() {
		return OrdemServicoEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"cliente","dataCadastr","valorServico","valorPeca","valorFrete","valorTotalOs","valorTotalServico","valorLucroServico","quantidadeParcelaCheque","primeiroVencimentoCheque",
				"quantidadeParcelaCarne","primeiroVencimentoCarne","quantidadeParcelaCartao","primeiroVencimentoCartao","quantidadeParcelaBoleto","primeiroVencimentoBoleto","dataExclusao","efetivada"};
	}
	
	@Transactional
	public List<OrdemServicoEntity> listaTodos() {
		return getSession().createQuery("from OrdemServicoEntity").list();
	}
	@Transactional
	public List<OrdemServicoEntity> buscarOsPorCliente(ClienteEntity cliente){

		List<OrdemServicoEntity> lista = new ArrayList<>();

		try{
			if(cliente!=null){
				lista =  getSession()
						.createQuery("from OrdemServicoEntity i where i.cliente = :cliente")
						.setParameter("cliente", cliente).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}

}
