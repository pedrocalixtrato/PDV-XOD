package dc.servicos.dao.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.ContadorEntity;
import dc.model.dao.geral.pessoal.IContadorDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalContadorDAO")
public class ContadorDAO extends AbstractCrudDAO<ContadorEntity> implements IContadorDAO {

	@Override
	public Class<ContadorEntity> getEntityClass() {
		return ContadorEntity.class;
	}

	@Transactional
	public List<ContadorEntity> listaTodos() {
		return getSession().createQuery("FROM ContadorEntity ent").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "logradouro", "bairro","complemento","numero","uf","email","fone","fax",
				"cpf","cnpj","cep" };
	}

	@Transactional
	public List<ContadorEntity> getContadorList() {
		try {
			List<ContadorEntity> auxLista = new ArrayList<ContadorEntity>();

			String sql = "SELECT new ContadorEntity(ent.id, ent.nome) FROM EmpresaEntity ent";

			auxLista = getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}