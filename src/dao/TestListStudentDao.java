package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

	private String baseSql;

	private List<TestListStudent> postFilter(ResultSet rset) throws Exception {

		List<TestListStudent> list = new ArrayList<>();

		while (rset.next()) {
			TestListStudent tls = new TestListStudent();
			tls.setSubjectName(rset.getString("subject_name"));
			tls.setSubjectCd(rset.getString("subject_cd"));
			tls.setNum(rset.getInt("num"));
			tls.setPoint(rset.getInt("point"));
			list.add(tls);
		}

		return list;

	}

	public List<TestListStudent> filter(Student student) throws Exception {

		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<TestListStudent> resultList = null;

		try {
			Connection con = getConnection();
			stmt = con.prepareStatement(baseSql);
			stmt.setString(1, student.getNo()); // 学生番号をセット
			rset = stmt.executeQuery();

			resultList = postFilter(rset);
		} catch (Exception e) {
			throw e;
		} finally {
			if (rset != null) rset.close();
			if (stmt != null) stmt.close();
		}

		return resultList;
	}

}

