package fr.epita.project.datamodel;

public class Admin {
    private String name;
    private Integer id;
    private String password;

    public Admin(String name, Integer id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "Student{" + "name='" + name + '\'' + ", id='" + id + '\'' + "password='" + password + '\'' + '}';
    }
}
