# 스프링 완전 정복 시리즈

#### 📣 [인프런] 김영한 님의 「스프링 완전 정복 시리즈」 강의 실습 코드입니다.

#### 개발 환경
* `Java 11` ➡️ `Java 17`
* `SpringBoot v2.7.17` ➡️ `SpringBoot v3.2.0`
* `Dependencies` : Spring Web, Thymeleaf, JPA, Lombok
* `Test` : JUnit5
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : Gradle ➡️ IntelliJ IDEA (자바 직접 실행으로 실행 속도 향상)

#### 01. 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
  <details> 
   <summary> 📑 정리 </summary>

  </details>

#### 02. 스프링 핵심 원리 - 기본편
  <details> 
   <summary> 📑 정리 </summary>
  
##### `Section 1) 객체 지향 설계와 스프링`
  * `객체 지향 설계`

      * 객체 지향의 핵심은 **다형성**
      * But, 다형성 만으로는 OCP (개방-폐쇄 원칙), DIP (의존관계 역전 원칙)를 지킬 수 없다 ! 뭔가 더 필요하다. ➡️ **스프링 프레임워크의 등장**
    
  * `스프링`

      * 스프링은 다음 기술로 다형성과 OCP, DIP이 가능하도록 지원한다.
        * DI(Dependency Injection) 
        * **DI 컨테이너**
      * 클라이언트의 코드 변경 없이 기능 확장 가능 ➡️ 쉽게 부품을 교체하듯이 개발을 가능하게 해주는 스프링
   <br>
  
##### `Section 2, 3) 스프링 핵심 원리`
  * 비즈니스 요구사항의 일부분이 미확정된 상황일지라도 개발을 무기한 연기할 수는 없다. 객체 지향 설계 기법에 따라 인터페이스를 만들어 구현체를 언제든지 갈아 끼울 수 있도록 설계한다면, 추후 요구사항이 변경되더라도 유연하게 대처할 수 있다.
      * 즉, 역할과 구현을 분리하여 자유롭게 구현 객체를 조립할 수 있게 설계하자 !
        
* `좋은 객체 지향 설계 5가지 원칙 중 SRP, DIP, OCP`
 
  *  SRP 단일 책임 원칙 : 한 클래스는 하나의 책임만 가져야 한다.
          * 관심사를 분리하자 !

  *  DIP 의존 관계 역전 원칙 : 객체는 구체화가 아닌 추상화에 의존해야 한다.
          * 의존 관계를 주입하자 !

  *  OCP 개방 폐쇄 원칙 : 소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다.
          * 클라이언트 코드를 변경하지 않도록 하자 !
       
* `제어의 역전 IoC (Inversion of Control)`

    * 프로그램의 제어 흐름을 직접 제어하는 것이 아닌 외부에서 관리하는 설계 원칙
        * 내가 호출하는 것이 아니라 프레임워크 같은 것이 내 코드를 대신 호출해준다 ! (예 : JUnit)
       
* `의존 관계 주입 DI (Dependency Injection)`

    * 애플리케이션 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존 관계가 연결 되는 것
    * 객체 인스턴스를 생성하고, 그 참조값을 전달하여 연결
    * 클라이언트 코드 변경 없이 클라이언트가 호출하는 대상의 타입 인스턴스 변경 가능
    * 정적인 클래스 의존 관계를 변경하지 않으면서 동적인 객체 인스턴스 의존 관계 변경 용이

* `IoC 컨테이너, DI 컨테이너`
 
    * 객체를 생성하고 관리하면서 의존 관계를 연결해주는 것
    <br>

##### `Section 4) 스프링 컨테이너와 스프링 빈`
  * `스프링 컨테이너`
    
    * 스프링에서 의존관계 주입(DI)을 이용하여 애플리케이션을 구성하는 여러 빈(Bean)들의 생명 주기와 애플리케이션의 서비스 실행 등을 관리하며 생성된 인스턴스들에게 기능 제공
      * 빈(Bean) : 스프링에서 스프링이 제어권을 가져 직접 생성하고, 의존 관계를 부여하는 오브젝트 
    * 스프링 컨테이너에 적절한 설정이 있다면, 프로그래머의 개입 없이 작성된 코드를 컨테이너가 빈을 스스로 참조한 뒤, 알아서 관리 가능
  * `BeanFactory와 ApplicationContext`

    * `BeanFactory`
      * 빈을 관리하고 조회하는 기능을 담당하는 스프링 컨테이너의 최상위 인터페이스

    * `ApplicationContext`
      * BeanFactory 기능을 모두 상속 받아 제공 
      * BeanFactory 기능 + 부가 기능 제공 ➡️ BeanFactory의 확장 버전

       💡 참고로 BeanFactory를 직접 사용할 일은 거의 없다. 부가 기능이 포함된 ApplicationContext 사용 !

##### `Section 5) 싱글톤 컨테이너`
  * `싱글톤 패턴`
      
      * 고객의 요청이 들어올 때마다 객체를 생성하는 것이 아닌, 이미 만들어진 객체를 공유하여 사용하는 패턴
        ```java
        public class Singleton {

            // 1. static 영역에 객체를 딱 1개만 생성
            private static Singleton instance = new Singleton();

            // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
            public static Singleton getInstance() {
                return instance;
            }

            // 3. 생성자를 private으로 선언하여 외부에서 new 키워드를 사용한 객체 생성을 못하게 막음
            private Singleton() {
            }
        
            public void logic() {
                System.out.println("싱글톤 객체 로직 호출");
            }
        }
        ```
       
  * `싱글톤 컨테이너`

      * 유연성이 떨어지는 각종 싱글톤 패턴의 문제점을 해결하면서, 싱글톤 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리
      * 스프링 빈 : 컨테이너의 도움을 받아 싱글톤 스콥으로 관리되는 빈
      
  * `싱글톤 방식의 주의점`
      * 무상태(stateless)로 설계하자.
        * 특정 클라이언트에 의존적이거나 값을 변경할 수 있는 필드가 있으면 안된다. ➡️ 가급적 읽기만 가능해야 한다!
        * **필드에 공유되지 않는** 지역변수, 파라미터, ThreadLocal 등을 사용하자 ➡️ ❗스프링 빈의 필드에 공유값을 설정하면, 정말 큰 장애가 발생할 수 있다❗
          
  * `@Configuration`
      * @Bean만 사용해도 스프링 빈으로 등록은 되지만, 싱글톤이 유지되지 않는다. ➡️ 이때 @Configuration을 사용하면 @Bean을 등록할 때 **싱글톤이 되도록 보장**해준다.
      * @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈 반환
      * 스프링 빈이 없으면, 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어짐

##### `Section 6) 컴포넌트 스캔`
  * `@ComponentScan`
    
      * `@Component` 애노테이션이 붙은 클래스를 스캔하여 설정 정보가 없어도 자동으로 스프링 빈으로 등록
      * 의존 관계를 자동으로 주입해주는 `@Autowired` 기능도 제공
   
  * `컴포넌트 스캔 기본 대상`
  
      * `@Component` : 컴포넌트 스캔
      * `@Controller` : 스프링 MVC 컨트롤러
      * `@Service` : 스프링 비즈니스 로직
      * `@Repository` : 스프링 데이터 접근 계층, 데이터 계층의 예외를 스프링 예외로 변환
      * `@Configuration` : 스프링 설정 정보, 스프링 빈이 싱글톤을 유지하도록 추가 처리
   
  * `중복 등록과 충돌`
  
      * `Case 1` : 자동 빈 등록 vs 자동 빈 등록
        * 이름이 같은 경우 스프링에서 `ConflictingBeanDefinitionException` 예외 발생 
      
      * `Case 2` : 수동 빈 등록 vs 자동 빈 등록
        * 우선권 : 수동 빈 등록 ➡️ 수동 빈이 자동 빈을 오버라이딩
        * 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면, 오류가 발생하도록 기본값 설정 

##### `Section 7) 의존관계 자동 주입`
  * `다양한 의존관계 주입 방법` : **생성자 주입**, 수정자 주입(setter 주입), 필드 주입(사용 권장 X), 일반 메서드 주입
  * ❗생성자 주입을 사용하자❗     
    * 객체 생성 시, 딱 1번만 호출 ➡️ 불변하게 설계 가능          
    * 필드에 final 키워드 사용 가능한 유일한 방식
      * 주입 데이터 누락 시, 컴파일 오류 발생 ➡️ 오류 확인 용이  
    * 생성자가 딱 1개만 있으면, `@Autowired` 생락해도 자동 주입

  * `주입할 스프링 빈이 없어도 동작해야 할 때`
      * 옵션 처리    
        * `@Autowired(required=false)` : 자동 주입 대상이 없으면, 수정자 메서드 자체 호출 X
        * org.springframework.lang.@Nullable : 자동 주입 대상이 없으면, null 입력
        * Optional<> : 자동 주입 대상이 없으면, Optional.empty 입력

  * `Lombok`
    * 반복 메서드 작성 코드를 줄여주는 코드 다이어트 라이브러리
    * `@RequiredArgsConstructor` : 필수 값인 final이 붙은 필드를 모아서 생성자 자동으로 생성
   
  * `정리`
    * 편리한 자동 기능은 기본으로 사용하되, 애플리케이션의 특징에 따라 적절한 빈 등록 방식을 사용하자
      * `업무 로직 빈` : 수가 많고 어느정도 유사한 패턴이 있는 업무 로직의 경우, 자동 기능 사용 권장 
      * `기술 지원 빈` : 수가 적고 애플리케이션 전반에 걸쳐 광범위하게 영향을 미치는 기술 지원의 경우, 수동 빈으로 등록하여 명확하게 드러내는 것이 좋음
     
##### `Section 8) 빈 생명주기 콜백`
  * `객체의 초기화 및 종료 작업이 필요한 이유`
    * 시간이 걸리는 작업(DB 연결, 네트워크 소켓 등)을 애플리케이션 시작 시점에 미리 연결하여 효율성 증대
    * 빈이 소멸하기 일보 직전에 안전하게 종료할 수 있도록 메서드 호출
    * 테스트 케이스 작성시, 테스트 픽스처를 준비하는 경우

  * `스프링 빈의 이벤트 라이프사이클`
    * 스프링 컨테이너 생성 ➡️ 스프링 빈 생성 ➡️ 의존 관계 주입 ➡️ 초기화 콜백 ➡️ 사용 ➡️ 소멸 전 콜백 ➡️ 스프링 종료
      * 초기화 콜백 : 빈이 생성되고, 빈의 의존 관계 주입이 완료된 후 호출
      * 소멸 전 콜백 : 빈이 소멸되기 직전 호출 
   
  * `스프링에서 빈 생명주기 콜백을 지원하는 방법`
    * `애노테이션 (@PostConstruct, @PreDestroy)`
      * 최신 스프링에서 가장 권장하는 방법
      * 패키지 : **javax**.annotation
        * 자바 표준 ➡️ 스프링이 아닌 다른 컨테이너에서도 동작
      * But, 외부 라이브러리 적용 불가 (코드 수정 불가) ➡️ 외부 라이브러리 초기화 및 종료 필요할 경우, 설정 정보를 사용하는 `@Bean`의 `initMethod`, `destroyMethod` 적용하기

    * `빈 등록 초기화, 소멸 메서드 지정`

    * `인터페이스 (InitializingBean, DisposableBean)`

##### `Section 9) 빈 스코프`  
  * `빈 스코프`
    * 번역 그대로 '빈이 존재할 수 있는 범위'
    * 스프링이 지원하는 빈 스코프 종류 : 싱글톤, 프로토타입, 웹 관련 스코프 (request, session, application)
      * `싱글톤 빈`
        * 스프링 빈이 기본적으로 생성하는 스코프
        * 스프링 컨테이너의 시작 ~ 종료까지 유지되는 가장 넓은 범위의 스코프
        * 스프링 컨테이너에 요청할 때마다 항상 같은 객체 인스턴스의 스프링 빈 반환

      * `프로토타입 빈`  
        * 스프링 컨테이너에 요청할 때마다 새로운 프로토타입 빈을 생성하여 반환
        * 스프링 컨테이너는 프로토타입 빈의 생성과 의존 관계 주입, 그리고 초기화까지만 관여 (➡️ @PreDestroy 같은 종료 메서드 호출 X)
        * 싱글톤 빈과 함께 사용시 문제점 : `싱글톤 빈이 프로토타입 빈을 주입받는 경우`
          * 싱글톤의 프로토타입 빈이 매번 바뀌지 않고 같은 빈 사용 (싱글톤 빈이 ApplicationContext가 처음 동작할 때 빈을 만들고, 주입해서 종료될 때까지 계속 사용하기 때문에 싱글톤 빈 안에 있는 프로토타입도 처음 주입된 채 그대로 사용하게 됨) ➡️ **객체 조회가 꼭 필요한 시점까지 스프링 빈 요청을 지연 시키자 !**
            * 해결 방법 1 : `Provider`
              * ObjectProvider , JSR330 Provider 등을 사용하여 내부에서 스프링 컨테이너를 통해 해당 빈을 찾아서 반환(**DL**, **D**ependency **L**ookup)
              ```java
              @Component
              public class Single {

              @Autowired
              ObjectProvider<ProtoType> protoType;

              public ProtoType getProtoType() {
              return protoType.getIfAvailable();
                  }
              }
              ```

            * 해결 방법 2 : `Proxy`
              ```java
              @Component
              @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
              public class ProtoType {
              }
              ```
       
      * `웹 스코프`
        * 웹 환경에서만 동작한다.
        * 스프링이 해당 스코프의 종료 시점까지 관리한다. (➡️ 종료 메서드가 호출된다.)
 
  </details>

  #### 03. 모든 개발자를 위한 HTTP 웹 기본 지식
  <details> 
   <summary> 📑 정리 </summary>

