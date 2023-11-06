# 스프링 완전 정복 시리즈

#### 📣 [인프런] 김영한 님의 「스프링 완전 정복 시리즈」 강의 실습 코드입니다.

#### 개발 환경
* `Java 11`
* `SpringBoot v2.7.14`
* `Dependencies` : Spring Web, Thymeleaf, JPA
* `Test` : JUnit5
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : Gradle

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
   * `HTTP 메시지` ⭐⭐⭐
   * `단순함, 확장 가능`

##### `Section 4) HTTP 메서드`
  
  </details>
  
