public interface Gradable {
    Grade getGrade();

    default boolean isPassing() {
        return getGrade() != Grade.F;
    }
}
