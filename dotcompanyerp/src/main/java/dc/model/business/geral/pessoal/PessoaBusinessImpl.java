package dc.model.business.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.ListUtils;
import dc.control.util.ObjectUtils;
import dc.entidade.framework.FmMenu;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.model.dao.geral.pessoal.IClienteDAO;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.model.dao.geral.pessoal.IEstadoCivilDAO;
import dc.model.dao.geral.pessoal.IPessoaContatoDAO;
import dc.model.dao.geral.pessoal.IPessoaDAO;
import dc.model.dao.geral.pessoal.IPessoaFisicaDAO;
import dc.model.dao.geral.pessoal.IPessoaJuridicaDAO;
import dc.model.dao.geral.pessoal.ITransportadoraDAO;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.geral.IPessoaEnderecoDAO;

/**
 * 
 * 
 */

@Service
@Transactional(readOnly = true)
public class PessoaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(PessoaBusinessImpl.class);

	@Autowired
	private IPessoaDAO dao;

	@Autowired
	private IPessoaFisicaDAO pessoaFisicaDAO;

	@Autowired
	private IPessoaJuridicaDAO pessoaJuridicaDAO;

	@Autowired
	private IPessoaContatoDAO pessoaContatoDAO;

	@Autowired
	private IPessoaEnderecoDAO pessoaEnderecoDAO;

	@Autowired
	private IEstadoCivilDAO estadoCivilDAO;

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private IColaboradorDAO colaboradorDAO;

	@Autowired
	private IFornecedorDAO fornecedorDAO;

	@Autowired
	private ITransportadoraDAO transportadoraDAO;

	/**
	 * **********************************************
	 */

	@Override
	public Class<PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(PessoaEntity t) throws Exception {
		Session session = this.dao.getSession();
		t = (PessoaEntity) session.get(PessoaEntity.class, t.getId());
		
		this.dao.delete(t);
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
	public PessoaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			PessoaEntity ent = this.dao.find(id);
			
			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public PessoaEntity find(PessoaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> findAll(PessoaEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaEntity> fullTextSearch(String valor,
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
	public void save(PessoaEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			PessoaEntity ent = (PessoaEntity) o;

			if (ent.getTipo().equals(TipoPessoaEn.F)) {
				if (ObjectUtils.isNotBlank(ent.getPessoaJuridica())) {
					this.pessoaJuridicaDAO.delete(ent.getPessoaJuridica());
				}

				ent.setPessoaJuridica(null);

				if (ObjectUtils.isNotBlank(ent.getPessoaFisica()
						.getEstadoCivil())) {
					EstadoCivilEntity estadoCivil = this.estadoCivilDAO
							.find(ent.getPessoaFisica().getEstadoCivil()
									.getId());
					ent.getPessoaFisica().setEstadoCivil(estadoCivil);
				}
			} else if (ent.getTipo().equals(TipoPessoaEn.J)) {
				if (ObjectUtils.isNotBlank(ent.getPessoaFisica())) {
					this.pessoaFisicaDAO.delete(ent.getPessoaFisica());
				}

				ent.setPessoaFisica(null);
			}

			/**
			 * 
			 */

			if (ent.getTipoCliente().equals("0")) {
				if (ObjectUtils.isNotBlank(ent.getCliente())
						&& ObjectUtils.isNotBlank(ent.getCliente().getId())) {
					this.clienteDAO.delete(ent.getCliente());
				}

				ent.setCliente(null);
			}

			if (!ent.getTipoColaborador()) {
				if (ObjectUtils.isNotBlank(ent.getColaborador())
						&& ObjectUtils.isNotBlank(ent.getColaborador().getId())) {
					this.colaboradorDAO.delete(ent.getColaborador());
				}

				ent.setColaborador(null);
			}

			if (!ent.getTipoFornecedor()) {
				if (ObjectUtils.isNotBlank(ent.getFornecedor())
						&& ObjectUtils.isNotBlank(ent.getFornecedor().getId())) {
					this.fornecedorDAO.delete(ent.getFornecedor());
				}

				ent.setFornecedor(null);
			}

			if (!ent.getTipoTransportadora()) {
				if (ObjectUtils.isNotBlank(ent.getTransportadora())
						&& ObjectUtils.isNotBlank(ent.getTransportadora()
								.getId())) {
					this.transportadoraDAO.delete(ent.getTransportadora());
				}

				ent.setTransportadora(null);
			}

			/**
			 * 
			 */

			this.dao.saveOrUpdate(ent);

			/**
			 * 
			 */

			if (ListUtils.isNotNullAndNotEmpty(ent.getPessoaContatoList())) {
				for (PessoaContatoEntity ent1 : ent.getPessoaContatoList()) {
					this.pessoaContatoDAO.saveOrUpdate(ent1);
				}
			}

			if (ListUtils.isNotNullAndNotEmpty(ent.getPessoaEnderecoList())) {
				for (PessoaEnderecoEntity ent1 : ent.getPessoaEnderecoList()) {
					if (ObjectUtils.isNotBlank(ent1.getUf())) {
						ent1.setIdUf(ent1.getUf().getId());
						ent1.setSiglaUf(ent1.getUf().getSigla());
					}

					this.pessoaEnderecoDAO.saveOrUpdate(ent1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<PessoaEntity> getAllForComboSelect(Class<PessoaEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<PessoaEntity> getAllForCombo(Class<PessoaEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<PessoaEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

}