package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.visao.geral.outro.ConvenioFormView;

public class ConvenioUtils {

	public static void validateRequiredFields(ConvenioFormView subView)
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

		PessoaEntity pessoa = subView.getMocPessoa().getValue();

		if (ObjectUtils.isBlank(pessoa)) {
			throw new DotErpException(subView.getMocPessoa(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(ConvenioFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ConvenioFormView subView) {

	}

}