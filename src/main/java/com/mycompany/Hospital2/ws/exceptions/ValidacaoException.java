package com.mycompany.Hospital2.ws.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault
public class ValidacaoException extends Exception{

    public ValidacaoException(String message) {
        super(message);
    }
    
    
    
}
