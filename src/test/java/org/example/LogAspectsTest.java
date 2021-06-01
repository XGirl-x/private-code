package org.example;

import org.example.aopTest.MainConfigofAop;
import org.example.aopTest.MathCalculator;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LogAspectsTest {

    @Test
    public void logAspectTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigofAop.class);
        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        calculator.div(520, 1314);
    }
}
