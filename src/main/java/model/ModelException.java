package model;

import javax.jws.WebParam;

public class ModelException extends RuntimeException {

    private static final long SerialVersionUID = 1l;

    public ModelException(String message){
        super(message);
    }
}
