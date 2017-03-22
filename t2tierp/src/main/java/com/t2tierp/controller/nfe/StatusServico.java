/*
 * The MIT License
 * 
 * Copyright: Copyright (C) 2014 T2Ti.COM
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * The author may be contacted at: t2ti.com@gmail.com
 *
 * @author Claudio de Barros (T2Ti.com)
 * @version 2.0
 */
package com.t2tierp.controller.nfe;

import br.inf.portalfiscal.www.nfe.wsdl.nfestatusservico2.NfeStatusServico2Stub;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.httpclient.protocol.Protocol;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class StatusServico {

    public String verificaStatusServico(KeyStore ks, String alias, char[] senha) {
        try {
            String codigoUf = "52";
            String ambiente = "2";
            String versaoDados = "3.10";
            String url = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl";

            //cria o xml de requisicao
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<consStatServ versao=\"" + versaoDados + "\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
                    + "<tpAmb>" + ambiente + "</tpAmb>"
                    + "<cUF>" + codigoUf + "</cUF>"
                    + "<xServ>STATUS</xServ>"
                    + "</consStatServ>";

            //busca os dados do certificado digital
            X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
            PrivateKey privatekey = (PrivateKey) ks.getKey(alias, senha);
            SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
            //arquivo que contém a cadeia de certificados do serviço a ser consumido
            socketFactory.setFileCacerts(this.getClass().getResourceAsStream("/br/inf/portalfiscal/nfe/jssecacerts"));

            //define o protocolo a ser utilizado na conexao
            Protocol protocol = new Protocol("https", socketFactory, 443);
            Protocol.registerProtocol("https", protocol);

            //cria os dados da mensagem
            OMElement omeElement = AXIOMUtil.stringToOM(xml);
            NfeStatusServico2Stub.NfeDadosMsg nfeDadosMsg = new NfeStatusServico2Stub.NfeDadosMsg();
            nfeDadosMsg.setExtraElement(omeElement);

            //define os dados do cabecalho da mensagem
            NfeStatusServico2Stub.NfeCabecMsg nfeCabecMsg = new NfeStatusServico2Stub.NfeCabecMsg();
            nfeCabecMsg.setCUF(codigoUf);
            nfeCabecMsg.setVersaoDados(versaoDados);
            NfeStatusServico2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeStatusServico2Stub.NfeCabecMsgE();
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);

            //cria o servico
            NfeStatusServico2Stub stub = new NfeStatusServico2Stub(url);

            //busca o resutado
            NfeStatusServico2Stub.NfeStatusServicoNF2Result result = stub.nfeStatusServicoNF2(nfeDadosMsg, nfeCabecMsgE);

            //processa o resultado
            ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes("UTF-8"));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(in);


            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("xMotivo");

            String retorno = "Status: ";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                retorno += element.getTextContent();
            }

            return retorno;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Erro ao consultar o status do servi�o!";
    }
}
