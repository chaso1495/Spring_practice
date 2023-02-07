package hello.hellospring.singleton;

public class SingletonService { // 싱글톤 패턴 적용법

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    } // 오직 getInstance()를 통해 항상 같은 인스턴스를 반환한다.

    private SingletonService() { // 클래스 외부에서 new를 통한 임의의 생성을 방어하기 위한 private 생성자이다.
    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
