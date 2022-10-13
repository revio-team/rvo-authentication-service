package com.sofia.revio.validation

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailValidator : ConstraintValidator<ValidEmail?, String> {
    override fun isValid(username: String, context: ConstraintValidatorContext): Boolean {
        return validateEmail(username)
    }

    private fun validateEmail(email: String): Boolean {
        val matcher: Matcher = PATTERN.matcher(email)
        return matcher.matches()
    }

    companion object {
        private const val EMAIL_PATTERN =
            "^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        private val PATTERN: Pattern = Pattern.compile(EMAIL_PATTERN)
    }
}