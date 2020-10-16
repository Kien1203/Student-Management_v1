
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Trung Kien
 */
public class StudentsModify {
    public static List<Students> findAll() {
        List<Students> studentList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            //Open connection to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
            
            //query
            statement = conn.createStatement();
            String sql = "select * from students";
            ResultSet  resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Students std = new Students(resultSet.getString("rollno"), 
                                            resultSet.getString("fullname"),
                                            resultSet.getString("gender"), 
                                            resultSet.getString("email"), 
                                            resultSet.getString("address"));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(conn != null)
                try {
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return studentList;
    }
    
    public static void insert(Students std){
        List<Students> studentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //Open connection to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
            System.out.println("connect successfully insert");
            //query
            String sql = "insert into students(rollno, fullname, gender, email, address) values(?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, std.getRollno());
            statement.setString(2, std.getFullname());
            statement.setString(3, std.getGender());
            
            statement.setString(5, std.getAddress());
            statement.setString(4, std.getEmail());
            
            statement.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(conn != null)
                try {
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    public static void update(Students std){
        List<Students> studentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //Open connection to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
            System.out.println("connect successfully update");
            //query
            String sql = "update students set fullname = ?, gender = ?, email = ?, address = ?" + "where rollno = ?";
            statement = conn.prepareStatement(sql);
            
            
            statement.setString(1, std.getFullname());
            statement.setString(2, std.getGender());
            statement.setString(3, std.getEmail());
            statement.setString(4, std.getAddress());
            statement.setString(5, std.getRollno());
            
            statement.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(conn != null)
                try {
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    public static void save(Students std){
        System.out.println("connect successfully save");
        Students stdFind = find(std.getRollno());
        
        if(stdFind == null)
            insert(std);
        else
            return;
        
        
    }
    
    public static Students find(String rollno){
        Connection conn = null;
        PreparedStatement statement = null;  // PreparedStatement vi co tham so truyen vao
        try {
            //Open connection to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
            System.out.println("connect successfully find");
            //query
            
            String sql = "select * from students where rollno = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, rollno);
            ResultSet  resultSet = statement.executeQuery();
            while(resultSet.next()){
                Students std = new Students(resultSet.getString("rollno"), 
                                            resultSet.getString("fullname"),
                                            resultSet.getString("gender"), 
                                            resultSet.getString("email"), 
                                            resultSet.getString("address"));
                return std;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(conn != null)
                try {
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static void delete(String rollno){
        Connection conn = null;
       PreparedStatement statement = null;  // PreparedStatement vi co tham so truyen vao
        try {
            //Open connection to DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
            
            //query
            
            String sql = "delete from students where rollno = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, rollno);
            statement.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(conn != null)
                try {
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentsModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
