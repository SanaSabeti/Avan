package ir.asta;

import java.util.Objects;

public class Student extends Person implements Gradable {
    private static int liveInstance = 0;
    private final int id;
    private int score;

    static {
        System.out.println("Student class loaded");
    }

    /**
     * Creates a student with default name and score.
     */
    public Student() {
        this("unknown", 0);
    }

    /**
     * Creates a student with a name and score.
     *
     * @param name  student name
     * @param score student score
     */
    public Student(String name, int score) {
        super(name);
        this.id = ++liveInstance;
        this.score = score;
    }

    /**
     * @return student score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return student ID
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Student number" + id + ", name= " + getName() + ", score= " + score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student temp = (Student) o;
        return this.id == temp.id;
    }

    /**
     * @return student information
     */
    @Override
    public String describe() {
        return super.showInfo() + ", Student ID: " + id + ", Score: " + score;
    }

    /**
     * Converts the student's score to a grade.
     *
     * @return grade based on the student's score
     */
    @Override
    public Grade getGrade() {
        return Grade.fromScore(score);
    }

    /**
     * Adds points to the student's score.
     * The score cannot be greater than 100.
     *
     * @param points points to add
     */
    public void curveScore(int points) {
        score += points;
        if (score > 100) {
            score = 100;
        }
    }

    /**
     * Checks if the student is passing.
     *
     * @return true if the student is passing
     */
    @Override
    public boolean isPassing() {
        return getGrade() != Grade.F;
    }
}
