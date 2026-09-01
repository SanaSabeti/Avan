public enum Grade{
    A(90),
    B(80),
    C(70),
    D(60),
    F(0);

    int minScore;

    Grade(int i) {
        this.minScore = i;
    }

    static Grade fromScore(int score) {
    if (score>= A.minScore)
        return A;
    if (score>= B.minScore)
            return B;
    if (score>= C.minScore)
            return C;
    if (score>= D.minScore)
            return D;
    return F;



    }
}