package dc.control.util.classes;

import dc.control.enums.TipoPessoaEn;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.visao.geral.eventos.PessoaEventosFormView;
import dc.visao.geral.pessoal.PessoaFormView;

public class PessoaUtils {

	public static void validateRequiredFields(PessoaFormView subView)
			throws DotErpException {
		TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) subView.getCbTipoPessoa()
				.getValue();

		if (ObjectUtils.isBlank(tipoPessoaEn)) {
			throw new DotErpException(subView.getCbTipoPessoa(),
					"::DotERP - Não pode ficar em branco");
		}

		String email = subView.getTfEmail().getValue();

		if (StringUtils.isBlank(email)) {
			throw new DotErpException(subView.getTfEmail(),
					"::DotERP - Não pode ficar em branco");
		}

		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String site = subView.getTfSite().getValue();

		if (StringUtils.isBlank(site)) {
			throw new DotErpException(subView.getTfSite(),
					"::DotERP - Não pode ficar em branco");
		}

		//

		if (tipoPessoaEn.equals(TipoPessoaEn.F)) {
			PessoaFisicaUtils.validateRequiredFields(subView);
		}

		//

		if (subView.getCkCliente().getValue()) {
			ClienteUtils.validateRequiredFields(subView);
		}

		//

		if (subView.getCkColaborador().getValue()) {
			ColaboradorUtils.validateRequiredFields(subView);
		}

		//

		if (subView.getCkFornecedor().getValue()) {
			FornecedorUtils.validateRequiredFields(subView);
		}

		//

		if (subView.getCkTransportadora().getValue()) {
			TransportadoraUtils.validateRequiredFields(subView);
		}
	}

	//

	public static void validateFieldValue(PessoaFormView subView)
			throws DotErpException {
		String tituloSecao = (String) subView.getTfTituloSecao().getValue();

		if (!NumberUtils.isNumber(tituloSecao)) {
			throw new DotErpException(subView.getTfTituloSecao(),
					"::DotERP - O campo não é um número");
		}

		String tituloEleitoralZona = (String) subView.getTfTituloZona()
				.getValue();

		if (!NumberUtils.isNumber(tituloEleitoralZona)) {
			throw new DotErpException(subView.getTfTituloZona(),
					"::DotERP - O campo não é um número");
		}
	}

	public static void clearFormFields(PessoaFormView subView) {

	}

	public static void validateRequiredFields(PessoaEventosFormView subView) throws DotErpException {
		
		String tituloSecao = (String) subView.getTfTituloSecao().getValue();

		if (!NumberUtils.isNumber(tituloSecao)) {
			throw new DotErpException(subView.getTfTituloSecao(),
					"::DotERP - O campo não é um número");
		}

		String tituloEleitoralZona = (String) subView.getTfTituloZona()
				.getValue();

		if (!NumberUtils.isNumber(tituloEleitoralZona)) {
			throw new DotErpException(subView.getTfTituloZona(),
					"::DotERP - O campo não é um número");
		}
		
	}

}