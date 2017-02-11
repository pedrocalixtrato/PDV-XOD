package br.com.pdv.service;

@SuppressWarnings("serial")
public class NegocioException extends RuntimeException {

	public NegocioException(String message) {
		super(message);
	}
	
}
