package dc.model.business;

import java.util.List;

import dc.entidade.framework.FmMenu;

public interface AbstractComboBusiness<T> {

	public List<T> getAllForComboSelect(final Class<T> type, int idEmpresa,
			FmMenu menu, final String typeSelected, Integer idSelected);

	public List<T> getAllForCombo(final Class<T> type, int idEmpresa,
			FmMenu menu, Boolean getAll);

	public List<T> comboTextSearch(String value, FmMenu menu, Boolean getAll);

	public Class<T> getEntityClass();

}