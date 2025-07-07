package model;

public class ClassInfo {
    private Integer id;
    private String name;

    public ClassInfo() {}

    public ClassInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}