package com.selfhealing.matcher;

import com.selfhealing.model.ElementSnapshot;

public class MatchResult {

    private ElementSnapshot element;
    private double score;

    public MatchResult(ElementSnapshot element, double score) {
        this.element = element;
        this.score = score;
    }

    public ElementSnapshot getElement() {
        return element;
    }

    public double getScore() {
        return score;
    }
}
