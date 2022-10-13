package com.sofia.revio.validation

import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.EnglishSequenceData
import org.passay.IllegalSequenceRule
import org.passay.LengthRule
import org.passay.PasswordData
import org.passay.PasswordValidator
import org.passay.RuleResult
import org.passay.WhitespaceRule
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class PasswordConstraintValidator : ConstraintValidator<ValidPassword?, String?> {
    override fun initialize(arg0: ValidPassword?) {}
    override fun isValid(password: String?, context: ConstraintValidatorContext): Boolean {
        val validator = PasswordValidator(
            listOf(
                LengthRule(8, 30),
                CharacterRule(EnglishCharacterData.UpperCase, 1),
                CharacterRule(EnglishCharacterData.Digit, 1),
                CharacterRule(EnglishCharacterData.Special, 1),
                IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),
                IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),
                IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),
                WhitespaceRule()
            )
        )
        val result: RuleResult = validator.validate(PasswordData(password))
        if (result.isValid) {
            return true
        }
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(
            validator.getMessages(result).joinToString()
        )
            .addConstraintViolation()
        return false
    }
}