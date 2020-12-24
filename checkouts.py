class Checkouts(list):
  def __init__(self, goal, darts, fields, in_fields=[], out_fields=[]):
    self.goal = goal
    self.darts = darts
    self.fields = fields
    self.in_fields = in_fields or fields
    self.out_fields = out_fields or fields

    self.find()

  def find(self):
    fields_sets = []
    fields_sets.append(self.in_fields)
    fields_sets.extend([self.fields, ] * (self.darts - 2))
    fields_sets.append(self.out_fields)
    
    # TOOD: Set links, get scores, call paths
