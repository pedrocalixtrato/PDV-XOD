package br.com.pdv.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.ItemPedido;
import br.com.pdv.domain.Pedido;
import br.com.pdv.filter.PedidoFilter;

@SuppressWarnings("serial")
public class PedidoDAO extends HibernateGenericDAO<Pedido, Long> implements Serializable{
	@PersistenceContext
	private EntityManager em;
	private UserTransaction transaction;
	
		

	
		public void salvarPedido(Pedido pedido, List<ItemPedido> itensPedido){
				
			
					
			try{	
				
				transaction.begin();
				em.merge(pedido);
				
				
				for(int posicao = 0; posicao <itensPedido.size();posicao ++){
					ItemPedido itemPedido = itensPedido.get(posicao);
					itemPedido.setPedido(pedido);
					
					em.merge(itemPedido);
				}
				transaction.commit();
				
			}catch(Exception e){
				e.printStackTrace();
			
			
			
			}
		}
		
			

		@SuppressWarnings("unchecked")
		public List<Pedido> filtrados(PedidoFilter filtro) {
			Session session = this.em.unwrap(Session.class);
			
			Criteria criteria = session.createCriteria(Pedido.class)
					
					// fazemos uma associação (join) com cliente e nomeamos como "c"
					.createAlias("cliente", "c")
					// fazemos uma associação (join) com vendedor e nomeamos como "v"
					.createAlias("vendedor", "v");
			
			if (filtro.getNumeroDe() != null) {
				// id deve ser maior ou igual (ge = greater or equals) a filtro.numeroDe
				criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
			}

			if (filtro.getNumeroAte() != null) {
				// id deve ser menor ou igual (le = lower or equal) a filtro.numeroDe
				criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
			}

			if (filtro.getDataCriacaoDe() != null) {
				criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
			}
			
			if (filtro.getDataCriacaoAte() != null) {
				criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
			}
			
			if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
				// acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
				criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
			}
			
			if (StringUtils.isNotBlank(filtro.getNomeVendedor())) {
				// acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
				criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
			}
			
			if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
				// adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
				
				criteria.add(Restrictions.in("status", filtro.getStatuses()));
			}
			//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.addOrder(Order.asc("id")).list();
		}
		
		
//		public Pedido guardar(Pedido pedido){
//					
//			return this.em.merge(pedido);			
//			
//		}

}
