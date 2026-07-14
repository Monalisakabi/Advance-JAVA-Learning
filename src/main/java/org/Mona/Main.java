package org.Mona;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student";
        String password = "root";
        String user = "root";

        //Step -1 -- Load and Register DRIVER class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//-Step 1 dONE
            Connection con=DriverManager.getConnection(url,user,password);//Step 2 Done
            PreparedStatement pr=con.prepareStatement("insert into student_data values (?,?,?)");
            System.out.println("Enter id :\n name :\n Domain :");
            Scanner sc=new Scanner(System.in);

            pr.setInt(1,sc.nextInt());
            sc.nextLine();
            pr.setString(2,sc.nextLine());
            pr.setString(3,sc.nextLine());

            int rowsaffect =pr.executeUpdate();//4th step(execute query)
            System.out.println(rowsaffect);
            if(rowsaffect>0){
                System.out.println("Data inserted");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("SUCCESSFULLY");
    }
}