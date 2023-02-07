package hello.hellospring.singleton;

public class StatefulService { // 싱글톤 패턴이 상태를 유지할 경우 벌어지는 일

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
        // return price; 위 줄을 지우고 이 줄로 대체한 뒤 int형으로 반환값을 수정하면 상태가 유지되는 문제가 해결된다.
    }

    public int getPrice() {
        return price;
    }
}
