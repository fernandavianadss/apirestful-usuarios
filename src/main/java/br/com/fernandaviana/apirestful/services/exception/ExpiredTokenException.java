package br.com.fernandaviana.apirestful.services.exception;

public class ExpiredTokenException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExpiredTokenException(String mensagem) {
		super(mensagem);
	}
	
	public ExpiredTokenException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
}
