import org.scalatest.{FlatSpec, Matchers}
import trees.{AVLTree, Node}

class AVLTreeTest  extends FlatSpec with Matchers{

  def checkTreeBalanced(tree: AVLTree): Boolean = {
    return checkBalanceAtNode(tree.root)._1
  }

  private def checkBalanceAtNode(node:Node):(Boolean, Int) = {
    if(node==null)
      return (true,-1)
    else{
      val leftResult = checkBalanceAtNode(node.left)
      val rightResult = checkBalanceAtNode(node.right)
      val returnHeight = Integer.max(leftResult._2,rightResult._2) + 1
      if(leftResult._1 && rightResult._1){
        if(Math.abs(leftResult._2-rightResult._2) >=2)
          return (false,returnHeight)
        else
          return (true,returnHeight)
      }else{
        return (false,returnHeight)
      }
    }
  }

  "addition or deletion of nodes" should "result tree in balanced" in {
    val avlTree = new AVLTree
    avlTree.add(0)
    checkTreeBalanced(avlTree)
  }

}
