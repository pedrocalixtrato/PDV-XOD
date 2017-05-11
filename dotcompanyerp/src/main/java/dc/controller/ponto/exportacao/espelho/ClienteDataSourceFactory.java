package dc.controller.ponto.exportacao.espelho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dc.entidade.ponto.EspelhoPonto;
import dc.entidade.ponto.EspelhoPontoPeriodo;
import dc.entidade.ponto.PontoHorario;
import dc.entidade.ponto.PontoMarcacao;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe para utilizacao no desenvolvimento do relatorio espelho de ponto.</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2010 T2Ti.COM
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 *        The author may be contacted at:
 *            t2ti.com@gmail.com</p>
 *
 * @author Claudio de Barros (t2ti.com@gmail.com)
 * @version 1.0
 */
public class ClienteDataSourceFactory {

    public ClienteDataSourceFactory() {
    }

    public static List<EspelhoPonto> createBeanCollection() {
        List<EspelhoPonto> listaEspelho = new ArrayList<EspelhoPonto>();

        EspelhoPonto espelho = new EspelhoPonto();
        espelho.setCnpjEmpregador("12345678000100");
        espelho.setNomeEmpregador("Empresa Matriz Para Testes");
        espelho.setEderecoEmpregador("Rua sem nome, 1 - Centro");
        espelho.setDataEmissaoRelatorio(new Date());
        espelho.setPisEmpregado("012873550688");
        espelho.setNomeEmpregado("Teste Pessoa Física");
        espelho.setDataAdmissao(new Date());

        List<PontoHorario> listaHorario = new ArrayList<PontoHorario>();
        PontoHorario horario;
        for (int i = 0; i < 3; i++){
            horario = new PontoHorario();
            horario.setCodigo("000" + i);
            horario.setEntrada01("08:00");
            horario.setSaida01("12:00");
            horario.setEntrada02("14:00");
            horario.setSaida02("18:00");

            listaHorario.add(horario);
        }

        List<EspelhoPontoPeriodo> listaPeriodo = new ArrayList<EspelhoPontoPeriodo>();
        EspelhoPontoPeriodo periodo;
        List<PontoMarcacao> marcacoesTratadas;
        PontoMarcacao marcacao;
        for (int i = 0; i < 31; i++) {
            periodo = new EspelhoPontoPeriodo();

            if (i < 10) {
                periodo.setCodigoHorario("000" + i);
            } else {
                periodo.setCodigoHorario("00" + i);
            }
            periodo.setDia(i);
            periodo.setMarcacaoEntrada01("08:00");
            periodo.setMarcacaoSaida01("12:00");
            periodo.setMarcacaoEntrada02("14:00");
            periodo.setMarcacaoSaida02("18:00");

            periodo.setJornadaEntrada01("08:00");
            periodo.setJornadaSaida01("12:00");
            periodo.setJornadaEntrada02("14:00");
            periodo.setJornadaSaida02("18:00");

            marcacoesTratadas = new ArrayList<PontoMarcacao>();
            periodo.setMarcacoesTratadas(marcacoesTratadas);
            if (i > 15) {
                for (int j = 0; j < 5; j++) {
                    marcacao = new PontoMarcacao();
                    marcacao.setHoraMarcacao("00:00");
                    marcacao.setTipoMarcacao("I");
                    marcacao.setJustificativa("justificativa " + j);

                    marcacoesTratadas.add(marcacao);
                }
            }

            listaPeriodo.add(periodo);
        }
        
        espelho.setListaHorarios(listaHorario);
        espelho.setListaPeriodo(listaPeriodo);
        listaEspelho.add(espelho);

        return listaEspelho;
    }
}