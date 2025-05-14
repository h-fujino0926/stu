package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao{

	public ClassNum get(String classnum, School school
			) throws Exception {
		 Connection connection = getConnection();
	        PreparedStatement ps = null;
	        ResultSet rset = null;
	        try {
	            ps = connection.prepareStatement("SELECT * FROM classnum WHERE classnum = ? AND school_id = ?");
	            ps.setString(1, classnum);
	            ps.setString(2, school.getCd());
	            rset = ps.executeQuery();
	            if (rset.next()) {
	                ClassNum classNum = new ClassNum();
	                classNum.setClass_num(rset.getString("classnum"));
	                classNum.setSchool(school);
	                return classNum;
	            }
	            return null;
	        } finally {
	            close(connection, ps, rset);
        }


	}

	public List<String> filter(School school) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rset = null;
        List<String> classNums = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT classnum FROM classnum WHERE school_cd = ?");
            ps.setString(1, school.getCd());  // ← 修正
            rset = ps.executeQuery();
            while (rset.next()) {
                classNums.add(rset.getString("classnum"));
            }
            return classNums;
        } finally {
            close(connection, ps, rset);
        }
    }


	public boolean save(ClassNum classNum) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO classnum (classnum, school_cd) VALUES (?, ?)");
            ps.setString(1, classNum.getClass_num());
            ps.setString(2, classNum.getSchool().getCd());  // ← 修正
            return ps.executeUpdate() > 0;
        } finally {
            close(connection, ps, null);
        }
    }



	public boolean save(ClassNum classNum, String newClassNum) throws Exception {
		Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE classnum SET classnum = ? WHERE classnum = ? AND school_cd = ?");
            ps.setString(1, newClassNum);
            ps.setString(2, classNum.getClass_num());
            ps.setString(3, classNum.getSchool().getCd());  // ← 修正
            return ps.executeUpdate() > 0;
        } finally {
            close(connection, ps, null);
        }
    }

}

