package dc.model.dao.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.AbstractDAO;

public interface IColaboradorDAO extends AbstractDAO<ColaboradorEntity> {
	public List<ColaboradorEntity> listaVendedores();
	public List<ColaboradorEntity> listaTecnicos();
	public List<ColaboradorEntity> colaboradorLista();
}