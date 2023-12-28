# Spring-MVC2
#### 📣 [인프런] 김영한 님의 「스프링 MVC2편 - 백엔드 웹 개발 활용 기술」 강의 실습 코드입니다.

#### 개발 환경
* `Java 17`
* `SpringBoot v3.2.1`
* `Dependencies` : Spring Web, Thymeleaf, Lombok
* `Test` : JUnit5
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : IntelliJ IDEA

### 🍃 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술

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
  ##### 🌿 타임리프의 리터럴
    * 문자 : `'hello'`
      * 항상 `'` (작은 따옴표)로 감싸야 함
        * BUT, 공백 없이 쭉 이어진다면 작은 따옴표 생략 가능
          ```html
          <li>'hello' + ' world!' = <span th:text="'hello' + ' world!'"></span></li>
          <li>'hello world!' = <span th:text="'hello world!'"></span></li>
          <li>'hello ' + ${data} = <span th:text="'hello ' + ${data}"></span></li>
          <li>리터럴 대체 |hello ${data}| = <span th:text="|hello ${data}|"></span></li>
          </div>
          ```
    * 숫자 : `10`
    * 참, 거짓 : `true`, `false`
    * null : `null`  

* 연산
  * 비교 연산 : HTML 엔티티 사용
    * `>` ➡️ `(gt)`
    * `<` ➡️ `(lt)`
    * `>=` ➡️ `(ge)`
    * `<=` ➡️ `(le)`
    * `!` ➡️ `not`
    * `==` ➡️ `(eq)`
    * `!=` ➡️ `(neq, ne)`
  * 조건식 : 자바 조건식과 유사
  * Elvis 연산자 : 조건식 편의 버전
  * No-Operation : _인 경우, 마치 타임리프가 실행되지 않는 것처럼 동작 (HTML 내용 그대로 출력 ➡️ HTML 내용이 기본값) 
</details>
