interface Gradable {
    abstract char getGrade();
    default boolean isPassing(){
        if (getGrade()!='F'){
            return true;
        }
        return false;
    };
}
