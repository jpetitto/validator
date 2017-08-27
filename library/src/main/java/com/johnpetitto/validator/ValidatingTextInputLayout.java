package com.johnpetitto.validator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.EditText;

/** An extension of {@code TextInputLayout} that validates the text of its child {@code EditText}
 * and displays an error when the input is invalid.
 * <p>
 * Adding a {@code ValidatingTextInputLayout} to your XML layout file is analogous to adding a
 * {@code TextInputLayout}:
 * <pre>
 * {@code
 * <com.johnpetitto.validator.ValidatingTextInputLayout
 *     android:layout_width="match_parent"
 *     android:layout_height="wrap_content"
 *     app:errorLabel="@string/some_error">
 *
 *     <EditText
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content" />
 *
 * </com.johnpetitto.validator.ValidatingTextInputLayout>
 * }
 * </pre>
 * To set a {@link Validator} for your {@code ValidatingTextInputLayout}, call
 * {@link #setValidator(Validator)}:
 * <pre><code>
 * ValidatingTextInputLayout layout = ...
 * layout.setValidator(new Validator() {
 *     public boolean isValid(String input) {
 *         return input.startsWith("J");
 *     }
 * });
 * </code></pre>
 * There are a handful of predefined validators in {@link Validators}, as well as a utility for
 * validating multiple {@link Validator} objects at once. You can use either the
 * {@link Validators#EMAIL} or {@link Validators#PHONE} validators in XML with the
 * {@code app:validator} tag.
 */
public class ValidatingTextInputLayout extends TextInputLayout {
    private static final int EMAIL_VALIDATOR = 1;
    private static final int PHONE_VALIDATOR = 2;

    private Validator validator;
    private CharSequence errorLabel;

    public ValidatingTextInputLayout(Context context) {
        this(context, null);
    }

    public ValidatingTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ValidatingTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ValidatingTextInputLayout);

        errorLabel = a.getString(R.styleable.ValidatingTextInputLayout_errorLabel);

        int validatorValue = a.getInt(R.styleable.ValidatingTextInputLayout_validator, 0);
        switch (validatorValue) {
            case EMAIL_VALIDATOR:
                validator = Validators.EMAIL;
                break;
            case PHONE_VALIDATOR:
                validator = Validators.PHONE;
                break;
        }

        a.recycle();
    }

    /** Set a {@link Validator} for validating the contained {@code EditText} input text. */
    public void setValidator(@NonNull Validator validator) {
        this.validator = validator;
    }

    /** Set the label to show when {@link #validate()} returns {@literal false}. */
    public void setErrorLabel(CharSequence label) {
        errorLabel = label;
    }

    /**
     * Invoke this when you want to validate the contained {@code EditText} input text against the
     * provided {@link Validator}. For validating multiple {@code ValidatingTextInputLayout}
     * objects at once, call {@link Validators#validate(ValidatingTextInputLayout...)}. Throws an
     * {@code IllegalStateException} if either no validator has been set or an error is triggered
     * and no error label is set.
     */
    public boolean validate() {
        if (validator == null) {
            throw new IllegalStateException("A Validator must be set; call setValidator first.");
        }

        CharSequence input = "";
        EditText editText = getEditText();
        if (editText != null) {
            input = editText.getText();
        }

        boolean valid = validator.isValid(input.toString());
        if (valid) {
            super.setError(null);
        } else {
            if (errorLabel == null) {
                throw new IllegalStateException("An error label must be set when validating an " +
                        "invalid input; call setErrorLabel or app:errorLabel first.");
            }
            super.setError(errorLabel);
        }

        return valid;
    }
}
