package dc.control.util.classes.financeiro;

import dc.control.util.ObjectUtils;
import dc.control.validator.DotErpException;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.visao.financeiro.LancamentoPagarFormView;

public class LancamentoPagarUtils {
	
	public static void validateRequiredFields(LancamentoPagarFormView subView)
			throws DotErpException {
		
		FornecedorEntity fornecedor = subView.getCbFornecedor().getValue();
		if (ObjectUtils.isBlank(fornecedor)) {
			throw new DotErpException(subView.getCbFornecedor(),
					"::DotERP - Não pode ficar em branco"); 
		}
		
		DocumentoOrigem documentoOrigem = subView.getCbDocumentoOrigem().getValue();
		if (ObjectUtils.isBlank(documentoOrigem)) {
			throw new DotErpException(subView.getCbDocumentoOrigem(),
					"::DotERP - Não pode ficar em branco");
		}
		
		ContaCaixa contaCaixa = subView.getCbContaCaixa().getValue();
		if (ObjectUtils.isBlank(contaCaixa)) {
			throw new DotErpException(subView.getCbContaCaixa(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(LancamentoPagarFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(LancamentoPagarFormView subView) {

	}

}
