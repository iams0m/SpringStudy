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

#### 웹 애플리케이션에 메시지 및 국제화 적용하기
  ##### 📍 메시지 적용  
  * 타임리프 메시지 표현식 : `#{...}`
    * `...` 에 메시지 코드를 넣어주면, 렌더링 할 때 메시지로 변환
    * 파라미터를 사용할 경우, 괄호 안에 값을 넣어 사용
      * `hello.name=안녕 {0}` 
      ```html
         <p th:text="#{hello.name(${item.itemName})}"></p>
      ```

  ##### 📍 국제화 적용
  * 이미 메시지를 사용하도록 타임리프를 적용했다면, 국제 메시지 파일 생성시 바로 국제화 적용
  * 웹 브라우저의 언어 설정 값을 변경하여 국제화 적용 확인 가능
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
    ```java
       public FieldError(String objectName, String field, String defaultMessage) {}
    ```
      * 필드에 오류가 있을 때, `FieldError` 객체를 생성하여 `bindingResult`에 담아둠
        * `objectName` : `@ModelAttribute` 이름
        * `field` : 오류 발생 필드 이름
        * `defaultMessage` : 오류 기본 메시지

  * `ObjectError`
    ```java
       public ObjectError(String objectName, String defaultMessage) {}
    ```
      * 특정 필드를 넘어서는 오류가 있을 때, `ObjectError` 객체를 생성하여 `bindingResult`에 담아둠
        * `objectName` : `@ModelAttribute` 이름
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

</details>
