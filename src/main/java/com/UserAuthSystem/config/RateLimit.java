package com.UserAuthSystem.config;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    int capacity() default 10;          // max requests
    int durationInMinutes() default 1;  // time window
}