package model;

public class Student {
    private Integer id;
    private String name;
    private String gender;
    private Integer classId;

    public Student() {}

    public Student(Integer id, String name, String gender, Integer classId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.classId = classId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getClassId() { return classId; }
    public void setClassId(Integer classId) { this.classId = classId; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", classId=" + classId +
                '}';
    }
}