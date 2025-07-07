package model;

import java.sql.Timestamp;

public class AdmissionResult {
    private Integer id;
    private Integer studentId;
    private Integer universityId;
    private Integer departmentId;
    private Integer majorId;
    private Timestamp admissionTime;

    public AdmissionResult() {}

    // 可添加带参构造方法

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getUniversityId() { return universityId; }
    public void setUniversityId(Integer universityId) { this.universityId = universityId; }
    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }
    public Integer getMajorId() { return majorId; }
    public void setMajorId(Integer majorId) { this.majorId = majorId; }
    public Timestamp getAdmissionTime() { return admissionTime; }
    public void setAdmissionTime(Timestamp admissionTime) { this.admissionTime = admissionTime; }

    @Override
    public String toString() {
        return "AdmissionResult{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", universityId=" + universityId +
                ", departmentId=" + departmentId +
                ", majorId=" + majorId +
                ", admissionTime=" + admissionTime +
                '}';
    }
}