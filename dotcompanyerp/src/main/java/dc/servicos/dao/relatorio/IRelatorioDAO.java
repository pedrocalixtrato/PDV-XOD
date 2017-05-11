package dc.servicos.dao.relatorio;

import java.io.Serializable;
import java.util.List;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.PapelEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.SeguimentoEntity;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.TipoRelatorio;
import dc.model.dao.AbstractDAO;

public interface IRelatorioDAO extends AbstractDAO<Relatorio>{

	public abstract Class<Relatorio> getEntityClass();

	public abstract String[] getDefaultSearchFields();

	public abstract List<Relatorio> findRelatoriosByMenuAndUserAndType(FmMenu menu, UsuarioEntity usuario, TipoRelatorio tipoRelatorio);

	public abstract Relatorio find(Serializable id);

	public abstract void salvar(Relatorio relatorio, List<UsuarioEntity> usuariosView, List<SeguimentoEntity> seguimentosView, List<PapelEntity> papeisView, List<EmpresaEntity> empresasView);

}