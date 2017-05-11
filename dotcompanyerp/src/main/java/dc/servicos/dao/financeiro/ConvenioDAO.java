package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.outro.ConvenioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
/*
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o Método do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações lá no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
*/


@Repository
@SuppressWarnings("unchecked")
public class ConvenioDAO extends AbstractCrudDAO<ConvenioEntity>{

	@Override
	public Class<ConvenioEntity> getEntityClass() {
		return ConvenioEntity.class;
	}
	
	
	@Transactional
	public List<ConvenioEntity> listaTodos() {
		return getSession().createQuery("from Convenio").list();
	}

	@Transactional
	public List<ConvenioEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Convenio where logradouro like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"pessoa","nome", "logradouro","numero", "bairro","dataVencimento","dataCadastro","email","telefone","contato",
				"cnpj","cep","site","descricao" };
	}

}