##### `Section 1) 인터넷 네트워크`
  * 복잡한 인터넷 망에서 메시지를 보내기 위해 제일 먼저 IP(인터넷 프로토콜) 필요
    * 지정한 IP 주소에 데이터를 전달해주고, 패킷이라는 통신 단위로 데이터를 전달해줌
  * But, IP 프로토콜만으로는 메시지가 잘 도착했는지 신뢰하기 어렵고 포트 개념이 존재하지 않아 메시지 순서가 꼬일 수 있음 ➡️ 이런 문제점들을 **TCP 프로토콜**이 해결해줌
    * `TCP` 
      * TCP 3 way handshake을 통해 클라이언트도 서버를 믿을 수 있고, 서버도 클라이언트를 믿을 수 있음
      * 서버가 중간에 꺼질 경우 : SYN 메시지 전송 ➡️ 서버 응답 X ➡️ 클라이언트 : 어 ? 이거 응답이 없는데 ?! 문제가 있네 ! ➡️ 연결 X
      * 메시지를 보냈는데 패킷이 중간에 누락될 경우, 내가 알 수 있음 ➡️ IP 프로토콜의 한계(비신뢰성) 해결
      * 순서 보장 ➡️ IP 프로토콜의 한계(비신뢰성) 해결
  * `UDP` : IP에 PORT 번호와 체크섬 추가, 필요하면 애플리케이션에서 UDP 프로토콜 위에 기능 확장 가능
    * `PORT` : 같은 IP 안에서 동작하는 애플리케이션을 구분하기 위해서 사용
  * `DNS (Domain Name Service)`: 변하기 쉽고 외우기 어려운 IP 주소를 DNS 서버를 통해 도메인 명(예 : google.com)을 등록하여 사용할 수 있도록 도와줌
  
##### `Section 2) URI와 웹 브라우저 요청 흐름`
  * `URI (Uniform Resource Identifier)` : 인터넷 자원을 나타내는 고유 식별자
    * `URL (Uniformed Resource Locator)` : URI 에 포함되는 개념으로, 해당 자원의 위치 지정
    * `URN (Uniformed Resource Name)` : URI 에 포함되는 개념으로, 해당 자원에 이름 부여
  
  * `웹 브라우저 요청 흐름`
    * 웹 브라우저가 HTTP 메시지 생성 ➡️ SOCKET 라이브러리를 통해 요청 패킷을 서버로 전달 ➡️ 서버로 요청 패킷이 도착하면 HTTP 응답 메시지 생성 ➡️ SOCKET 라이브러리를 통해 응답 패킷을 웹 브라우저로 전달 ➡️ 웹 브라우저로 응답 패킷이 도착하면 HTML 렌더링

##### `Section 3) HTTP 기본`
 * `HTTP 특징`

   * `클라이언트 서버 구조`
     * 클라이언트 : 서버에 요청을 보내고, 응답 대기 (Request) ➡️ 서버 : 요청에 대한 결과를 만들어서 응답 (Response)
   * `무상태 프로토콜 (Stateless)`
     * 서버가 클라이언트 상태 보존 X ➡️ 클라이언트 요청이 증가해도 서버 대거 투입 가능 (스케일 아웃)
     * 웹 애플리케이션을 설계할 때는 최대한 무상태로 설계
     * 정말 어쩔 수 없는 경우에 한해서만 상태 유지 (예 : 로그인)
   * `비연결성 (Connectionless)`
     * 클라이언트 요청이 들어오면, 서버는 응답을 하고 연결 유지 X ➡️ 최소한의 자원 유지로 서버 자원 효율적으로 사용 가능
     * HTTP : 연결을 유지하지 않는 모델, 비연결성의 한계를 극복한 `HTTP 지속 연결 (Persistent Connections)`사용 
   * `HTTP 메시지`
     * HTTP 메시지 구조 
       * `start-line`
         * `request-line`
           * method SP request-target SP HTTP-version CRLF 
         * `status-line`
           * HTTP-version SP status-code SP reason-phrase CRLF 
       * `header`
         * field-name ":" OWS field-value OWS (OWS : 띄어쓰기 허용)
       * `empty line (CRLF)`
       * `message body` 
   * `단순함, 확장 가능`

##### `Section 4) HTTP 메서드`
 * HTTP 메서드 종류
   * `GET`
     * 리소스 조회 
   * `POST`
      * 요청 데이터 처리       
   * `PUT`
     * 리소스 완전 대체 (덮어쓰기), 해당 리소스가 없으면 생성
     * 클라이언트가 리소스 식별 (POST와 차이점) 
   * `PATCH`
     * 리소스 부분 변경
     * `PATCH` 지원이 안 될 경우, `POST` 사용    
   * `DELETE`
     * 리소스 제거

  * HTTP 메서드 속성
     * `안전 (Safe Methods)`
       * 계속 호출해도 리소스 변경 X  
     * `멱등 (Idempotent Methods)`
       * 같은 요청을 여러번 해도 동일 결과 조회 (GET, PUT, DELETE)
       * 외부 요인으로 중간에 리소스가 변경되는 것까지 고려 X
     * `캐시 가능(Cacheable Methods)`
       * GET, HEAD 캐시 사용 가능

##### `Section 5) HTTP 메서드 활용`
 * 데이터 전달 방식
   * `쿼리 파라미터`를 통한 데이터 전송
     * GET (조회에만 사용, 리소스 변경이 발생하는 곳은 사용 금지)
     * 주로 정렬 필터 (검색어)   

   * `메시지 바디`를 통한 데이터 전송
     * POST, PUT, PATCH
     * 회원 가입, 상품 주문, 리소스 등록, 리소스 변경

     * `HTML Form`을 통한 데이터 전송
       * GET, POST만 지원 ➡️ 제약 O
       * 컨트롤 URI (컨트롤러, Controller)
         * 동사로 된 리소스 경로 사용
         * HTTP 메서드로 해결하기 어려운 경우 추가 프로세스 실행

     * `HTTP API`를 통한 데이터 전송
       * 컬렉션 (Collection)
         * POST 기반 등록
         * 서버가 관리하는 리소스 저장소
         * 서버가 리소스 URI 생성 및 관리

       * 스토어 (Store)
         * PUT 기반 등록
         * 클라이언트가 관리하는 리소스 저장소
         * 클라이언트가 리소스 URI를 알고 관리

##### `Section 6) HTTP 상태코드`
  * 1xx (Informational) : 요청이 수신되어 처리 중
  
  * 2xx (Successful) : 요청 정상 처리
    * `200 OK`: 요청 성공
    * `201 Created` : 요청 성공해서 새로운 리소스 생성 (➡️ 생성된 리소스는 응답의 Location 헤더 필드로 식별)
    * `202 Accepted` : 요청이 접수되었으나, 처리 완료 X (예 : 배치 처리)
    * `204 No Content` : 서버가 요청을 성공적으로 수행했으나, 응답 페이로드 본문에 보낼 데이터 X (예 : 웹 문서 편집기 save 버튼)
  
  * 3xx (Redirection) : 요청을 완료하려면, 유저 에이전트(클라이언트 프로그램, 주로 웹 브라우저)의 추가 조치 필요
    * Redirect : 웹 브라우저는 3xx 응답 결과에 Location 헤더가 있으면, Location 위치로 자동 이동
    * **영구 리다이렉션** : 리소스의 URI가 영구적으로 이동 (➡️ 원래 URL 사용 X, 검색 엔진 등에서도 변경 인지)
      * `301 Moved Permanently` : 리다이렉트 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음 **(MAY)**
      * `308 Permanent Redirect` : 리다이렉트 요청 메서드와 본문 유지
    * **일시 리다이렉션** : 리소스의 URI가 일시적으로 변경 (➡️ 검색 엔진 등에서 URL 변경 금지)
      * `302 Found` : 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음 **(MAY)**
      * `307 Temporary Redirect` : 리다이렉트시 요청 메서드와 본문 유지
      * `303 See Other` : 리다이렉트시 요청 메서드가 GET으로 변경

    * **특수 리다이렉션** : 결과 대신 캐시 사용
      * `304 Not Modified` : 클라이언트에게 리소스가 수정되지 않았음을 알려줌 (➡️ 클라이언트는 로컬 PC에 저장된 캐시 재사용)
  
  * 4xx (Client Error) : 클라이언트 오류, 잘못된 문법 등으로 서버가 요청을 수행할 수 없음
    * `400 Bad Request` : 클라이언트의 잘못된 요청으로 서버가 요청을 처리할 수 없음 (예 : 요청 파라미터를 잘못 넘겼을 때, API 스펙이 맞지 않을 때)
    * `401 Unauthorized` : 클라이언트가 해당 리소스에 대한 인증이 필요함
    * `403 Forbidden` : 서버가 요청을 이해했지만, 접근 권한이 불충분하여 승인 거부
    * `404 Not Found` : 요청 리소스를 찾을 수 없거나, 클라이언트가 권한이 부족한 리소스에 접근할 때 해당 리소스를 숨기고 싶은 경우
  
  * 5xx (Server Error) : 서버 오류, 서버가 정상 요청을 처리하지 못함
    * `500 Internal Server Error` : 서버 내부 문제로 오류 발생
    * `503 Service Unavailable` : 서비스 이용 불가 (➡️ 서버가 일시적인 과부하 또는 예정된 작업으로 잠시 요청 처리 X)

