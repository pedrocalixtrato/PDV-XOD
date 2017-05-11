package dc.model.business.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;
import dc.entidade.geral.tabela.CboEntity;
import dc.model.dao.geral.tabela.CboDAO;


@Service
@Transactional(readOnly = true)
public class CboBusinessImpl implements Serializable,CboBusiness<CboEntity> {

/**
* 
*/
private static final long serialVersionUID = 1L;

private static Logger logger = Logger.getLogger(CboBusinessImpl.class);

@Autowired
private CboDAO<CboEntity> dao;

/**
* **********************************************
*/

@Override
public Class<CboEntity> getEntityClass() {
return CboEntity.class;
}

@Transactional(readOnly = false)
@Override
public void delete(CboEntity t) throws Exception {
// TODO Auto-generated method stub

}

@Transactional(readOnly = false)
@Override
public void deleteAll(List<Serializable> list) throws Exception {
// TODO Auto-generated method stub
try {
	System.out.println(":: [" + getClass().getSimpleName()
			+ "] deleteAll");

	this.dao.deleteAllByIds(list);
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

@Override
public CboEntity find(Serializable id) throws Exception {
// TODO Auto-generated method stub
try {
	System.out.println(":: [" + getClass().getSimpleName() + "] find");

	CboEntity ent = this.dao.find(id);

	return ent;
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

@Override
public CboEntity find(CboEntity t) throws Exception {
// TODO Auto-generated method stub
return null;
}

@Override
public List<CboEntity> find(String s) throws Exception {
// TODO Auto-generated method stub
return null;
}

@Override
public List<CboEntity> findAll() throws Exception {
// TODO Auto-generated method stub
try {
	List<CboEntity> auxLista = this.dao.getAll();

	return auxLista;
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

@Override
public List<CboEntity> findAll(CboEntity t) throws Exception {
// TODO Auto-generated method stub
return null;
}

@Override
public List<CboEntity> fullTextSearch(String valor) throws Exception {
// TODO Auto-generated method stub
return null;
}

@Override
public List<CboEntity> fullTextSearch(String valor, int first,
	int pageSize, String[] sortingFields, boolean[] sortingStates,
	List<Filter> filters) throws Exception {
// TODO Auto-generated method stub
return null;
}

@Override
public List<CboEntity> fullTextSearch(String valor,
	String[] sortingFields, boolean[] states, List<Filter> filters)
	throws Exception {
// TODO Auto-generated method stub
return null;
}

@Override
public <E> List<E> getAll(Class<E> type) throws Exception {
// TODO Auto-generated method stub
return null;
}

@Transactional(readOnly = false)
@Override
public void save(CboEntity t) throws Exception {
// TODO Auto-generated method stub

}

@Transactional(readOnly = false)
@Override
public <E> void saveOrUpdate(E o) throws Exception {
// TODO Auto-generated method stub
try {
	System.out.println(":: [" + getClass().getSimpleName()
			+ "] saveOrUpdate");

	CboEntity ent = (CboEntity) o;

	this.dao.saveOrUpdate(ent);
} catch (Exception e) {
	e.printStackTrace();

	throw e;
}
}

@Override
public List<CboEntity> getAllForComboSelect(Class<CboEntity> type,
	int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
		idSelected);
}

@Override
public List<CboEntity> getAllForCombo(Class<CboEntity> type,
	int idEmpresa, FmMenu menu, Boolean getAll) {
return dao.getAllForCombo(type, idEmpresa, menu, getAll);
}

@Override
public List<CboEntity> comboTextSearch(String value, FmMenu menu,
	Boolean getAll) {
return dao.comboTextSearch(value, menu, getAll);
}


}
