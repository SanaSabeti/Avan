package ir.asta;

import java.util.List;

public class PersonUtils {
    public static <T extends Person> T findByName(List<T> people, String name) {
        for (T person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
}
