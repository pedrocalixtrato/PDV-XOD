package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.financeiro.BancoEntity;
import dc.visao.financeiro.AgenciaBancoFormView;

public class AgenciaBancoUtils {

	public static void validateRequiredFields(AgenciaBancoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String logradouro = subView.getTfLogradouro().getValue();

		if (StringUtils.isBlank(logradouro)) {
			throw new DotErpException(subView.getTfLogradouro(),
					"::DotERP - Não pode ficar em branco");
		}

		String cep = subView.getTfCep().getValue();

		if (StringUtils.isBlank(cep)) {
			throw new DotErpException(subView.getTfCep(),
					"::DotERP - Não pode ficar em branco");
		}

		BancoEntity banco = (BancoEntity) subView.getMocBanco().getValue();

		if (ObjectUtils.isBlank(banco)) {
			throw new DotErpException(subView.getMocBanco(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(AgenciaBancoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(AgenciaBancoFormView subView) {

	}

}