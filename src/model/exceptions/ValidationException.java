package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errors = new HashMap<>();
	//vamos ter que guardar os erros de cada campo do formulário
	//por isso precisa ser par, primeiro campo (key) para indicar o nome do campo e o segundo a msg de erro
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String errorMessage) {
		errors.put(fieldName, errorMessage);
	}

}
