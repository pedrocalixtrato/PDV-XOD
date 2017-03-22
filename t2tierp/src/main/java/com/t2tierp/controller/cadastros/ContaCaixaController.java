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
package com.t2tierp.controller.cadastros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.t2tierp.controller.AbstractController;
import com.t2tierp.model.bean.cadastros.AgenciaBanco;
import com.t2tierp.model.bean.cadastros.ContaCaixa;
import com.t2tierp.model.bean.cadastros.Empresa;
import com.t2tierp.model.bean.contabilidade.ContabilConta;
import com.t2tierp.model.dao.InterfaceDAO;
import com.t2tierp.util.FacesContextUtil;

@ManagedBean
@ViewScoped
public class ContaCaixaController extends AbstractController<ContaCaixa> implements Serializable {

    private static final long serialVersionUID = 1L;
	@EJB
	private InterfaceDAO<ContabilConta> contabilContaDao;
	@EJB
	private InterfaceDAO<AgenciaBanco> agenciaBancoDao;

    @Override
    public Class<ContaCaixa> getClazz() {
        return ContaCaixa.class;
    }

    @Override
    public String getFuncaoBase() {
        return "CONTA_CAIXA";
    }
    
    @Override
    public void incluir() {
    	super.incluir();
    	Empresa empresa = FacesContextUtil.getEmpresaUsuario();
    	getObjeto().setEmpresa(empresa);
    }

	public List<ContabilConta> getListaContabilConta(String descricao) {
		List<ContabilConta> listaContabilConta = new ArrayList<>();
		try {
			listaContabilConta = contabilContaDao.getBeansLike(ContabilConta.class, "descricao", descricao);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return listaContabilConta;
	}

	public List<AgenciaBanco> getListaAgenciaBanco(String nome) {
		List<AgenciaBanco> listaAgenciaBanco = new ArrayList<>();
		try {
			listaAgenciaBanco = agenciaBancoDao.getBeansLike(AgenciaBanco.class, "nome", nome);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return listaAgenciaBanco;
	}
	
}