package dc.controller.ponto.exportacao.afdt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.ponto.PontoMarcacao;

public class GeraArquivoAFDT {

	public InputStream geraArquivoAFDT(Date dataInicial, Date dataFinal, List<PontoMarcacao> marcacoes, EmpresaEntity empresa)
			throws Exception {

		SimpleDateFormat formatoHora = new SimpleDateFormat("HHmm");
		Date dataAtual = new Date();

		// buscar o xml com o layout
		File layout = File.createTempFile("layout", ".xml");
 		layout.deleteOnExit();
		InputStream in = this.getClass().getResourceAsStream("/dc/controller/ponto/exportacao/afdt/afdt.xml");
		FileUtils.copyInputStreamToFile(in, layout);

		// cria um objeto FlatFile
		FlatFile<Record> ff = Texgit.createFlatFile(new FileInputStream(layout));

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
		PontoMarcacao marcacao;
		for (int i = 0; i < marcacoes.size(); i++) {
			tipo2 = new Tipo2(ff.createRecord("Tipo2"));
			marcacao = marcacoes.get(i);

			sequencia++;
			tipo2.setNumeroSequencialRegistro(sequencia);
			tipo2.setDataMarcacao(marcacao.getDataMarcacao());
			tipo2.setHoraMarcacao(marcacao.getHoraMarcacao().substring(0, 2)
					+ marcacao.getHoraMarcacao().substring(3, 5));
			tipo2.setPisEmpregado(marcacao.getColaborador().getPisNumero());
			tipo2.setNumFabricacaoREP(Long.valueOf(marcacao.getPontoRelogio().getNumeroSerie()));
			tipo2.setTipoMarcacao(marcacao.getTipoMarcacao());
			tipo2.setNumeroSequencialEmpregado(Integer.valueOf(marcacao.getParEntradaSaida().substring(1)));
			tipo2.setTipoRegistroMarcado(marcacao.getTipoRegistro());
			if (marcacao.getJustificativa() != null) {
				tipo2.setMotivo(marcacao.getJustificativa());
			} else {
				tipo2.setMotivo(" ");
			}

			ff.addRecord(tipo2.getRecord());
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
