package com.nexters.keyme.global.common.annotation;

import com.nexters.keyme.global.common.exceptions.KeymeException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SwaggerErrorCode {
    int code();
    Class<? extends KeymeException> exception();
}
