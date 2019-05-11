package com.xust.service.search;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 11:03 2019/5/10
 * @modified By:
 */
public class HouseSuggest {

    private String input;
    private int weight = 10;  // 默认权重

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
