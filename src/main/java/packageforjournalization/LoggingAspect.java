package packageforjournalization;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* tn.enicarthage.EnseignantReclamation.*.*(..))")
    private void publicMethodsFromLoggingPackage() {
    }
}
