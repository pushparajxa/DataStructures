
package trees

object TestTreeUtils extends App{
  val root: Node = new Node(null, null, null, 1)
  val left:Node = new Node(null,null,root,3)
  root.left = left
  val right:Node = new Node(null,null,root,5)
  root.right = right
  val lleft = new Node(null,null,left,7)
  left.left = lleft
  val rright = new Node(null,null,right,9)
  right.right = rright
  TreeUtils.printSubTreeAtNode(root)
}
