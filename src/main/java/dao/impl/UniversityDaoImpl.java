package dao.impl;

import dao.UniversityDao;
import model.University;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UniversityDaoImpl implements UniversityDao {
    @Override
    public void addUniversity(University u) {
        String sql = "INSERT INTO university (name, location) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getLocation());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) u.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public University getUniversityById(int id) {
        String sql = "SELECT id, name, location FROM university WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new University(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<University> getAllUniversities() {
        List<University> list = new ArrayList<>();
        String sql = "SELECT id, name, location FROM university";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                University u = new University(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
                list.add(u);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}