package dc.model.dao.administrativo.seguranca;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.model.dao.AbstractDAO;

public interface IUsuarioDAO extends AbstractDAO<UsuarioEntity> {
	public UsuarioEntity getUsuarioByLogin(String login);
	public String getNomeUsuario(UsuarioEntity usuario);
}