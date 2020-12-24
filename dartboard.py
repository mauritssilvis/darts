from fields import Fields

class Dartboard:
  """A dartboard containing pre-defined fields"""

  def __init__(self):
    self.single_fields = self._single_fields()
    self.double_fields = self._double_fields()
    self.triple_fields = self._triple_fields()
    self.master_fields = self._master_fields()
    self.all_fields = self._all_fields()

  def _single_fields(self):
    # Store basic fields
    fields = self._basic_fields("", 1)

    # Add special fields
    fields.add({"25": 25})

    return fields

  def _double_fields(self):
    # Store basic fields
    fields = self._basic_fields("D", 2)

    # Add special fields
    fields.add({"D25": 50})

    return fields

  def _triple_fields(self):
    fields = self._basic_fields("T", 3)
    return fields

  def _master_fields(self):
    fields = Fields()
    fields.add(self.double_fields)
    fields.add(self.triple_fields)
    return fields

  def _all_fields(self):
    fields = Fields()
    fields.add(self.single_fields)
    fields.add(self.double_fields)
    fields.add(self.triple_fields)
    return fields

  def _basic_fields(self, prefix, factor):
    fields = Fields()

    for num in range(1, 21):
      name = prefix + str(num)
      score = factor * num
      fields.add({name: score})

    return fields
