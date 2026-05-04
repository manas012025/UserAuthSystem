package com.UserAuthSystem.config;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final HttpServletRequest request;

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {

        String ip = getClientIP();

        String key = ip + ":" + joinPoint.getSignature().toShortString();

        Bucket bucket = cache.computeIfAbsent(key, k -> createBucket(rateLimit));

        if (bucket.tryConsume(1)) {
            return joinPoint.proceed();
        }

        return ResponseEntity
                .status(429)
                .body("Too many requests. Please try again later.");
    }

    private Bucket createBucket(RateLimit rateLimit) {
        Bandwidth limit = Bandwidth.simple(
                rateLimit.capacity(),
                Duration.ofMinutes(rateLimit.durationInMinutes())
        );

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    private String getClientIP() {
        String ip = request.getHeader("X-Forwarded-For");
        return (ip != null) ? ip : request.getRemoteAddr();
    }
}