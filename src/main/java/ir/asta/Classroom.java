package ir.asta;

import java.util.*;

public class Classroom implements Iterable<Student> {
    private List<Student> students = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private Set<String> names = new HashSet<>();
    private HashMap<Integer, Student> studentsWithId = new HashMap<>();

    /**
     * Adds a student to the classroom
     *
     * @param student to add
     * @return true if added
     * @throws InvalidScoreException     if score is invalid
     * @throws DuplicateStudentException if name is duplicated
     */
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

    /**
     * Adds a teacher.
     *
     * @param teacher to add
     */
    public void addTeacher(Teacher teacher) {
        persons.add(teacher);
    }

    /**
     * @return average score
     */
    public double average() {
        return students.stream().mapToInt(Student::getScore).average().orElse(0.0);
    }

    /**
     * @return highest score
     */
    public int max() {
        return students.stream().mapToInt(Student::getScore).max().orElse(0);
    }

    /**
     * @return lowest score
     */
    public int min() {
        return students.stream().mapToInt(Student::getScore).min().orElse(0);
    }

    /**
     * Adds points to all student scores.
     *
     * @param points to add
     */
    public void curve(int points) {
        students.stream().forEach(student -> student.curveScore(points));
    }

    /**
     * Sorts students by score and prints their ranks.
     */
    public void sortStudents() {
        students.sort((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));
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

    /**
     * Prints a report of all classroom members (both students and teachers).
     * For students, it also prints their grade and pass/fail status.
     * At the end, it prints the average, maximum, and minimum scores.
     */
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

    /**
     * Finds a student by ID using HashMap.
     *
     * @param id student ID
     * @return student with the given ID, or null
     */
    public Student findStudentWithIdMap(int id) {
        return studentsWithId.get(id);
    }

    /**
     * Finds a student by ID using linear search.
     *
     * @param id student ID
     * @return student with the given ID, or null
     */
    public Student findStudentWithIdLinear(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

    /**
     * @return student iterator
     */
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

    /**
     * Adds multiple students.
     *
     * @param students to add
     * @throws InvalidScoreException if a score is invalid
     */
    public void addStudents(Student... students) throws InvalidScoreException {
        for (Student student : students) {
            addStudent(student);
        }
    }
}

