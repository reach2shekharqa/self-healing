package com.selfhealing.healing;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CandidateGenerator {

        public List<ElementSnapshot> generate(
                        DOMSnapshot snapshot,
                        String failedLocator) {

                List<ElementSnapshot> candidates = new ArrayList<>();

                String searchValue = extractLocatorValue(failedLocator);

                for (ElementSnapshot element : snapshot.getElements()) {

                        String tag = element.getTag();

                        if (tag == null) {
                                continue;
                        }

                        String id = element.getAttributes()
                                        .get("id");

                        if (id != null && id.isBlank()) {
                                id = null;
                        }

                        /*
                         * Strong ID match
                         */
                        if (id != null
                                        && isSimilar(id, searchValue)) {

                                candidates.add(element);

                                continue;
                        }

                        /*
                         * Only allow input elements
                         * for password/user/email healing
                         */
                        if (tag.equalsIgnoreCase("input")) {

                                String type = element.getAttributes()
                                                .get("type");

                                String name = element.getAttributes()
                                                .get("name");

                                if (name != null
                                                && isSimilar(name, searchValue)) {

                                        candidates.add(element);

                                        continue;
                                }

                                if (type != null
                                                && isSimilar(type, searchValue)) {

                                        candidates.add(element);

                                }

                        }

                }

                return candidates;
        }

        private String extractLocatorValue(
                        String locator) {

                /*
                 * Example:
                 * By.id: react-burger-menu-btn1
                 */

                if (locator.contains(":")) {

                        return locator.substring(
                                        locator.indexOf(":") + 1).trim();

                }

                return locator;

        }

        private boolean isSimilar(
                        String value,
                        String target) {

                value = value.toLowerCase();

                target = target.toLowerCase();

                /*
                 * Avoid very short comparisons
                 */
                if (value.length() < 3
                                || target.length() < 3) {

                        return false;

                }

                int length = Math.min(
                                5,
                                target.length());

                String prefix = target.substring(
                                0,
                                length);

                return value.contains(prefix)
                                ||
                                target.contains(value);

        }

}