package hello.hellospring.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
    // 1. 주문 생성 및 최종 주문 반환 역할
}
