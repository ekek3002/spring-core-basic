package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    //자동으로 getter, setter를 자동으로 만들어 줌
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hello");

//        String name = helloLombok.getName();
//        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
