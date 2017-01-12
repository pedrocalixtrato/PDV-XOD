//package br.com.pdv.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.inject.Inject;
//
//import javax.persistence.EntityManager;
//import javax.transaction.UserTransaction;
//
//import br.com.pdv.dao.hibernate.HibernateGenericDAO;
//import br.com.pdv.domain.ItemPedido;
//import br.com.pdv.domain.Pedido;
//
//@SuppressWarnings("serial")
//public class PedidoDAO extends HibernateGenericDAO<Pedido, Long> implements Serializable{
//	@Inject
//	private EntityManager em;
//	private UserTransaction transaction;
//	
//		
//		public void salvarPedido(Pedido pedido, List<ItemPedido> itensPedido){
//				
//			
//					
//			try{	
//				
//				transaction.begin();
//				em.merge(pedido);
//				
//				
//				for(int posicao = 0; posicao <itensPedido.size();posicao ++){
//					ItemPedido itemPedido = itensPedido.get(posicao);
//					itemPedido.setPedido(pedido);
//					
//					em.merge(itemPedido);
//				}
//				transaction.commit();
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			
//			
//			
//			}
//		}
//
//}
