from path import Path

class Paths(list):
  """Find the possible paths to a goal taking user-defined steps"""

  def __init__(self, steps, links, goal):
    # Check input
    if len(steps) != len(links):
      raise ValueError("The lists of steps and links should have equal length.")

    if links[0]:
      raise ValueError("First step cannot be linked.")

    # Store settings
    self._steps = steps
    self._links = links
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

    # Initialize list of walks
    self._walks = []

    # Determine number of possible walks
    self._count_walks()

  @property
  def stats(self):
    return self._stats

  def print(self):
    print("Paths:")
    for num, path, walks in zip(range(len(self)), self, self._walks):
      print(f"#{num + 1}: {path}: {walks['txt']} = {walks['num']}")

  def _find(self):
    # Initialize recursion
    steps = [sorted(cur_steps, reverse=True) for cur_steps in self._steps]
    links = self._links
    goal = self._goal
    step_bound = max([max(cur_steps) for cur_steps in steps])
    path = Path([], links)
    stats = self._stats

    # Recursion
    self._recur(steps, links, goal, step_bound, path, stats)

  # Stepping
  def _recur(self, steps, links, goal, step_bound, path, stats):
    stats["calls"] += 1

    # Prepare checking variables
    path_sum = sum(path)
    min_add = self._min_sum(steps)
    max_add = self._max_sum(steps, links, step_bound, stats)

    if len(steps) == 0:
        # Final step reached
        if path_sum == goal:
            # Goal reached
            super().append(Path(path.copy(), path.links))
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
        max_add = self._max_sum(steps[1:], links[1:], step_bound, stats)

        # Try stepping
        for step in steps[0]:
            stats["fors"] += 1

            stats["ifs"] += 1
            if links[0] and step > step_bound:
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
            self._recur(steps[1:], links[1:], goal, step, path, stats)
            path.pop()

  # Minimal possible sum
  def _min_sum(self, steps):
    return sum([min(cur_steps) for cur_steps in steps])

  # Maximal possible sum
  def _max_sum(self, steps, links, step_bound, stats):
    ceiling = 0
    for cur_steps, link in zip(steps, links):
        max_step = max(cur_steps)

        if link and step_bound != -1:
            max_step = min([max_step, step_bound])
        stats["ifs"] += 1

        step_bound = max_step
        ceiling += max_step

    return ceiling

  # Number of possible walks
  def _count_walks(self):
    for path in self:
      path.count_walks()
      self._walks.append(path.walks)
