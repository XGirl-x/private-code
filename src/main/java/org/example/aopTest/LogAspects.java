package org.example.aopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面类
 *@Aspect:作用是把当前类标识为一个切面供容器读取
 */
@Component
@Aspect
public class LogAspects {

    /**
     * 公共的切入点表达式
     *@Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。
     * 可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。
     * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     */
    @Pointcut("execution(public int org.example.aopTest.MathCalculator.*(..))")
    public void pointCut() {

    }

    /**
     * 通知:
     * 前置通知（@Befor）
     * 后置通知 (@After)
     * 返回通知 (@AfterReturning)
     * 异常通知 (@AfterThrowing)
     * 环绕通知(@Around)：动态代理，手动推进目标方法运行(joinpoint.procced)
     * @param joinPoint
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "方法运行前执行，参数是：" + Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "方法运行后执行，无论是否异常都会执行");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void resultReturn(JoinPoint joinPoint, Object result) {
        System.out.println("" + joinPoint.getSignature().getName() + "方法成功后执行，获得返回结果：" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("" +joinPoint.getSignature().getName() + "方法成功后执行，查看异常：" + e.getMessage());
    }
}
