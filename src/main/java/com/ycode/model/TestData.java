package com.ycode.model;

public class TestData
{
	private String name;
	private String age;
	private String sex;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	@Override
	public String toString()
	{
		return "TestData [name=" + name + ", age=" + age + ", sex=" + sex + ", getName()=" + getName() + ", getAge()="
				+ getAge() + ", getSex()=" + getSex() + "]";
	}

}
