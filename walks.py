class Walks(dict):
  def __init__(self, num=0, txt=""):
    self["num"] = num
    self["txt"] = txt

  @property
  def num(self):
    return self["num"]

  @property
  def txt(self):
    return self["txt"]
