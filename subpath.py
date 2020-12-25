from math import factorial

from walks import Walks

class Subpath(list):
  """A path of linked user-defined steps"""

  def __init__(self, subpath=[]):
    self.extend(subpath)

  def walks(self):
    # Initialize properties
    num = factorial(len(self))
    txt = str(len(self)) + "!"

    # Count step occurrences
    times = {}
    for step in self:
      if step not in times:
        times[step] = 1
      else:
        times[step] += 1

    # Determine subpath walks
    for step in sorted(times.keys()):
      if times[step] > 1:
        num /= factorial(times[step])
        txt += " / " + str(times[step]) + "!"

    # Postprocess result
    num = int(num)

    return Walks(num, txt)
