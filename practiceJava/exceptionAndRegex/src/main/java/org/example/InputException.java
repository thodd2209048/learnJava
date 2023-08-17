package org.example;

import java.util.List;

public class InputException extends Throwable {
    private List<CommonException> errors;

    public InputException(List<CommonException> errors) {
        this.errors = errors;
    }

    public List<CommonException> getErrors() {
        return errors;
    }


}
