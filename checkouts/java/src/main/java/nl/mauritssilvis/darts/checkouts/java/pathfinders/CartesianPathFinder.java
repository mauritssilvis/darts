/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;
import nl.mauritssilvis.darts.checkouts.java.paths.SimplePath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartesianPathFinder implements PathFinder {
    @Override
    public List<Path> find(List<List<Integer>> steps, int length) {
        boolean hasEmptySteps = steps.stream()
                .anyMatch(List::isEmpty);

        return hasEmptySteps ? new ArrayList<>() : new Finder(steps, length).find();
    }

    private static class Finder {
        private final List<List<Integer>> steps;
        private final int length;
        private final List<Integer> path;
        private final List<Path> paths;

        Finder(List<List<Integer>> steps, int length) {
            this.steps = steps;
            this.length = length;

            path = new ArrayList<>();
            steps.forEach(step -> path.add(0));

            paths = new ArrayList<>();
        }

        List<Path> find() {
            paths.clear();

            int level = 0;
            int distance = 0;
            findRecursively(level, distance);

            return Collections.unmodifiableList(paths);
        }

        private void findRecursively(int level, int distance) {
            if (level == steps.size()) {
                if (level > 0 && distance == length) {
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
