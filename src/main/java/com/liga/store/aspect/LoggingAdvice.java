package com.liga.store.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liga.store.domain.User;
import com.liga.store.repos.UserRepository;
import com.liga.store.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Logging Advice  class.
 * Marked with @@Slf4j, @Aspect, @Component annotation
 *
 * @author Nazirov Ilhomjon (naziroffjr@gmail.com)
 * @version 1.0
 */

@Slf4j
@Aspect
@Component
public class LoggingAdvice {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final UserRepository userRepository;

    public LoggingAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationManager.authenticate(..))")
    public void loginHandler() {}

    @Pointcut("@annotation(com.liga.store.annotations.Loggable)")
    public void loggableMethod() {}

    @Pointcut("within(@com.liga.store.annotations.Loggable *)")
    public void loggableClass() {}


    @Around("loggableMethod() || loggableClass()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable {
        String fullName = obtainFullName(point, null);
        String requestId = UUID.randomUUID().toString().substring(30);

        Object[] args = point.getArgs();
        log.info(requestId + "-START: " + fullName + ". Args: " + MAPPER.writeValueAsString(args));

        Object object = point.proceed();
        log.info(requestId + "-END: " + fullName + " Response: " + MAPPER.writeValueAsString(object));

        return object;
    }

    @Before("loginHandler()")
    public void loggingSecurity(JoinPoint point) throws JsonProcessingException {
        Object[] args = point.getArgs();
        String name = ((Authentication) args[0]).getName();
        String fullName = obtainFullName(point, name);
        String requestId = UUID.randomUUID().toString().substring(30);

        log.info(requestId + ": " + fullName + ". Args: " + MAPPER.writeValueAsString(args));
    }

    private String obtainFullName(JoinPoint point, @Nullable String email) {
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().toString().substring(6);

        User entity;
        if(email == null) {
            entity = User.builder()
                    .username(methodName)
                    .email(Utils.getAuthenticatedUser().getEmail())
                    .build();
        } else {
            entity = User.builder()
                    .email(email)
                    .build();
        }
        userRepository.save(entity);
        return className + ":" + methodName + "()";
    }


}

