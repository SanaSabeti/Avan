package ir.asta;

import java.util.Scanner;

public class RosterAnalyzer {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Classroom classroom = new Classroom();
        System.out.println("Teacher's name:");
        String teacherName = scanner.next();
        Teacher teacher = new Teacher(teacherName);
        classroom.addTeacher(teacher);
        System.out.println("How many students do you have?");
        int n = scanner.nextInt();
        int counter = 0;
        while (counter < n) {
            System.out.println("Student's name:");
            String name = scanner.next();
            System.out.println("Student's score:");
            int score = scanner.nextInt();
            if (score == -1) {
                System.out.println("Enter the student again!");
            } else if (score >= 0 && score <= 100) {
                Student student = new Student(name, score);
                if (classroom.addStudent(student)) {
                    counter++;
                    System.out.println("OK, Next!");
                } else {
                    System.out.println("Duplicate name!Pls Enter the student again!");
                }
            } else {
                System.out.println("Warning: invalid score!");
                System.out.println("Enter the student again!");
            }
        }
        int response;
        do {
            System.out.println("What do you want to do?");
            System.out.println("Menu:\n1) Add student\n2) Show all members\n3) Curve scores\n4) Add teacher\n5) Sort students\n6)Find student by ID and compare performance\n7) Exit");
            response = scanner.nextInt();
            switch (response) {
                case 1:
                    char answer = 'y';
                    while (answer == 'y') {
                        while (true) {
                            System.out.println("Student's name:");
                            String name = scanner.next();
                            System.out.println("Student's score:");
                            int score = scanner.nextInt();
                            if (score == -1) {
                                System.out.println("Enter the student again!");
                            } else if (score >= 0 && score <= 100) {
                                Student student = new Student(name, score);
                                if (classroom.addStudent(student)) {
                                    break;
                                } else {
                                    System.out.println("Duplicate name!Pls Enter the student again!");
                                }
                            } else {
                                System.out.println("Warning: invalid score!");
                                System.out.println("Enter the student again!");
                            }
                        }
                        System.out.println("Add another student? (y/n)");
                        answer = scanner.next().charAt(0);
                    }
                    break;
                case 2:
                    classroom.showReport();
                    break;
                case 3:
                    System.out.println("How many points should be added?");
                    int points = scanner.nextInt();
                    classroom.curve(points);
                    break;
                case 4:
                    System.out.println("Teacher's name:");
                    teacherName = scanner.next();
                    teacher = new Teacher(teacherName);
                    classroom.addTeacher(teacher);
                    break;
                case 5:
                    classroom.sortStudents();
                    break;
                case 6:
                    Student linearResult = null;
                    Student mapResult = null;
                    System.out.println("Enter ur desired student ID:");
                    int desiredId = scanner.nextInt();
                    long linearStart = System.nanoTime();
                    linearResult = classroom.findStudentWithIdLinear(desiredId);
                    long linearEnd = System.nanoTime();
                    long mapStart = System.nanoTime();
                    mapResult = classroom.findStudentWithIdMap(desiredId);
                    long mapEnd = System.nanoTime();
                    long linearTime = linearEnd - linearStart;
                    long mapTime = mapEnd - mapStart;
                    System.out.println("Linear search time: " + linearTime + " - " + linearResult.getName());
                    System.out.println("HashMap search time: " + mapTime + " - " + mapResult.getName());
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (response != 7);
    }
}