package dc.controller.ponto;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.controller.ponto.exportacao.afd.ImportaArquivoAFD;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.ponto.AFDTipo3;
import dc.entidade.ponto.PontoClassificacaoJornada;
import dc.entidade.ponto.PontoEscala;
import dc.entidade.ponto.PontoFechamentoJornada;
import dc.entidade.ponto.PontoHorario;
import dc.entidade.ponto.PontoMarcacao;
import dc.entidade.ponto.PontoParametro;
import dc.entidade.ponto.PontoRelogio;
import dc.entidade.ponto.PontoTurma;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.ponto.PontoClassificacaoJornadaDAO;
import dc.servicos.dao.ponto.PontoFechamentoDAO;
import dc.servicos.dao.ponto.PontoHorarioDAO;
import dc.servicos.dao.ponto.PontoMarcacaoDAO;
import dc.servicos.dao.ponto.PontoParametroDAO;
import dc.servicos.dao.ponto.PontoRelogioDAO;
import dc.servicos.dao.ponto.PontoTurmaDAO;
import dc.servicos.util.Util;
import dc.visao.framework.geral.BlankFormController;
import dc.visao.ponto.PontoImportacaoFormView;

@Controller
@Scope("prototype")
public class PontoImportacaoFormController extends BlankFormController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoImportacaoFormView subView;

	@Autowired
	private PontoMarcacaoDAO pontoMarcacaoDAO;

	@Autowired
	private PontoHorarioDAO pontoHorarioDAO;

	@Autowired
	private PontoRelogioDAO pontoRelogioDAO;

	@Autowired
	private PontoFechamentoDAO pontoFechamentoDAO;

	@Autowired
	private ColaboradorDAO colaboradorDAO;

	@Autowired
	private PontoTurmaDAO pontoTurmaDAO;

	@Autowired
	private PontoParametroDAO pontoParametroDAO;

	@Autowired
	private PontoClassificacaoJornadaDAO pontoClassificacaoJornadaDAO;

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Ponto Importação";
	}

	@Override
	public String getViewIdentifier() {
		return "PontoImportacaoFormController";
	}

	@Override
	protected void initSubView() {
		subView = new PontoImportacaoFormView();
		configuraComponentes();
	}

	private void configuraComponentes() {

		subView.getUpArquivo().setFieldType(FieldType.FILE);
		subView.getUpArquivo().addListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				UploadField upload = (UploadField) event.getProperty();
				processaArquivo((File) upload.getValue());
				atualizaTabelaAFD();
			}

			private void processaArquivo(File value) {
				ImportaArquivoAFD importa = new ImportaArquivoAFD();
				try {
					List<AFDTipo3> importacoes = importa.leArquivoAFD(value);

					for (AFDTipo3 afd : importacoes) {
						ColaboradorEntity colaborador = colaboradorDAO.getColaboradorByPisNumero(afd.getPisEmpregado());
						afd.setColaborador(colaborador);
					}

					subView.fillAFDSubForm(importacoes);
					subView.getAFDSubForm().markAsDirtyRecursive();
				} catch (Exception e) {
					
					e.printStackTrace();
					mensagemErro(e.getMessage());
				}

			}
		});

		subView.getBtnGravarFechamento().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			@Transactional
			public void buttonClick(ClickEvent event) {

				List<PontoFechamentoJornada> listaFechamento = subView.getPontoFechamentoSubForm().getDados();

				PontoFechamentoJornada vo = null;

				for (int i = 0; i < listaFechamento.size(); i++) {
					vo = listaFechamento.get(i);
					pontoFechamentoDAO.save(vo);
				}

				mensagemSalvoOK();

			}
		});

		subView.getBtnProcessarFechamento().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				atualizaTabelaAFD();
				atualizaTabelaPontoMarcaoes();
				atualizaTabelaPontoFechamento();
			}

		});

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	private void atualizaTabelaPontoFechamento() {
		List<AFDTipo3> marcacoes = subView.getAFDSubForm().getDados();
		try {

			List<PontoClassificacaoJornada> listaClassificacao = pontoClassificacaoJornadaDAO
					.getPontoClassificacaoJornadaByPadrao("S");
			if (listaClassificacao.isEmpty()) {
				throw new Exception("Nenhuma classificação de jornada cadastrada como padrão");
			}
			PontoClassificacaoJornada classificacao = listaClassificacao.get(0);
			// ordena a lista pelo numero do PIS e Data da Marcacao
			Collections.sort(marcacoes, new Comparator<AFDTipo3>() {

				public int compare(AFDTipo3 m1, AFDTipo3 m2) {

					int resultado = m1.getPisEmpregado().compareTo(m2.getPisEmpregado());
					if (resultado != 0) {
						return resultado;
					}
					resultado = m1.getDataMarcacao().compareTo(m2.getDataMarcacao());
					return resultado;
				}
			});

			String numeroPis = "";
			Calendar dataMarcacao = Calendar.getInstance();
			AFDTipo3 marcacao;
			PontoFechamentoJornada fechamentoJornada = null;
			List<PontoFechamentoJornada> listaFechamento = new ArrayList<PontoFechamentoJornada>();
			for (int i = 0; i < marcacoes.size(); i++) {
				marcacao = marcacoes.get(i);
				if (!marcacao.getDesconsiderar()) {
					if ((!marcacao.getPisEmpregado().equals(numeroPis))
							|| (marcacao.getDataMarcacao().compareTo(dataMarcacao.getTime()) != 0)) {
						numeroPis = marcacao.getPisEmpregado();
						dataMarcacao.setTime(marcacao.getDataMarcacao());

						fechamentoJornada = new PontoFechamentoJornada();
						fechamentoJornada.setPontoClassificacaoJornada(classificacao);
						fechamentoJornada.setColaborador(marcacao.getColaborador());
						fechamentoJornada.setDataFechamento(dataMarcacao.getTime());
						switch (dataMarcacao.get(Calendar.DAY_OF_WEEK)) {
						case 1:
							fechamentoJornada.setDiaSemana("DOMINGO");
							break;
						case 2:
							fechamentoJornada.setDiaSemana("SEGUNDA");
							break;
						case 3:
							fechamentoJornada.setDiaSemana("TERCA");
							break;
						case 4:
							fechamentoJornada.setDiaSemana("QUARTA");
							break;
						case 5:
							fechamentoJornada.setDiaSemana("QUINTA");
							break;
						case 6:
							fechamentoJornada.setDiaSemana("SEXTA");
							break;
						case 7:
							fechamentoJornada.setDiaSemana("SABADO");
							break;
						}
						
						if(marcacao.getPontoHorario() != null)
						{
							fechamentoJornada.setCodigoHorario(marcacao.getPontoHorario().getCodigo());
							fechamentoJornada.setCargaHorariaEsperada(marcacao.getPontoHorario().getCargaHoraria());
							fechamentoJornada.setHoraInicioJornada(marcacao.getPontoHorario().getHoraInicioJornada());
							fechamentoJornada.setHoraFimJornada(marcacao.getPontoHorario().getHoraFimJornada());
	
							listaFechamento.add(fechamentoJornada);
						}
					}
					defineTipoRegistro(fechamentoJornada, marcacao);
				}
			}

			// realiza os calculos necessarios
			Calendar dataFechamento = Calendar.getInstance();
			for (int i = 0; i < listaFechamento.size(); i++) {
				fechamentoJornada = listaFechamento.get(i);
				dataFechamento.setTime(fechamentoJornada.getDataFechamento());
				int mes = dataFechamento.get(Calendar.MONTH) + 1;
				int ano = dataFechamento.get(Calendar.YEAR);
				String mesAno = mes < 10 ? "0" + mes + "/" + ano : mes + "/" + ano;

				PontoParametro parametro = pontoParametroDAO.getPontoParametroByMesAno(mesAno);

				if (parametro == null) {
					throw new Exception("Não existe parametros cadastrados para o período " + mesAno);
				}
				// verifica se os pares Entrada/Saida foram registrados
				verificaParesFechamento(fechamentoJornada);

				// calcula a quantidade de horas trabalhadas
				fechamentoJornada.setCargaHorariaTotal(calculaHorasTrabalhados(fechamentoJornada));
				fechamentoJornada.setCargaHorariaNoturna(calculaCargaHorariaNoturna(fechamentoJornada, parametro));
				fechamentoJornada.setCargaHorariaDiurna(calculaCargaHorariaDiurna(fechamentoJornada));
				calculaHorasExtras(fechamentoJornada, parametro);
			}

			subView.getPontoFechamentoSubForm().fillWith(listaFechamento);
		} catch (Exception ex) {
			ex.printStackTrace();
			mensagemErro(ex.getMessage());

		} finally {

		}

	}

	private Map<String, Serializable> defineTipoMarcacaoHorario(PontoEscala escala, PontoMarcacao marcacao)
			throws Exception {
		Calendar dataMarcacao = Calendar.getInstance();
		dataMarcacao.setTime(marcacao.getDataMarcacao());
		dataMarcacao.set(Calendar.HOUR_OF_DAY, Integer.valueOf(marcacao.getHoraMarcacao().substring(0, 2)));
		dataMarcacao.set(Calendar.MINUTE, Integer.valueOf(marcacao.getHoraMarcacao().substring(3, 5)));
		dataMarcacao.set(Calendar.SECOND, Integer.valueOf(marcacao.getHoraMarcacao().substring(6, 8)));
		PontoHorario horario;
		String codigoHorario = null;

		// busca o horario de acordo com o dia da semana
		switch (dataMarcacao.get(Calendar.DAY_OF_WEEK)) {
		case 1: {// domingo
			codigoHorario = escala.getCodigoHorarioDomingo();
			break;
		}
		case 2: {// seguna
			codigoHorario = escala.getCodigoHorarioSegunda();
			break;
		}
		case 3: {// terca
			codigoHorario = escala.getCodigoHorarioTerca();
			break;
		}
		case 4: {// quarta
			codigoHorario = escala.getCodigoHorarioQuarta();
			break;
		}
		case 5: {// quinta
			codigoHorario = escala.getCodigoHorarioQuinta();
			break;
		}
		case 6: {// sexta
			codigoHorario = escala.getCodigoHorarioSexta();
			break;
		}
		case 7: {// sabado
			codigoHorario = escala.getCodigoHorarioSabado();
			break;
		}
		}
		horario = pontoHorarioDAO.getPontoHorarioByCodigo(codigoHorario);
		if (horario == null) {
			throw new Exception("Código do horário cadastrado na escala não corresponde a um horário armazenado!");
		}

		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		int segundosHoraMarcada = Util.getHoraSeg(dataMarcacao);
		int segundosHoraHorario = 0;
		int diferencaSegundos = 0;
		int diferencaSegundosAnterior = 0;
		String parEntradaSaida = null;
		Calendar dataHorario = Calendar.getInstance();
		// verifica qual registro foi efetuado de acordo com os horário
		// cadastrados
		// ser� realizada a diferença entre os segundos da marcação e os
		// segundos do horário cadastrado
		// o tipo de marcação a ser registrada é o que der a menor diferença
		// entre os registros
		if (horario.getEntrada01() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getEntrada01()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			diferencaSegundosAnterior = diferencaSegundos;
			parEntradaSaida = "E1";
		}
		if (horario.getSaida01() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getSaida01()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "S1";
			}
		}
		if (horario.getEntrada02() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getEntrada02()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "E2";
			}
		}
		if (horario.getSaida02() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getSaida02()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "S2";
			}
		}
		if (horario.getEntrada03() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getEntrada03()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "E3";
			}
		}
		if (horario.getSaida03() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getSaida03()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "S3";
			}
		}
		if (horario.getEntrada04() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getEntrada04()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "E4";
			}
		}
		if (horario.getSaida04() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getSaida04()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "S4";
			}
		}
		if (horario.getEntrada05() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getEntrada05()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "E5";
			}
		}
		if (horario.getSaida05() != null) {
			dataHorario.setTime(formatoHora.parse(horario.getSaida05()));
			segundosHoraHorario = Util.getHoraSeg(dataHorario);
			diferencaSegundos = Math.abs(segundosHoraMarcada - segundosHoraHorario);
			if (diferencaSegundos < diferencaSegundosAnterior) {
				diferencaSegundosAnterior = diferencaSegundos;
				parEntradaSaida = "S5";
			}
		}
		Map<String, Serializable> resultado = new HashMap<String, Serializable>();
		resultado.put("pontoHorario", horario);
		resultado.put("parEntradaSaida", parEntradaSaida);

		return resultado;
	}

	@Transactional
	private void atualizaTabelaPontoMarcaoes() {
		List<AFDTipo3> marcacoes = subView.getAFDSubForm().getDados();
		PontoRelogio relogio = pontoRelogioDAO.getPontoRelogioByNumeroSerie(marcacoes.get(0)
				.getNumeroSerieRelogioPonto());

		ColaboradorEntity colaborador;
		PontoMarcacao pontoMarcacao;
		for (int i = 0; i < marcacoes.size(); i++) {
			if (relogio == null) {
				marcacoes.get(i).setSituacaoRegistro("Relógio de Ponto não encontrado na base de dados!");
			} else {
				// busca os dados do colaborador
				colaborador = marcacoes.get(i).getColaborador();

				if (colaborador == null) {
					marcacoes.get(i).setSituacaoRegistro("Nr. PIS não cadastrado!");
				} else {
					if (colaborador.getCodigoTurmaPonto() == null) {
						marcacoes.get(i).setColaborador(colaborador);
						marcacoes.get(i).setSituacaoRegistro("Colaborador está sem o Código da turma cadastrada!");
					} else {
						marcacoes.get(i).setColaborador(colaborador);
						// Verifica se o registro já foi armazenado no
						// banco de dados
						PontoMarcacao marc = pontoMarcacaoDAO.getPontoMarcacao(colaborador, relogio, marcacoes.get(i)
								.getDataMarcacao(), marcacoes.get(i).getHoraMarcacao());

						// busca os horário
						PontoTurma turma = pontoTurmaDAO.getPontoTurmaByCodigo(colaborador.getCodigoTurmaPonto());

						if (marc == null) {
							if (turma != null) {
								pontoMarcacao = new PontoMarcacao();
								pontoMarcacao.setColaborador(colaborador);
								pontoMarcacao.setPontoRelogio(relogio);
								if (marcacoes.get(i).getSequencial() != null) {
									pontoMarcacao.setNsr(marcacoes.get(i).getSequencial().intValue());
								}
								pontoMarcacao.setDataMarcacao(marcacoes.get(i).getDataMarcacao());
								pontoMarcacao.setHoraMarcacao(marcacoes.get(i).getHoraMarcacao());
								try {
									Map<String, Serializable> m = defineTipoMarcacaoHorario(turma.getPontoEscala(),
											pontoMarcacao);
									String parEntradaSaida = (String) m.get("parEntradaSaida");
									if (marcacoes.get(i).getDesconsiderar()) {
										pontoMarcacao.setTipoMarcacao("D");
									} else {
										pontoMarcacao.setTipoMarcacao(parEntradaSaida.substring(0, 1));
									}
									pontoMarcacao.setTipoRegistro(marcacoes.get(i).getTipoRegistro());
									pontoMarcacao.setParEntradaSaida(parEntradaSaida);
									pontoMarcacao.setJustificativa(marcacoes.get(i).getJustificativa());

									pontoMarcacaoDAO.save(pontoMarcacao);

									marcacoes.get(i).setColaborador(colaborador);
									marcacoes.get(i).setSituacaoRegistro("Registro incluído!");
									marcacoes.get(i).setPontoHorario((PontoHorario) m.get("pontoHorario"));
									marcacoes.get(i).setParEntradaSaida(parEntradaSaida);
								} catch (Exception e) {
									marcacoes.get(i).setSituacaoRegistro(e.getMessage());
								}
							}
							else
							{
								marcacoes.get(i).setJustificativa("Ponto Turma não existe.");	
							}
						} else {
							marcacoes.get(i).setSituacaoRegistro("Registro já incluído anteriormente!");
							marcacoes.get(i).setParEntradaSaida(marc.getParEntradaSaida());
							Map<String, Serializable> m = null;
							try {
								m = defineTipoMarcacaoHorario(turma.getPontoEscala(), marc);
							} catch (Exception e) {
								mensagemErro(e.getMessage());
								e.printStackTrace();
							}
							marcacoes.get(i).setPontoHorario((PontoHorario) m.get("pontoHorario"));

							if (marcacoes.get(i).getDesconsiderar()) {
								marc.setTipoMarcacao("D");
							} else {
								marc.setTipoMarcacao(marcacoes.get(i).getParEntradaSaida().substring(0, 1));
							}
							marc.setTipoRegistro(marcacoes.get(i).getTipoRegistro());
							marc.setJustificativa(marcacoes.get(i).getJustificativa());

							pontoMarcacaoDAO.saveOrUpdate(marc);
						}
					}
				}
			}
		}

		subView.getAFDSubForm().fillWith(marcacoes);
	}

	@Transactional
	private void atualizaTabelaAFD() {
		List<AFDTipo3> importacoes = subView.getAFDSubForm().getDados();
		subView.getTabSheet().setSelectedTab(1);

		if (!importacoes.isEmpty()) {
			for (int i = (importacoes.size() - 1); i > 0; i--) {
				if (importacoes.get(i).getSequencial().intValue() == 0) {
					importacoes.remove(i);
				}
			}
		}

		/*
		 * transforma as Marcações que foram feitas na tela em AFD para serem
		 * utilizadas no fechamentos
		 */
		List<PontoMarcacao> marcacoes = subView.getPontoMarcacaoSubForm().getDados();
		AFDTipo3 tipo3;
		for (int i = 0; marcacoes != null && i < marcacoes.size(); i++) {
			tipo3 = new AFDTipo3(); //
			tipo3.setColaborador(marcacoes.get(i).getColaborador());
			tipo3.setPisEmpregado(marcacoes.get(i).getColaborador().getPisNumero());
			tipo3.setDataMarcacao(marcacoes.get(i).getDataMarcacao());
			tipo3.setHoraMarcacao(marcacoes.get(i).getHoraMarcacao()); // tipo3.setSequencial(0l);
			tipo3.setTipoRegistro("I");
			tipo3.setJustificativa(marcacoes.get(i).getJustificativa());
			tipo3.setDesconsiderar(false);

			importacoes.add(tipo3);
		}
	}

	private void defineTipoRegistro(PontoFechamentoJornada fechamento, AFDTipo3 marcacao) {
		if (marcacao.getParEntradaSaida() != null) {
			if (marcacao.getParEntradaSaida().equals("E1")) {
				fechamento.setEntrada01(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("S1")) {
				fechamento.setSaida01(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("E2")) {
				fechamento.setEntrada02(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("S2")) {
				fechamento.setSaida02(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("E3")) {
				fechamento.setEntrada03(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("S3")) {
				fechamento.setSaida03(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("E4")) {
				fechamento.setEntrada04(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("S4")) {
				fechamento.setSaida04(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("E5")) {
				fechamento.setEntrada05(marcacao.getHoraMarcacao());
			}
			if (marcacao.getParEntradaSaida().equals("S5")) {
				fechamento.setSaida05(marcacao.getHoraMarcacao());
			}
		}
	}

	private void verificaParesFechamento(PontoFechamentoJornada fechamento) {
		if (fechamento.getEntrada01() != null && fechamento.getSaida01() == null) {
			fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
		}
		if (fechamento.getEntrada01() == null && fechamento.getSaida01() != null) {
			fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
		}
		if (fechamento.getEntrada02() != null && fechamento.getSaida02() == null) {
			fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
		}
		if (fechamento.getEntrada02() == null && fechamento.getSaida02() != null) {
			fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
		}
		if (fechamento.getEntrada03() != null && fechamento.getSaida03() == null) {
			fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
		}
		if (fechamento.getEntrada03() == null && fechamento.getSaida03() != null) {
			fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
		}
		if (fechamento.getEntrada04() != null && fechamento.getSaida04() == null) {
			fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
		}
		if (fechamento.getEntrada04() == null && fechamento.getSaida04() != null) {
			fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
		}
		if (fechamento.getEntrada05() != null && fechamento.getSaida05() == null) {
			fechamento.setObservacao("Registro de entrada sem o registro de saída correspondente!");
		}
		if (fechamento.getEntrada05() == null && fechamento.getSaida05() != null) {
			fechamento.setObservacao("Registro de saida sem o registro de entrada correspondente!");
		}
	}

	private String calculaHorasTrabalhados(PontoFechamentoJornada fechamento) {
		int segundosTrabalhados = 0;
		Calendar dataC = Calendar.getInstance();
		// par E1/S1
		if (fechamento.getEntrada01() != null && fechamento.getSaida01() != null) {
			dataC = Util.horaStrToCalendar(fechamento.getSaida01());
			segundosTrabalhados += Util.getHoraSeg(dataC);

			dataC = Util.horaStrToCalendar(fechamento.getEntrada01());
			segundosTrabalhados -= Util.getHoraSeg(dataC);
		}

		// par E2/S2
		if (fechamento.getEntrada02() != null && fechamento.getSaida02() != null) {
			dataC = Util.horaStrToCalendar(fechamento.getSaida02());
			segundosTrabalhados += Util.getHoraSeg(dataC);

			dataC = Util.horaStrToCalendar(fechamento.getEntrada02());
			segundosTrabalhados -= Util.getHoraSeg(dataC);
		}
		// par E3/S3
		if (fechamento.getEntrada03() != null && fechamento.getSaida03() != null) {
			dataC = Util.horaStrToCalendar(fechamento.getSaida03());
			segundosTrabalhados += Util.getHoraSeg(dataC);

			dataC = Util.horaStrToCalendar(fechamento.getEntrada03());
			segundosTrabalhados -= Util.getHoraSeg(dataC);
		}
		// par E4/S4
		if (fechamento.getEntrada04() != null && fechamento.getSaida04() != null) {
			dataC = Util.horaStrToCalendar(fechamento.getSaida04());
			segundosTrabalhados += Util.getHoraSeg(dataC);

			dataC = Util.horaStrToCalendar(fechamento.getEntrada01());
			segundosTrabalhados -= Util.getHoraSeg(dataC);
		}
		// par E5/S5
		if (fechamento.getEntrada05() != null && fechamento.getSaida05() != null) {
			dataC = Util.horaStrToCalendar(fechamento.getSaida05());
			segundosTrabalhados += Util.getHoraSeg(dataC);

			dataC = Util.horaStrToCalendar(fechamento.getEntrada01());
			segundosTrabalhados -= Util.getHoraSeg(dataC);
		}
		return Util.getHoraMinutoSegundo(segundosTrabalhados);
	}

	private String calculaCargaHorariaNoturna(PontoFechamentoJornada fechamento, PontoParametro parametro) {
		int segundosHoraNoturnaTrabalhada = 0;
		int segundosHoraSaida = 0;
		int segundosHoraEntrada = 0;
		int inicioHoraNoturna = Util.getHoraSeg(Util.horaStrToCalendar(parametro.getHoraNoturnaInicio()));
		int fimHoraNoturna = Util.getHoraSeg(Util.horaStrToCalendar(parametro.getHoraNoturnaFim()));

		// par E1/S1
		if (fechamento.getEntrada01() != null && fechamento.getSaida01() != null) {
			segundosHoraEntrada = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getEntrada01()));
			segundosHoraSaida = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getSaida01()));

			segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida,
					inicioHoraNoturna, fimHoraNoturna);
		}
		// par E2/S2
		if (fechamento.getEntrada02() != null && fechamento.getSaida02() != null) {
			segundosHoraEntrada = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getEntrada02()));
			segundosHoraSaida = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getSaida02()));

			segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida,
					inicioHoraNoturna, fimHoraNoturna);
		}
		// par E3/S3
		if (fechamento.getEntrada03() != null && fechamento.getSaida03() != null) {
			segundosHoraEntrada = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getEntrada03()));
			segundosHoraSaida = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getSaida03()));

			segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida,
					inicioHoraNoturna, fimHoraNoturna);
		}
		// par E4/S4
		if (fechamento.getEntrada04() != null && fechamento.getSaida04() != null) {
			segundosHoraEntrada = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getEntrada04()));
			segundosHoraSaida = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getSaida04()));

			segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida,
					inicioHoraNoturna, fimHoraNoturna);
		}
		// par E5/S5
		if (fechamento.getEntrada05() != null && fechamento.getSaida05() != null) {
			segundosHoraEntrada = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getEntrada05()));
			segundosHoraSaida = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getSaida05()));

			segundosHoraNoturnaTrabalhada += calculaSegundosHoraNoturna(segundosHoraEntrada, segundosHoraSaida,
					inicioHoraNoturna, fimHoraNoturna);
		}

		return Util.getHoraMinutoSegundo(segundosHoraNoturnaTrabalhada);
	}

	private int calculaSegundosHoraNoturna(int segundosHoraEntrada, int segundosHoraSaida, int inicioHoraNoturna,
			int fimHoraNoturna) {
		if (segundosHoraSaida > inicioHoraNoturna) {
			if (segundosHoraEntrada > inicioHoraNoturna) {
				return segundosHoraSaida - segundosHoraEntrada;
			} else {
				return segundosHoraSaida - inicioHoraNoturna;
			}
		}
		if (segundosHoraEntrada < fimHoraNoturna) {
			if (segundosHoraSaida < fimHoraNoturna) {
				return segundosHoraSaida - segundosHoraEntrada;
			} else {
				return fimHoraNoturna - segundosHoraEntrada;
			}
		}
		return 0;
	}

	private String calculaCargaHorariaDiurna(PontoFechamentoJornada fechamento) {
		int segundosCargaHorariaTotal = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getCargaHorariaTotal()));
		int segundosCargaHorariaNoturna = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getCargaHorariaNoturna()));

		return Util.getHoraMinutoSegundo(segundosCargaHorariaTotal - segundosCargaHorariaNoturna);
	}

	private void calculaHorasExtras(PontoFechamentoJornada fechamento, PontoParametro parametro) {

		int segundosTrabalhados = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getCargaHorariaTotal()));
		int segundosCargaHoraria = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getCargaHorariaEsperada()));
		int cargaHorariaNoturna = Util.getHoraSeg(Util.horaStrToCalendar(fechamento.getCargaHorariaNoturna()));
		int totalHoraExtra = 0;
		if (segundosTrabalhados > segundosCargaHoraria) {
			totalHoraExtra = segundosTrabalhados - segundosCargaHoraria;
			if (parametro.getTratamentoHoraMais().equals("E")) {
				if (cargaHorariaNoturna > 0) {
					if (cargaHorariaNoturna == totalHoraExtra) {
						fechamento.setHoraExtra01(Util.getHoraMinutoSegundo(cargaHorariaNoturna));
						fechamento.setPercentualHoraExtra01(parametro.getPercentualHeNoturna());
						fechamento.setModalidadeHoraExtra01("N");
					} else if (cargaHorariaNoturna < totalHoraExtra) {
						fechamento.setHoraExtra01(Util.getHoraMinutoSegundo(cargaHorariaNoturna));
						fechamento.setPercentualHoraExtra01(parametro.getPercentualHeNoturna());
						fechamento.setModalidadeHoraExtra01("N");

						fechamento.setHoraExtra02(Util.getHoraMinutoSegundo(totalHoraExtra - cargaHorariaNoturna));
						fechamento.setPercentualHoraExtra02(parametro.getPercentualHeDiurna());
						fechamento.setModalidadeHoraExtra02("D");
					}
				} else {
					fechamento.setPercentualHoraExtra01(parametro.getPercentualHeDiurna());
					fechamento.setModalidadeHoraExtra01("D");
				}
			} else if (parametro.getTratamentoHoraMais().equals("B")) {
				fechamento.setCompensar("1");
				if (cargaHorariaNoturna > 0) {
					if ((cargaHorariaNoturna == totalHoraExtra) || cargaHorariaNoturna > totalHoraExtra) {
						fechamento.setBancoHoras(Util.getHoraMinutoSegundo(totalHoraExtra * (8 / 7)));
					} else if (cargaHorariaNoturna < totalHoraExtra) {
						fechamento.setBancoHoras(Util.getHoraMinutoSegundo((cargaHorariaNoturna * (8 / 7))
								+ (totalHoraExtra - cargaHorariaNoturna)));
					}
				} else {
					fechamento.setBancoHoras(Util.getHoraMinutoSegundo(totalHoraExtra));
				}
			}
		} else if (segundosTrabalhados < segundosCargaHoraria) {
			totalHoraExtra = segundosCargaHoraria - segundosTrabalhados;
			if (parametro.getTratamentoHoraMenos().equals("F")) {
				fechamento.setFaltaAtraso(Util.getHoraMinutoSegundo(totalHoraExtra));
			} else {
				fechamento.setCompensar("2");
				fechamento.setBancoHoras(Util.getHoraMinutoSegundo(totalHoraExtra));
			}
		}
	}
}
