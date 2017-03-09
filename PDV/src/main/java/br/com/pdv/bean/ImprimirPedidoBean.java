package br.com.pdv.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.com.pdv.domain.Pedido;
import br.com.pdv.util.jsf.ExecutorRelatorio;
import br.com.pdv.util.jsf.FacesUtil;

@Named
@RequestScoped
public class ImprimirPedidoBean implements Serializable{
	
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("CodigoPedido", this.pedido.getId());
		
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/Venda.jasper",
				this.response, parametros, "Pedido Numero " + pedido.getId()+ ".pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	
	
	
}
