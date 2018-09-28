package com.chj.springbootdemo.domain;

import lombok.Builder;
import lombok.Data;

//
//@Setter
//@Getter
//@RequiredArgsConstructor(staticName = "of")
@Data
@Builder
public class Foo {

    private final String name;
//    @Setter(AccessLevel.PACKAGE)@Getter(AccessLevel.MODULE) private Integer age;
//    private boolean isChinese;

//    private Foo(){}

    public static void main(String[] args) {
        Foo foo = Foo.builder().build();
        System.out.println(foo);
    }
}
