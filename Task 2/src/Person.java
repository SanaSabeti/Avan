import java.time.LocalDateTime;
public abstract class Person {
    protected final String name;
    protected final String createdAt;
    {
        createdAt = LocalDateTime.now().toString();
    }
    protected Person(String name){
        this.name = name;
    }
    protected String showInfo() {
        return "name= " + name + ", createdAt= " + createdAt;
    }
    public abstract String describe();
}
