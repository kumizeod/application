package dao.impl;

import dao.AdmissionResultDao;
import model.AdmissionResult;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdmissionResultDaoImpl implements AdmissionResultDao {

    @Override
    public void addAdmissionResult(AdmissionResult admissionResult) {
        String sql = "INSERT INTO admission_result (student_id, university_id, major_id) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, admissionResult.getStudentId());

            if (admissionResult.getUniversityId() != null) {
                ps.setInt(2, admissionResult.getUniversityId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (admissionResult.getMajorId() != null) {
                ps.setInt(3, admissionResult.getMajorId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) admissionResult.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AdmissionResult> getResultsByUniversity(int universityId) {
        List<AdmissionResult> list = new ArrayList<>();
        String sql = "SELECT * FROM admission_result WHERE university_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, universityId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                AdmissionResult r = new AdmissionResult();
                r.setId(rs.getInt("id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setUniversityId(rs.getInt("university_id"));
                r.setDepartmentId(rs.getInt("department_id"));
                r.setMajorId(rs.getInt("major_id"));
                r.setAdmissionTime(rs.getTimestamp("admission_time"));
                list.add(r);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public AdmissionResult getResultByStudent(int studentId) {
        String sql = "SELECT * FROM admission_result WHERE student_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                AdmissionResult r = new AdmissionResult();
                r.setId(rs.getInt("id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setUniversityId(rs.getInt("university_id"));
                r.setDepartmentId(rs.getInt("department_id"));
                r.setMajorId(rs.getInt("major_id"));
                r.setAdmissionTime(rs.getTimestamp("admission_time"));
                return r;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}