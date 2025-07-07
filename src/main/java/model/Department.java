package model;

public class Department {
    private Integer id;
    private String name;
    private Integer universityId;

    public Department() {}

    public Department(Integer id, String name, Integer universityId) {
        this.id = id;
        this.name = name;
        this.universityId = universityId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getUniversityId() { return universityId; }
    public void setUniversityId(Integer universityId) { this.universityId = universityId; }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", universityId=" + universityId +
                '}';
    }
}