# Validator
Get easy error validation by swapping out [TextInputLayout](https://developer.android.com/reference/android/support/design/widget/TextInputLayout.html) with `ValidatingTextInputLayout`:

```xml
<com.johnpetitto.validator.ValidatingTextInputLayout
   android:layout_width="match_parent"
   android:layout_height="wrap_content"
   app:errorLabel="@string/some_error">

   <EditText
       android:layout_width="match_parent"
       android:layout_height="wrap_content" />

</com.johnpetitto.validator.ValidatingTextInputLayout>
```

Then set a `Validator` on your `ValidatingTextInputLayout`:

```java
layout.setValidator(new Validator() {
   @Override
   public boolean isValid(String input) {
       return input.startsWith("J");
   }
});
```

`Validators` provides a handful of predefined validators, including email and phone validation, which you can set with the `app:validator` tag. It also contains a helpful function for validating multiple `ValidatingTextInputLayout` objects at once.

## Download
```groovy
compile 'com.johnpetitto.validator:validator:1.0.0'
```

## License
```
Copyright 2017 John Petitto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
