from math import factorial

class Path(list):
  """A path of user-defined steps"""

  def __init__(self, path=[], links=[], walks={}):
    self.extend(path)
    self._links = links
    self._walks = walks

  @property
  def links(self):
    return self._links

  @property
  def walks(self):
    return self._walks

  # Count possible walks
  def count_walks(self):
    # Initialize counter
    walks_num = 1
    walks_txt = []

    subpaths = self._get_subpaths()

    # Count subpath walks
    for subpath in subpaths:
      num, txt = self._count_subwalks(subpath)
      walks_num *= num
      walks_txt.append(txt)

    # Store result
    self._walks = {
      "num": int(walks_num),
      "txt": " * ".join(walks_txt),
    }

  # Get subpaths
  def _get_subpaths(self):
    subpaths = []

    for step, link in zip(self, self._links):
      if not link:
        subpaths.append([])
      subpaths[-1].append(step)

    return subpaths

  # Count subpath walks
  def _count_subwalks(self, subpath):
    num = factorial(len(subpath))
    txt = str(len(subpath)) + "!"

    occurrences = {}
    for step in subpath:
      if step not in occurrences:
        occurrences[step] = 1
      else:
        occurrences[step] += 1
      
    for step in occurrences:
      if occurrences[step] > 1:
        num /= factorial(occurrences[step])
        txt += " / " + str(occurrences[step]) + "!"

    return num, txt
