from subpath import Subpath
from walks import Walks

class Path(list):
  """A path of (possibly unlinked) user-defined steps"""

  def __init__(self, path=[], links=[]):
    self.extend(path)
    self.links = links

  def walks(self):
    # Initialize properties
    num = 1
    txt = []

    # Determine subpath walks
    subpaths = self.get_subpaths()
    for subpath in subpaths:
      subwalks = subpath.walks()
      num *= subwalks.num
      txt.append(subwalks.txt)

    # Postprocess properties
    num = int(num)
    txt = " * ".join(txt)

    return Walks(num, txt)

  def get_subpaths(self):
    subpaths = []

    for step, link in zip(self, self.links):
      if not link:
        subpaths.append(Subpath())
      subpaths[-1].append(step)

    return subpaths
