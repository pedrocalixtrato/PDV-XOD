package dc.control.util.classes;

import java.util.Date;

import dc.control.util.DateUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.visao.geral.pessoal.ClienteFormView;
import dc.visao.geral.pessoal.PessoaFormView;

public class ClienteUtils {

	public static void validateRequiredFields(ClienteFormView subView)
			throws DotErpException {

	}

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		System.out.println(":: valida cliente");

		Date dataDesde = (Date) subView.getPdfClienteDesde().getValue();

		if (DateUtils.isBlank(dataDesde)) {
			throw new DotErpException(subView.getPdfClienteDesde(),
					"::DotERP - Não pode ficar em branco");
		}

		String contaTomador = subView.getTfClienteContaTomador().getValue();

		if (StringUtils.isBlank(contaTomador)) {
			throw new DotErpException(subView.getTfClienteContaTomador(),
					"::DotERP - Não pode ficar em branco");
		}

		String limiteCredito = subView.getTfClienteLimiteCredito()
				.getConvertedValue().toString();

		if (StringUtils.isBlank(limiteCredito)) {
			throw new DotErpException(subView.getTfClienteLimiteCredito(),
					"::DotERP - Não pode ficar em branco");
		}

		SituacaoForCliEntity situacao = subView.getMocClienteSituacao()
				.getValue();

		if (ObjectUtils.isBlank(situacao)) {
			throw new DotErpException(subView.getMocClienteSituacao(),
					"::DotERP - Não pode ficar em branco");
		}

		AtividadeForCliEntity atividade = subView.getMocClienteAtividade()
				.getValue();

		if (ObjectUtils.isBlank(atividade)) {
			throw new DotErpException(subView.getMocClienteAtividade(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	//

	public static void validateFieldValue(ClienteFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(ClienteFormView subView) {

	}

}