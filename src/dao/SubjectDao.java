package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{

	public Subject get(String cd, School school) throws Exception{

		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            String sql = "SELECT * FROM subject WHERE cd = ? AND school_cd = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cd);
            ps.setString(2, school.getCd());

            rset = ps.executeQuery();
            if (rset.next()) {
                Subject subject = new Subject();
                subject.setCd(rset.getString("cd"));
                subject.setName(rset.getString("name"));
                subject.setSchool(school);
                return subject;
            }

            return null;
        } finally {
            close(connection, ps, rset);
        }

	}

	public List<Subject> filter(School school) throws Exception{

		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;
        List<Subject> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd";
            ps = connection.prepareStatement(sql);
            ps.setString(1, school.getCd());
            rset = ps.executeQuery();

            while (rset.next()) {
                Subject subject = new Subject();
                subject.setCd(rset.getString("cd"));
                subject.setName(rset.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }

            return list;
        } finally {
            close(connection, ps, rset);
        }

	}

	public boolean save(Subject subject) throws Exception{
		Connection connection = getConnection();
        PreparedStatement ps = null;

        try {
            // すでに存在するか確認
            Subject existing = get(subject.getCd(), subject.getSchool());

            if (existing == null) {
                // 新規INSERT
                String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, subject.getCd());
                ps.setString(2, subject.getName());
                ps.setString(3, subject.getSchool().getCd());
            } else {
                // UPDATE
                String sql = "UPDATE subject SET name = ? WHERE cd = ? AND school_cd = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, subject.getName());
                ps.setString(2, subject.getCd());
                ps.setString(3, subject.getSchool().getCd());
            }

            return ps.executeUpdate() > 0;
        } finally {
            close(connection, ps, null);
        }

	}

	public boolean delete(Subject subject) throws Exception{

		Connection connection = getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getSchool().getCd());

            return ps.executeUpdate() > 0;
        } finally {
            close(connection, ps, null);
        }

	}

}
