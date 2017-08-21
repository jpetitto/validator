package com.johnpetitto.validator;

/** Validates input for {@link ValidatingTextInputLayout} to meet some requirement. */
public interface Validator {
    /** Returns {@literal true} if the input is considered valid for some requirement. */
    boolean isValid(String input);
}
