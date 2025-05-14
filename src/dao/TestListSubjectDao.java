package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private final String baseSql =
		"SELECT s.ent_year, s.no AS student_no, s.name AS student_name, " +
		"       s.class_num, t.no AS test_no, t.point " +
		"FROM student s " +
		"JOIN test t ON s.no = t.student_no " +
		"WHERE s.ent_year = ? " +
		"  AND s.class_num = ? " +
		"  AND t.subject_cd = ? " +
		"  AND t.school_cd = ? " +
		"ORDER BY s.no, t.no";

	private List<TestListSubject> postFilter(ResultSet rset) throws Exception {
		List<TestListSubject> list = new ArrayList<>();
		TestListSubject current = null;
		String lastStudentNo = null;

		while (rset.next()) {
			String studentNo = rset.getString("student_no");

			if (!studentNo.equals(lastStudentNo)) {
				current = new TestListSubject();
				current.setEntYear(rset.getInt("ent_year"));
				current.setStudentNo(studentNo);
				current.setStudentName(rset.getString("student_name"));
				current.setClassNum(rset.getString("class_num"));
				current.setPoints(new LinkedHashMap<>());

				list.add(current);
				lastStudentNo = studentNo;
			}

			int testNo = rset.getInt("test_no");
			int point = rset.getInt("point");
			current.getPoints().put(testNo, point);
		}

		return list;
	}

	public List<TestListSubject> filter(
			int entYear, String classNum, Subject subject, School school
	) throws Exception {

		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<TestListSubject> resultList;

		try {
			Connection connection = getConnection();
			stmt = connection.prepareStatement(baseSql);
			stmt.setInt(1, entYear);
			stmt.setString(2, classNum);
			stmt.setString(3, subject.getCd()); // Subject クラスに getCd() が必要
			stmt.setString(4, school.getCd());  // School クラスに getCd() が必要

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
