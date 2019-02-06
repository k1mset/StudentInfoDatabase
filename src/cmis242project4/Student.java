/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmis242project4;

/**
 * File: Student.java
 * Date: 4/30/2018
 * Author: Dillan Cobb
 * Purpose: To define the student constructor and preform the calculations
 * and updates to the students GPA.
 */

public class Student {
    // Variables for the student class
    private String studentName, studentMajor;
    private double studentGpa, totalQualityPoints; 
    private int totalCreditComplete;

    // Default constructor of the Student class
    public Student(String name, String major) {
        this.studentName = name;
        this.studentMajor = major;
        this.totalCreditComplete = 0;
        this.totalQualityPoints = 0.0;
        this.studentGpa = 4.0;
    }
    
    // courseCompleted method accepts the course grade and credit hours and
    // updates the variables used for the gpa to be computed.
    public void courseCompleted(char grade, int hours) {
        double tempNumber = 0.0;
        if (grade == 'A') 
            tempNumber = 4.0;
        if (grade == 'B')
            tempNumber = 3.0;
        if (grade == 'C')
            tempNumber = 2.0;
        if (grade == 'D')
            tempNumber = 1.0;
        if (grade == 'F')
            tempNumber = 0.0;
        
        // Updates the variables
        totalCreditComplete += hours;
        totalQualityPoints += tempNumber * hours;
        studentGpa = totalQualityPoints / totalCreditComplete;
    }
    
    // toString method returns all the students information
    public String toString() {
        String str = ("Student Name: " + studentName 
                + "\nStudent Major: " + studentMajor 
                + "\nStudent GPA: " + studentGpa);
        return str;
    }
}
