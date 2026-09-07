package ir.asta;

import java.util.List;
import java.util.Objects;

public class PersonUtils {
    public static <T extends Person> T findByName(List<T> people, String name) {
        for (T person : people) {
            if (Objects.equals(person.getName(), name)) {
                return person;
            }
        }
        return null;
    }
}
