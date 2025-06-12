package com.target.targetcasestudy.core.utils

class TargetCaseStudyException(
    message: String?, val errorCode: Int? = null, throwable: Throwable? = null
) : Exception(message, throwable)