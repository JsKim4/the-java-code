# 더자바, 코드를 조작하는 다양한 방법

해당 리포지토리는 [더자바, 코드를 조작하는 다양한 방법](https://www.inflearn.com/course/the-java-code-manipulation/dashboard) 의 학습을 정리합니다.

## 목차
- [1장 JVM 이해하기](./chapter_1)
- [2장 바이트코드 조작](./chapter_2)
- [3장 리플렉션](./chapter_3)
- [4장 다이나믹 프록시](./chapter_4)
- [5장 애노테이션 프로세서](./chapter_5)


## 키워드 정리
- 바이트코드 조작 - ASM, `ByteBuddy`
- 리플렉션 API - 클래스의 정보 참조 가능(메소드, 필드, 생성자,...) / 성능적 이슈 있을 수 있음!
- 다이나믹 프록시 기법 - Proxy, CGlib, `ByteBuddy`
- 애노테이션 프로세서 - Abstract`Processor`, Filer, ... ,`JavaPoet`, `AutoService`