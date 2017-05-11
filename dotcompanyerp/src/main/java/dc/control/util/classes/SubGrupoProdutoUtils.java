package dc.control.util.classes;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.GrupoEntity;
import dc.visao.geral.produto.SubGrupoFormView;

public class SubGrupoProdutoUtils {

	public static void validateRequiredFields(SubGrupoFormView subView)
			throws DotErpException {
		String nome = subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			throw new DotErpException(subView.getTfNome(),
					"::DotERP - Não pode ficar em branco");
		}

		String descricao = subView.getTfDescricao().getValue();

		if (StringUtils.isBlank(descricao)) {
			throw new DotErpException(subView.getTfDescricao(),
					"::DotERP - Não pode ficar em branco");
		}

		GrupoEntity grupo = subView.getMocGrupoProduto().getValue();

		if (ObjectUtils.isBlank(grupo)) {
			throw new DotErpException(subView.getMocGrupoProduto(),
					"::DotERP - Não pode ficar em branco");
		}
	}

	public static void validateFieldValue(SubGrupoFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(SubGrupoFormView subView) {

	}

}