/*
 * Copyright 2024 Channelling.lk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lk.channelling.handlers;

import jakarta.servlet.http.HttpServletRequest;
import lk.channelling.log.LogRequest;
import org.apache.logging.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * Aspect class for logging operations based on a specified pointcut.
 *
 * <p>This aspect class defines a pointcut and associated advice for logging
 * operations in an application. It is typically used with an AOP framework
 * such as Spring AOP to apply cross-cutting concerns, such as logging,
 * to specific points in the application's execution flow.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Component
public class LoggingHandler {

    /**
     * Pointcut method for capturing REST controller method calls.
     *
     * <p>This method defines a pointcut for capturing calls to methods within
     * classes annotated with {@code @RestController}. It is typically used in
     * conjunction with an AOP framework, such as Spring AOP, to apply cross-cutting
     * concerns to REST controller methods.</p>
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void pointcutController() {
    }

    /**
     * Advice method executed before REST controller method calls.
     *
     * <p>This method is annotated with {@code @Before} to specify that it should
     * be executed before the methods matched by the {@code pointcutController}
     * pointcut. It is typically used in conjunction with an AOP framework, such as
     * Spring AOP, to apply cross-cutting concerns (e.g., logging) before REST
     * controller method execution.</p>
     */
    @Before("pointcutController()")
    public void logBeforeController(JoinPoint joinPoint) {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String requestId = httpRequest.getHeader("x-request-id");
        String apiName = httpRequest.getRequestURI();
        String userName = httpRequest.getHeader("user-name");

        Object[] methodParams = joinPoint.getArgs();
        LogRequest.getInstance().setRequestId(requestId);
        LogRequest.getInstance().log(Level.INFO,
                apiName,
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.deepToString(methodParams));

        LoginAuthenticationHandler.getInstance().setUserName(userName);
    }
}
