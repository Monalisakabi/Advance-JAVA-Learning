package org.Mona;

import java.sql.*;
import java.util.Scanner;

public class EmployeeMenu {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/student";
        String password = "root";
        String user = "root";

        // Step-1-Load and Register DRIVER class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step-2-Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);

            Scanner sc = new Scanner(System.in);
            int choice;

            do {

                System.out.println("\n==============================");
                System.out.println("  EMPLOYEE MANAGEMENT SYSTEM");
                System.out.println("==============================");

                System.out.println("1. Register Employee");
                System.out.println("2. Get Name By ID");
                System.out.println("3. Get All Employees");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Search By Role");
                System.out.println("7. Group By Department");
                System.out.println("8. Having Clause");
                System.out.println("9. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        // REGISTER EMPLOYEE
                        PreparedStatement pr = con.prepareStatement(
                                "insert into employee_data " +
                                        "(emp_id, emp_name, emp_salary, emp_role, " +
                                        "emp_designation, emp_department, emp_email, emp_city) " +
                                        "values (?,?,?,?,?,?,?,?)"
                        );

                        System.out.println("Enter Employee ID:");
                        pr.setInt(1, sc.nextInt());

                        sc.nextLine();

                        System.out.println("Enter Name:");
                        pr.setString(2, sc.nextLine());

                        System.out.println("Enter Salary:");
                        pr.setDouble(3, sc.nextDouble());

                        sc.nextLine();

                        System.out.println("Enter Role:");
                        pr.setString(4, sc.nextLine());

                        System.out.println("Enter Designation:");
                        pr.setString(5, sc.nextLine());

                        System.out.println("Enter Department:");
                        pr.setString(6, sc.nextLine());

                        System.out.println("Enter Email:");
                        pr.setString(7, sc.nextLine());

                        System.out.println("Enter City:");
                        pr.setString(8, sc.nextLine());

                        int rowsaffect = pr.executeUpdate();

                        System.out.println(rowsaffect);

                        if (rowsaffect > 0) {
                            System.out.println("Data inserted");
                        }

                        break;


                    case 2:
                        // GET NAME BY ID
                        PreparedStatement pr2 = con.prepareStatement(
                                "select emp_name from employee_data where emp_id=?"
                        );

                        System.out.println("Enter Employee ID:");

                        pr2.setInt(1, sc.nextInt());

                        ResultSet rs = pr2.executeQuery();

                        if (rs.next()) {
                            System.out.println(
                                    "Employee Name: "
                                            + rs.getString("emp_name")
                            );
                        } else {
                            System.out.println("Employee Not Found");
                        }

                        break;


                    case 3:
                        // GET ALL EMPLOYEES
                        Statement st = con.createStatement();

                        ResultSet rs2 = st.executeQuery(
                                "select * from employee_data"
                        );

                        while (rs2.next()) {

                            System.out.println(
                                    "ID: " + rs2.getInt("emp_id") +
                                            " | Name: " + rs2.getString("emp_name") +
                                            " | Salary: " + rs2.getDouble("emp_salary") +
                                            " | Role: " + rs2.getString("emp_role") +
                                            " | Designation: " + rs2.getString("emp_designation") +
                                            " | Department: " + rs2.getString("emp_department") +
                                            " | Email: " + rs2.getString("emp_email") +
                                            " | City: " + rs2.getString("emp_city")
                            );
                        }

                        break;


                    case 4:
                        // UPDATE EMPLOYEE
                        PreparedStatement pr3 = con.prepareStatement(
                                "update employee_data set " +
                                        "emp_salary=?, emp_role=?, " +
                                        "emp_designation=?, emp_department=? " +
                                        "where emp_id=?"
                        );

                        System.out.println("Enter Employee ID:");
                        int id = sc.nextInt();

                        System.out.println("Enter New Salary:");
                        pr3.setDouble(1, sc.nextDouble());

                        sc.nextLine();

                        System.out.println("Enter New Role:");
                        pr3.setString(2, sc.nextLine());

                        System.out.println("Enter New Designation:");
                        pr3.setString(3, sc.nextLine());

                        System.out.println("Enter New Department:");
                        pr3.setString(4, sc.nextLine());

                        pr3.setInt(5, id);

                        int update = pr3.executeUpdate();

                        if (update > 0) {
                            System.out.println("Employee Updated Successfully");
                        } else {
                            System.out.println("Employee Not Found");
                        }

                        break;


                    case 5:
                        // DELETE EMPLOYEE
                        PreparedStatement pr4 = con.prepareStatement(
                                "delete from employee_data where emp_id=?"
                        );

                        System.out.println("Enter Employee ID:");

                        pr4.setInt(1, sc.nextInt());

                        int delete = pr4.executeUpdate();

                        if (delete > 0) {
                            System.out.println("Employee Deleted Successfully");
                        } else {
                            System.out.println("Employee Not Found");
                        }

                        break;


                    case 6:
                        // WHERE CLAUSE - SEARCH BY ROLE
                        PreparedStatement pr5 = con.prepareStatement(
                                "select * from employee_data where emp_role=?"
                        );

                        sc.nextLine();

                        System.out.println("Enter Role:");

                        pr5.setString(1, sc.nextLine());

                        ResultSet rs3 = pr5.executeQuery();

                        while (rs3.next()) {

                            System.out.println(
                                    "ID: " + rs3.getInt("emp_id") +
                                            " | Name: " + rs3.getString("emp_name") +
                                            " | Salary: " + rs3.getDouble("emp_salary") +
                                            " | Role: " + rs3.getString("emp_role") +
                                            " | Department: " +
                                            rs3.getString("emp_department")
                            );
                        }

                        break;


                    case 7:
                        // GROUP BY CLAUSE
                        Statement st2 = con.createStatement();

                        ResultSet rs4 = st2.executeQuery(
                                "select emp_department, count(*) as total " +
                                        "from employee_data " +
                                        "group by emp_department"
                        );

                        while (rs4.next()) {

                            System.out.println(
                                    "Department: " +
                                            rs4.getString("emp_department") +
                                            " | Total Employees: " +
                                            rs4.getInt("total")
                            );
                        }

                        break;


                    case 8:
                        // HAVING CLAUSE
                        Statement st3 = con.createStatement();

                        ResultSet rs5 = st3.executeQuery(
                                "select emp_department, count(*) as total " +
                                        "from employee_data " +
                                        "group by emp_department " +
                                        "having count(*) > 1"
                        );

                        while (rs5.next()) {

                            System.out.println(
                                    "Department: " +
                                            rs5.getString("emp_department") +
                                            " | Total Employees: " +
                                            rs5.getInt("total")
                            );
                        }

                        break;

                    case 9:

                        System.out.println("Exiting...");
                        break;

                    default:

                        System.out.println("Invalid Choice");

                }

            } while (choice != 9);

            con.close();

        } catch (ClassNotFoundException | SQLException e) {

            throw new RuntimeException(e);
        }

        System.out.println("SUCCESSFULLY");
    }
}
