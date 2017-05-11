package dc.servicos.dao.financeiro;

import java.util.List;

import dc.entidade.financeiro.ConfiguracaoBoleto;
import dc.entidade.financeiro.ContaCaixa;
import dc.model.dao.AbstractDAO;

public interface IConfiguracaoBoletoDAO extends AbstractDAO<ConfiguracaoBoleto>{

	public abstract List<ConfiguracaoBoleto> getConfiguracoesBoletoByContaCaixa(ContaCaixa contaCaixa);

}