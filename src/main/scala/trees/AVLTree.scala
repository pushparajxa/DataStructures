package trees



/*
* Copyright 2017 @ Pushparaj Motamari. All rights reserved.
*  Proprietary and Confidential.
*/
/*
trees.AVLTree handling no-duplicate values.
*/
class AVLTree {
  var root: Node = null

  def add(arg: Int): Unit = {
    /*
    find the node to which this arg to be added
    Check for unbalance in the tree
    Do rotation
    */
    root match {
      case null => root = new Node(null, null, null, arg)
        return
      case _ => //
    }

    //Add the arg to the tree.
    var temp = root
    var done = false
    while (!done) {
      if (arg < temp.value) {
        if (temp.left == null) {
          temp.left = new Node(null, null, temp, arg)
          done = true
        }
        else {
          temp = temp.left
        }
      } else {
        if (temp.right == null) {
          temp.right = new Node(null, null, temp, arg)
          done = true
        }
        else {
          temp = temp.right
        }
      }
    }
    //temp is the parent of the arg

    //Check where the tree got unbalanced
    var temp2 = temp
    var unbalanced = false
    while (!(temp2 eq null) && !unbalanced) {
      if (Math.abs(height(temp2.right) - height(temp2.left)) >= 2) {
        unbalanced = true
      } else {
        temp2 = temp2.parent
      }
    }
    //temp2 will be the node which is unbalanced.

    if (!unbalanced) {
      println("Addition doesn't result in unbalanced tree")
      return
    }
    else {
      restructure(temp2)
    }
  }

  /*
  Removes the given element and returns the node from where we have to start checking whether the height is balanced or not up until the root
  */
  def remove(arg: Int) = {
    val result = binarySearch(root, arg)
    var toReturn: Option[Node] = None
    result match {
      case None =>
        throw new RuntimeException(new StringBuilder("Element with value ").append(arg).append(" was not found").toString())
      case Some(node) =>
        val parent = node.parent
        val isLeftChild: Boolean = if (parent != null)
          parent.left == node
        else
          false
        if (node.left == null && node.right == null) {
          if (parent != null) { //case where node is the root.root's parent is null
            if (isLeftChild)
              parent.left = null
            else
              parent.right = null
          }
          toReturn = Some(node.parent)
        } else if (node.right == null) {
          if (parent != null) {
            if (isLeftChild)
              parent.left = node.left
            else
              parent.right = node.left
          }

          if (node.left != null)
            node.left.parent = parent

          toReturn = Some(node.parent)
        } else if (node.left == null) {
          if (parent != null) {
            if (isLeftChild)
              parent.left = node.right
            else
              parent.right = node.right
          }

          if (node.right != null)
            node.right.parent = parent

          toReturn = Some(node.parent)
        } else {
          //find the first element in the in-order traversal list.
          val firstInorderNode = getFirstInorder(node.right)
          toReturn = Some(replaceNode(node, firstInorderNode))
        }
    }

    rebalance_after_remove(toReturn.get)
  }

  def getFirstInorder(nde: Node): Node = {
    if (nde == null)
      throw new RuntimeException("Argument can't be null")
    var node = nde
    while (node.left != null)
      node = node.left
    return node
  }

  def binarySearch(node: Node, arg: Int): Option[Node] = {
    var node2 = node
    while (node2 != null) {
      if (node2.value == arg)
        return Option(node2)
      else if (node2.value < arg)
        node2 = node2.right
      else
        node2 = node2.left
    }
    return None
  }

  def height(node: Node): Int = {
    if (node == null)
      -1
    else
      height(node.left).max(height(node.right)) + 1
  }

  private def restructure(node: Node): Unit = {
    if (height(node.left) > height(node.right)) {
      if (height(node.left.left) >= height(node.left.right)) {
        rightRotate(node)
      } else {
        leftRotate(node.left)
        rightRotate(node)
      }
    }
    else {
      if (height(node.right.left) <= height(node.right.right)) {
        leftRotate(node)
      } else {
        rightRotate(node.right)
        leftRotate(node)
      }
    }
  }

  private def rebalance_after_remove(startFrom: Node) = {
    var node = startFrom
    var parent: Node = null
    while (node != null) {
      if (unbalanced(node)) {
        parent = node.parent //node's parent could be changed after restructuring.
        restructure(node)
      } else {
        parent = node.parent
      }
      node = parent
    }
  }

  private def unbalanced(node: Node): Boolean = {
    println("Checking unbalancedness of " + node.value)
    return Math.abs(height(node.right) - height(node.left)) >= 2
  }

  /**
    * Replace the "nodeToReplace" with "nodeToBeReplacedWith(ntbr)" where ntbr's left child is null. It is the first inOrder element after node
    *
    * @param nodeToReplace
    * @param nodeToBeReplacedWith
    */
  private def replaceNode(nodeToReplace: Node, nodeToBeReplacedWith: Node): Node = {
    val ntr = nodeToReplace
    val ntbr = nodeToBeReplacedWith
    if (ntr.right == ntbr) {
      changeParent(ntbr, ntr.left, false)
      if (ntr.parent == null) {
        ntbr.parent = null
        root = ntbr
      } else {
        changeParent(ntr.parent, ntbr, isRightChild(ntr.parent, ntr))
      }
      return ntbr
    } else {
      changeParent(ntbr.parent, ntbr.right, false)
      changeParent(ntbr, ntr.left, false)
      changeParent(ntbr, ntr.right, true)
      val ntbrParent = ntbr.parent
      if (ntr.parent == null) {
        ntbr.parent = null
        root = ntbr
      } else {
        changeParent(ntr.parent, ntbr, isRightChild(ntr.parent, ntr))
      }
      return ntbrParent
    }
  }

  private def isRightChild(parent: Node, child: Node): Boolean = {
    if (parent.right == child)
      true
    else
      false
  }

  private def changeParent(newParent: Node, child: Node, rightChild: Boolean): Unit = {
    if (newParent == null) {
      if (child == null)
        throw new RuntimeException("New parent and child are both null.This should never happen")
      child.parent = null
      root = child
      return
    }
    if (rightChild) {
      newParent.right = child
      if (child != null) {
        child.parent = newParent
      }
    } else {
      newParent.left = child
      if (child != null) {
        child.parent = newParent
      }
    }
  }

  private def rightRotate(node: Node) = {
    val left = node.left
    if (node.parent == null) {
      node.left.parent = null
      root = node.left
    }
    else
      changeParent(node.parent, node.left, isRightChild(node.parent, node))

    changeParent(node, node.left.right, false)
    changeParent(left, node, true)
  }

  private def leftRotate(node: Node) = {
    val right = node.right
    if (node.parent == null) {
      node.right.parent = null
      root = node.right
    }
    else
      changeParent(node.parent, node.right, isRightChild(node.parent, node))

    changeParent(node, node.right.left, true)
    changeParent(right, node, false)
  }
}

object AVLTree extends App {
  val avlTree = new AVLTree
  avlTree.add(0)
  // TreeUtils.printNode(new mutable.StringBuilder(""),avlTree.root)
  /*avlTree.add(1)
  avlTree.add(2)
  avlTree.add(3)
  avlTree.add(4)
  avlTree.add(5) */

  avlTree.add(5)
  avlTree.add(4)
  avlTree.add(3)
  avlTree.add(2)
  avlTree.add(1)
  avlTree.add(6)
  avlTree.add(7)
  //TreeUtils.printSubTreeAtNode(avlTree.root)
  //println("Finished")
  avlTree.remove(2)
  println("done")
  //avlTree.remove(1)
  TreeUtils.printSubTreeAtNode(avlTree.root)
}


