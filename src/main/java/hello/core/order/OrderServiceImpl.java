package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자 주입 1. 생성자가 1개면 @Autowired 생략 가능 생성자는 스프링라이프 사이클 빈 등록 시 자동주입 됨
    //@Autowired 매칭 1. 타입 매칭 2. 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭
    //@Qualifier 매칭 1. 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.
    //@Primary 매칭 1. 우선순위를 정하는 방법이다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository1 = " + memberRepository);
//        System.out.println("discountPolicy1 = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    //수정자 주입 1. 수정자는 의존관계  주입 2번째 관계에서 주입됨 2. 주입할 대상이 없어도 동작하게 하려면 (required = false) 사용 기본 true
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
////        System.out.println("discountPolicy2 = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
////        System.out.println("memberRepository2 = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

//    //필드 주입 1. 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적 단점이 있다 2. Test코드 사용 권장 3.스프링 설정 목적  @Configuration 같은 곳에서만 특별한 용도로 사용
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
//
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    //알반 메서드 주입 1. 일반 메서드를 통해서 주입 받을 수 있다 2. 스프링 빈이어야 동작한다 2. 일반적으로 사용 X
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    //lombok @RequiredArgsConstructor final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
