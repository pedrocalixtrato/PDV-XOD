package dc.servicos.dao.contabilidade.livrocontabil;

import java.util.List;

import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.model.dao.AbstractDAO;

public interface ILivroDAO extends AbstractDAO<LivroEntity> {

	List<LivroEntity> procuraNomeContendo(String valor);

}