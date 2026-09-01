public enum Grade {
    A(90), B(80), C(70), D(60), F(0);
    private final int minScore;

    Grade(int i) {
        this.minScore = i;
    }

    public static Grade fromScore(int score) {
        for (Grade grade : Grade.values()) {
            if (score >= grade.minScore) {
                return grade;
            }
        }
        return F;
    }
}
