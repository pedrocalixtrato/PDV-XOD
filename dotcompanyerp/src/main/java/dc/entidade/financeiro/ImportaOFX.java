package dc.entidade.financeiro;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import net.sf.ofx4j.domain.data.banking.BankingResponseMessageSet;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.io.AggregateUnmarshaller;

@SuppressWarnings("rawtypes")
public class ImportaOFX {
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ExtratoContaBancoEntity> importaArquivoOFX(InputStream is) {
	       try {
	           
				AggregateUnmarshaller a = new AggregateUnmarshaller(ResponseEnvelope.class);
	            ResponseEnvelope re = (ResponseEnvelope) a.unmarshal(is);

	            //define o tipo de mensagem
	            MessageSetType type = MessageSetType.banking;
	            ResponseMessageSet message = re.getMessageSet(type);

	            if (message != null) {
	                //busca a lista de contas no arquivo
	            	ExtratoContaBancoEntity extrato;
	                List<ExtratoContaBancoEntity> listaExtrato = new ArrayList<ExtratoContaBancoEntity>();
	                List bank = ((BankingResponseMessageSet) message).getStatementResponses();
	                for (int i = 0; i < bank.size(); i++) {
	                    //objeto que contÃ©m os dados das contas
	                    BankStatementResponseTransaction conta = (BankStatementResponseTransaction) bank.get(i);

	                    //busca os dados das transaÃ§Ãµes
	                    List transacoes = conta.getMessage().getTransactionList().getTransactions();
	                    for (int j = 0; j < transacoes.size(); j++) {
	                        Transaction transaction = (Transaction) transacoes.get(j);

	                        extrato = new ExtratoContaBancoEntity();
	                        extrato.setDataMovimento(transaction.getDatePosted());
	                        extrato.setDataBalancete(transaction.getDatePosted());
	                        extrato.setDocumento(transaction.getCheckNumber());
	                        extrato.setHistorico(transaction.getMemo());
	                        extrato.setValor(transaction.getBigDecimalAmount());

	                        listaExtrato.add(extrato);
	                    }
	                }
	                return listaExtrato;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


}
