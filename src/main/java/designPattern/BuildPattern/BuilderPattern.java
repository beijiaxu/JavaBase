package designPattern.BuildPattern;

import lombok.Data;

/**
 * 建造模式
 * Created by Xu on 2017/3/24.
 */
public class BuilderPattern {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.retrieveResult();
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }
}

@Data
//@lombok.Builder  该模式约等效@Builder
class Product {
    private String part1;
    private String part2;
}

interface Builder {
    void buildPart1();
    void buildPart2();
    Product retrieveResult();
}

class ConcreteBuilder implements Builder {

    private Product product = new Product();

    public void buildPart1() {
        product.setPart1("编号：9527");
    }

    public void buildPart2() {
        product.setPart2("名称：xxx");
    }

    public Product retrieveResult() {
        return product;
    }
}

class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}
