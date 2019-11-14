package advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//一个注解永爱标注不按照ResultAdvice统一响应的方式响应
@Target({ElementType.TYPE,ElementType.METHOD}) //可将该注解标注在类上或方法上
@Retention(RetentionPolicy.RUNTIME)   //Retention运行时
public @interface IgnoreResponseAdvice {
}
