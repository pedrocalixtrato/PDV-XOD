package dc.servicos.dao.financeiro;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dc.entidade.financeiro.FluxoCaixaDetalheEntity;
import dc.entidade.financeiro.FluxoCaixaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class FluxoCaixaDetalheDAO extends AbstractCrudDAO<FluxoCaixaDetalheEntity> {
	
		@Override
		public Class<FluxoCaixaDetalheEntity> getEntityClass() {
			return FluxoCaixaDetalheEntity.class;
		}

		@Transactional
		public List<FluxoCaixaDetalheEntity> listaTodos() {
			return getSession().createQuery("from FluxoCaixaDetalhe").list();
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "valor","dataInclusao" };
		}
		
		@Transactional
		public List<FluxoCaixaDetalheEntity> findByNatureza(FluxoCaixaEntity currentBean) {

				List<FluxoCaixaDetalheEntity> lista = new ArrayList<>();

				try{
					if(currentBean!=null){
						lista =  getSession()
								.createQuery("from FluxoCaixaDetalhe i where i.fluxoCaixa = :fluxoCaixa")
								.setParameter("fluxoCaixa", currentBean).list();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return lista;
		}

}
