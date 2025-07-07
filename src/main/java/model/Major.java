package model;

public class Major {
    private Integer id;
    private String name;
    private Integer departmentId;

    public Major() {}

    public Major(Integer id, String name, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}