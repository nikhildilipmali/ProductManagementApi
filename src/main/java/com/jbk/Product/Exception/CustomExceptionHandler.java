package com.jbk.Product.Exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jbk.Product.response.ErrorResponce;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object>
	handleMethodArgumentNotValid(MethodArgumentNotValidException ex) 
	{
	HashMap<String, Object> map = new HashMap<>();
	map.put("Time", new Date());
	ex.getBindingResult().getFieldErrors().forEach(error -> 
	{
	map.put(error.getField(), error.getDefaultMessage());
	});
	return map;
	}

	
	@ExceptionHandler(ProductNotFoundEx.class)
	public ResponseEntity<Object>ProductNotFound(ProductNotFoundEx ex){
		ErrorResponce response=new ErrorResponce(ex.getMessage(), new Date());
		
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	

}
