import java.util.Objects;

public class Student extends Person implements Gradable{
    private static int liveInstance = 0;
    private final int id;
    private int score;

    static {
        System.out.println("Student class loaded");
    }

    public Student(){
        this("unknown" , 0);
    }
    public Student(String name, int score) {
        super(name);
        this.id = ++liveInstance;
        this.score = score;
    }

    public int  getScore(){

        return score;
    }

    @Override
    public String toString() {

        return "Student number" + id + ", name= " + name + ", score= " + score ;
    }
    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object o){
        if (o==this){
            return true;
        }
        if (!(o instanceof Student)){
            return false;
        }
        Student temp = (Student) o;
        return this.id == temp.id;
    }
    @Override
    public String describe() {
        return super.showInfo() + ", Student ID: " + id + ", Score: " + score;
    }

    @Override
    public char getGrade() {
        return Grade.fromScore(score).name().charAt(0);
    }

    public void curveScore(int points) {
        score += points;
        if (score > 100) {
            score = 100;
        }
    }

}
