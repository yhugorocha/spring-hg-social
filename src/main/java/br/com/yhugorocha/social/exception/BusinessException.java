package br.com.yhugorocha.social.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(){
        super("Bad request");
    }
    public BusinessException(String message){
        super(message);}
}
