
package dc.control.util.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dc.control.enums.TipoEmpresaEn;
import dc.control.util.DateUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.Fpas;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.visao.administrativo.empresa.EmpresaFormView;

public class EmpresaUtils {

	public static List<DotErpException> validateRequiredFields(EmpresaFormView subView){
		List<DotErpException> erros = new ArrayList<DotErpException>();
		
		String razaoSocial = subView.getTfRazaoSocial().getValue();

		if (StringUtils.isBlank(razaoSocial)) {
			erros.add( new DotErpException(subView.getTfRazaoSocial(),
					"::DotERP - Não pode ficar em branco"));
		}

		String nomeFantasia = subView.getTfNomeFantasia().getValue();

		if (StringUtils.isBlank(nomeFantasia)) {
			erros.add( new DotErpException(subView.getTfNomeFantasia(),
					"::DotERP - Não pode ficar em branco"));
		}

		ContadorEntity contador = (ContadorEntity) subView.getCbContador()
				.getValue();

		if (ObjectUtils.isBlank(contador)) {
			erros.add( new DotErpException(subView.getCbContador(),
					"::DotERP - Não pode ficar em branco"));
		}

		SindicatoEntity sindicato = (SindicatoEntity) subView.getCbSindicato()
				.getValue();

		if (ObjectUtils.isBlank(sindicato)) {
			erros.add( new DotErpException(subView.getCbSindicato(),
					"::DotERP - Não pode ficar em branco"));
		}

		Fpas fpas = (Fpas) subView.getCbFpas().getValue();

		if (ObjectUtils.isBlank(fpas)) {
			erros.add( new DotErpException(subView.getCbFpas(),
					"::DotERP - Não pode ficar em branco"));
		}

		Date dataInicioAtividades = subView.getPdfDataInicioAtividades()
				.getValue();

		if (DateUtils.isBlank(dataInicioAtividades)) {
			erros.add( new DotErpException(subView.getPdfDataInicioAtividades(),
					"::DotERP - Não pode ficar em branco"));
		}

		String cnpj = subView.getMtfCnpj().getValue();

		if (StringUtils.isBlank(cnpj)) {
			erros.add( new DotErpException(subView.getMtfCnpj(),
					"::DotERP - Não pode ficar em branco"));
		}

		String inscricaoEstadual = subView.getTfInscricaoEstadual().getValue();

		if (StringUtils.isBlank(inscricaoEstadual)) {
			erros.add( new DotErpException(subView.getTfInscricaoEstadual(),
					"::DotERP - Não pode ficar em branco"));
		}

		String inscricaoEstadualSt = subView.getTfInscricaoEstadualSt()
				.getValue();

		if (StringUtils.isBlank(inscricaoEstadualSt)) {
			erros.add( new DotErpException(subView.getTfInscricaoEstadualSt(),
					"::DotERP - Não pode ficar em branco"));
		}

		String inscricaoMunicipal = subView.getTfInscricaoMunicipal()
				.getValue();

		if (StringUtils.isBlank(inscricaoMunicipal)) {
			erros.add( new DotErpException(subView.getTfInscricaoMunicipal(),
					"::DotERP - Não pode ficar em branco"));
		}

		String inscricaoJuntaComercial = subView.getTfInscricaoJuntaComercial()
				.getValue();

		if (StringUtils.isBlank(inscricaoJuntaComercial)) {
			erros.add( new DotErpException(subView.getTfInscricaoJuntaComercial(),
					"::DotERP - Não pode ficar em branco"));
		}

		Date dataInscricaoJuntaComercial = subView
				.getPdfDataInscricaoJuntaComercial().getValue();

		if (DateUtils.isBlank(dataInscricaoJuntaComercial)) {
			erros.add( new DotErpException(
					subView.getPdfDataInscricaoJuntaComercial(),
					"::DotERP - Não pode ficar em branco"));
		}

		String suframa = subView.getTfSuframa().getValue();

		if (StringUtils.isBlank(suframa)) {
			erros.add( new DotErpException(subView.getTfSuframa(),
					"::DotERP - Não pode ficar em branco"));
		}

		String contato = subView.getTfContato().getValue();

		if (StringUtils.isBlank(contato)) {
			erros.add( new DotErpException(subView.getTfContato(),
					"::DotERP - Não pode ficar em branco"));
		}

		String codigoTerceiros = subView.getTfCodigoTerceiros().getValue();

		if (StringUtils.isBlank(codigoTerceiros)) {
			erros.add( new DotErpException(subView.getTfCodigoTerceiros(),
					"::DotERP - Não pode ficar em branco"));
		}

		String cei = subView.getTfCei().getValue();

		if (StringUtils.isBlank(cei)) {
			erros.add( new DotErpException(subView.getTfCei(),
					"::DotERP - Não pode ficar em branco"));
		}

		String aliquotaPis = subView.getTfAliquotaPis().getValue();

		if (StringUtils.isBlank(aliquotaPis)) {
			erros.add( new DotErpException(subView.getTfAliquotaPis(),
					"::DotERP - Não pode ficar em branco"));
		}

		String aliquotaCofins = subView.getTfAliquotaCofins().getValue();

		if (StringUtils.isBlank(aliquotaCofins)) {
			erros.add( new DotErpException(subView.getTfAliquotaCofins(),
					"::DotERP - Não pode ficar em branco"));
		}

		String aliquotaSat = subView.getTfAliquotaSat().getValue();

		if (StringUtils.isBlank(aliquotaSat)) {
			erros.add(new DotErpException(subView.getTfAliquotaSat(),
					"::DotERP - Não pode ficar em branco"));
		}

		String codigoGps = subView.getTfCodigoGps().getValue();

		if (StringUtils.isBlank(codigoGps)) {
			erros.add( new DotErpException(subView.getTfCodigoGps(),
					"::DotERP - Não pode ficar em branco"));
		}

		String codigoMunicipio = subView.getTfMunicipio().getValue();

		if (StringUtils.isBlank(codigoMunicipio)) {
			erros.add(new DotErpException(subView.getTfMunicipio(),
					"::DotERP - Não pode ficar em branco"));
		}

		String codigoUf = subView.getTfUf().getValue();

		if (StringUtils.isBlank(codigoUf)) {
			erros.add(new DotErpException(subView.getTfUf(),
					"::DotERP - Não pode ficar em branco"));
		}

		EmpresaCnaeEntity cnaePrincipal = (EmpresaCnaeEntity) subView.getCbCnaePrincipal()
				.getValue();

		if (ObjectUtils.isBlank(cnaePrincipal)) {
			erros.add(new DotErpException(subView.getCbCnaePrincipal(),
					"::DotERP - Não pode ficar em branco"));
		}

		EmpresaEntity empresaMatriz = (EmpresaEntity) subView.getCbMatriz()
				.getValue();
		
		TipoEmpresaEn tipoEmpresa = (TipoEmpresaEn) subView.getCbTipoEmpresa().getValue();

		if (ObjectUtils.isBlank(empresaMatriz) && !TipoEmpresaEn.M.equals(tipoEmpresa)) {
			erros.add(new DotErpException(subView.getCbMatriz(),
					"::DotERP - Não pode ficar em branco"));
		}
		
		return erros;
	}

	public static void validateFieldValue(EmpresaFormView subView)
			throws DotErpException {

	}

	public static void clearFormFields(EmpresaFormView subView) {

	}

}