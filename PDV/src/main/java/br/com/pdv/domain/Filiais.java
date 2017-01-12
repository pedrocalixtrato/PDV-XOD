package br.com.pdv.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="FILIAIS")
public class Filiais implements Serializable{

	@Id
	@Column(name = "FIL_CODIGO")
	@SequenceGenerator(name="id_sequence_fil",sequenceName="HIB_SEQ")
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator = "id_sequence_fil")
	private Integer codigo;
	@Column(name="FIL_NOME")
	private String nome;
	
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}