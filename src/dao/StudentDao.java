package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao{

	private String baseSql;

	public Student get(String no) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM student WHERE no = ?");
            ps.setString(1, no);
            rset = ps.executeQuery();

            if (rset.next()) {
                Student student = new Student();
                student.setNo(rset.getString("no"));
                student.setName(rset.getString("name"));
                student.setEntYear(rset.getInt("ent_year"));
                student.setclassNum(rset.getString("class_num"));
                student.setAttend(rset.getBoolean("is_attend"));

                // 学校は外から渡されていないので省略、必要なら別途取得
                return student;
            }

            return null;
        } finally {
            close(connection, ps, rset);
        }

	}

	private List<Student> postFilter(ResultSet rset, School school) throws Exception {
		List<Student> list = new ArrayList<>();
        while (rset.next()) {
            Student student = new Student();
            student.setNo(rset.getString("no"));
            student.setName(rset.getString("name"));
            student.setEntYear(rset.getInt("ent_year"));
            student.setclassNum(rset.getString("class_num"));
            student.setAttend(rset.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }
        return list;

	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            String sql = baseSql + " AND ent_year = ? AND class_num = ? AND is_attend = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setBoolean(4, isAttend);
            rset = ps.executeQuery();

            return postFilter(rset, school);
        } finally {
            close(connection, ps, rset);
        }

	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            String sql = baseSql + " AND ent_year = ? AND is_attend = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setBoolean(3, isAttend);
            rset = ps.executeQuery();

            return postFilter(rset, school);
        } finally {
            close(connection, ps, rset);
        }
	}

	public List<Student> filter(School school, boolean isAttend) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            String sql = baseSql + " AND is_attend = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, school.getCd());
            ps.setBoolean(2, isAttend);
            rset = ps.executeQuery();

            return postFilter(rset, school);
        } finally {
            close(connection, ps, rset);
        }

	}

	public boolean save(Student student) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getclassNum());
            ps.setBoolean(5, student.isAttend());
            ps.setString(6, student.school().getCd());

            return ps.executeUpdate() > 0;
        } finally {
            close(connection, ps, null);
        }
    }

}
