class Paths(list):
  """Find the possible paths to a goal taking user-defined steps"""

  def __init__(self, steps, bounded, goal):
    # Store settings
    self._steps = steps
    self._bounded = steps
    self._goal = goal

    # Initialize statistics
    self._stats = {
      "calls": 0,
      "ifs": 0,
      "preovershoots": 0,
      "preundershoots": 0,
      "fors": 0,
      "foroverbound": 0,
      "forovershoots": 0,
      "forundershoots": 0,
      "fails": 0,
    }

    # Find paths
    self._find()

  @property
  def stats(self):
    return self._stats

  def _find(self):
    # Initialize recursion
    steps = [sorted(base_steps, reverse=True) for base_steps in self._steps]
    bounded = self._bounded
    goal = self._goal
    step_bound = max([max(base_step) for base_step in steps])
    path = []
    stats = self._stats

    # Recursion
    self._recur(steps, bounded, goal, step_bound, path, stats)

  # Stepping
  def _recur(self, steps, bounded, goal, step_bound, path, stats):
    stats["calls"] += 1

    # Prepare checking variables
    path_sum = sum(path)
    min_add = self._min_sum(steps)
    max_add = self._max_sum(steps, bounded, step_bound, stats)

    if len(steps) == 0:
        # Final step reached
        if path_sum == goal:
            # Goal reached
            super().append(list(path))
        else:
            # Should not occur
            stats["fails"] += 1
        stats["ifs"] += 2
        return
    elif path_sum + max_add < goal:
        # Path sum too small to reach goal
        stats["preundershoots"] += 1
        stats["ifs"] += 2
        return
    elif path_sum + min_add > goal:
        # Path sum too large to reach goal
        stats["preovershoots"] += 1
        stats["ifs"] += 3
        return
    else:
        stats["ifs"] += 3

        # Prepare checking variables
        min_add -= min(steps[0])
        max_add = self._max_sum(steps[1:], bounded[1:], step_bound, stats)

        # Try stepping
        for step in steps[0]:
            stats["fors"] += 1

            stats["ifs"] += 1
            if bounded[0] and step > step_bound:
                # Step larger than bound
                stats["foroverbound"] += 1
                continue

            stats["ifs"] += 1
            if path_sum + step + min_add > goal:
                # Step too large to reach goal
                stats["forovershoots"] += 1
                continue

            stats["ifs"] += 1
            if path_sum + step + max_add < goal:
                # Step too small to reach goal
                stats["forundershoots"] += 1
                return

            path.append(step)
            self._recur(steps[1:], bounded[1:], goal, step, path, stats)
            path.pop()

  # Minimal possible sum
  def _min_sum(self, steps):
    return sum([min(base_steps) for base_steps in steps])

  # Maximal possible sum
  def _max_sum(self, steps, bounded, step_bound, stats):
    ceiling = 0
    for base_steps, base_bounded in zip(steps, bounded):
        max_step = max(base_steps)

        if base_bounded and step_bound != -1:
            max_step = min([max_step, step_bound])
        stats["ifs"] += 1

        step_bound = max_step
        ceiling += max_step

    return ceiling
