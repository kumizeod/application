package dao;

import model.AdmissionResult;

import java.util.List;

public interface AdmissionResultDao {
    void addAdmissionResult(AdmissionResult r);
    List<AdmissionResult> getResultsByUniversity(int universityId);
    AdmissionResult getResultByStudent(int studentId);
}