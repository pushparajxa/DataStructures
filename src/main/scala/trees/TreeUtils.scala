
package trees

import scala.collection.mutable

object TreeUtils {
  /*
  * Print a node in depth-first manner
  * 1
├──5
│  ├──9
│  │  ├──*
│  │  └──*
│  └──*
└──3
   ├──*
   └──7
      ├──*
      └──*
   */
  def printSubTreeAtNode(node: Node): Unit = {
    printNode(new mutable.StringBuilder(""), node)
  }

  private def printNode(suffix: StringBuilder, node: Node): Unit = {
    suffix.foreach(c => {print(c)
      if(c.equals('├') || c.equals('└')){
        print("──")
      }
      else{
        print("  ")
      }
    })

    if(suffix.nonEmpty){
      if(suffix.last.equals('├')) {
        suffix.replace(suffix.length - 1, suffix.length, "│")
      }
      else if (suffix.last.equals('└')) {
        suffix.replace(suffix.length - 1, suffix.length, " ")
      }
    }
    if(node!=null) {
      println(node.value)
      printNode(new mutable.StringBuilder(suffix.toString()).append("├"), node.right)

      printNode(new mutable.StringBuilder(suffix.toString()).append("└"), node.left)
    }else{
      println("*")
    }
  }
}
