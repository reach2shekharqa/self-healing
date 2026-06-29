package com.selfhealing.decision;

import com.selfhealing.model.ElementFingerprint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ContextScorer {

    private static final int MIN_SCORE = 8;

    public List<ElementFingerprint> reduce(List<ElementFingerprint> fingerprints) {

        List<ScoredFingerprint> scored = new ArrayList<>();

        for (ElementFingerprint fp : fingerprints) {

            int score = calculateScore(fp);

            if (score >= MIN_SCORE) {
                scored.add(new ScoredFingerprint(fp, score));
            }
        }

        scored.sort(
                Comparator.comparingInt(ScoredFingerprint::getScore)
                        .reversed());

        List<ElementFingerprint> result = new ArrayList<>();

        for (ScoredFingerprint item : scored) {
            result.add(item.getFingerprint());
        }

        return result;
    }

    private int calculateScore(ElementFingerprint fp) {

        int score = 0;

        String tag = safe(fp.getTag()).toLowerCase();
        String text = safe(fp.getText()).trim();

        Map<String, String> attrs = fp.getImportantAttributes();

        // Ignore useless HTML elements
        switch (tag) {

            case "html":
            case "head":
            case "body":
            case "script":
            case "style":
            case "svg":
            case "path":
            case "meta":
            case "link":
            case "noscript":
            case "iframe":
                return -100;
        }

        // Interactive elements
        switch (tag) {

            case "input":
            case "textarea":
            case "select":
                score += 10;
                break;

            case "button":
                score += 9;
                break;

            case "label":
                score += 8;
                break;

            case "option":
                score += 7;
                break;

            case "a":
                score += 5;
                break;
        }

        if (attrs != null) {

            if (has(attrs, "id"))
                score += 4;

            if (has(attrs, "name"))
                score += 5;

            if (has(attrs, "placeholder"))
                score += 5;

            if (has(attrs, "aria-label"))
                score += 5;

            if (has(attrs, "for"))
                score += 4;

            if (has(attrs, "type"))
                score += 3;

            if (has(attrs, "value"))
                score += 3;

            if (has(attrs, "title"))
                score += 3;
        }

        // Useful visible text
        if (!text.isBlank()) {

            if (text.length() <= 30)
                score += 3;
            else
                score += 1;
        }

        /*
         * Quality penalties
         */

        // Anonymous inputs are rarely useful
        if (tag.equals("input")) {

            if (attrs == null
                    || (!has(attrs, "id")
                    && !has(attrs, "name")
                    && !has(attrs, "placeholder")
                    && !has(attrs, "aria-label")
                    && !has(attrs, "type"))) {

                score -= 8;
            }
        }

        // Empty links are useless
        if (tag.equals("a") && text.isBlank()) {
            score -= 20;
        }

        // Extremely long text is usually layout/footer content
        if (text.length() > 80) {
            score -= 10;
        }

        // Parent looks like footer
        String parent = safe(fp.getParentContext()).toLowerCase();

        if (parent.contains("footer")
                || parent.contains("copyright")) {

            score -= 10;
        }

        return score;
    }

    private boolean has(Map<String, String> attrs, String key) {

        return attrs.containsKey(key)
                && attrs.get(key) != null
                && !attrs.get(key).trim().isEmpty();
    }

    private String safe(String value) {

        return value == null ? "" : value;
    }

    private static class ScoredFingerprint {

        private final ElementFingerprint fingerprint;
        private final int score;

        public ScoredFingerprint(ElementFingerprint fingerprint,
                                 int score) {

            this.fingerprint = fingerprint;
            this.score = score;
        }

        public ElementFingerprint getFingerprint() {
            return fingerprint;
        }

        public int getScore() {
            return score;
        }
    }
}