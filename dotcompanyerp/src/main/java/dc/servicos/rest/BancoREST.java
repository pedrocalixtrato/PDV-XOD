package dc.servicos.rest;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.financeiro.BancoDAO;

@Path("/banco")
@Service
public class BancoREST {

	@Autowired
	BancoDAO bancoDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BancoEntity> hello(@QueryParam("q") String query) {
		if (query == null) {
			return bancoDAO.getAll();
		} else {
			return bancoDAO.procuraNomeContendo(query);
		}
	}
}
