package com.mansha99.contactapp.utils;

import java.util.HashMap;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class MsValidator {

    public static HashMap<String, String> validate(Object model) {
        HashMap<String, String> map = new HashMap<String, String>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(model);
        for (ConstraintViolation<Object> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            map.put(propertyPath, message);
        }
        return map;
    }

    public static boolean validate(HashMap<String, String> errors) {
        if (errors == null) {
            return false;
        }
        return errors.size() == 0;

    }

}
