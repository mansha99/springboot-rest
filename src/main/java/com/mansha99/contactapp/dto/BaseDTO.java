package com.mansha99.contactapp.dto;



import com.mansha99.contactapp.utils.MsValidator;
import java.util.HashMap;

public class BaseDTO {

    private HashMap<String, String> errors;
    private String status;
    private String message;
    public  BaseDTO(){
        setStatus("success");
        setMessage("success");
    }

    public boolean isValid(){
        return errors.size()==0;
    }
    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
        if (MsValidator.validate(errors)) {
            setStatus("success");
            setMessage("success");
        } else {
            setStatus("fail");
            setMessage("Please correct validation errors");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
