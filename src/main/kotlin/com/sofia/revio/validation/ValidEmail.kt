package com.sofia.revio.validation

import javax.validation.Constraint
import kotlin.reflect.KClass


@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.ANNOTATION_CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EmailValidator::class])
@MustBeDocumented
annotation class ValidEmail(
    val message: String = "Invalid Email",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)