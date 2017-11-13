package trees


class Node(var left: Node, var right: Node, var parent: Node, var value: Int) {
  def this(left: Node, right: Node, parent: Node) {
    this(left, right, parent, 0)
  }

  def printSize: Int = {
    if (value > 0) {
      Math.log10(value).toInt + 1
    } else {
      Math.log10(-value).toInt + 1 + 1 // 1 for printing "-", -ve symbol
    }
  }
}

