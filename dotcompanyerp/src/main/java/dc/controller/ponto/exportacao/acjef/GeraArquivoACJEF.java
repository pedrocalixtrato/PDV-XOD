package dc.controller.ponto.exportacao.acjef;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.springframework.stereotype.Service;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ponto.PontoFechamentoJornada;
import dc.entidade.ponto.PontoHorario;

@Service
public class GeraArquivoACJEF {

	public InputStream geraArquivoACJEF(Date dataInicial, Date dataFinal, List<PontoHorario> horarios,
			List<PontoFechamentoJornada> listaFechamento, EmpresaEntity empresa) throws Exception {

		SimpleDateFormat formatoHora = new SimpleDateFormat("HHmm");
		Date dataAtual = new Date();
		DecimalFormat formatoHoraExtra = new DecimalFormat("000");

		// buscar o xml com o layout
		File layout = File.createTempFile("layout", ".xml");
		layout.deleteOnExit();
		InputStream in = this.getClass().getResourceAsStream("/dc/controller/ponto/exportacao/acjef/acjef.xml");
		FileUtils.copyInputStreamToFile(in, layout);

		// cria um objeto FlatFile
		FlatFile<Record> ff = Texgit.createFlatFile(layout);

		// registro tipo 1
		Tipo1 tipo1 = new Tipo1(ff.createRecord("Tipo1"));
		tipo1.setNumeroSequencialRegistro(1l);
		tipo1.setTipoIdentificadorEmpregador(1);
		tipo1.setCnpjCpfEmpreador(Long.valueOf(empresa.getCnpj()));
		tipo1.setCeiEmpregador(empresa.getCei());
		tipo1.setRazaoSocialEmpregador(empresa.getRazaoSocial());
		tipo1.setDataInicial(dataInicial);
		tipo1.setDataFinal(dataFinal);
		tipo1.setDataGeracaoArquivo(dataAtual);
		tipo1.setHoraGeracaoArquivo(formatoHora.format(dataAtual));

		ff.addRecord(tipo1.getRecord());

		// registros tipo 2
		Tipo2 tipo2;
		long sequencia = 1;
		PontoHorario horario;
		for (int i = 0; i < horarios.size(); i++) {
			tipo2 = new Tipo2(ff.createRecord("Tipo2"));
			horario = horarios.get(i);

			sequencia++;
			tipo2.setNumeroSequencialRegistro(sequencia);
			tipo2.setCodigoHorario(Integer.valueOf(horario.getCodigo().trim()));
			tipo2.setHoraEntrada(horario.getHoraInicioJornada().substring(0, 2)
					+ horario.getHoraFimJornada().substring(3, 5));
			tipo2.setHoraSaida(horario.getHoraFimJornada().substring(0, 2)
					+ horario.getHoraFimJornada().substring(3, 5));
			if (horario.getEntrada02() == null) {
				tipo2.setInicioIntervalo("");
				tipo2.setFimIntervalo("");
			} else {
				tipo2.setInicioIntervalo(horario.getSaida01().substring(0, 2) + horario.getSaida01().substring(3, 5));
				tipo2.setFimIntervalo(horario.getEntrada02().substring(0, 2) + horario.getEntrada02().substring(3, 5));
			}

			ff.addRecord(tipo2.getRecord());
		}

		// registros tipo 3
		Tipo3 tipo3;
		PontoFechamentoJornada fechamento;
		for (int i = 0; i < listaFechamento.size(); i++) {
			tipo3 = new Tipo3(ff.createRecord("Tipo3"));
			fechamento = listaFechamento.get(i);

			sequencia++;
			tipo3.setNumeroSequencialRegistro(sequencia);
			tipo3.setPisEmpregado(fechamento.getColaborador().getPisNumero());
			tipo3.setDataInicioJornada(fechamento.getDataFechamento());
			tipo3.setPrimeiroHorarioEntrada(fechamento.getEntrada01().substring(0, 2)
					+ fechamento.getEntrada01().substring(3, 5));
			tipo3.setCodigoHorario(Integer.valueOf(fechamento.getCodigoHorario().trim()));
			if (fechamento.getCargaHorariaDiurna() != null) {
				tipo3.setHorasDiurnasNaoExtraordinarias(Integer.valueOf(fechamento.getCargaHorariaDiurna().substring(0,
						2)
						+ fechamento.getCargaHorariaDiurna().substring(3, 5)));
			} else {
				tipo3.setHorasDiurnasNaoExtraordinarias(0);
			}
			if (fechamento.getCargaHorariaNoturna() != null) {
				tipo3.setHorasNoturnasNaoExtraordinarias(Integer.valueOf(fechamento.getCargaHorariaNoturna().substring(
						0, 2)
						+ fechamento.getCargaHorariaNoturna().substring(3, 5)));
			} else {
				tipo3.setHorasNoturnasNaoExtraordinarias(0);
			}
			// horas extras 01
			if (fechamento.getHoraExtra01() != null) {
				tipo3.setHorasExtras1(Integer.valueOf(fechamento.getHoraExtra01().substring(0, 2)
						+ fechamento.getHoraExtra01().substring(3, 5)));
			} else {
				tipo3.setHorasExtras1(0);
			}
			if (fechamento.getPercentualHoraExtra01() != null) {
				tipo3.setPercentualAdicionalHorasExtras1(Integer.valueOf(formatoHoraExtra.format(fechamento
						.getPercentualHoraExtra01())));
			} else {
				tipo3.setPercentualAdicionalHorasExtras1(0);
			}
			if (fechamento.getModalidadeHoraExtra01() != null) {
				tipo3.setModalidadeHoraExtra1(fechamento.getModalidadeHoraExtra01());
			} else {
				tipo3.setModalidadeHoraExtra1(" ");
			}
			// horas extras 02
			if (fechamento.getHoraExtra02() != null) {
				tipo3.setHorasExtras2(Integer.valueOf(fechamento.getHoraExtra02().substring(0, 2)
						+ fechamento.getHoraExtra02().substring(3, 5)));
			} else {
				tipo3.setHorasExtras2(0);
			}
			if (fechamento.getPercentualHoraExtra02() != null) {
				tipo3.setPercentualAdicionalHorasExtras2(Integer.valueOf(formatoHoraExtra.format(fechamento
						.getPercentualHoraExtra02())));
			} else {
				tipo3.setPercentualAdicionalHorasExtras2(0);
			}
			if (fechamento.getModalidadeHoraExtra02() != null) {
				tipo3.setModalidadeHoraExtra2(fechamento.getModalidadeHoraExtra02());
			} else {
				tipo3.setModalidadeHoraExtra2(" ");
			}
			// horas extras 03
			if (fechamento.getHoraExtra03() != null) {
				tipo3.setHorasExtras3(Integer.valueOf(fechamento.getHoraExtra03().substring(0, 2)
						+ fechamento.getHoraExtra03().substring(3, 5)));
			} else {
				tipo3.setHorasExtras3(0);
			}
			if (fechamento.getPercentualHoraExtra03() != null) {
				tipo3.setPercentualAdicionalHorasExtras3(Integer.valueOf(formatoHoraExtra.format(fechamento
						.getPercentualHoraExtra03())));
			} else {
				tipo3.setPercentualAdicionalHorasExtras3(0);
			}
			if (fechamento.getModalidadeHoraExtra03() != null) {
				tipo3.setModalidadeHoraExtra3(fechamento.getModalidadeHoraExtra03());
			} else {
				tipo3.setModalidadeHoraExtra3(" ");
			}
			// horas extras 04
			if (fechamento.getHoraExtra04() != null) {
				tipo3.setHorasExtras4(Integer.valueOf(fechamento.getHoraExtra04().substring(0, 2)
						+ fechamento.getHoraExtra04().substring(3, 5)));
			} else {
				tipo3.setHorasExtras4(0);
			}
			if (fechamento.getPercentualHoraExtra04() != null) {
				tipo3.setPercentualAdicionalHorasExtras4(Integer.valueOf(formatoHoraExtra.format(fechamento
						.getPercentualHoraExtra04())));
			} else {
				tipo3.setPercentualAdicionalHorasExtras4(0);
			}
			if (fechamento.getModalidadeHoraExtra04() != null) {
				tipo3.setModalidadeHoraExtra4(fechamento.getModalidadeHoraExtra04());
			} else {
				tipo3.setModalidadeHoraExtra4(" ");
			}
			if (fechamento.getFaltaAtraso() != null) {
				tipo3.setHorasFaltasAtrasos(Integer.valueOf(fechamento.getFaltaAtraso().substring(0, 2)
						+ fechamento.getFaltaAtraso().substring(3, 5)));
			} else {
				tipo3.setHorasFaltasAtrasos(0);
			}
			if (fechamento.getCompensar() != null) {
				tipo3.setSinalHorasCompensar(Integer.valueOf(fechamento.getCompensar()));
			} else {
				tipo3.setSinalHorasCompensar(0);
			}
			if (fechamento.getBancoHoras() != null) {
				tipo3.setSaldoHorasCompensar(Integer.valueOf(fechamento.getBancoHoras().substring(0, 2)
						+ fechamento.getBancoHoras().substring(3, 5)));
			} else {
				tipo3.setSaldoHorasCompensar(0);
			}

			ff.addRecord(tipo3.getRecord());
		}

		// registro tipo 9
		sequencia++;
		Tipo9 tipo9 = new Tipo9(ff.createRecord("Tipo9"));
		tipo9.setNumeroSequencialRegistro(sequencia);

		ff.addRecord(tipo9.getRecord());

		List<String> linhas = ff.write();

		StringBuffer sb = new StringBuffer();
		for (String linha : linhas) {
			sb.append(linha).append(System.getProperty("line.separator"));
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(sb.toString().getBytes());

		return bais;
	}
}
