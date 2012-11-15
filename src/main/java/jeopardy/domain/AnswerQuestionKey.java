package jeopardy.domain;

import java.io.Serializable;

public class AnswerQuestionKey implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String category;
    public final int value;

    public AnswerQuestionKey(String category, int value) {
        this.category = category;
        this.value = value;
    }

    public int hashCode() {
        return category.hashCode() * value;
    }

    @Override
    public boolean equals(Object aq) {
        if (aq instanceof AnswerQuestionKey) {
            AnswerQuestionKey rhs = (AnswerQuestionKey) aq;
            return category.equals(rhs.category) && value == rhs.value;
        }
        return false;
    }

    public String toString() {
        return String.format("Aqk{%s, %s}", category, value);
    }
}
