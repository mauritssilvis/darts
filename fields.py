class Fields(dict):
  """Fields with names and scores"""

  @property
  def names(self):
    return sorted(self.keys())

  @property
  def scores(self):
    return sorted(self.scores2names.keys())

  @property
  def names2scores(self):
    return self

  @property
  def scores2names(self):
    scores = {}

    for name, score in self.items():
      if score not in scores:
        scores[score] = []
      scores[score].append(name)

    return scores

  def add(self, fields):
    self.update(fields)
