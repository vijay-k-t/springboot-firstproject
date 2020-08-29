package com.mine.firstproject.springbootfirstproject;

public class Test {

    public Test() {
        super();
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestDO [id=" + id + ", name=" + name + "]";
    }

    

}