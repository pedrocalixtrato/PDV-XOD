package dc.control.util.classes.ordemservico;

import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.ordemservico.CombustivelEntity;
import dc.entidade.ordemservico.CorEntity;
import dc.entidade.ordemservico.MarcaOsEntity;
import dc.entidade.ordemservico.ModeloOsEntity;
import dc.visao.ordemservico.CarroFormView;


public class CarroUtils {

	public static void validateRequiredFields(CarroFormView subView)
			throws DotErpException {
		
		String placa = subView.getTfPlaca().getValue();
		if (StringUtils.isBlank(placa)) {
			throw new DotErpException(subView.getTfPlaca(),
					"::DotERP - Não pode ficar em branco");
		}

		ClienteEntity cliente = subView.getCbCliente().getValue();
		if (ObjectUtils.isBlank(cliente)) {
			throw new DotErpException(subView.getCbCliente(),
					"::DotERP - Não pode ficar em branco");
		}

		MarcaOsEntity marca = subView.getCbMarca().getValue();
		if (ObjectUtils.isBlank(marca)) {
			throw new DotErpException(subView.getCbMarca(),
					"::DotERP - Não pode ficar em branco");
		}

		ModeloOsEntity modelo = subView.getCbModelo().getValue();
		if (ObjectUtils.isBlank(modelo)) {
			throw new DotErpException(subView.getCbModelo(),
					"::DotERP - Não pode ficar em branco");
		}

		CorEntity cor = subView.getCbCor().getValue();
		if (ObjectUtils.isBlank(cor)) {
			throw new DotErpException(subView.getCbCor(),
					"::DotERP - Não pode ficar em branco");
		}

		CombustivelEntity combustivel = subView.getCbCombustivel().getValue();
		if (ObjectUtils.isBlank(combustivel)) {
			throw new DotErpException(subView.getCbCombustivel(),
					"::DotERP - Não pode ficar em branco");
		}

	}

	public static void validateFieldValue(CarroFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(CarroFormView subView) {

	}

}