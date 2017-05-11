package dc.servicos.dao.folhapagamento.ausencia;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.ausencia.TipoAfastamentoEntity;

public interface ITipoAfastamentoDAO {

	public abstract List<TipoAfastamentoEntity> tipoAfastamentoLista();

}