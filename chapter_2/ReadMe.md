# 바이트코드 조작

## 목차
- 코드 커버리지는 어떻게 측정할까?
- 모자에서 토끼를 꺼내는 마술
- javaagent 실습
- 바이트코드 조작 정리

# 코드 커버리지는 어떻게 측정할까?
- 바이트 코드를 읽어 코드 커버리지를 측정해야 하는 부분의 개수를셈
- 코드가 실행될떄 어떤 코드를 지나갔는지 확인함

# 모자에서 토끼를 꺼내는 마술
- 바이트코드 조작 라이브러리
    - ASM : https://asm.ow2.io/
    - Javassist : https://www.javassist.org/
    - `ByteBuddy` : https://bytebuddy.net/#/

- 코드 조작 순서
    - byteBuddy 를 이용하여 코드 조작
    - 코드 조작이 런타임 시에 일어남
    - 클래스 로더로 읽은 클래스는 초기 선언한 클래스
    - 처음 실행시 조작된 코드로 실행되지 않음
    - 두번째 실행부터 이전에 조작한 코드로 실행됨
# javaagent
- 실행시 javaagent 옵션으로 미리만들어놓은 jar 파일을 이용하여 클래스 로딩 시점에서 바이트 코드를 조작함