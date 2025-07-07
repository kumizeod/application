package dao.impl;

import dao.StudentInterestCourseDao;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentInterestCourseDaoImpl implements StudentInterestCourseDao {
    @Override
    public void addInterestCourse(int studentId, String courseName) {
        String sql = "INSERT INTO student_interest_course (student_id, course_name) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setString(2, courseName);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getInterestCourses(int studentId) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT course_name FROM student_interest_course WHERE student_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(rs.getString("course_name"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}