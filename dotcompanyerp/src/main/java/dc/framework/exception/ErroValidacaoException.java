package dc.framework.exception;

public class ErroValidacaoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String msgErro;
	
	public ErroValidacaoException(String msgErro ){
		this.msgErro = msgErro;
	}
	
	public String montaMensagemErro(){
		return msgErro;
	}
	
}
