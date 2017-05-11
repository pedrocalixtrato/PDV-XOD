package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaReceber;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParcelaReceberDAO extends AbstractCrudDAO<ParcelaReceber> implements IParcelaReceberDAO {

	@Override
	public Class<ParcelaReceber> getEntityClass() {
		return ParcelaReceber.class;
	}

	@Transactional
	public List<ParcelaReceber> listaTodos() {
		return getSession().createQuery("from ParcelaReceber").list();
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"contaCaixa", "numeroParcela", "dataEmissao", "dataVencimento", "descontoAte", "valor", "valorFaltante", "taxaJuro",
				"valorJuro", "taxaMulta", "valorMulta", "taxaDesconto", "valorDesconto", "emitiuBoleto", "boletoNossoNumero"};
	}

}
