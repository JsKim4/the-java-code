package me.kjs;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보를 가져가는 범위
@Inherited // 상속 여부
public @interface AnotherAnnotation {

    String name() default "JunSeop";

    int number() default 100;

    String value() default "junseop";


}
