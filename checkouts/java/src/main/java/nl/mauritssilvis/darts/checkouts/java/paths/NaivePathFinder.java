/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NaivePathFinder implements PathFinder {
    @Override
    public Set<Path> find(List<Set<Integer>> steps, int target) {
        Set<Path> paths = new HashSet<>();

        boolean hasEmptySteps = steps.stream()
                .anyMatch(Set::isEmpty);

        if (hasEmptySteps) {
            return paths;
        }

        find(steps, target, paths);

        return paths;
    }

    private void find(List<Set<Integer>> steps, int target, Set<Path> paths) {
        List<Integer> path = new ArrayList<>();
        steps.forEach(step -> path.add(0));

        int distance = 0;

        int level = 0;
        int depth = steps.size();

        find(level, depth, steps, path, distance, target, paths);
    }

    private void find(int level, int depth, List<Set<Integer>> steps, List<Integer> path, int distance, int target, Set<Path> paths) {
        if (level == depth) {
            if (level > 0 && distance == target) {
                paths.add(new SimplePath(path));
            }

            return;
        }

        for (int step : steps.get(level)) {
            path.set(level, step);
            find(level + 1, depth, steps, path, distance + step, target, paths);
        }
    }
}
