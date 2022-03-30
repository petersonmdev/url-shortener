package br.com.zg.urlShortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullReturnShortenerException extends RuntimeException
{

     private static long serialVersionUID = 1L;
     public NullReturnShortenerException(String exception)
     {
          super(exception);
     }
}
