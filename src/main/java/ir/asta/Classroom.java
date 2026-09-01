package ir.asta;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

public class Classroom {
    private List<Student> students = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        persons.add(student);
    }

    public void addTeacher(Teacher teacher) {
        persons.add(teacher);
    }

    public double average() {
        int sum = 0;
        for (Student student : students) {
            sum += student.getScore();
        }
        return (double) sum / students.size();
    }

    public int max() {
        int maximum = students.get(0).getScore();
        for (Student student : students) {
            if (student.getScore() > maximum) {
                maximum = student.getScore();
            }
        }
        return maximum;
    }

    public int min() {
        int minimum = students.get(0).getScore();
        for (Student student : students) {
            if (student.getScore() < minimum) {
                minimum = student.getScore();
            }
        }
        return minimum;
    }

    public void curve(int points) {
        for (Student student : students) {
            student.curveScore(points);
        }
    }

    public void sortStudents() {
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getScore(), s2.getScore());
            }
        });
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    public void showReport() {
        for (Person person : persons) {
            System.out.println(person.describe());
            if (person instanceof Student) {
                Student student = (Student) person;
                System.out.println("Grade: " + student.getGrade());
                String status = student.isPassing() ? "Pass" : "Fail";
                System.out.println("Status: " + status);
                System.out.println("____________________");
            }
        }
        System.out.println("Average: " + average());
        System.out.println("Maximum: " + max());
        System.out.println("Minimum: " + min());
    }
}
