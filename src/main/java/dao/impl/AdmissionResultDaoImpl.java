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
    public void addAdmissionResult(AdmissionResult r) {
        String sql = "INSERT INTO admission_result (student_id, university_id, department_id, major_id, admission_time) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.getStudentId());
            ps.setInt(2, r.getUniversityId());
            ps.setInt(3, r.getDepartmentId());
            ps.setInt(4, r.getMajorId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) r.setId(rs.getInt(1));
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