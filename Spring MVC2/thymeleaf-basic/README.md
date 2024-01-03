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

      ##### 방법 2️⃣ 타임리프 적용하기
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
      * `${#ids.prev('...')}` : `each`문 내에 설정된 경우 `index`마다 동적으로 중복 없이 id 생성

      ```html
      <div th:each="region : ${regions}" class="form-check form-check-inline">
        <input type="checkbox" th:field="*{regions}" th:value="${region.key}" class="form-check-input">
        <label th:for="${#ids.prev('regions')}" th:text="${region.value}" class="form-check-label"></label>
      </div>
      ```
      
  * `radio button`
  * `select box`
  
  ##### ✔️ 스프링의 메시지, 국제화 기능의 편리한 통합
  
  ##### ✔️ 스프링의 검증, 오류 처리 통합
  
  ##### ✔️ 스프링의 변환 서비스 통합(ConversionService)

</details>
