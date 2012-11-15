package jeopardy.domain;

import java.io.Serializable;

public class AnswerQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String answer;
    private String question;
    private String category;
    private int value;
    public boolean used;

    public AnswerQuestion(String answer, String question, String category, int value) {
        this.answer = answer;
        this.question = question;
        this.category = category;
        this.value = value;
        setUsed(false);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
    public String toString() {
        return String.format("Aq{%s, %s, %s, %s, %s, %s}", super.toString(), category,
                answer, question, value, used);
    }
    
}
