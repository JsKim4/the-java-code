# JVM 이해하기

## 목차
- 자바, JVM, JDK 그리고 JRE
- JVM 구조
- 클래스 로더

# 자바, JVM, JDK 그리고 JRE
- JVM
    - 자바 가상 머신의 약자
    - 자바 바이트 코드를 OS에 특화된 코드로 변환하여 실행하는 구현체
    - 바이트코드 = 자바 파일을 컴파일시 나오는 클래스 파일 안에있는 것
    - 바이트 코드를 인터프리터와, JIT 컴파일러로 변경및 실행
    - 각 밴더사가 스팩에 맞게 구현
    - [JVM 스펙정보](https://docs.oracle.com/javase/specs/jvms/se11/html/)
    - 특정 플랫폼에 종속적

- JRE
    - 자바 애플리케이션을 실행할 수 있도록 구성됨
    - JVM 과 Library로 구성됨
    - 자바 어플리케이션 개발도구 X
- JDK
    - JRE + 개발에 필요한 툴
    - 오라클은 자바 11부터 JDK만 제공하며 JRE는 제공하지 않음
- 자바
    - 프로그래밍 언어
    - 유료화는 Oracle 에서 만든 Oracle JDK 자바 11이상 버전에서 상용 서비스에 사용할때만 적용됨
- JVM 언어
    - 클로저, 그루비, JRuby, Jython, `Kotlin`, Scala, ...
    - 다른 언어를 사용한다고 해도 JVM 기반이기 때문에 JDK에 따라 유료 혹은 무료인건 동일함
# JVM 구조
- 클래스 로더 시스템
    - 컴파일되어 있는 class 파일들의 바이트코드들을 읽어들이는 역활
    1. 로딩
        - class 파일에서 바이트 코드를 읽어오는 과정 
    2. 링크
        - 래퍼런스를 연결하는 과정
    3. 초기화
        - static 값들 초기화 및 변수에 할당
- 메모리
    1. 스택
        - 쓰레드마다 런타임 스택을 만들고 그안에 스택 프레임을 쌓음
        - 스택 프레임 == Method Call
        - 쓰레드가 종료하면 런타임 스택도 사라진다.
    2. PC
        - 쓰레드 마다 쓰레드 내 현재 실행할 스택 프레임을 가르키는 포인터가 생성됨
    3. 네이티브 메소드 스택
        - 네이티브 메소드를 호출할때 사용하는 별도의 스택
        - 네이티브 메소드
            - 메소드에 네이티브라는 키워드가 붙어있고 해당 구현이 자바가아닌 C나 C++로 구현된것
    4. 힙
        - 객체를 저장,공유한다.
    5. 메소드
        - 클래스 수준의 정보를 저장,공유한다
        - 패키지 경로를 포함한 풀패키지 경로 저장
        - 부모클래스의 이름을 저장한다.
    - 힙과 메소드는 공유자원
    - 스택,PC,네이티브 메소드 스택은 쓰레드에서만 공유하는 자원

- 실행엔진
    - 인터프리터 
        - 바이트코드를 읽을 수 있음
        - 바이트코드를 네이티브 코드로 한줄씩 컴파일
        - 반복적인 코드 발견시 JIT 컴파일러 이용
    - JIT 컴파일러
        - 인터프리터 효율을 높이기 위해 반복되는 코드는 JIT컴파일러로  모두 네이티브 코드로 바꿔둔다.
    - GC
        - 더이상 참조되지 않는 객체를 모아서 정리한다.

# 클래스 로더
- 로딩, 링크, 초기화 순으로 진행된다.
- 로딩
    - .class 파일을 읽고 바이너리 데이터를 만들고 메소드 영역에 저장
    - 메소드 영역에 저장하는 데이터
        - FQCN(클래스가 포함된 패키지명을 포함한 이름) 
    - 로딩이 끝나면 해당 클래스 타입의 Class 객체를 생성하여 힙 영역에 저장
    - 클래스 로더
        - 클래스로더 종류
            - 부트 스트랩 클래스로더
                - 네이티브 코드로 구현되어 있음
            - 플랫폼 클래스로더
            - 애플리케이션 클래스로더
        - 클래스로더는 계층형 구조
        - 클래스로더 동작원리
            - 클래스를 읽을시 부모 클래스 로더가 읽어옴
            - 모든 클래스로더가 클래스 정보를 읽지못할경우 Exception 발생

- 링크
    - Vertify: class 파일 형식의 유효성 체크
    - Preparation: Static 변수와 기본값 필요한 메모리를 준비하는 과정
    - Resolve: 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체

- 초기화
    - Static 변수의 값을 할당한다.
