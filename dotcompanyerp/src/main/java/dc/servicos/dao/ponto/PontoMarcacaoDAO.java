package dc.servicos.dao.ponto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.ponto.PontoMarcacao;
import dc.entidade.ponto.PontoRelogio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PontoMarcacaoDAO extends AbstractCrudDAO<PontoMarcacao> {

	@Override
	public Class<PontoMarcacao> getEntityClass() {
		return PontoMarcacao.class;
	}

	@Transactional
	public List<PontoMarcacao> listaTodos() {
		return getSession().createQuery("from PontoMarcacao").list();
	}

	@Override
	public String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

	@Transactional
	public PontoMarcacao getPontoMarcacao(ColaboradorEntity colaborador, PontoRelogio relogio, Date dataMarcacao, String horaMarcacao) {
		Criteria criteria = getSession().createCriteria(PontoMarcacao.class);
		criteria.add(Restrictions.eq("colaborador", colaborador));
		criteria.add(Restrictions.eq("pontoRelogio", relogio));
		criteria.add(Restrictions.eq("dataMarcacao", dataMarcacao));
		criteria.add(Restrictions.eq("horaMarcacao", horaMarcacao));
		PontoMarcacao marc = (PontoMarcacao) criteria.uniqueResult();

		return marc;
	}

	@Transactional
	public PontoMarcacao getPontoMarcacao(UsuarioEntity usuario, Calendar dataAtual) {
		// define o tipo de marcacao
		Criteria criteria = getSession().createCriteria(PontoMarcacao.class);
		criteria.add(Restrictions.eq("colaborador", usuario.getColaborador()));
		criteria.add(Restrictions.eq("dataMarcacao", dataAtual.getTime()));
		criteria.addOrder(Order.asc("horaMarcacao"));

		List<PontoMarcacao> listaMarcacaoes = criteria.list();
		String tipoMarcacao = "E";
		int par = 1;
		for (int i = 0; i < listaMarcacaoes.size(); i++) {
			if (listaMarcacaoes.get(i).getTipoMarcacao().equals("E")) {
				tipoMarcacao = "S";
			} else if (listaMarcacaoes.get(i).getTipoMarcacao().equals("S")) {
				tipoMarcacao = "E";
			}
			par = Integer.valueOf(listaMarcacaoes.get(i).getParEntradaSaida().substring(1));
		}
		if (!listaMarcacaoes.isEmpty()) {
			if (listaMarcacaoes.size() % 2 == 0) {
				par += 1;
			}
		}

		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

		PontoMarcacao marcacao = new PontoMarcacao();
		marcacao.setColaborador(usuario.getColaborador());
		marcacao.setDataMarcacao(dataAtual.getTime());
		marcacao.setHoraMarcacao(formatoHora.format(dataAtual.getTime()));
		marcacao.setTipoRegistro("O");

		marcacao.setTipoMarcacao(tipoMarcacao);
		marcacao.setParEntradaSaida(tipoMarcacao + par);
		return marcacao;
	}

	@Transactional
	public List<PontoMarcacao> searchByInterval(Date dataInicial, Date dataFinal) {
		Criteria criteria = getSession().createCriteria(PontoMarcacao.class);
		criteria.add(Restrictions.between("dataMarcacao", dataInicial, dataFinal));
		criteria.addOrder(Order.asc("colaborador"));
		criteria.addOrder(Order.asc("dataMarcacao"));
		criteria.addOrder(Order.asc("horaMarcacao"));

		List<PontoMarcacao> marcacoes = criteria.list();

		return marcacoes;
	}

}
