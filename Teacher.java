import java.util.Objects;

public class Teacher extends Person {
    private static int liveInstance = 0;
    private final int id;

    public Teacher(String name) {
        super(name);
        this.id = ++liveInstance;
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
}

