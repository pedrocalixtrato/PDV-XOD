package dc.controller.ponto;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.controller.ponto.exportacao.acjef.GeraArquivoACJEF;
import dc.controller.ponto.exportacao.afdt.GeraArquivoAFDT;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ponto.PontoFechamentoJornada;
import dc.entidade.ponto.PontoMarcacao;
import dc.servicos.dao.administrativo.empresa.EmpresaDAO;
import dc.servicos.dao.ponto.PontoFechamentoDAO;
import dc.servicos.dao.ponto.PontoHorarioDAO;
import dc.servicos.dao.ponto.PontoMarcacaoDAO;
import dc.visao.framework.geral.BlankFormController;
import dc.visao.ponto.PontoConsultaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoConsultaFormController extends BlankFormController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoConsultaFormView subView;

	@Autowired
	private PontoMarcacaoDAO pontoMarcacaoDAO;

	@Autowired
	private PontoHorarioDAO pontoHorarioDAO;

	@Autowired
	private PontoFechamentoDAO pontoFechamentoDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Ponto Marcação Consulta";
	}

	@Override
	public String getViewIdentifier() {
		return "PontoConsultaFormController";
	}

	@Override
	protected void initSubView() {
		subView = new PontoConsultaFormView();

		configuraAFDTButton();
		configuraSearchButton();
		configuraACJEFButton();

	}

	private void configuraACJEFButton() {
		StreamResource ACJEFResource = createACJEFResource();
		if (ACJEFResource != null) {
			FileDownloader fileDownloader = new FileDownloader(ACJEFResource);
			fileDownloader.extend(subView.getBtnACJEF());
		}
	}

	private StreamResource createACJEFResource() {
		final GeraArquivoACJEF geraArquivo = new GeraArquivoACJEF();

		return new StreamResource(new StreamSource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public InputStream  getStream() {
				InputStream is = null;
				try {

					EmpresaEntity empresa = SecuritySessionProvider.getUsuario().getConta().getEmpresa();

					// Empresa empresa = empresaDAO.find(1);
					is = geraArquivo.geraArquivoACJEF(subView.getDtDataInicial().getValue(), subView.getDtDataFinal().getValue(),
							pontoHorarioDAO.getAll(), subView.getPontoFechamentoSubForm().getDados(), empresa);
				} catch (Exception e) {

					e.printStackTrace();
				}

				return is;
			}
		}, "ACJEF.txt");
	}

	private void configuraSearchButton() {

		this.subView.getBtnSearch().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Date dataInicial = subView.getDtDataInicial().getValue();
				Date dataFinal = subView.getDtDataFinal().getValue();

				if (!ObjectValidator.validateObject(dataInicial) || !ObjectValidator.validateObject(dataFinal)) {
					mensagemErro("Nenhuma das datas podem ser nulas!");
				} else if (dataFinal.compareTo(dataInicial) < 0) {
					mensagemErro("Data inicial não pode ser maior que data final");
				} else {
					List<PontoMarcacao> pontos = pontoMarcacaoDAO.searchByInterval(dataInicial, dataFinal);
					subView.fillMarcacoesSubForm(pontos);

					List<PontoFechamentoJornada> fechamentos = pontoFechamentoDAO.searchByInterval(dataInicial, dataFinal);
					subView.fillPontoFechamentoSubForm(fechamentos);
				}

			}
		});

	}

	private void configuraAFDTButton() {
		StreamResource AFDTResource = createAFTDResource();
		if (AFDTResource != null) {
			FileDownloader fileDownloader = new FileDownloader(AFDTResource);
			fileDownloader.extend(subView.getBtnAFDT());
		}
	}

	private StreamResource createAFTDResource() {
		final GeraArquivoAFDT geraArquivo = new GeraArquivoAFDT();
		try {

		} catch (Exception e) {

			e.printStackTrace();
		}
		return new StreamResource(new StreamSource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public InputStream getStream() {
				InputStream is = null;
				try {
					/*
					 * Usuario usuario = SecuritySessionProvider.getUsuario();
					 * Empresa empresa = usuario.getConta().getEmpresa();
					 */
					EmpresaEntity empresa = empresaDAO.find(1);
					is = geraArquivo.geraArquivoAFDT(subView.getDtDataInicial().getValue(), subView.getDtDataFinal().getValue(), subView
							.getMarcacoesSubForm().getDados(), empresa);
				} catch (Exception e) {

					e.printStackTrace();
				}

				return is;
			}
		}, "AFDT.txt");

	}

	@Override
	protected boolean isFullSized() {
		return true;
	}
}
