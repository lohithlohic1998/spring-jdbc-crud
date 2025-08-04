package com.example.SpringCrudOperation.SpringDemo.repository;

import com.example.SpringCrudOperation.SpringDemo.entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12793545";
    private final String username = "sql12793545";
    private final String password = "xugRzIVwdc";

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

    public List<Student> findAll()
    {
        List<Student> students = new ArrayList<>();

        try(Connection con = getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM student");

            while(rs.next())
            {
                students.add(new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student findbyId(int id)
    {
        Student student = null;
        try(Connection conn = getConnection())
        {
            PreparedStatement statement = conn.prepareStatement("select * from student where id = ?");
            statement.setInt(1,id);

            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                student = new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public void savedetails(Student student) {

        try(Connection con = getConnection())
        {
            PreparedStatement st = con.prepareStatement("INSERT INTO student(id,name,email) values(?,?,?)");
            st.setInt(1,student.getId());
            st.setString(2,student.getName());
            st.setString(3, student.getEmail());
            st.executeUpdate();
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public void updateDetails(Student student) {

        try(Connection conn = getConnection())
        {
            PreparedStatement st = conn.prepareStatement("UPDATE student SET name = ?, email = ? where id = ?");

            st.setString(1, student.getName());
            st.setString(2, student.getEmail());
            st.setInt(3,student.getId());
            st.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {

        try {
            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM student where id = ?");
            st.setInt(1,id);
            st.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}