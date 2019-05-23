package ssm.test.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//Retention注解决定MyAnnotation注解的生命周期
@Target( { ElementType.METHOD})
public @interface MenuController {
	String url() default "";
	
	String name();
//	 String color() default "blue";//为属性指定缺省值
	
//	增加数组类型的属性：int[] arrayAttr() default {1,2,4};
//	应用数组类型的属性：@MyAnnotation(arrayAttr={2,4,5})
//	如果数组属性只有一个值，这时候属性值部分可以省略大括号，如：@MyAnnotation(arrayAttr=2)，这就表示数组属性只有一个值，值为2

//	增加枚举类型的属性：EumTrafficLamp lamp() default EumTrafficLamp.RED;
//	应用枚举类型的属性：@MyAnnotation(lamp=EumTrafficLamp.GREEN)
}
