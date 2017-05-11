package dc.visao.spring;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

public interface ISearchIndexer {

	public abstract Class<Serializable> getEntityClass();

	public abstract void loadIndex();

	public abstract String[] getDefaultSearchFields();

}