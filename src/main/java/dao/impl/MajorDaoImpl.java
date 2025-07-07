package dao.impl;

import dao.MajorDao;
import model.Department;
import model.Major;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MajorDaoImpl implements MajorDao {
    @Override
    public void addMajor(Major m) {
        String sql = "INSERT INTO major (department_id, name) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getDepartmentId());
            ps.setString(2, m.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) m.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Major> getMajorsByDepartment(int departmentId) {
        List<Major> list = new ArrayList<>();
        String sql = "SELECT id, name FROM major WHERE department_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Major m = new Major(rs.getInt("id"), rs.getString("name"), new Department(departmentId, null, null).getId());
                list.add(m);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Major getMajorById(int id) {
        String sql = "SELECT id, name, department_id FROM major WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Department d = new Department(rs.getInt("department_id"), null, null);
                return new Major(rs.getInt("id"), rs.getString("name"), d.getId());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}