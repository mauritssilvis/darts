from paths import Paths

class Checkouts(list):
  def __init__(self, goal, darts, fields, in_fields=[], out_fields=[]):
    self.goal = goal
    self.darts = darts
    self.fields = fields
    self.in_fields = in_fields or fields
    self.out_fields = out_fields or fields

    self.fields_sets = self.gen_fields_sets()
    self.links = self.find_links()
    self.scores_sets = self.get_scores_sets()

    self.paths = Paths(self.scores_sets, self.links, self.goal)
    # self.throws = self.get_throws()

    # TODO: Convert paths to throws

  def gen_fields_sets(self):
    fields_sets = []

    if self.darts >= 1:
      fields_sets.append(self.out_fields)

    if self.darts >= 2:
      fields_sets.insert(0, self.in_fields)

    for _ in range(self.darts - 2):
      fields_sets.insert(1, self.fields)

    return fields_sets

  def find_links(self):
    links = []

    last_field_set = []
    for fields_set in self.fields_sets:
      links.append(fields_set == last_field_set)
      last_field_set = fields_set

    return links

  def get_scores_sets(self):
    scores_sets = []

    for fields_set in self.fields_sets:
      scores_sets.append(fields_set.scores)

    return scores_sets
