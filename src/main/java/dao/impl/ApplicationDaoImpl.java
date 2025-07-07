package dao.impl;

import dao.ApplicationDao;
import model.Application;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {

    @Override
    public void addApplication(Application app) {
        String sql = "INSERT INTO application (student_id, university_id, first_major_id, second_major_id, third_major_id, allow_adjustment, priority) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, app.getStudentId());
            ps.setInt(2, app.getUniversityId());

            if (app.getFirstMajorId() != null) {
                ps.setInt(3, app.getFirstMajorId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            if (app.getSecondMajorId() != null) {
                ps.setInt(4, app.getSecondMajorId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            if (app.getThirdMajorId() != null) {
                ps.setInt(5, app.getThirdMajorId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setBoolean(6, app.getAllowAdjustment());
            ps.setInt(7, app.getPriority());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) app.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Application> getApplicationsByStudent(int studentId) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM application WHERE student_id = ? ORDER BY priority";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Application app = new Application();
                app.setId(rs.getInt("id"));
                app.setStudentId(rs.getInt("student_id"));
                app.setUniversityId(rs.getInt("university_id"));
                app.setFirstMajorId(rs.getInt("first_major_id"));
                app.setSecondMajorId(rs.getInt("second_major_id"));
                app.setThirdMajorId(rs.getInt("third_major_id"));
                app.setAllowAdjustment(rs.getBoolean("allow_adjustment"));
                app.setPriority(rs.getInt("priority"));
                list.add(app);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Application> getAllApplications() {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM application ORDER BY student_id, priority";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Application app = new Application();
                app.setId(rs.getInt("id"));
                app.setStudentId(rs.getInt("student_id"));
                app.setUniversityId(rs.getInt("university_id"));
                app.setFirstMajorId(rs.getInt("first_major_id"));
                app.setSecondMajorId(rs.getInt("second_major_id"));
                app.setThirdMajorId(rs.getInt("third_major_id"));
                app.setAllowAdjustment(rs.getBoolean("allow_adjustment"));
                app.setPriority(rs.getInt("priority"));
                list.add(app);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}