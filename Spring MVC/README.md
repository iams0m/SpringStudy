# Spring-MVC
#### 📣 [인프런] 김영한 님의 「스프링 MVC」 강의 실습 코드입니다.

#### 개발 환경
* `Java 11` ➡️ `Java 17`
* `SpringBoot v2.7.17` ➡️ `SpringBoot v3.2.0`
* `Dependencies` : Spring Web, Thymeleaf, Lombok
* `Test` : JUnit5
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : Gradle ➡️ IntelliJ IDEA (자바 직접 실행으로 실행 속도 향상)

### 🍃 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술

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
* 속성을 배열로 제공 ➡️ 다중 설정 가능 (`{"/hello-basic", "hello-go"}`)
* 클래스 레벨에 매핑 정보를 두면, 메서드 레벨에서 해당 정보를 조합하여 사용


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
</details>

