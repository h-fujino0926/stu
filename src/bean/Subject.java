package bean;

import java.io.Serializable;

public class Subject implements Serializable{

//
	private String cd;
//	教科名
	private String name;
//	所属校
	private School school;

	public String getCd(){
		return cd;
	}

	public void setCd(String cd){
		this.cd=cd;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public School getSchool(){
		return school;
	}

	public void setSchool(School school){
		this.school=school;
	}

}
