package jeopardy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Board implements Serializable {
    private static final long serialVersionUID = 4464632153703286777L;

    private Map<AnswerQuestionKey, AnswerQuestion> theBoard = new HashMap<AnswerQuestionKey, AnswerQuestion>();

    public void add(String category, int value, String answer, String question) {
        AnswerQuestionKey key = new AnswerQuestionKey(category, value);
        AnswerQuestion aq = new AnswerQuestion(answer, question, category, value);
        theBoard.put(key, aq);
    }

    public AnswerQuestion retrieve(String category, int value) {
        AnswerQuestion aq = theBoard.get(new AnswerQuestionKey(category, value));
        return aq;
    }

    public List<AnswerQuestion> retrieve(String category) {
        List<AnswerQuestion> matching = new ArrayList<AnswerQuestion>();

        Iterator<Entry<AnswerQuestionKey, AnswerQuestion>> iter = theBoard.entrySet().iterator();

        while (iter.hasNext()) {
            Entry<AnswerQuestionKey, AnswerQuestion> e = iter.next();
            if (e.getKey().category.equals(category)) {
                matching.add(e.getValue());
            }
        }

        Collections.sort(matching, new Comparator<AnswerQuestion>() {

            public int compare(AnswerQuestion lhs, AnswerQuestion rhs) {
                int lhsV = lhs.getValue();
                int rhsV = rhs.getValue();

                if (lhsV < rhsV) {
                    return -1;
                } else if (lhsV > rhsV) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return matching;
    }

    public Set<String> getCategories() {
        Set<String> categories = new HashSet<String>();
        Iterator<AnswerQuestionKey> keys = theBoard.keySet().iterator();
        while (keys.hasNext()) {
            categories.add(keys.next().category);
        }

        return categories;
    }

    public void answer(AnswerQuestion aq) {
        AnswerQuestion actual = retrieve(aq.getCategory(), aq.getValue());
        actual.setUsed(true);
    }
}
