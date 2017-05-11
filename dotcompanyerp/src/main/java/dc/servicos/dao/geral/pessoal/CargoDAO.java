package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.model.dao.geral.pessoal.ICargoDAO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalCargoDAO")
public class CargoDAO extends AbstractCrudDAO<CargoEntity> implements ICargoDAO{

	@Override
	public Class<CargoEntity> getEntityClass() {
		return CargoEntity.class;
	}

	@Transactional
	public List<CargoEntity> listaTodos() {
		return getSession().createQuery("FROM CargoEntity").list();
	}

	@Transactional
	public List<CargoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("FROM CargoEntity WHERE nome LIKE :q")
				.setParameter("q", "%" + query + "%").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao","salario","cbo1994" ,"cbo2002"};
	}

	@Transactional
	public List<CargoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("FROM CargoEntity WHERE lower(nome) LIKE :q")
				.setParameter("q", q).list();
	}

	@Transactional
	public List<CargoEntity> list(EmpresaEntity empresa) {
		List<CargoEntity> auxLista = getSession()
				.createQuery("FROM CargoEntity WHERE empresa.id = :bid")
				.setParameter("bid", empresa.getId()).list();

		return auxLista;
	}

}