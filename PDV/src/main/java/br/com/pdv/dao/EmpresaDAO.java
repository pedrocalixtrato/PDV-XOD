package br.com.pdv.dao;

import java.io.Serializable;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Empresa;

@SuppressWarnings("serial")
public class EmpresaDAO extends HibernateGenericDAO<Empresa, Long> implements Serializable{

}
