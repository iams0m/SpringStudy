# 스프링 완전 정복 시리즈

#### 📣 [인프런] 김영한 님의 「스프링 완전 정복 시리즈」 강의 실습 코드입니다.

#### 개발 환경
* `Java 11` ➡️ `Java 17`
* `SpringBoot v2.7.17` ➡️ `SpringBoot v3.2.0`
* `Dependencies` : Spring Web, Thymeleaf, JPA, Lombok
* `Test` : JUnit5
* `IDE` : IntelliJ IDEA Ultimate 2023.2
* `Build` : Gradle ➡️ IntelliJ IDEA (자바 직접 실행으로 실행 속도 향상)

#### 01. 🍃 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
  #### 📑 [정리](https://github.com/iams0m/SpringStudy/tree/main/Spring%20Introduction)

#### 02. 🍃 스프링 핵심 원리 - 기본편
  #### 📑 [정리](https://github.com/iams0m/SpringStudy/tree/main/Spring%20Core)

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
  #### 📑 [정리](https://github.com/iams0m/SpringStudy/tree/main/Spring%20MVC)

#### 05. 🍃 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술
  #### 📑 [정리](https://github.com/iams0m/SpringStudy/tree/main/Spring%20MVC2)
