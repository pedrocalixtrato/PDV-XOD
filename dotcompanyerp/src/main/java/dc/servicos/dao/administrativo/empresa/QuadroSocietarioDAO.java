package dc.servicos.dao.administrativo.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.QuadroSocietarioEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class QuadroSocietarioDAO extends AbstractCrudDAO<QuadroSocietarioEntity> implements IQuadroSocietarioDAO {

	

	@Override
	public Class<QuadroSocietarioEntity> getEntityClass() {
		return QuadroSocietarioEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro","quantidadeCotas"};
	}
	
	@Transactional
	public List<QuadroSocietarioEntity> listaTodos() {
		return getSession().createQuery("from QuadroSocietario").list();
	}
	
}
