package ir.asta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassroomTest {
    private Classroom classroom;

    @BeforeEach
    void setUp() {
        classroom = new Classroom();
    }

    @Test
    void addStudentTest() throws InvalidScoreException {
        Student student = new Student("Sana", 80);
        boolean result = classroom.addStudent(student);
        assertTrue(result);
    }

    @Test
    void invalidScoreTest() throws InvalidScoreException{
        Student student = new Student("Mamad", 101);
        assertThrows(InvalidScoreException.class, () -> {
            classroom.addStudent(student);
        });
    }

    @Test
    void averageTest() throws InvalidScoreException {
        classroom.addStudent(new Student("Sana", 31));
        classroom.addStudent(new Student("Sara", 20));
        double result = classroom.average();
        assertEquals(25.5, result);
    }

    @Test
    void maxTest() throws InvalidScoreException {
        classroom.addStudent(new Student("Sana", 100));
        classroom.addStudent(new Student("Sara", 50));
        classroom.addStudent(new Student("Mamad", 98));

        int result = classroom.max();
        assertEquals(100, result);
    }

    @Test
    void minTest() throws InvalidScoreException {
        classroom.addStudent(new Student("Sana", 100));
        classroom.addStudent(new Student("Sara", 50));
        classroom.addStudent(new Student("Mamad", 98));

        int result = classroom.min();
        assertEquals(50, result);
    }
}