package com.ben.JDBC;

/* JDBC assignment due oct 3--Ben Huggins
 */

import java.sql.*;
import java.util.*;
public class JDBCProject {

    public static void main(String[] args) throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "root";
        // Drivers to establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Successfully connected!");

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        Statement st = con.createStatement();
        String query = "";
        String userFirst = "";
        String userLast = "";
        int randEmpNum = rand.nextInt(10000);

        System.out.println("Enter the first name: ");
        userFirst = scan.nextLine();
        System.out.println("Enter the last name: ");
        userLast = scan.nextLine();
        System.out.println("Employee added:  " + randEmpNum);

        // Add employee to db
        query = "INSERT INTO employees "+
                "VALUES (" + randEmpNum + ", \'" + userLast + "\', \'" + userFirst + "\', " +
                "'45678', 'bgates@microsoft.com', 1, x4611, 'Software God')";
        st.executeUpdate(query);

        System.out.println("Showing delete function. \"Bob Bobberson\" with employeenumber 5");
        
        //Adding example for deletion query
        query = "INSERT INTO employees " +
                "VALUES (5, 'Bob' , 'Bobberson' , 'x4611', 'bob@bobby.bob', 1, 1002, 'Bobbing Manager')";
        st.executeUpdate(query);
        query = "SELECT employeeNumber, lastName, firstName FROM employees WHERE employeeNumber = 5";
        ResultSet result = st.executeQuery(query); // Execute query

        while(result.next()) {
            int employeeNumber = result.getInt("employeeNumber");
            String lastName = result.getString("lastName");
            String firstName = result.getString("firstName");

            System.out.printf("%d | %s | %s\n",employeeNumber, lastName, firstName);
        }

        // Deleting records
        query = "DELETE FROM employees WHERE employeeNumber = 2727";
        st.executeUpdate(query);

        System.out.printf("Search for employee %s %s by their Employee Number: %d\n", userFirst, userLast, randEmpNum);
        query = "SELECT employeeNumber, lastName, firstName FROM employees WHERE employeeNumber = " + randEmpNum;
        result = st.executeQuery(query);
        // display results
        while(result.next()) {
            int employeeNumber = result.getInt("employeeNumber");
            String lastName = result.getString("lastName");
            String firstName = result.getString("firstName");
            System.out.printf("%d | %s | %s\n",employeeNumber, lastName, firstName);
        }
        query = "SELECT lastName, firstName FROM employees";
        result = st.executeQuery(query);
        while(result.next()) {
            String lastName = result.getString("lastName");
            String firstName = result.getString("firstName");
            System.out.printf("%s | %s\n", lastName, firstName);
        }
        //End
        st.close();
        con.close();
        System.out.println("Byebye!");
    }
}
