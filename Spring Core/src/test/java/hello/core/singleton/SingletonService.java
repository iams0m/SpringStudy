package hello.core.singleton;

public class SingletonService {
    // 자기 자신을 인스턴스 객체로 생성
    private static final SingletonService instance = new SingletonService();
    // 조회할 때 사용
    public static SingletonService getInstance() {
        return instance;
    }
    // private로 외부 생성을 막아줌
    private SingletonService() {
    }
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
