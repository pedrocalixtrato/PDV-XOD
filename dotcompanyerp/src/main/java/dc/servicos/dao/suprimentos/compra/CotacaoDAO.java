package dc.servicos.dao.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.compra.CotacaoCompraEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraCotacaoDAO")
public class CotacaoDAO extends AbstractCrudDAO<CotacaoCompraEntity> implements ICotacaoDAO {

	@Override
	public Class<CotacaoCompraEntity> getEntityClass() {
		return CotacaoCompraEntity.class;
	}

	@Override
	public CotacaoCompraEntity find(Serializable id) {
		CotacaoCompraEntity cotacao = super.find(id);
		// workaround para lazy initialization exception
		cotacao.getCompraFornecedorCotacaos().size();
		cotacao.getCompraReqCotacaoDetalhes().size();

		return cotacao;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "dataCotacao", "descricao", "situacao" };
	}
	
	@Transactional
	public List<CotacaoCompraEntity> listaTodos() {
		return getSession().createQuery("from CompraCotacao").list();
	}
	
}