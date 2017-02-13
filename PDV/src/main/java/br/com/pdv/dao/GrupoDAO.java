package br.com.pdv.dao;

import java.io.Serializable;

import br.com.pdv.dao.hibernate.HibernateGenericDAO;
import br.com.pdv.domain.Grupo;

@SuppressWarnings("serial")
public class GrupoDAO extends HibernateGenericDAO<Grupo, Long> implements Serializable{

}