##### `Section 7) HTTP 헤더1 - 일반 헤더`
 * `HTTP 헤더`
   * HTTP 전송에 필요한 모든 부가 정보
   * HTTP 표준 : `RFC723x`
      * 표현
          * 표현 데이터를 해석할 수 있는 정보 제공, 전송과 응답 둘 다 사용  
          * `Content-Type` : 표현 데이터 형식 설명
          * `Content-Encoding` : 표현 데이터 압축 방식
          * `Content-Language` : 표현 데이터 자연 언어
          * `Content-Length` : 표현 데이터 길이
     * 협상 (Content Negotiation)
          * 클라이언트가 선호하는 표현 요청, 요청시에만 사용, 구체적인 것이 우선
          * `Accept` : 클라이언트가 선호하는 미디어 타입 전달
          * `Accept-Charset` : 클라이언트가 선호하는 문자 인코딩
          * `Accept-Encoding` : 클라이언트가 선호하는 압축 인코딩
          * `Accept-Language` : 클라이언트가 선호하는 자연 언어
      * 전송 방식
          * `단순 전송` - Content-Length
          * `압축 전송` - Content-Encoding: gzip
          * `분할 전송` - Transfer-Encoding: chunked, Content-Length 사용 불가
          * `범위 전송` - Range, Content-Range
       * 일반 정보
         * `From` : 유저 에이전트의 이메일 정보
         * `Referer` : 현재 요청된 페이지의 이전 웹 페이지 주소
         * `User-Agent` : 유저 에이전트(클라이언트) 애플리케이션 정보
         * `Server` : 요청을 처리하는 오리진 서버의 소프트웨어 정보
         * `Date` : 메시지 생성 날짜
       * 특별한 정보
         * `Host` : 요청한 호스트 정보 (도메인)
         * `Location` : 페이지 리다이렉션
         * `Allow` : 허용 가능한 HTTP 메서드 
         * `Retry-After` : 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간
       * 인증
         * `Authorization` : 클라이언트 인증 정보를 서버에 전달
         * `WWW-Authenticate` : 리소스 접근시 필요한 인증 방법 정의 (➡️ 401 Unauthorized 응답과 함께 사용)
       * 쿠키 🍪
         * 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달
         * 쿠키 사용시, 무상태 프로토콜(Stateless)인 HTTP의 특성을 극복하여 사용자 로그인 세션 관리 용이
         * 쿠키 정보는 항상 서버에 전송됨 ➡️ 추가 네트워크 트래픽 유발 (최소한의 정보만 사용하고, 보안에 민감한 데이터 저장하지 않기)
         * `Set-Cookie` : 서버에서 클라이언트로 쿠키 전달 (응답)
           * 예) set-cookie: **sessionId=abcde1234**; **expires=Sat, 26-Dec-2020 00:00:00 GMT**; **path=/**; **domain=.google.com**; **Secure**

##### `Section 8) HTTP 헤더2 - 캐시와 조건부 요청`
 * `캐시 적용 ❌️`
   * 데이터가 변경되지 않아도 계속 네트워크를 통해 데이터 다운로드 ➡️ 브라우저 로딩 속도 저하 (느린 사용자 경험)
 * `캐시 적용 ⭕️`
   * 캐시 유효 시간 동안 네트워크 사용 X ➡️ 브라우저 로딩 속도 향상 (빠른 사용자 경험)
   * 만약 캐시 유효 시간을 초과하게 된다면? ➡️ `검증 헤더와 조건부 요청`
      * `검증 헤더` : 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터 (Last-Modified, ETag)
      * `조건부 요청 헤더`
        * 검증 헤더로 조건에 따른 분기
          * `방법 1` If-Modified-Since, If-Unmodified-Since : Last-Modified 사용
              ##### [Case1] 캐시 유효 시간을 초과하고, 서버 데이터가 변경되지 않은 경우
              * `304 Not Modified` + 헤더 메타 정보만 응답
              * 클라이언트 : 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보 갱신 ➡️ 캐시에 저장된 데이터 재활용
              ##### [Case2] 캐시 유효 시간을 초과하고, 서버 데이터가 변경된 경우
              * `200 OK` + 모든 데이터 전송

            #### 🤔 캐시 제어 로직을 서버에서 완전히 관리하고 싶으면 어떻게 해야할까 ? ➡️ 아래의 `방법 2`를 사용하자 !
          * `방법 2` If-Match, If-None-Match : ETag 사용 (Entity Tag)
              * 캐시용 데이터에 날짜가 아닌 **임의의 고유한 버전 이름** 사용
              * 데이터가 변경되면, 버전 이름도 변경 (Hash 다시 생성)
              * 클라이언트 : 단순히 값을 서버에 제공 (캐시 매커니즘 모름)
       
       * `캐시 제어 헤더`
         ##### 캐시 지시어
         * **`Cache-Control`**
           * `Cache-Control: max-age` : 캐시 유효 시간, 초 단위 (**사용 권장**)
           * `Cache-Control: no-cache` : 데이터는 캐시 해도 되지만, 항상 origin 서버에 검증하고 사용
           * `Cache-Control: no-store` : 데이터에 민감한 정보가 있으므로 저장 ❌️ 
         * `Pragma`
           * `Pragma: no-cache` : HTTP 1.0 하위 호환 
         * `Expires`
           * 캐시 만료일을 정확한 날짜로 지정 (➡️ 유연 ❌️)
           * `Cache-Control: max-age`와 함께 사용하면 Expires 무시
       
       
       * `프록시 캐시` : 클라이언트와 서버 사이에 대리로 통신을 수행하는 중계 기능 서버 
         ##### 캐시 지시어
         * `Cache-Control: public` : public 캐시에 응답 저장 가능 
         * `Cache-Control: private` : 해당 사용자만을 위한 응답, private 캐시에 응답 저장 (기본값)
         * `Cache-Control: s-maxage` : 프록시 캐시에만 적용되는 max-age
         * `Age: 60` : origin 서버에서 응답 후, 프록시 캐시 내에 머문 시간(초)
        
         #### 🤔 클라이언트가 캐시를 적용하지 않아도 임의로 웹 브라우저가 캐시를 적용하는 경우, 특정 페이지에서 캐시가 되면 안되는 정보가 있다면 어떻게 무효화할까?
       * `캐시 무효화`
         ##### 캐시 지시어 - 확실한 캐시 무효화
          * `Cache-Control: no-cache`
          * `Cache-Control: no-store`
          * `Cache-Control: must-revalidate`
            * 캐시 만료 후, 최초 조회시 origin 서버 검증 (캐시 유효 시간이라면, 캐시 사용)
            * origin 서버 접근 실패시, 항상 오류 발생 ➡️ `504(Gateway Timeout)`
          * `Pragma: no-cache`
         #### ✔️ no-cache 와 must-revaildate의 차이점
         #### ➡️ 프록시 캐시 서버와 원 서버 사이에서 네트워크 단절이 일어나 접근이 불가능한 경우
         * `no-cache` : 오류보다는 오래된 데이터라도 보여주자! ➡️ `200 OK`
         * `must-revalidate` : 항상 오류 발생 (⚠️ 중요한 정보를 사용할 경우, 예전 데이터를 보여준다면 큰 문제 발생) ➡️ `504 Gateway Timeout`
  </details>

#### 04. 🍃 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술
<details>

**<summary> `Section 1) 웹 애플리케이션 이해` </summary>**
   
* `웹 서버 (Web Server)` : http 프로토콜로 서로 데이터를 주고 받을 수 있고, 정적 데이터 및 기타 부가 기능 제공
* `웹 애플리케이션 서버 (WAS - Web Application Server)` : `웹 서버` 기능 + 프로그램 코드를 실행하여 애플리케이션 로직 수행
* `웹 시스템 구성 - WEB, WAS, DB`
  * `WEB` : 정적 리소스 처리, 애플리케이션 로직 같은 동적인 처리가 필요하면 `WAS`에 요청 위임
  * `WAS` : 중요한 애플리케이션 로직 처리 전담
  * `WAS`, `DB` 장애시, `WEB` 서버는 오류 화면 제공
 
  * `서블릿 (Servlet)` : 클라이언트의 요청을 처리하고, 그 결과를 반환하는 Servlet 클래스의 구현 규칙을 지킨 자바 기반 웹 프로그래밍 기술 (➡️ 웹 요청과 응답의 흐름을 간단한 메서드 호출만으로 체계적으로 다룰 수 있게 해줌)
  * `서블릿 컨테이너 (Servlet Container)` : 구현되어 있는 Servlet 클래스의 규칙에 맞게 서블릿을 담고 관리해주는 컨테이너
    * 웹 서버와의 통신 지원
    * 서블릿 생명주기 관리
    * 서블릿 객체 **싱글톤으로 관리** (⚠️ **공유 변수 사용 주의**)
    * 멀티 스레드 지원 및 관리
      * `스레드` : 프로세스 내에서 실제로 작업을 수행하는 주체로, 모든 프로세스는 한 개 이상의 스레드가 존재하여 작업 수행 (➡️ 멀티 스레드 프로세스 : 두 개 이상의 스레드를 가지는 프로세스)
      * 멀티 스레드에 대한 부분은 WAS가 처리 ➡️ 개발자 : 멀티 스레드 관련 코드 신경 ❌️
        #### 🤔 다중 요청이 들어올 경우, 어떻게 처리할 것인가?
        * `방법 1` 요청마다 스레드 생성
          #### [장점]
          * 동시 요청 처리 가능
          * 리소스(CPU, 메모리)가 허용할 때까지 처리 가능
          * 하나의 스레드가 지연되어도 나머지 스레드 정상 작동
          #### [단점]
          * 스레드 생성 및 Context Switching 비용 문제
          * 요청마다 스레드 생성으로 응답 속도 저하
          * 스레드 생성 제한 ❌️ (➡️ 고객 요청이 많이 올 경우, 서버 과부하)

        * **`방법 2`** ✔️ 스레드 풀
          #### [특징]
          * 요청마다 스레드 생성의 단점을 보완한 방법
          * 필요한 스레드를 스레드 풀에 보관하고 관리
          * 스레드 풀에 생성 가능한 스레드의 최대치 관리
          #### [사용]
          * 스레드가 필요하면, 이미 생성되어 있는 스레드를 스레드 풀에서 꺼내어 사용
          * 사용 종료시, 스레드 풀에 해당 스레드 반납
          #### 🤔 최대 스레드가 모두 사용중이여서 스레드 풀에 스레드가 없는 경우
          * 기다리는 요청을 거절하거나, 특정 숫자만큼만 대기할 수 있도록 설정
          #### [장점]
          * 스레드 생성 및 종료 비용 절약 
          * 스레드가 미리 생성되어 있어 응답 속도 향상
          * 스레드 생성 제한 ⭕️ (➡️ 고객 요청이 많이 오더라도 기존 요청 안전하게 처리 가능)

   #### 👉 백엔드 개발자가 HTTP를 통해서 데이터를 제공할 때 고민해야할 포인트 3가지   
   * `정적 리소스`를 어떻게 제공할 것인가?
   * 동적으로 제공되는 `HTML 페이지`를 어떻게 제공할 것인가?
   * `HTTP API`를 어떻게 제공할 것인가?
     #### ✔️ 정적 리소스
     * 웹 브라우저 요청이 들어오면, 웹 서버는 **이미 생성된 리소스 파일 제공**
     #### ✔️ HTML 페이지
     * 웹 브라우저 요청이 들어오면, 애플리케이션 로직을 수행할 수 있는 WAS가 DB에서 정보 제공 ➡️ 뷰 템플릿을 사용하여 **동적으로 HTML 생성** ➡️ 웹 브라우저로 전송 ➡️ 웹 브라우저에서 HTML 렌더링
     #### ✔️ HTTP API
     * 웹 브라우저 요청이 들어오면, 애플리케이션 로직을 수행할 수 있는 WAS가 DB에서 정보 제공 ➡️ **JSON 형식의 데이터를 웹 브라우저로 전송**
     * 다양한 시스템에서 호출
       * `웹 클라이언트` to `서버`
       * `서버` to `서버`
       * `앱 클라이언트` to `서버`
       #### 👉 데이터만 주고 받음 (➡️ UI 화면이 필요하면, 클라이언트가 별도 처리)
* `렌더링 방식`
    #### ✔️ SSR - 서버 사이드 렌더링
    * 서버 측에서 렌더링 될 페이지를 그려 클라이언트로 내려주는 방식
    * 주로 정적인 화면에 사용
    #### ✔️ CSR - 클라이언트 사이드 렌더링
    * 렌더링 될 페이지를 자바스크립트를 사용해 클라이언트 측에서 동적으로 생성하여 적용하는 방식
    * 주로 동적인 화면에 사용
    * 첫 로딩에서 빈 html 파일에 필요한 번들파일을 모두 다운로드 받음

</details>

<details>
   
**<summary> `Section 2) 서블릿` </summary>**

* 스프링 부트 서블릿 환경 구성
   * `@ServletComponentScan`
      * 서블릿 자동 등록
      * main() 메서드가 존재하는 클래스 또는 최상위 경로의 클래스에 부착하는 애노테이션
      * 하위 경로에 존재하는 모든 클래스 스캔
      ```java
      @ServletComponentScan // 서블릿 자동 등록
      @SpringBootApplication
      public class ServletApplication {
         public static void main(String[] args)  {
   		SpringApplication.run(ServletApplication.class, args);
   	   }
      }
      ```

   * `@WebServlet`, `HttpServlet`, `service() 메서드`
      ```java
      @WebServlet(name = "사용할 서블릿 객체명", urlPatterns = "url 매핑 주소")
      public class HelloServlet extends HttpServlet {

         @Override
         protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         }
      }
      ```
      * `@WebServlet` : `@ServletComponentScan`의 대상임을 명시
      * `HttpServlet` : Servlet Container에 등록된 Servlet 객체가 호출되면, service() 메서드 자동 호출 ➡️ service() 메서드를 오버라이드하기 위해 HttpServlet 상속 필수
      * `service() 메서드` : response의 결과물을 생성하는 비즈니스 로직을 실행하는 코드 작성

   * `HttpServletRequest`, `HttpServletResponse`
      * 개발자가 HTTP 요청 메시지, HTTP 응답 메시지를 편리하게 사용할 수 있도록 개발자 대신 HTTP 요청 메시지 파싱
      * `HttpServletRequest` : 클라이언트로부터 전달 받은 HTTP request 메시지의 내용을 객체로 만들어줌
         * HTTP 요청 메시지
            *  `start-line` : HTTP 메서드, URL, 쿼리 스트링, 스키마, 프로토콜
            *  `request-header` : 헤더 조회
            *  `message-body` : form 파라미터 형식 조회, message body 데이터 직접 조회
         
         * 임시 저장소 기능
         
         * 세션 관리 기능
      * `HttpServletResponse` : service() 메서드에서 수행한 비즈니스 로직의 결과물을 담을 객체를 만들어줌
         * HTTP 응답 메시지
            * `status-line` : HTTP 응답 코드 지정 
            * `response-headers` : 헤더 생성 (Content-Type, Cache-Control 등) 
            * `message-body` : 바디 내용 생성
       
         * Content, 쿠키, Redirect 편의 메서드 제공 

* 서블릿 컨테이너 동작 방식
   #### 1. 스프링 부트 실행시, 내장된 톰캣 서버 실행
   #### 2. 톰캣 서버가 실행되면, ServletComponentScan이 동작하면서 Servlet Container에 Servlet 객체 등록
   #### 3. 클라이언트로부터 HTTP Request가 들어오면, WAS에서 이를 HttpServletRequest 객체로 만들어 Servlet 객체에 전달
   #### 4. Servlet 객체는 service() 메서드를 호출하고, 비즈니스 로직 수행
   #### 5. service() 메서드에서 수행한 비즈니스 로직의 결과물을 HttpServletResponse 객체에 담아 클라이언트에게 전달   

* HTTP 요청 데이터
   * `HTTP 요청 메시지를 통해 클라이언트 ➡️ 서버로 데이터를 전달하는 방법`
      #### ✔️ `GET` - 쿼리 파라미터
      * message body 없이 URL의 쿼리 파라미터에 데이터를 포함하여 전달
      * 예) 검색, 필터, 페이징 등
      ```java
      // 단일 파라미터 조회
      String username = request.getParameter("username"); 

      // 복수 파라미터 조회
      String[] usernames = request.getParameterValues("username");

      // 파라미터 이름들 모두 조회
      Enumeration<String> parameterNames = request.getParameterNames();

      // 파라미터를 Map으로 조회 
      Map<String, String[]> parameterMap = request.getParameterMap(): 
      ```
  
      #### ✔️ `POST` - HTML Form
      * message body에 쿼리 파라미터 형식으로 데이터 전달 ➡️ 따라서 body에 포함된 데이터가 어떤 형식인지 `content-type` 지정 필수
      * `content-type: application/x-www-form-urlencoded`
         * `content-type` : HTTP 메시지 바디의 데이더 형식 지정 
         * `application/x-www-form-urlencoded`
            * form으로 데이터 전송
            * **`GET` URL 쿼리 파라미터 형식**과 동일 ➡️ 쿼리 파라미터 조회 메서드 그대로 사용 가능 (`request.getParameter()`)
      * 예) 회원가입, 상품 주문 등

      #### ✔️ `HTTP message body`에 데이터를 직접 담아서 요청
      * HTTP API에 주로 사용
      * 데이터 형식 : **JSON(주로 사용)**, XML, TXT 등
         #### ✔️ 단순 text를 사용할 경우
         * content-type : **text/plain**
         * InputStream 사용
            ```java
            @WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
            public class RequestBodyStringServlet extends HttpServlet {
   
               @Override
               protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                  ServletInputStream inputStream = request.getInputStream(); // byte 코드 반환
                  String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // byte 코드 ➡️ 문자(String)
               }
            }
            ```

        #### ✔️ JSON 형식을 사용할 경우 (주로 사용)
        * content-type : **application/json**
        * InputStream & ObjectMapper 사용
           ```java
            @WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
            public class RequestBodyJsonServlet extends HttpServlet {
   
               @Override
               protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                  ServletInputStream inputStream = request.getInputStream(); // byte 코드 반환
                  String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // byte 코드 ➡️ 문자(String)
                  HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환         
               }
            }
            ```
        * ObjectMapper
           * JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환
           * JSON 변환 라이브러리(Jackson, Gson 등)에 포함
              * Spring Boot : 기본으로 Jackson 라이브러리 제공      

* HTTP 응답 데이터
     #### ✔️ 단순 텍스트 응답
     * `response.getWriter()` 사용  

     #### ✔️ HTML 응답
     * content-type : **text/html**
     * `response.getWriter()` 사용 

     #### ✔️ HTTP API - MessageBody JSON 응답  
     * content-type : **application/json** (utf-8 형식을 사용하도록 정의되어 있음 ➡️ charset=utf-8 지원 ❌️)
     * `objectMapper.writeValueAsString()` : 객체 ➡️ JSON 문자

</details>

<details>

**<summary> `Section 3) MVC 패턴` </summary>**

* MVC 패턴 개요
     #### ✔️ 너무 많은 역할
     * 기존 방식
        * Servlet : view 화면을 위한 html을 만드는 작업이 자바 코드에 섞여 지저분하고 복잡함
        * JSP : 자바 코드, 데이터 조회 리포지토리 등 다양한 코드가 모두 JSP에 담겨 있음 ➡️ 🙁 너무 많은 역할 담당 (유지보수 어려움) 
     #### ✔️ 비즈니스 로직과 뷰 렌더링 변경 라이프 사이클 상이
     * 비즈니스 로직과 뷰 렌더링을 하나의 코드로 관리하면 유지보수 어려움  
     #### ✔️ 기능 특화
     * JSP와 같은 뷰 템플릿은 화면을 렌더링 하는데 최적화 되어 있기 때문에 이 부분의 업무만 담당하는 것이 효과적
 
* MVC 패턴 (Model View Controller)
   * 하나의 서블릿이나 JSP로 처리하던 것을 Controller와 View 라는 영역으로 서로 역할을 나눈 것
      * **컨트롤러 (Controller)** : HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직 실행 ➡️ 뷰에 전달할 결과 데이터를 조회하여 모델에 담음
      * **모델 (Model)** : 뷰에 출력할 데이터를 담아둠
      * **뷰 (View)** : 모델에 담겨있는 데이터를 사용하여 화면을 그려줌 (HTML 생성)      

   * MVC2 패턴 동작 방식 
   
<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/8f031808-6885-4227-8bcc-ffc9e0640d8b"/></p>

* MVC 패턴의 한계
    #### ✔️ 포워드 중복
    #### ✔️ ViewPath 중복
    #### ✔️ 사용하지 않는 코드 존재
    #### ✔️ 공통 처리의 어려움

   #### 🤔 그렇다면 어떻게 MVC 패턴의 단점을 해결할 것인가?
   * **`프론트 컨트롤러 (Front Controller)`**
      * 컨트롤러 호출 전, 먼저 **공통 기능 처리**
      * 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받아 요청에 맞는 컨트롤러를 찾아 추가로 컨트롤러 호출
      * 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿 사용하지 않아도 됨

</details>

<details>

**<summary> `Section 4) MVC 프레임워크 만들기` </summary>**

#### 프론트 컨트롤러 도입 - V1
* 모든 HTTP 요청을 받는 FrontController 클래스 생성 ➡️ 해당 클래스에서 URI를 통해 Controller의 매핑 정보 조회 ➡️ Controller 호출하여 로직 처리

<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/11d2a8c9-7658-4890-b468-96af39d19edd"/></p>

#### View 분리 - V2
   * V1 구조 : 컨트롤러 ➡️ 뷰로 이동하는 부분에 중복 존재, 코드가 깔끔하지 않음 ➡️ **별도로 뷰를 처리하는 객체를 생성**하여 V1 구조 개선

<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/acd0290f-068d-483e-9c01-4d24e742d4cf"/></p>
        
   * 기존 V1의 컨트롤러에 존재하는 View 관련 로직을 MyView 객체에 추가 ➡️ V2 Controller는 로직을 처리한 후, MyView 객체 반환 ➡️ Servlet에서 이를 받아 render() 메서드 실행 ➡️ MyView 객체가 JSP를 forward하여 처리
    
#### Model 추가 - V3
  * V2 구조 : Controller에서 사용하지 않는 코드를 파라미터로 전달 받음(HttpServletRequest, HttpServletResponse), 뷰 이름 중복 ➡️ 서블릿 의존 코드를 제거하기 위해 **Model을 추가**하고, **중복되는 뷰 이름을 제거**하여 V2 구조 개선
 
<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/25e2429a-0fa5-4fd8-92b4-e74c1c98f45f"/></p>
   
   #### ✔️ ModelView
   * 뷰의 논리적인 이름과 뷰를 렌더링 할 때 필요한 model 객체를 map으로 가짐 ➡️ 컨트롤러 : 뷰에 필요한 데이터를 key, value로 넣어주면 됨 (프레임워크에 종속적), 실제 뷰의 물리적인 이름은 프론트 컨트롤러에서 처리 (viewResolver)
   * `HttpServletRequest`가 제공하는 파라미터 : 프론트 컨트롤러가 `Map`에 담아 호출 ➡️ 응답 결과로 ModelView 객체 반환

   #### ✔️viewResolver
   * 실제 뷰를 찾아주는 해결사
      #### 🤔 왜 뷰 리졸버를 써야할까 ?
      * 폴더 이름이 변경될 경우, Controller 건들 필요 전혀 ❌️ ➡️ viewResolver 메서드 안에 있는 경로만 수정    


#### 단순하고 실용적인 컨트롤러 - V4
   * V3 구조 : 컨트롤러에 ModelView 객체를 생성하고 반환해야 하는 번거로움 존재 ➡️ 조금 더 단순하고 실용성 있는 V4 버전을 사용하여 V3 구조 개선

<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/cbd716b6-9ba8-4303-9a60-2ff0f33b2b9b"/></p>

   #### 기존 ➡️ 변경
   * 인터페이스에 ModelView ⭕️ ➡️ **인터페이스에 ModelView ❌️**
   * 컨트롤러 : ModelView 반환 ➡️ **뷰의 논리적인 이름인 ViewName만 반환**
   * ModelView에서 model을 꺼냄 ➡️ **프론트 컨트롤러에서 model 객체를 파라미터로 넘김 (컨트롤러에서 모델을 별도로 생성할 필요 ❌️)**

#### 유연한 컨트롤러 - V5
   #### 🤔 하나의 프로젝트에서 여러가지 컨트롤러 방식을 사용하고 싶은 경우 어떻게 해야할까?
   * V4 구조 : 프론트 컨트롤러에서 한가지 방식의 컨트롤러 인터페이스만 사용 가능 (인터페이스 제약으로 컨트롤러 방식 유연하게 사용 ❌️) ➡️ 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있도록 **어댑터 패턴** 적용
 
<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/f24a0925-badf-40ea-bba8-78a1416af6ae"/></p>  

   #### 기존 ➡️ 변경
   * 컨트롤러 (Controller) ➡️ 핸들러 (Handler)
     * 컨트롤러 직접 매핑하여 사용 ➡️ 어댑터 사용으로 컨트롤러 뿐만 아니라 **어댑터가 지원하기만 하면 어떤 것이라도 URL에 매핑하여 사용 가능 !** 그래서 더 넓은 범위로 이름 변경
     * 프론트 컨트롤러가 실제 컨트롤러 호출 ➡️ **어댑터를 통해 실제 컨트롤러 호출**
    
   #### ✔️ 핸들러
   * 컨트롤러의 더 넓은 범위
   
   #### ✔️ 핸들러 어댑터
   * 인터페이스의 스펙이 다를 때, 중간에 스펙이 맞도록 변환하여 다양한 종류의 컨트롤러를 호출할 수 있도록 하는 객체
   * 컨트롤러가 반환한 뷰 이름을 ModelView로 만들어서 형식을 맞추어 반환

</details>

<details>
  
**<summary> `Section 5) 스프링 MVC - 구조 이해` </summary>**

#### SpringMVC 구조
<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/17cb0401-0848-4ffa-8228-980cbda7a92d"/></p> 

* #### 직접 만든 프레임워크 ➡️ 스프링 MVC 비교
   * `FrontController` ➡️ `DispatcherServlet`
   * `handlerMappingMap` ➡️ `handlerMapping`
   * `MyHandlerAdapter` ➡️ `HandlerAdapter`
   * `ModelView` ➡️ `ModelAndView`
   * `viewResolver(메서드)` ➡️ `ViewResolver(인터페이스)` 
   * `MyView(메서드)` ➡️ `View(인터페이스)` 

* #### 동작 순서
   1. **`핸들러 조회`** : 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러(컨트롤러) 조회
   2. **`핸들러 어댑터 조회`** : 핸들러를 실행할 수 있는 핸들러 어댑터 조회
   3. **`핸들러 어댑터 실행`**
   4. **`핸들러 실행`** : 핸들러 어댑터가 실제 핸들러 실행
   5. **`ModelAndView 반환`** : 핸들러 어댑터에서 핸들러가 반환하는 정보를 ModelAndView로 변환하여 반환
   6. **`viewResolver 호출`** : 뷰 리졸버를 찾아 실행
   7. **`View 반환`** : 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체 반환
   8. **`뷰 렌더링`** : 뷰를 통해 뷰 렌더링


#### ✔️ 핸들러 매핑과 핸들러 어댑터 
* 스프링 부트가 자동 등록하는 핸들러 매핑과 핸들러 어댑터
   * `HandlerMapping`
      * 0 = RequestMappingHandlerMapping ➡️ 애노테이션 기반의 컨트롤러 `@RequestMapping`에서 사용
      * 1 = BeanNameUrlHandlerMapping ➡️ 스프링 빈 이름으로 핸들러를 찾음 
   * `HandlerAdapter`
      * 0 = RequestMappingHandlerAdapter ➡️ 애노테이션 기반의 컨트롤러 `@RequestMapping`에서 사용
      * 1 = HttpRequestHandlerAdapter ➡️ HttpRequestHandler 처리
      * 2 = SimpleControllerHandlerAdapter ➡️ Controller 인터페이스 처리

* 동작 방식
   * `HandlerMapping`, `HandlerAdapter`를 순서대로 찾고 만약 없으면 다음 순서로 이동
      #### 1. 핸들러 매핑으로 핸들러 조회 - `HandlerMapping`을 순서대로 실행하여 핸들러 찾기
      #### 2. 핸들러 어댑터 조회 - `HandlerAdapter`의 `supports()`를 순서대로 호출
      #### 3. 핸들러 어댑터 실행   


#### ✔️ 뷰 리졸버  
* 스프링 부트 : `InternalResourceViewResolver` 라는 뷰 리졸버 자동 등록 ➡️ `application.properties`에 등록한 `spring.mvc.view.prefix`, `spring.mvc.view.suffix` 설정 정보 사용하여 등록

* 스프링 부트가 자동 등록하는 뷰 리졸버
   * 1 = BeanNameViewResolver ➡️ 빈 이름으로 뷰를 찾아서 반환
   * 2 = InternalResourceViewResolver ➡️ JSP를 처리할 수 있는 뷰 반환

* 동작 방식
   * `InternalResourceViewResolver`를 호출하는 경우 
      #### 1. 핸들러 어댑터 호출 - 핸들러 어댑터를 통해 논리 뷰 이름 획득
      #### 2. ViewResolver 호출 - 논리 뷰 이름으로 ViewResolver 순서대로 호출 
      #### 3. InternalResourceViewResolver 호출 - `InternalResourceView` 반환
        * JSP : `forward()`를 통해 해당 JSP로 이동하여 렌더링
        * JSP를 제외한 나머지 뷰 템플릿 : `forward()` 과정 없이 바로 실제 뷰 렌더링
      #### 4. view.render() 호출


**`1. 스프링 MVC - 시작하기`**
* `@RequestMapping` 애노테이션 기반의 스프링 MVC 컨트롤러 사용

#### ✔️ `@Controller`
* 스프링이 자동으로 스프링 빈으로 등록 (➡️ 내부에 `@Component` 애노테이션이 있어서 **컴포넌트 스캔의 대상**이 됨)
* 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식 (➡️ `RequestMappingHandlerMapping`에서 사용)

#### ✔️ `@RequestMapping`
* 요청 정보 매핑
* 해당 URL이 호출되면 이 메서드 호출


**`2. 스프링 MVC - 컨트롤러 통합`**
* 컨트롤러를 하나로 통합하면서 중복되는 `@RequestMapping`의 URL을 `메서드 레벨` ➡️ `클래스 레벨`로 변경
   * `클래스 레벨`과 `메서드 레벨`을 조합하여 사용


**`3. 스프링 MVC - 실용적인 방식`**
   #### 기존 ➡️ 변경
   * ModelAndView 직접 생성하여 반환 ➡️ ViewName 직접 반환
   * `request.getParameter()` ➡️ `@RequestParam` 사용
   * `@RequestMapping` ➡️ `@GetMapping`, `@PostMapping` 등으로 HTTP Method를 애노테이션으로 구분
</details>

<details>
  
**<summary> `Section 6) 스프링 MVC - 기본 기능` </summary>**
#### 요청 매핑
   * `@RestController`
      * `@Controller` : 반환 값이 `String`이면 뷰를 찾고 뷰 렌더링
      * `@RestController` : 반환 값으로 뷰를 찾는 것이 아닌 **HTTP 메시지 바디에 바로 입력** ➡️ **실행 결과로 메시지 출력**

   * 요청 매핑 방법
      #### ✔️ HTTP 메서드
      * `@RequestMapping`에 `method` 속성으로 HTTP 메서드를 지정하지 않으면, HTTP 메서드와 무관하게 호출
         * GET, POST, PUT, PATCH, DELETE, HEAD 모두 허용
   
      #### ✔️ HTTP 메서드 매핑
      * `method` : 특정 HTTP 메서드 요청만 허용
      * 축약하여 사용 가능 (`method` + `@RequestMapping`) : `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`

      #### ✔️ PathVariable(경로 변수) 사용
      * 리소스 경로에 식별자 삽입 ➡️ 최근 HTTP API에서 선호하는 방식
      * `@PathVariable`의 이름과 파라미터 이름이 같으면 생략 가능
         * @PathVariable("userId") String userId ➡️ @PathVariable userId
      * 다중 사용 가능
    
      #### ✔️ 특정 파라미터/헤더 조건 매핑
      * 특정 파라미터/헤더가 있거나 없는 조건 추가

      #### ✔️ 미디어 타입 조건 매핑
      * HTTP 요청 Content-Type, consume
         * HTTP 요청의 Content-Type 헤더를 기반으로 미디어 타입 매핑
         * 타입이 맞지 않으면, `HTTP 415 상태코드(Unsupported Media Type)` 반환
 
      * HTTP 요청 Accept, produce
         * HTTP 요청의 Accept 헤더를 기반으로 미디어 타입 매핑
         * 타입이 맞지 않으면, `HTTP 406 상태코드(Not Acceptable)` 반환
       
#### HTTP 헤더 정보 조회
   * 컨트롤러로 사용 가능한 파라미터 목록 예시
      * `HttpServletRequest`, `HttpServletResponse`
      * `HttpMethod` : HTTP 메서드 조회
      * `Locale` : Locale 정보 조회 
      * `@RequestHeader MultiValueMap<String, String> headerMap` : 모든 HTTP 헤더를 MultiValueMap 형식으로 조회
         * `MultiValueMap` : 하나의 키에 여러 값을 받을 때 사용
      * `@RequestHeader("host") String host` : 특정 HTTP 헤더 조회    
      * `@CookieValue(value = "myCookie", required = false) String cookie` : 특정 쿠키 조회
      * 그 외 파라미터 목록은 [공식 메뉴얼](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)에서 확인
    
#### HTTP 요청 파라미터
* 클라이언트 ➡️ 서버로 요청 데이터를 전달할 때 주로 다음 3가지 방법 사용
   * GET - 쿼리 파라미터
   * POST - HTML Form
   * HTTP message body에 직접 데이터를 담아 요청 

* 요청 파라미터를 편리하게 사용할 수 있는 방법 - `@RequestParam`, `@ModelAttribute`
   #### ✔️ `@RequestParam`
   * 파라미터 이름으로 바인딩
   
   * `@RequestParam`의 `name(value)` 속성을 파라미터 이름으로 사용
   
   * HTTP 파라미터 이름이 변수 이름과 같으면, `@RequestParam(name="xx")` 생략 가능 ➡️ String, int, Integer 등의 **단순 타입이면 `@RequestParam`도 생략 가능**
   * `@RequestParam.required` : 파라미터 필수 여부 ➡️ 기본값은 파라미터 필수 (`required = true`)
   
   * `@RequestParam.defaultValue` : 파라미터에 값이 없는 경우 기본 값 적용, 빈 문자의 경우에도 설정한 기본 값 적용
   
   * 파라미터 Map, MultiValueMap으로 조회하기
      * `@RequestParam Map`
         * `Map(key=value)`
       
      * `@RequestParam MultiValueMap`
         * `MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])`    

   #### ✔️`@ModelAttribute`
   * 요청 파라미터를 받아서 필요한 객체를 만들고, 그 객체에 값을 넣어주는 과정을 완전히 자동화해주는 애노테이션

     ```java
     @ResponseBody
     @RequestMapping("/model-attribute-v1")
     public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
     }
     ```
      #### 1. `HelloData` 객체 생성
      #### 2. 요청 파라미터 이름으로 `HelloData` 객체의 프로퍼티의 setter를 호출하여 파라미터 값 입력 (파라미터 이름이 `username`이면, `setUsername()` 메서드를 찾아 호출하면서 값 입력)
       * 프로퍼티 : 객체에 `getUsername()`, `setUsername()` 메서드가 있으면, 이 객체는 `username` 이라는 프로퍼티를 가지고 있음
  
   * argument resolver로 지정해둔 타입을 제외하고 **기본적으로 내가 만드는 클래스들은 `@ModelAttribute` 생략 가능**

#### HTTP 요청 메시지
* 요청 파라미터와 다르게 HTTP 메시지 바디를 통해 데이터가 직접 넘어오는 경우 `@RequestParam`, `@ModelAttribute` 사용 ❌️ (HTML Form 형식으로 전달되는 경우 제외)
   #### ✔️ 단순 텍스트
   * `Input/OutputSteam`
     * `InputStream`(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
     * `OutputStream`(Writer) : HTTP 응답 메시지 바디에 결과 직접 출력 

    ```java
     @Slf4j
     @Controller
     public class RequestBodyStringController {
          @PostMapping("/request-body-string")
          public void requestBodyString(InputStream inputStream, Writer responseWriter) throws IOException {
             String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

             log.info("messageBody={}", messageBody);
             responseWriter.write("ok");
     }
    ```
   
   * `HttpEntity`
     * HTTP header, body 정보를 편리하게 조회 (메시지 바디 정보 직접 조회)
     * 응답에서도 사용 가능
        * 메시지 바디 정보 직접 반환
        * 헤더 정보 포함 가능
        * view 조회 ❌️ (바로 바디의 데이터를 가지고 HTTP 응답 메시지에 넣어버림) 
     * 스프링 MVC 내부에서 HTTP 메시지 바디를 읽어 문자나 객체로 변환하여 전달해주는 `HttpMessageConverter` 사용
     * `HttpEntity`를 상속받은 다음 객체들도 동일 기능 제공
        * `RequestEntity`(요청) : HttpMethod, url 정보 추가
        * `ResponseEntity`(응답) : HTTP 상태 코드 설정 가능
 
    ```java
     @Slf4j
     @Controller
     public class RequestBodyStringController {
          @PostMapping("/request-body-string")
          public HttpEntity<String> requestBodyString(HttpEntity<String> httpEntity) throws IOException {
             String messageBody = httpEntity.getBody();
             log.info("messageBody={}", messageBody);

             return new HttpEntity<>("ok");
     }
    ```
   * `@RequestBody`
      * HTTP 메시지 바디 정보를 편리하게 조회할 수 있는 애노테이션
      * 실무에서 주로 사용하는 방식 

    ```java
     @Slf4j
     @Controller
     public class RequestBodyStringController {
          @ResponseBody // 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있는 애노테이션
          @PostMapping("/request-body-string")
          public String requestBodyString(@RequestBody String messageBody) {
             log.info("messageBody={}", messageBody);
             return "ok";
     }
    ```

   #### ✔️ JSON
   * 문자로 된 JSON 데이터를 Jackson 라이브러리인 `objectMapper`를 사용해 자바 객체로 변환
   * `Input/OutputSteam`
      * `HttpServletRequest`를 사용하여 직접 HTTP 메시지 바디에서 데이터를 읽어와 문자로 변환
      ```java
      @Slf4j
      @Controller
      public class RequestBodyJsonController {

          private ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터 ➡️ 자바 객체

          @PostMapping("/request-body-json")
          public void requestBodyJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
             ServletInputStream inputStream = request.getInputStream();
             String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

             log.info("messageBody={}", messageBody);
             HelloData data = objectMapper.readValue(messageBody, HelloData.class);
             log.info("username={}, age={}", data.getUsername(), data.getAge());

             response.getWriter().write("ok");
          }
     }
     ```
   * `@RequestBody` 문자 변환
      * HTTP 메시지에서 데이터를 꺼내고 messageBody에 저장
      ```java
      @Slf4j
      @Controller
      public class RequestBodyJsonController {

          private ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터 ➡️ 자바 객체

          @ResponseBody
          @PostMapping("/request-body-json")
          public String requestBodyJson(@RequestBody String messageBody) throws IOException {
             HelloData data = objectMapper.readValue(messageBody, HelloData.class);
             log.info("username={}, age={}", data.getUsername(), data.getAge());
             return "ok";
          }
     }
     ```
   * `@RequestBody` 객체 변환
      * `@RequestBody`에 직접 만든 객체 지정 (`@RequestBody HelloData data`) ➡️ HTTP 메시지 컨버터가 JSON을 객체로 변환해주어 `HelloData data = objectMapper.readValue(messageBody, HelloData.class);` 코드를 대신해줌 
      ```java
      @Slf4j
      @Controller
      public class RequestBodyJsonController {

          private ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터 ➡️ 자바 객체

          @ResponseBody
          @PostMapping("/request-body-json")
          public String requestBodyJson(@RequestBody HelloData data) { 
             log.info("username={}, age={}", data.getUsername(), data.getAge());
             return "ok";
          }
     }
     ```
     ⚠️ 주의
      * `@RequestBody` 생략 불가능
      * 생략시, `@ModelAttribute`가 적용되어 요청 파라미터 처리 (`HelloData data` ➡️ `@ModelAttribute HelloData data`)

    * `@RequestBody` 객체 변환 - `@ResponseBody`로 응답
       * `@ResponseBody` 사용으로 해당 객체를 HTTP 메시지 바디에 직접 넣어줄 수 있음
      ```java
      @Slf4j
      @Controller
      public class RequestBodyJsonController {

          private ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터 ➡️ 자바 객체

          @ResponseBody
          @PostMapping("/request-body-json")
          public HelloData requestBodyJson(@RequestBody HelloData data) { 
             log.info("username={}, age={}", data.getUsername(), data.getAge());
             return data;
          }
      }
        ```
       * `@RequestBody` 요청
          * JSON 요청 ➡️ HTTP 메시지 컨버터 ➡️ 객체
       * `@ResponseBody` 응답
          * 객체 ➡️ HTTP 메시지 컨버터 ➡️ JSON 응답

     * `HttpEntity`
    ```java
     @Slf4j
     @Controller
     public class RequestBodyJsonController {

          private ObjectMapper objectMapper = new ObjectMapper(); // JSON 데이터 ➡️ 자바 객체

          @ResponseBody
          @PostMapping("/request-body-json")
          public String requestBodyJson(HttpEntity<HelloData> httpEntity) { // helloData 제네릭으로 선언
             HelloData data = httpEntity.getBody();
             log.info("username={}, age={}", data.getUsername(), data.getAge());
             return "ok";
          }
     }
    ```

#### HTTP 응답
   #### ✔️ 정적 리소스
   * 웹 브라우저에 정적인 HTML, CSS, JS를 제공할 경우 사용
   * 스프링 부트가 기본적으로 제공하는 경로 : `src/main/resources/static`
   * 파일 변경 없이 그대로 서비스
   #### ✔️ 뷰 템플릿
   * 웹 브라우저에 동적인 HTML을 제공할 경우 사용
   * 스프링 부트가 기본적으로 제공하는 경로 : `src/main/resources/templates`
     ```java
     @Controller
     public class ResponseViewController {

          @RequestMapping("/response-view-v1")
          public ModelAndView responseViewV1() {
             ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
             
             return mav;
          }

          // String을 반환하는 경우
          @RequestMapping("/response-view-v2")
          public String responseViewV2(Model model) {
             model.addAttribute("data", "hello!");             
             return "response/hello";
          }

          // void를 반환하는 경우
          @RequestMapping("/response/hello")
          public void responseViewV3(Model model) {
             model.addAttribute("data", "hello!");             
          }
     }
     ```
   * **String**을 반환하는 경우 - View or HTTP 메시지
      * `@ResponseBody` ❌ : `response/hello`로 뷰 리졸버가 실행되어 뷰를 찾고 렌더링
      * `@ResponseBody` ⭕ : 뷰 리졸버 실행 ❌ ➡️ HTTP 메시지 바디에 `response/hello` 출력
   
   * **void**를 반환하는 경우 (권장 ❌)
      * `@Controller`를 사용하고 HTTP 메시지 바디를 처리하는 파라미터가 없으면, 요청 URL을 참고해서 논리 뷰 이름으로 사용
   #### ✔️ HTTP 메시지 
   * `@ResponseBody`, `HttpEntity`를 사용하면 뷰 템플릿이 아닌 HTTP 메시지 바디에 직접 응답 데이터 출력 가능
   * `responseBodyV1`
     ```java
     @Slf4j
     @@RestController
     public class ResponseBodyController {

          @GetMapping("/response-body-string-v1")
          public void responseBodyV1(HttpServletResponse response) throws IOException {
             response.getWriter().write("ok");
          } 
     }
     ```
        * `HttpServletResponse` 객체를 통해 HTTP 메시지 바디에 직접 응답 메시지 전달

   * `responseBodyV2`
     ```java
     @Slf4j
     @@RestController // @ResponseBody + @Controller
     public class ResponseBodyController {

          // HttpEntity, ResponseEntity(Http Status) 추가
          @GetMapping("/response-body-string-v2")
          public ResponseEntity<String> responseBodyV2() {
             return new ResponseEntity<>("ok", HttpStatus.OK);
          } 
     }
     ```
        * `HttpEntity`를 상속 받은 `ResponseEntity`를 사용하여 **HTTP 메시지 바디에 응답 메시지 전달 ➕ HTTP 응답 코드 설정**

   * `responseBodyV3`
     ```java
     @Slf4j
     @@RestController
     public class ResponseBodyController {

          // @ResponseBody
          @GetMapping("/response-body-string-v3")
          public String responseBodyV3() {
             return "ok";
          } 
     }
     ```
        * `@ResponseBody` 사용하여 HTTP 메시지 컨버터를 통해 HTTP 메시지 바디에 응답 메시지 전달

   * `responseBodyJsonV1`
     ```java
     @Slf4j
     @@RestController
     public class ResponseBodyController {

          @GetMapping("/response-body-json-v1")
          public ResponseEntity<HelloData> responseBodyJsonV1() {
             HelloData helloData = new HelloData();
             helloData.setUsername("userA");
             helloData.setAge(20);
             return new ResponseEntity<>(helloData, HttpStatus.OK);
          } 
     }
     ```
        * `ResponseEntity` 사용하여 HTTP 메시지 컨버터를 통해 JSON 형식으로 변환되어 반환
           * 동적으로 응답 코드 변경 ⭕

   * `responseBodyJsonV2`
     ```java
     @Slf4j
     @@RestController
     public class ResponseBodyController {

          @ResponseStatus(HttpStatus.OK)
          // @ResponseBody
          @GetMapping("/response-body-json-v2")
          public HelloData responseBodyJsonV2() {
             HelloData helloData = new HelloData();
             helloData.setUsername("userA");
             helloData.setAge(20);
             return helloData;
          } 
     }
     ```
        * `@ResponseBody` 사용하여 HTTP 메시지 컨버터를 통해 HTTP 메시지 바디에 응답 메시지 전달 ➡️ HTTP 응답 코드 설정 불가능
           * `@ResponseStatus` 애노테이션 사용 (동적으로 응답 코드 변경 ❌)
         
#### HTTP 메시지 컨버터
* 스프링 MVC에서 HTTP 메시지 컨버터를 적용하는 경우
   * HTTP 요청 : `@RequestBody`, `HttpEntity(RequestEntity)`
   * HTTP 응답 : `@ResponseBody`, `HttpEntity(ResponseEntity)`

* HTTP 메시지 컨버터 인터페이스
   * `canRead()`, `canWrite()` : 메시지 컨버터가 해당 클래스, 미디어 타입을 지원하는지 체크
   * `read()`, `write()` : 메시지 컨버터를 통해 메시지를 읽고 쓰는 기능
 
* 스프링 부트 기본 메시지 컨버터 (일부 생략)
   * 0 = ByteArrayHttpMessageConverter ➡️ `byte[]` 데이터 처리
      * 클래스 타입 : `byte[]`, 미디어 타입 : `*/*` 
   * 1 = StringHttpMessageConverter ➡️ `String` 문자 데이터 처리
      * 클래스 타입 : `String`, 미디어 타입 : `*/*` 
   * 2 = MappingJackson2HttpMessageConverter
      * 클래스 타입 : 객체 또는 `HashMap`, 미디어 타입 : `application/json` 관련
    
* **HTTP 요청 데이터 읽기**
   * HTTP 요청 ➡️ `canRead()` 호출하여 메시지 컨버터가 메시지를 읽을 수 있는지 확인 (1. 대상 클래스 타입 지원 여부 2. HTTP 요청의 **Content-Type** 미디어 타입 지원 여부 체크) ➡️ `canRead()` 조건 만족시, `read()` 호출하여 객체 생성 후 반환
* **HTTP 응답 데이터 생성**
   * `canWrite()` 호출하여 메시지 컨버터가 메시지를 쓸 수 있는지 확인 (1. 대상 클래스 타입 지원 여부 2. HTTP 요청의 **Accept** 미디어 타입 지원 여부 체크) ➡️ `canWrite()` 조건 만족시, `write()` 호출하여 HTTP 응답 메시지 바디에 데이터 생성

#### 🤔 그렇다면 HTTP 메시지 컨버터는 스프링 MVC 어디쯤에서 사용되는 걸까?
* `@RequestMapping`을 처리하는 `@RequestMappingHandlerAdapder`
   #### [RequestMappingHandlerAdapter 동작 방식]
<p align="center"><img width="60%" src="https://github.com/iams0m/SpringStudy/assets/105639531/a46f022e-00c3-4059-b8e6-4c8ca79e8c7a"/></p>
   
   * `ArgumentResolver`
      * **HTTP 메시지 컨버터를 사용하여** 핸들러(컨트롤러)가 필요로 하는 다양한 파라미터의 값(객체) 생성 ➡️ 컨트롤러를 호출하여 값을 넘겨줌
      * 스프링에서 기본적으로 제공하는 `ArgumentResolver` : `HttpServletRequest`, `Model`, `@RequestParam`, `@ModelAttribute`, `@RequestBody`, `HttpEntity` 등 (➡️ 유연한 파라미터 처리 가능)
      * 동작 방식
         * `ArgumentResolver`의 `supportsParameter()`를 호출하여 해당 파라미터 지원 여부 체크 ➡️ 지원하면,`resolveArgument`를 호출하여 실제 객체 생성 ➡️ 컨트롤러 호출시, 생성된 객체 넘겨줌
       
   * `ReturnValueHandler`
      * **HTTP 메시지 컨버터를 호출하여** 응답 결과 생성
      * 스프링에서 기본적으로 제공하는 `ReturnValueHandler` : `ModelAndView`, `@ResponseBody`, `HttpEntity`, `String` 등

</details>

<details>

**<summary> `Section 7) 스프링 MVC - 웹 페이지 만들기` </summary>**
#### Thymeleaf
* `서버 사이드 HTML 렌더링 (SSR)` : 백엔드 서버에서 HTML을 동적으로 렌더링
* `네츄럴 템플릿` : HTML을 유지하여 웹 브라우저에서 파일을 직접 열어도 내용을 확인할 수 있고, 서버를 통해 뷰 템플릿을 거쳐 동적으로 변경된 결과도 확인 가능
* `스프링 통합 지원` : 스프링과 통합되어 있어 스프링의 다양한 기능 편리하게 사용 가능 (➡️ **Spring 진영에서 공식적으로 Thymeleaf 사용 권장 !**)
* 기본 기능
   * `타임리프 태그 속성`
      * 주로 HTML 태그에 `th:*` 속성을 지정하는 방식으로 동작
      * `th:*`로 속성을 적용하면 서버사이드에서 렌더링 되고, 기존 HTML 속성 대체
      * `th:*`이 없으면 기존 HTML 속성 그대로 사용
   
   * `URL 링크 표현식`
      * 타임리프에서 URL을 생성할 때 `@{...}` 문법 사용
      * 경로를 템플릿처럼 편리하게 사용 가능
      * 경로 변수 뿐만 아니라 쿼리 파라미터도 생성
         * `th:href="@{/basic/items/{itemId}(itemId=${item.id}, query='test')}"`
   
   * `리터럴`
      * 문법 : `|...|`
      * 타임리프에서 문자와 표현식 등은 분리되어 있기 때문에 더해서 사용해야 함
         * `<span th:text="'Welcome to our application, ' + ${user.name} + '!'">`
      * 리터럴 문법을 사용하면, 더하기 없이 편리하게 사용 가능
         * `<span th:text="|Welcome to our application, ${user.name}!|">`     

   * `반복 출력` - `th:each`
      * `<tr th:each="item : ${items}">` : 모델에 포함된 `items` 컬렉션 데이터가 `item` 변수에 하나씩 포함되고, 반복문 안에서 `item` 변수 사용 가능
         * List뿐만 아니라 배열, `java.util.Iterable`, `java.util.Enumeration`을 구현한 모든 객체 반복문 사용 가능
         * 컬렉션 수만큼 `<tr>..</tr>`이 하위 태그를 포함하여 생성됨

   * `변수 표현` - `${...}`
      * `<td th:text="${item.price}">10000</td>` : 모델에 포함된 값이나, 타임리프 변수로 선언한 값 조회 가능하며 프로퍼티 접근법 사용
    
   * `내용 변경` - `th:text`
      * 태그 안의 텍스트를 서버에서 전달 받은 값에 따라 표현하고자 할 때 사용
    
   * `속성 변경`
      * `th:value` 
         * 사용자의 입력이 필요한 요소의 value값 설정 (`input`, `checkboxes`, `radio buttons`, `dropdowns` 등)
      * `th:action`
         * HTML form에서 `action`에 값이 없으면, 현재 URL에 데이터 전송 ➡️ 하나의 URL을 사용하여 HTTP 메서드로 기능 구분 가능
            * 상품 등록 폼 : GET `/basic/items/add`
            * 상품 등록 처리 : POST `/basic/items/add`   

#### 상품 등록 처리 - `@ModelAttribute`
* 상품 등록 폼은 다음 방식으로 서버에 데이터 전달
   * **POST - HTML Form**
      * `content-type: application/x-www-form-urlencoded`
      * 메시지 바디에 쿼리 파라미터 형식으로 전달

```java
@PostMapping("/add")
public String addItem(@ModelAttribute("item") Item item, Model model) {
     itemRepository.save(item);
     //model.addAttribute("item", item); // 자동 추가, 생략 가능
     return "redirect:/basic/items/" + item.getId();
}
```

* `@ModelAttribute` 특징
   #### ✔️요청 파라미터 처리
   * `Item` 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력

   #### ✔️Model 추가
   * 모델에 `@ModelAttribute`로 지정한 객체 자동 추가 (➡️ `model.addAttribute("item", item)` 생략 가능)

   #### ✔️`@ModelAttribute name` 생략 가능
   * 생략시, model에 저장되는 name : 클래스명 첫글자를 소문자로 변경하여 등록 (`Item` ➡️ `item`)

   #### ✔️`@ModelAttribute` 전체 생략 가능
   * 생략시, 대상 객체 모델에 자동 등록

* 마지막에 뷰 템플릿을 호출하는 대신, 상품 상세 화면으로 이동하도록 **리다이렉트** 호출
#### ⚠️ 리다이렉트를 하지 않으면, 등록 화면에서 새로 고침시 중복 등록
   * 웹 브라우저의 새로 고침 : 마지막으로 서버에 전송한 데이터 다시 전송
   * 상품 등록 폼에서 데이터를 입력하고 저장하면, **`POST /add` + 상품 데이터**를 서버로 전송 ➡️ 이 상태에서 새로 고침을 하면, 마지막에 전송한 **`POST /add` + 상품 데이터**를 서버로 다시 전송 (중복 등록 발생 !)

#### 🤔 그렇다면 이 문제를 어떻게 해결할 수 있을까?
* PRG (Post/Redirect/Get)
   * 상품 저장 후, 뷰 템플릿으로 이동하는 것이 아닌 상품 상세 화면으로 리다이렉트 호출 ➡️ 마지막에 호출한 내용이 상품 상세 화면인 `GET /items/{id}`로 변경되어 새로 고침 문제 해결

#### 🫢 만약 여기서 고객이 저장이 잘 된 건지 확인할 수 있도록 저장이 잘 되었으면 상품 상세 화면에 "저장 완료"라는 메시지를 보여달라는 요구사항이 들어왔다면 어떻게 해결할 것인가?
##### 1. `RedirectAttributes`
   ```java
   @PostMapping("/add")
   public String addItem(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
   }
   ```
   
   * `redirect:/basic/items/{itemId}`
      * `pathVariable` 바인딩 : `{itemId}`
      * 나머지는 쿼리 파라미터로 처리 : `?status=true`

##### 2. 뷰 템플릿 메시지 추가
```java
   <h2 th:if="${param.status}" th:text="'저장 완료'"></h2>
```

* `th:if` : 해당 조건이 참이면 실행
* `${param.status}` : 타임리프에서 쿼리 파라미터를 편리하게 조회하는 기능  

#### 상품 수정 처리
* 상품 등록과 마찬가지로 **리다이렉트** 호출 

```java
@PostMapping("/{itemId}/edit")
public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
     itemRepository.update(itemId, item);
     return "redirect:/basic/items/{itemId}";
}
```

* `redirect:/basic/items/{itemId}` : `redirect`에서 컨트롤러에 매핑된 `@PathVariable` 값 사용 가능

</details>

#### 05. 🍃 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술

<details>

**<summary> `Section 1) 타임리프 - 기본 기능` </summary>**
#### 🌿 타임리프 특징
* 서버 사이드 HTML 렌더링 (SSR)
   * 백엔드 서버에서 HTML을 동적으로 렌더링 하는 용도로 사용 

* 네츄럴 템플릿 (natural templates)
   * 순수 HTML을 그대로 유지하면서 뷰 템플릿 사용 가능 (➡️ 웹 브라우저에서 파일을 직접 열어도 내용을 확인할 수 있고, 서버를 통해 뷰 템플릿을 거쳐 동적으로 변경된 결과도 확인할 수 있음)

* 스프링 통합 지원
   * 스프링과 자연스럽게 통합되고, 스프링의 다양한 기능을 편리하게 사용할 수 있도록 지원 

#### 기본 표현식
* 텍스트 - text, utext
  ##### 1️⃣ HTML의 콘텐츠에 데이터를 출력할 경우
    * `th:text`

  ##### 2️⃣ HTML 콘텐츠 영역 안에서 직접 데이터를 출력할 경우
    * `[[...]]`

  #### ⚠️ HTML 문서는 특수 문자를 기반으로 정의되어 있어 뷰 템플릿으로 HTML 화면을 생성할 때, 특수 문자가 있는 것을 주의해서 사용해야 함

  * Escape
    * HTML에서 사용하는 특수 문자를 ***HTML 엔티티**로 변경하는 것
      * HTML 엔티티 : HTML에 미리 예약된 문자인 HTML 예약어(reserved characters)를 HTML 코드에서 사용하면, 웹 브라우저는 평소와 다른 의미로 해석하게 됨 ➡️ 따라서 HTML 예약어를 기존에 사용하던 의미 그대로 사용하기 위해 별도로 만든 문자셋
  
    * 🌿 타임리프의 `th:text`, `[[...]]` : 기본적으로 이스케이프 제공
      * 예 : `<` ➡️ `&lt;`, `>` ➡️ `&gt;` 
  
  * UnEscape
    * 🌿 타임리프에서 이스케이프 기능을 사용하지 않을 경우
      * `th:text` ➡️ `th:utext`
      * `[[...]]` ➡️ `[(...)]`

  #### 👉 Escape를 기본으로 하고, 꼭 필요할 때만 UnEscape를 사용하자!

* 변수 - SpringEL
  * 🌿 타임리프에서 변수를 사용할 때 : 변수 표현식(`${...}`) ➕ 스프링이 제공하는 표현식(SpringEL) 사용

  #### SpringEL 다양한 표현식 사용
  ##### ✔️ Object
  * `user.username` : `user`의 `username`에 프로퍼티 접근 (`user.getUsername()`)
  * `user['username`]` : 위와 동일
  * `user.getUsername()` : `user`의 `getUsername()` 직접 호출

  ##### ✔️ List
  * `users[0].username` : List에서 첫번째 회원을 찾고, `username`에 프로퍼티 접근 (`list.get(0).getUsername()`)
  * `users[0]['username']` : 위와 동일
  * `users[0].getUsername()` : List에서 첫번째 회원을 찾고 `getUsername()` 직접 호출

  ##### ✔️ Map
  * `userMap['userA']` : Map에서 userA를 찾고, `username`에 프로퍼티 접근 (`map.get("userA").getUsername()`)
  * `userMap['userA']['username']` : 위와 동일
  * `userMap['userA'].getUsername()` :  Map에서 userA를 찾고, `getUsername()` 직접 호출
 
  #### 지역 변수
  * `th:with`
  * 선언한 태그 안에서만 사용 가능
  ```html
  <h1>지역 변수 - (th:with)</h1>
  <div th:with="first=${users[0]}">
   <p>처음 사람의 이름은 <span th:text="${first.username}"></span></p>
  </div>
  ```
    * List에서 첫번째 회원을 찾아 `first`에 담아두고 `username`에 프로퍼티 접근 ➡️ 결과 : 처음 사람의 이름 출력

* 기본 객체
  * 🌿 타임리프에서 제공하는 기본 객체
  * `#` 기호로 시작
    * `#ctx`
    * `#vars`
    * `#locale`
  * 편의 객체도 제공
    * HTTP 요청 파라미터 접근 : param
      * 예) `${param.paramData}` 
    * HTTP 세션 접근 : session
      * 예) `${session.sessionData}` 
    * 스프링 빈 접근 : @
      * 예) `${@helloBean.hello('Spring!')}` 

* 유틸리티 객체와 날짜
  * 유틸리티 객체 : 문자, 숫자, 날짜, URI 등을 편리하게 다루는 객체
    * [타임리프 유틸리티 객체 목록](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#expression-utility-objects)  

* URL 링크
  * `@{...}`
  ##### ✔️ 단순한 URL
  * `@{/hello}` ➡️ `/hello` 

  ##### ✔️ 쿼리 파라미터
  * `@{/hello(param1=${param1}, param2=${param2})}` ➡️ `/hello?param1=data1&param2=data2`
  * () : 쿼리 파라미터로 처리

  ##### ✔️ 경로 변수
  * `@{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}` ➡️ `/hello/data1/data2`
  * URL 경로상 변수가 있으면, () 부분은 경로 변수로 처리
    * 경로를 만드는 부분과 데이터가 있는 부분이 분리 되어 있어 유지보수 용이

  ##### ✔️ 경로 변수 + 쿼리 파라미터
  * `@{/hello/{param1}(param1=${param1}, param2=${param2})}` ➡️ `/hello/data1?param2=data2`
  * 경로 변수와 쿼리 파라미터 함께 사용 가능

* 리터럴 (Literals) - 소스 코드상 고정된 값
  * 🌿 타임리프의 리터럴
    * 문자 : `'hello'`
      * 항상 `'` (작은 따옴표)로 감싸야 함
        * BUT, 공백 없이 쭉 이어진다면 작은 따옴표 생략 가능
          ```html
          <li>'hello' + ' world!' = <span th:text="'hello' + ' world!'"></span></li>
          <li>'hello world!' = <span th:text="'hello world!'"></span></li>
          <li>'hello ' + ${data} = <span th:text="'hello ' + ${data}"></span></li>
          <li>리터럴 대체 |hello ${data}| = <span th:text="|hello ${data}|"></span></li>
          ```
    * 숫자 : `10`
    * 참, 거짓 : `true`, `false`
    * null : `null`  

* 연산
  * 산술 연산
    ```html
    <li>10 + 2 = <span th:text="10 + 2"></span></li> // 10 + 2 = 12
    <li>10 % 2 == 0 = <span th:text="10 % 2 == 0"></span></li> // 10 % 2 == 0 = true
    ``` 

  * 비교 연산 : HTML 엔티티 사용
    * `>` ➡️ `(gt)`
    * `<` ➡️ `(lt)`
    * `>=` ➡️ `(ge)`
    * `<=` ➡️ `(le)`
    * `!` ➡️ `not`
    * `==` ➡️ `(eq)`
    * `!=` ➡️ `(neq, ne)`
    ```html
    <li>1 > 10 = <span th:text="1 > 10"></span></li> // 1 > 10 = false
    <li>1 gt 10 = <span th:text="1 gt 10"></span></li> // 1 gt 10 = false
    <li>1 &gt; 10 = <span th:text="1 &gt; 10"></span></li> // 1 &gt; 10 = false
    ``` 

  * 조건식 : 자바 조건식과 유사
    ```html
    <li>(10 % 2 == 0)? '짝수':'홀수' = <span th:text="(10 % 2 == 0)? '짝수':'홀수'"></span></li> // (10 % 2 == 0)? '짝수':'홀수' = 짝수
    ```   

  * Elvis 연산자
    * `?:`
    * `?:`의 왼쪽 객체가 Not-Null ➡️ 그 객체의 값 리턴, Null ➡️ `?:`의 오른쪽 값 리턴 
    ```html
    <li>${data}?: '데이터가 없습니다.' = <span th:text="${data}?: '데이터가 없습니다.'"></span></li> // ${data}?: '데이터가 없습니다.' = Spring!
    <li>${nullData}?: '데이터가 없습니다.' = <span th:text="${nullData}?: '데이터가 없습니다.'"></span></li> // ${nullData}?: '데이터가 없습니다.' = 데이터가 없습니다.
    ```  

  * No-Operation : _인 경우, 마치 타임리프가 실행되지 않는 것처럼 동작 (HTML 내용 그대로 출력 ➡️ HTML 내용이 기본값)
    * `?: _`
    ```html
    <li>${data}?: _ = <span th:text="${data}?: _">데이터가 없습니다.</span></li> // ${data}?: _ = Spring!
    <li>${nullData}?: _ = <span th:text="${nullData}?: _">데이터가 없습니다.</span></li> // ${nullData}?: _ = 데이터가 없습니다.
    ```

* 속성
  * 속성 설정 
    * 🌿 타임리프는 주로 HTML 태그에 `th:*` 속성을 지정하는 방식으로 동작
    * 기존 속성이 있으면 `th:*`로 지정한 속성으로 대체, 기존 속성이 없으면 새로 생성

  * 속성 추가
    * `th:attrappend` : 속성 값 뒤에 값 추가
    * `th:attrprepend` : 속성 값 앞에 값 추가
    * `th:classappend` : class 속성의 적절한 위치에 값 추가

  * checked 처리
    * HTML의 checked 속성 : checked 속성 값과 상관없이 checked라는 속성만 있어도 체크됨 ➡️ 타임리프의 `th:checked` 사용시, 값이 false면 checked 속성 자체를 제거해줌 (체크 처리 ❌)

* 반복
  * 반복 기능
    * `<tr th:each="user : ${users}">`
      * 오른쪽 컬렉션 (`${users}"`)의 값을 하나씩 꺼내 왼쪽 변수(`user`)에 담아 태그 반복 실행
      * `java.util.Iterable`, `java.util.Enumeration`을 구현한 모든 객체에 반복 사용 가능

  * 반복 상태 유지
    * `<tr th:each="user, userStat : ${users}">`
      * 반복에 두번째 파라미터를 설정하여 반복 상태 확인 가능 (두번째 파라미터 생략 가능 ➡️ 생락시 지정한 변수명 ➕ Stat)

  * 반복 상태 유지 기능
      * `index` : 0부터 시작하는 값
      * `count` : 1부터 시작하는 값
      * `size` : 전체 크기
      * `even`, `odd` : 홀수, 짝수 여부 (결괏값 boolean)
      * `first`, `last` : 처음, 마지막 여부 (결괏값 boolean)
      * `current` : 현재 객체 정보
   
* 조건부 평가
##### 🌿 타임리프의 조건식
* `if`, `unless` (↔ `if`)
  * 해당 조건이 맞지 않으면, 태그 자체 렌더링 ❌

* `switch`
  * case에 따른 태그 출력
  * `*` : 조건이 없을 때 사용하는 디폴트
  ```html
  <td th:switch="${user.age}">
    <span th:case="10">10살</span>
    <span th:case="20">20살</span>
    <span th:case="*">기타</span>
  </td>
  ```

* 주석
  ##### 1️⃣ 표준 HTML 주석
  * `<!-- ... -->`
  * 타임리프가 렌더링 하지 않고 그대로 남겨둠

  ##### 2️⃣ 타임리프 파서 주석
  * 한 줄 처리 : `<!--/* ... /-->`
    * 절대경로로 열었을 때, HTML 주석으로 처리하여 웹 브라우저에서 렌더링 ❌
  * 여러 줄 처리 : `<!--/--> ... <!--/-->`
    * 절대경로로 열었을 때, HTML 주석 규칙에 따라 렌더링
  * 타임리프의 진짜 주석 ➡️ 렌더링 해서 주석 부분 제거 

  ##### 3️⃣ 타임리프 프로토타입 주석
  * `<!--/*/ ... /*/-->`
  * 절대경로로 열었을 때, HTML 주석으로 처리하여 웹 브라우저에서 렌더링 ❌
  * 타임리프 렌더링을 거치면, 정상 렌더링

* 블록
  * `th:block`
  * 🌿 타임리프 자체 태그
  * 반복할 태그가 여러 가지일 때 사용하면 편리
  * 렌더링시 `<th:block>` 태그 제거됨
  ```html
  <th:block th:each="user : ${users}">
    <div>
      사용자 이름<span th:text="${userStat.count} + ' ' + ${user.username}"></span>
      사용자 나이<span th:text="${userStat.count} + ' ' + ${user.age}"></span>
    </div>
    <div>
      요약 <span th:text="${user.username} + ' / ' + ${user.age}"></span>
    </div>
  </th:block>
  ```

* 자바스크립트 인라인
  * `<script th:inline="javascript">`
  * 자바스크립트에서 타임리프를 편리하게 사용할 수 있는 기능
  #### 🌿 타임리프에서 제공하는 자바스크립트 인라인 기능
    ##### 1️⃣ 텍스트 렌더링
    * `var username = [[${user.username}]];`
      * 인라인 사용 전 ➡️ `var username = UserA;`
        * `userA`가 변수명으로 사용되어 자바스크립트 오류 발생 (⚠️`UserA is not defined`)  
      * 인라인 사용 후 ➡️ `var username = "UserA";`
        * 문자 타입에 `"` 포함해줌
        * 문제가 될 수 있는 문자가 포함되어 있으면, 이스케이프 처리 

    ##### 2️⃣ 자바스크립트 내추럴 템플릿
    * `var username2 = /*[[${user.username}]]*/ "test username";`
      * 인라인 사용 전 ➡️ `var username2 = /*UserA*/ "test username";`
        * 렌더링 내용 주석 처리 ➡️ 내추럴 템플릿 기능 동작 ❌
      * 인라인 사용 후 ➡️ `var username2 = "UserA";`

    ##### 3️⃣ 객체
    * `var user = [[${user}]];`
      * 인라인 사용 전 ➡️ `var user = BasicController.User(username=UserA, age=10);`
        * 객체의 `toString()` 호출 
      * 인라인 사용 후 ➡️ `var user = {"username":"UserA","age":10};`
        * 객체를 JSON으로 자동 변환 

* 템플릿 조각
  * `th:fragment`
  * 웹 페이지의 상단, 하단 영역 등과 같은 공통 영역을 효율적으로 사용할 수 있도록 제공하는 기능
  * 다른 템플릿의 일부를 조각처럼 가져와서 사용할 수 있음
    * `template/fragment/footer :: copy` :  : `template/fragment/footer.html` 경로에 있는 이름이 `th:fragment="copy"`인 부분을 템플릿 조각으로 가져와서 사용

  #### 🌿 타임리프에서 제공하는 템플릿 조각 기능
    ##### 1️⃣ `th:insert`
    * 현재 태그 내부에 추가

    ##### 2️⃣ `th:replace`
    * 현재 태그 대체

    ##### 3️⃣ 단순 표현식
    * `~{...}`를 사용하는 것이 원칙. BUT, 템플릿 조각을 사용하는 코드가 단순하면 생략 가능
 
    ##### 4️⃣ 파라미터 전달 가능

* 템플릿 레이아웃
  * 코드 조각을 레이아웃에 넘겨서 사용하는 방법

  ##### 1️⃣ `<head>`에 공통으로 사용되는 `css`, `javascript` 같은 정보들을 한 곳에 모아두고 공통으로 사용하되, 각 페이지 별로 필요한 부분을 추가할 수 있도록 제공하는 기능
    * `common_header(~{::title},~{::link})`
      * `::title` : 현재 페이지의 `title` 태그들을 전달
      * `::link` : 현재 페이지의 `link` 태그들을 전달 


  ##### 2️⃣ `<html>` 전체에도 적용 가능

</details>

<details>

**<summary> `Section 2) 타임리프 - 스프링 통합과 폼` </summary>**
##### ♻️「스프링 MVC1편 - 백엔드 웹 개발 핵심 기술」에서 진행한 프로젝트를 타임리프가 지원하는 기능을 사용하여 코드 개선해보기

  #### 스프링과 타임리프의 통합으로 추가되는 기능
  ##### ✔️ 스프링의 SpringEL 문법 통합
  
  ##### ✔️ 스프링 빈 호출 지원
  
  ##### ✔️ 편리한 폼 관리
  * `th:object` : 커맨드 객체 지정
    * `*{...}` : 선택 변수 식, `th:object`에서 선택한 객체에 접근 
  * `th:field`
    * HTML 태그의 `id`, `name`, `value` 속성 자동 생성
  
  ##### ✔️ 폼 컴포넌트 기능
  * `checkbox`
    #### 1. 단일 
      ##### 방법 1️⃣ HTML checkbox
      ```html
      <input type="checkbox" id="open" name="open" class="form-check-input">
      ```
      * 체크박스를 선택한 경우 : `true`
      * 체크박스를 선택하지 않은 경우 : 클라이언트에서 서버로 값 자체를 보내지 않음 (`null`)
        * ⚠️ 체크박스 선택을 수정하려고 할 때, 사용자가 체크되어 있던 값을 체크 해체해도 저장시 아무 값도 넘어가지 않아 값이 오지 않은 것으로 판단하여 변경된 값으로 인식하지 못 할 수도 있음

      ##### 방법 2️⃣ 🌿 타임리프 적용하기
      ```html
      <input type="checkbox" id="open" th:field="*{open}" class="form-check-input">
      ```
      * 체크박스를 선택한 경우 : `true`
        ```html
        <input type="checkbox" id="open" class="form-check-input" name="open" value="true" checked="checked">
        ```
        * `checked` 속성이 자동 생성되면서 체크 유무 확인
      
      * 체크박스를 선택하지 않은 경우 : `false`
        ```html
        <input type="checkbox" id="open" class="form-check-input" name="open" value="true">
        ```
        * `checked` 속성 생성 ❌

    #### 2. 멀티
      ##### Controller
      * `@ModelAttribute`
        * 이 애노테이션이 선언된 메서드를 생성하면, 해당 컨트롤러 내의 모든 매핑 메서드의 `model`에 반환값 자동 전달
        * `@ModelAttribute` 의 매개변수 : Web 계층에서 사용할 변수명

      ##### Web
      * `th:each` : `@ModelAttribute` 의 매개변수를 사용하여 반복문을 돌면서 여러가지 체크박스 생성
      * input 태그의 `th:field` : Entity 필드값
        * `th:value` : param으로 보내야 되기 때문에 map의 key값 사용 ➡️ Entity의 List region에 저장
      * label 태그의 `th:text` : map의 value가 체크박스 이름으로 랜더링
      * `${#ids.prev('...')}` : `each`문 내에 설정된 경우 `index`마다 id가 동적으로 중복 없이 생성되는데, 이 생성된 id를 labal 태그로 가져와서 사용할 수 있도록 함
        * 여기서는 input 태그의 id와 label 태그의 id를 맞춤 

      ```html
      <div th:each="region : ${regions}" class="form-check form-check-inline">
        <input type="checkbox"
               th:field="*{regions}"
               th:value="${region.key}"
               class="form-check-input">
        <label th:for="${#ids.prev('regions')}"
               th:text="${region.value}"
               class="form-check-label"></label>
      </div>
      ```
      
  * `radio button`
    * 멀티 체크박스와 유사
    * 라디오 버튼을 선택한 경우 : 선택한 값 전송
    * 라디오 버튼을 처음에 선택하지 않은 경우 : null
      * 값을 한번 선택하면, 이후 선택하지 않을 수 없음 ➡️ 별도의 hidden 필드 사용할 필요 ❌ 
    ##### Controller
    ```java
    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
     return ItemType.values();
    }
    ```
    * `ItemType.values();` : 해당 ENUM의 모든 정보를 배열로 반환
  
    ##### Web
    ```html
     <div th:each="type : ${itemTypes}" class="form-check form-check-inline">
  	   <input type="radio"
              th:field="*{itemType}"
              th:value="${type.name()}"
              class="form-check-input">
  	   <label th:for="${#ids.prev('itemType')}"
              th:text="${type.description}"
              class="form-check-label"></label>
     </div>
    ```
    * 🌿 타임리프는 model에 ENUM을 담아서 전달하는 대신, 스프링 EL 문법으로 자바 객체 직접 접근도 가능 (권장 ❌)

  * `select box`
    ##### Web
    ```html
     <select th:field="*{deliveryCode}" class="form-select">
  	   <option value="">==배송 방식 선택==</option>
       <option th:each="deliveryCode : ${deliveryCodes}"
               th:value="${deliveryCode.code}"
          	   th:text="${deliveryCode.displayName}"></option>
     </select>
    ```
    * select 태그 사용
    * 선택된 select box에 `selected="selected"` 생성 
  
  ##### ✔️ 스프링의 메시지, 국제화 기능의 편리한 통합
  
  ##### ✔️ 스프링의 검증, 오류 처리 통합
  
  ##### ✔️ 스프링의 변환 서비스 통합(ConversionService)

</details>

<details>

**<summary> `Section 3) 메시지, 국제화` </summary>**
#### 메시지
* 화면에 렌더링 된 text를 변경해야 하는 경우
  * 웹 애플리케이션의 규모가 클수록 변경해야 하는 text 증가 및 직접 파일을 수정하다가 오류가 발생할 수 있음
#### ➡️ 이런 다양한 text들을 별도의 파일에서 관리하도록 하는 메시지
##### [예시]
* `message.properties`라는 메시지 관리용 파일 생성
* HTML 내에서 사용할 데이터를 파일에 선언된 key 값으로 불러서 사용 (`item.itemName`) ➡️ text를 변경해야될 경우, 파일에 선언해준 부분만 변경하면 됨 (🦴 **유지보수 용이 및 일관성 증가**)
```text
item=상품
item.id=상품 ID
item.itemName=상품명
item.price=가격
item.quantity=수량
```

#### 국제화
* 메시지 파일(`message.properties`)을 각 나라별로 별도 관리하여 서비스 국제화
  * 방법 1️⃣ : HTTP accept-language 헤더 값 사용
  * 방법 2️⃣ : 사용자가 직접 언어를 선택하도록 하고, 쿠키 등을 사용하여 처리

#### ⚠️ 기본적인 메시지, 국제화 기능은 Spring이 제공함
  ##### 🌿 타임리프도 스프링이 제공하는 메시지와 국제화 기능을 편리하게 통합하여 제공함

#### 스프링 메시지 소스 설정
  ##### 📍 스프링 빈 등록
  * 스프링이 제공하는 메시지 관리 기능을 사용하려면 `MessageSource`를 Spring Bean에 등록해야함 (➡️ 스프링 부트를 사용하면, 자동으로 등록해줌)
  ```java
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages", "errors");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }
  ```
  * `setBasenames` : 설정 파일 이름 지정
    * `messages`로 지정하면 `messages.properties` 파일을 읽어서 사용
    * 클라이언트가 사용하는 특정 언어가 있을 경우, 그 파일로 대체
    * 여러 파일 한번에 지정 가능 (`messages`, `errors`)
  * `setDefaultEncoding` : 인코딩 정보 지정 
  
  ##### 📍 스프링 부트 메시지 소스 설정
  * `application.properties`에 `basename` 추가 
  ```properties
    spring.messages.basename=messages
  ```
  * 기본값으로 `messages` 라는 파일을 사용하겠다는 의미
  * 클라이언트가 사용하는 특정 언어가 있을 경우, 그 파일로 대체됨

#### 스프링 메시지 소스 사용
  ##### 📍 `MessageSource` 인터페이스
  ```java
    public interface MessageSource {

        @Nullable
        String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale);

        String getMessage(String code, @Nullable Object[] args, Locale locale) throws NoSuchMessageException;
    }
  ```

  ##### 📍 기본 메시지 조회 방법
  * code : 메시지 파일에 등록한 변수
  * args : 파일에 설정한 파라미터를 args로 치환
  * locale : 로케일 정보, 정보가 없으면 `basename`에서 설정한 기본 이름 메시지 파일 조회

  ##### 📍 메시지가 없는 경우
  * 파일에 정의되지 않은 코드를 입력할 경우 `NoSuchMessageException` 발생
  
  ##### 📍 기본 메시지
  * `defaultMessage` : 파일에 정의되지 않은 코드를 입력할 경우 기본적으로 나타나는 메시지
    * `args`와 `locale` 사이에 `defaultMessage` 파라미터 설정 가능
   
  ##### 📍 args 매개변수 사용
  * `args` 매개변수를 활용하여 상황에 맞게 message 변형 가능
    * Object 배열로 넘겨주어야 함
    * 배열의 인덱스 번호에 맞게 파라미터 변형
      * `hello.name=안녕 {0}` 
    ```java
       @Test
       void argumentMessage() {
           String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
           assertThat(result).isEqualTo("안녕 Spring");
       }
    ```
  
  ##### 📍 국제화
  * locale 정보를 기반으로 국제화 파일 선택
    * `null` : 시스템의 기본 locale 사용
      * 시스템 기본 locale이 ko_KR이면, `messages_ko.properties` 조회 시도 ➡️ 조회 실패 ➡️ `messages.properties` 순으로 조회  
    * `Locale.KOREA` : `messages_ko`를 찾고, 없으면 시스템의 기본 locale 사용 (`basename`을 `messages`로 가정)
    * `Locale.ENGLISH` : `messages_en`을 찾고, 없으면 시스템의 기본 locale 사용 (`basename`을 `messages`로 가정)
</details>

<details>

**<summary> `Section 4) 검증1 - Validation` </summary>**
#### 검증 요구사항
  ##### 📍 검증 로직 추가
  * 상품 저장 성공
    * 사용자가 상품 등록 폼에서 정상 범위 데이터를 입력하면 서버에서는 검증 로직이 통과하고, 상품을 저장하고, 상품 상세 화면으로 redirect
  * 상품 저장 실패 
    * 검증에 실패한 경우, 고객에게 다시 상품 등록 폼을 보여주고 어떤 값을 잘못 입력했는지 정보 전달
  
  ##### ⚠️ HTTP 요청이 정상인지 검증하는 것은 컨트롤러의 중요한 역할 중 하나
  ##### 클라이언트 검증과 서버 검증
  * 클라이언트 검증 : 조작이 가능하기 때문에 보안 취약
  * 서버 검증 : 즉각적인 고객 사용성 부족 
    ##### ➡️ 적절히 섞어서 사용하되, 최종적으로 서버 검증 필수

#### V1. 직접 검증 처리하기
  ##### 📍 Controller
  * 어떤 검증에서 오류가 발생했는지 `errors`에 결과 보관
    * `Map<String, String> errors = new HashMap<>();`
  
  * 검증 로직
    * Map을 사용하여 어떤 필드에서 오류가 발생했는지 구분하기 위해 오류가 발생한 필드명을 `key`로, 클라이언트에게 보여줄 메시지를 `value`로 설정
    ```java
       if (!StringUtils.hasText(item.getItemName())) {
           errors.put("itemName", "상품 이름은 필수입니다.");
       }
    ```      

  * 검증 실패 로직
    * 검증에서 오류 메시지가 하나라도 있으면, 오류 메시지 출력을 위해 `model`에 `errors`를 담고 입력 폼이 있는 뷰 템플릿으로 이동 
    ```java
       if (!errors.isEmpty()) {
           model.addAttribute("errors", errors);
           return "validation/v1/addForm";
       }
    ```  

  ##### 📍 Web
  * 오류 메시지
    * `th:if` : 조건문을 사용해 `errors`의 `key`가 있다면, 해당 `value`를 렌더링하여 태그 출력
    ```html
       <div class="field-error" th:if="${errors?.containsKey('itemName')}" th:text="${errors['itemName']}">
           상품명 오류
       </div>
    ```
    ##### 🤔 `errors` 뒤의 물음표는 뭘까?
    * 신규로 등록하는 상황은 `map`을 `model`로 보내지 못했기 때문에 `errors`자체가 null이 됨
      * `errors.containsKey` : null에 .containsKey을 했다는 의미 ➡️ `NullPointerException` 발생
    * `errors?.` : `errors`가 null일 때, null 반환 ➡️ 오류 메시지 출력 ❌ 

  * 필드 오류 처리
    ##### 방법 1️⃣ `th:class`
    * `class`에 조건식을 사용하여 참일 경우, `form-control`과 `field-error` 호출
      * 결과 : `class="form-control field-error”`
    * 조건식을 만족하지 않으면, `form-control` 호출
      * 결과 : `class="form-control”` 
    ```html
       <input type="text" id="itemName" th:field="*{itemName}" th:class="${errors?.containsKey('itemName')} ? 'form-control field-error' : 'form-control'" class="form-control">
    ```

    ##### 방법 2️⃣ `th:classappend`
    * `classappaend`에 조건식을 사용하여 참일 경우, 기존 클래스(`form-control`)에 `field-error` 추가
    * 조건식을 만족하지 않으면, `_`(No-Operation)을 사용해서 기존 클래스(`form-control`)만 호출
    ```html
       <input type="text" th:classappend="${errors?.containsKey('itemName')} ? 'field-error' : _" class="form-control">
    ```

  ##### ✏️ V1의 문제점
  * 코드의 중복
    * 하나의 input 로직에 같은 변수를 계속해서 입력해줘야 함
  * 타입 오류 처리 ❌
    * 타입이 다른 text를 입력할 경우, 예외 처리가 되지 않고 400 에러 발생
    * 클라이언트가 작성한 데이터의 보존을 보장할 수 없어 사용자는 어떤 문제로 오류가 발생했는지 이해하기 어려움
  
#### V2. BindingResult
  ##### 📍 Controller
  * `BindingResult`
    * 스프링에서 제공하는 validation 라이브러리
    * 검증 오류 발생시, 검증 오류 보관
    * `Model`로 넘겨주지 않아도 스프링이 자동 전달
    ##### ⚠️ `BindingResult` 파라미터의 위치는 항상 바인딩 대상이 되는 객체 바로 뒤에 위치해야 한다.

  * `FieldError`
    * `FieldError`에서 제공하는 생성자 두 가지
      ```java
         // 생성자 1
         public FieldError(String objectName, String field, String defaultMessage);

         // 생성자 2
         public FieldError(String objectName, String field, @Nullable Object rejectedValue, boolean bindingFailure, @Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage);
      ```   
      * **생성자 1️⃣** : 필드 검증 실패시, text box에 클라이언트가 입력한 데이터 삭제
        * 필드에 오류가 있을 때, `FieldError` 객체를 생성하여 `bindingResult`에 담아둠
          * `objectName` : `@ModelAttribute` 이름
          * `field` : 오류 발생 필드 이름
          * `defaultMessage` : 오류 기본 메시지
        
      * **생성자 2️⃣** : 필드 검증 실패시, text box에 클라이언트가 입력한 **데이터 유지**
        * 필드에 오류가 있을 때, `FieldError` 객체를 생성하여 사용자가 입력한 값을 `rejectedValue`에 담아둠 ➡️ 해당 오류를 `bindingResult`에 담아서 컨트롤러 호출
          * `rejectedValue` : 사용자가 입력한 값(거절된 값)을 저장하는 필드
          * `bindingFailure` : type 오류일 경우 `true` / 검증 실패일 경우 `false` 입력
          * `codes` : 메시지 코드
          * `arguments` : 메시지 코드에서 사용하는 인자

  * `ObjectError`
    ```java
       public ObjectError(String objectName, @Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage) {}
    ```
      * 특정 필드를 넘어서는 오류가 있을 때, `ObjectError` 객체를 생성하여 `bindingResult`에 담아둠

  #### ✏️ `BindingResult`는 바인딩 대상이 되는 객체 바로 뒤에 위치하기 때문에 이미 본인이 검증해야하는 객체를 알고 있다! (➡️ 따라서 objectName을 넣어주는 과정 생략 가능)

  ##### 📍 `rejectValue()`, `reject()`
  * `BindingResult`가 제공하는 두 가지 method를 사용하면, Error를 직접 생성하지 않고 깔끔한 검증 오류 가능
  * Field : `rejectValue()` / Object : `reject()`
    ```java
    void rejectValue(@Nullable String field, String errorCode, @Nullable Object[] errorArgs, @Nullable String defaultMessage);
    ```  
     * `field` : 오류 발생 필드 이름
     * `errorCode` : 오류 코드
     * `errorArgs` : 에러 코드에서 사용하는 인자
     * `defaultMessage` : 오류 기본 메시지
  
  ##### 📍 Web
  * `타임리프 - 스프링` 검증 오류 통합 기능
    * `#fields` : `BindingResult`의 타임리프 변수 표현식
    * `th:errors` : 해당 필드에 오류가 있는 경우 태그 출력 (`th:if`와 유사)
    * `th:errorclass` : `th:field`에서 지정한 필드에 오류가 있으면, `class` 정보 추가 (class append와 유사)
  
  * 글로벌 오류 처리
    * if문을 사용하여 글로벌 오류가 있다면, 오류 메시지 렌더링 
    ```html
       <div th:if="${#fields.hasGlobalErrors()}">
           <p class="field-error" th:each="err : ${#fields.globalErrors()}" th:text="${err}">전체 오류 메시지</p>
       </div>
    ```
    
  * 필드 오류 처리
    ```html
       <input type="text" id="itemName" th:field="*{itemName}" th:errorclass="field-error" class="form-control" placeholder="이름을 입력하세요">
       <div class="field-error" th:errors="*{itemName}">
           상품명 오류
       </div>
    ```

  ##### 📍 `@ModelAttribute`에 바인딩 시, 타입 오류가 발생했다면?
  * `BindingResult`가 없는 경우 : 400에러가 발생하면서 오류 페이지로 이동하고 컨트롤러 호출 ❌
  * `BindingResult`가 있는 경우 : 오류 정보를 `BindingResult`에 담고 **컨트롤러 정상 호출**

#### 오류 메시지 일관성 유지
  ##### 📍 문제점
  * 지금까지 구현한 로직의 에러 메시지 : 검증 로직마다 그때그때 개발자가 입력 ➡️ 메시지의 일관성 떨어짐 
    * 🤓 메시지 파일에 에러 메시지를 등록해 일관성 있게 관리해보자 
  
  ##### 1️⃣ 메시지 파일 생성 
  * `errors.properties` 생성 
  ##### 2️⃣ 메시지 설정 추가
  * 스프링 부트가 해당 메시지 파일을 인식할 수 있도록 `application.properties`에 `errors` 설정 추가
    * `spring.messages.basename=messages,errors`
  ##### 3️⃣ 메시지 파일 적용
  * 메시지 코드와 argument 매개변수를 배열로 입력
    * 0번 index가 없다면 1번 index가 출력되는 방식으로 우선순위를 설정할 수 있도록 배열 사용
    * 메시지 코드를 찾지 못하면, 디폴트 메시지 출력
  ```java
      //range.item.price=가격은 {0} ~ {1} 까지 허용합니다.
          new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null)
  ```

#### 오류 코드 설계
  ##### 🤔 오류 코드를 디테일하게 만들어야 할까, 단순하게 만들어야 할까?
  * 오류 코드를 단순하게 만들 경우, 범용성이 좋아 여러 곳에서 사용할 수 있지만 세밀한 메시지 작성 어려움
  * 오류 코드를 자세하게 만들 경우, 범용성은 떨어지지만 세밀한 메시지 작성 가능
  
  ##### 📍 해결 방법 
  * 오류 코드를 단순하게 작성하여 범용적으로 사용하다가, 기존 메시지보다 세밀한 메시지가 필요할 경우 메시지를 단계적으로 작성
    * 단계별로 세밀한 정도를 높혀갈 수 있도록 설계
  * 개발 코드를 별도 수정할 필요 없이 메시지가 담겨 있는 `properties` 파일 수정 만으로도 오류 메시지 관리 가능
  * 스프링은 `MessageCodesResolver`를 통해 위의 기능 제공

  ##### 📍 `MessageCodesResolver`
  * `MessageCodesResolver`에서 메시지 코드 생성 (➡️ rejectValue(), reject() : 내부에서 `MessageCodesResolver` 사용)
  * 생성된 순서대로 오류 코드 보관
    ```java
        MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
    ```
   
  * 기본 메시지 생성 규칙
    ##### ✔️ ObjectError
    ```text
       1 : code + "." + object name
       2 : code
    ```

    ##### ✔️ FieldError
    ```text
       1 : code + "." + object name + "." + field
       2 : code + "." + field
       3 : code + "." + field type
       4 : code
    ```   

</details>
