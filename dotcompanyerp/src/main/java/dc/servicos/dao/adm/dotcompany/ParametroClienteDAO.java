package dc.servicos.dao.adm.dotcompany;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParametroClienteDAO extends AbstractCrudDAO<ParametroCliente> implements IParametroClienteDAO {

	@Override
	public Class<ParametroCliente> getEntityClass() {
		return ParametroCliente.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"empresa","valorEntrada","valorMensalidade", "dataEntrada","nomeResponsavel","emailPrincipal"};
	}
	
	@Transactional
	public List<ParametroCliente> listaTodos() {
		return getSession().createQuery("from ParametroCliente").list();
	}
	/* (non-Javadoc)
	 * @see dc.servicos.dao.adm.dotcompany.IParametroClienteDAO#buscarOsParametro(dc.entidade.adm.dotcompany.ParametroCliente)
	 */
	@Override
	@Transactional
	public List<ParametroCliente> buscarOsParametro(ParametroCliente cliente){

		List<ParametroCliente> lista = new ArrayList<>();

		try{
			if(cliente!=null){
				lista =  getSession()
						.createQuery("from ParametroCliente i where i.cliente = :cliente")
						.setParameter("cliente", cliente).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
