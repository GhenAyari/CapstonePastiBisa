package app.model;

public class QuestionItem {
    private final int number;       // soal ke-
    private final String question;  // teks soal
    private final char correct;     // 'A' | 'B' | 'C' | 'D'

    public QuestionItem(int number, String question, char correct) {
        this.number = number;
        this.question = question;
        this.correct = correct;
    }

    public int getNumber()   { return number; }
    public String getQuestion() { return question; }
    public char getCorrect() { return correct; }
}
