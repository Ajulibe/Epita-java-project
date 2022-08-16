package fr.epita.project.datamodel;

import java.util.Arrays;

public class Student {
    private String name;
    private Integer id;

    public Student(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", id='" + id + '\'' + '}';
    }
}
