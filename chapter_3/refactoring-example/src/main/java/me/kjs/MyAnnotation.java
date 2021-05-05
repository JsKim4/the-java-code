package me.kjs;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보를 가져가는 범위
@Target({ElementType.TYPE,ElementType.FIELD}) // 붙일 수 있는 위치
@Inherited // 상속 여부
public @interface MyAnnotation {

    String name() default "JunSeop";

    int number() default 100;

    String value();


}
