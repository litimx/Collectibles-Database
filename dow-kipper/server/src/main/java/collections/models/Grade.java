package collections.models;

public enum Grade {
    UNGRADED(0),
    SEVEN(7),

    EIGHT(8),

    NINE(9),

    NINEFIVE(9.5),

    TEN(10);

    private double grade;
    Grade(double grade) {
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
