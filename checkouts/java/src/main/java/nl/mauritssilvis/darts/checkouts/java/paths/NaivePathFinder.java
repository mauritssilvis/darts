/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.*;

public class NaivePathFinder implements PathFinder {
    @Override
    public Set<Path> find(List<Set<Integer>> steps, int target) {
        boolean hasEmptySteps = steps.stream()
                .anyMatch(Set::isEmpty);

        return hasEmptySteps ? new HashSet() : new Finder(steps, target).find();
    }

    private static class Finder {
        private final List<Set<Integer>> steps;
        private final int target;
        private final List<Integer> path;
        private final Set<Path> paths;

        Finder(List<Set<Integer>> steps, int target) {
            this.steps = steps;
            this.target = target;

            path = new ArrayList<>();
            steps.forEach(step -> path.add(0));

            paths = new HashSet<>();
        }

        Set<Path> find() {
            paths.clear();

            int level = 0;
            int distance = 0;
            findRecursively(level, distance);

            return Collections.unmodifiableSet(paths);
        }

        private void findRecursively(int level, int distance) {
            if (level == steps.size()) {
                if (level > 0 && distance == target) {
                    paths.add(new SimplePath(path));
                }

                return;
            }

            for (int step : steps.get(level)) {
                path.set(level, step);
                findRecursively(level + 1, distance + step);
            }
        }
    }
}
