package com.johnpetitto.validator;

import android.util.Patterns;

/** Collection of utilities for working with {@link ValidatingTextInputLayout}. */
public final class Validators {
    /** Validates input for email formatting. */
    public static final Validator EMAIL;
    /** Validates input for phone number formatting. */
    public static final Validator PHONE;

    static {
        EMAIL = new Validator() {
            @Override
            public boolean isValid(String input) {
                return Patterns.EMAIL_ADDRESS.matcher(input).matches();
            }
        };

        PHONE = new Validator() {
            @Override
            public boolean isValid(String input) {
                return Patterns.PHONE.matcher(input).matches();
            }
        };
    }

    private Validators() {
        throw new AssertionError("No instances");
    }

    /** Validates multiple inputs at once and returns {@code true} if all inputs are valid. */
    public static boolean validate(ValidatingTextInputLayout... layouts) {
        boolean allInputsValid = true;

        for (ValidatingTextInputLayout layout : layouts) {
            if (!layout.validate()) {
                allInputsValid = false;
            }
        }

        return allInputsValid;
    }

    /**
     * Validates input for meeting a minimum number of characters. For validating against trimmed
     * input, {@link #minimum(int, boolean)}.
     */
    public static Validator minimum(int length) {
        return minimum(length, false);
    }

    /**
     * Validates input for meeting a minimum number of characters. Pass {@literal true} for
     * {@code trim} to validate against trimmed input.
     */
    public static Validator minimum(final int length, final boolean trim) {
        return new Validator() {
            @Override
            public boolean isValid(String input) {
                if (trim) {
                    input = input.trim();
                }

                return length <= input.length();
            }
        };
    }
}