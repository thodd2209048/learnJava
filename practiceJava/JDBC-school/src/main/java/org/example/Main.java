package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
//        List<Student> students = Student.readStudent("Student.csv");
//        DataUtil.insertOrUpdateStudents(students);
//        List<School> schools = School.readSchools("School.csv");
//        DataUtil.insertSchool(schools);
        List<Student> updateStudentList = Student.readStudent("Student update.csv");
        DataUtil.insertOrUpdateStudents(updateStudentList);

//        List<Student> result = DataUtil.listByAge(18);
//        System.out.println(result);

    }
}