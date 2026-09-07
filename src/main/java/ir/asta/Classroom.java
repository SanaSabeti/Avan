package ir.asta;

import java.util.*;

public class Classroom implements Iterable<Student> {
    private List<Student> students = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private Set<String> names = new HashSet<>();
    private HashMap<Integer, Student> studentsWithId = new HashMap<>();

    public boolean addStudent(Student student) throws InvalidScoreException {
        if (student.getScore() < 0 || student.getScore() > 100) {
            throw new InvalidScoreException("Invalid score!");
        }

        if (!names.add(student.getName())) {
            throw new DuplicateStudentException("Duplicate student!");
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
        return students.stream().mapToInt(Student::getScore).average().orElse(0.0);
    }

    public int max() {
        return students.stream().mapToInt(Student::getScore).max().orElse(0);
    }

    public int min() {
        return students.stream().mapToInt(Student::getScore).min().orElse(0);
    }

    public void curve(int points) {
        students.stream().forEach(student -> student.curveScore(points));
    }

    public void sortStudents() {
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s2.getScore(), s1.getScore());
            }
        });
        List<Pair<Student, Integer>> rankedStudents = new ArrayList<>();
        int rank = 1;
        for (int i = 0; i < students.size(); i++) {
            if (i > 0 && students.get(i).getScore() != students.get(i - 1).getScore()) {
                rank++;
            }
            rankedStudents.add(new Pair<>(students.get(i), rank));
        }
        for (Pair<Student, Integer> pair : rankedStudents) {
            System.out.println("Rank: " + pair.getSecondElement() + ") " + pair.getFirstElement().getName() + ", " + pair.getFirstElement().getScore());
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
        return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
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

    public void addStudents(Student... students) throws InvalidScoreException {
        for (Student student : students) {
            addStudent(student);
        }
    }
}

