package ir.asta;

import java.util.*;

public class Classroom implements Iterable<Student> {
    private List<Student> students = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private Set<String> names = new HashSet<>();
    private HashMap<Integer, Student> studentsWithId = new HashMap<>();

    public boolean addStudent(Student student) {
        if (!names.add(student.getName())) {
            return false;
        }
        students.add(student);
        persons.add(student);
        studentsWithId.put(student.getId(), student);
        return true;
    }

    public void addTeacher(Teacher teacher) {
        persons.add(teacher);
    }

    public double average() {
        int sum = 0;
        for (Student student : this) {
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
        for (Student student : this) {
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

    public Student findStudentWithIdMap(int id) {
        return studentsWithId.get(id);
    }

    public Student findStudentWithIdLinear(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<Student>() {
            int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < students.size();
            }

            @Override
            public Student next() {
                return students.get(counter++);
            }
        };
    }
}
