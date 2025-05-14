package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    private String baseSql = "SELECT * FROM test WHERE school_cd = ?";

    // 特定のテストデータ1件を取得
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            String sql = "SELECT * FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, student.getNo());
            ps.setString(2, subject.getCd());
            ps.setString(3, school.getCd());
            ps.setInt(4, no);
            rset = ps.executeQuery();

            if (rset.next()) {
                Test test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rset.getInt("no"));
                test.setPoint(rset.getInt("point"));
                test.setClassNum(rset.getString("class_num"));
                return test;
            }

            return null;
        } finally {
            close(connection, ps, rset);
        }
    }

    // ResultSet → List<Test> に変換
    private List<Test> postFilter(ResultSet rset, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        while (rset.next()) {
            Test test = new Test();

            Student student = new Student();
            student.setNo(rset.getString("student_no"));
            test.setStudent(student);

            Subject subject = new Subject();
            subject.setCd(rset.getString("subject_cd"));
            test.setSubject(subject);

            test.setSchool(school);
            test.setNo(rset.getInt("no"));
            test.setPoint(rset.getInt("point"));
            test.setClassNum(rset.getString("class_num"));

            list.add(test);
        }
        return list;
    }

    // 絞り込み取得（入学年度・クラス・教科・回数・学校）
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            String sql = baseSql + " AND class_num = ? AND subject_cd = ? AND no = ? " +
                         "AND student_no IN (SELECT no FROM student WHERE ent_year = ? AND class_num = ? AND school_cd = ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, classNum);
            ps.setString(2, subject.getCd());
            ps.setInt(3, num);
            ps.setInt(4, entYear);
            ps.setString(5, classNum);
            ps.setString(6, school.getCd());

            rset = ps.executeQuery();
            return postFilter(rset, school);
        } finally {
            close(conn, ps, rset);
        }
    }

    // 複数件のテストデータを保存（トランザクション処理）
    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection();
        try {
        	connection.setAutoCommit(false); // トランザクション開始

            for (Test test : list) {
                if (!save(test, connection)) {
                	connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        } catch (Exception e) {
        	connection.rollback();
            throw e;
        } finally {
        	connection.setAutoCommit(true);
            close(connection, null, null);
        }
    }

    // 1件のテストデータを保存（INSERT or UPDATE）
    private boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement ps = null;
        try {
            // 既存データ確認
            String checkSql = "SELECT COUNT(*) FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
            ps = connection.prepareStatement(checkSql);
            ps.setString(1, test.getStudent().getNo());
            ps.setString(2, test.getSubject().getCd());
            ps.setString(3, test.getSchool().getCd());
            ps.setInt(4, test.getNo());

            ResultSet rset = ps.executeQuery();
            rset.next();
            boolean exists = rset.getInt(1) > 0;
            rset.close();
            ps.close();

            if (exists) {
                // UPDATE
                String updateSql = "UPDATE test SET point = ?, class_num = ? WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
                ps = connection.prepareStatement(updateSql);
                ps.setInt(1, test.getPoint());
                ps.setString(2, test.getClassNum());
                ps.setString(3, test.getStudent().getNo());
                ps.setString(4, test.getSubject().getCd());
                ps.setString(5, test.getSchool().getCd());
                ps.setInt(6, test.getNo());
            } else {
                // INSERT
                String insertSql = "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(insertSql);
                ps.setString(1, test.getStudent().getNo());
                ps.setString(2, test.getSubject().getCd());
                ps.setString(3, test.getSchool().getCd());
                ps.setInt(4, test.getNo());
                ps.setInt(5, test.getPoint());
                ps.setString(6, test.getClassNum());
            }

            return ps.executeUpdate() > 0;
        } finally {
            if (ps != null) ps.close();
        }
    }
}
