package model;

public class Application {
    private Integer id;
    private Integer studentId;
    private Integer universityId;
    private Integer firstMajorId;
    private Integer secondMajorId;
    private Integer thirdMajorId;
    private Boolean allowAdjustment;
    private Integer priority;

    public Application() {}

    // 可根据需要添加带参构造器

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getUniversityId() { return universityId; }
    public void setUniversityId(Integer universityId) { this.universityId = universityId; }
    public Integer getFirstMajorId() { return firstMajorId; }
    public void setFirstMajorId(Integer firstMajorId) { this.firstMajorId = firstMajorId; }
    public Integer getSecondMajorId() { return secondMajorId; }
    public void setSecondMajorId(Integer secondMajorId) { this.secondMajorId = secondMajorId; }
    public Integer getThirdMajorId() { return thirdMajorId; }
    public void setThirdMajorId(Integer thirdMajorId) { this.thirdMajorId = thirdMajorId; }
    public Boolean getAllowAdjustment() { return allowAdjustment; }
    public void setAllowAdjustment(Boolean allowAdjustment) { this.allowAdjustment = allowAdjustment; }
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", universityId=" + universityId +
                ", firstMajorId=" + firstMajorId +
                ", secondMajorId=" + secondMajorId +
                ", thirdMajorId=" + thirdMajorId +
                ", allowAdjustment=" + allowAdjustment +
                ", priority=" + priority +
                '}';
    }
}