package ir.asta.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Teacher extends Person {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "classroomId", nullable = false)
    private Classroom classroom;

    public Teacher(String name) {
        super(name);
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher number" + id + ", name=" + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher temp = (Teacher) o;
        return this.id == temp.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String describe() {
        return super.showInfo() + ", Teacher ID: " + id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }
}

