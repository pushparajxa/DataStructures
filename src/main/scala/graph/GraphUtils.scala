
package graph

import java.util

import scala.collection.mutable

class GraphUtils {

}

class Vertex(val name:String){
  val edgeList:java.util.ArrayList[Edge] = new util.ArrayList[Edge]
}

class Edge(val end1:Vertex, val end2:Vertex, val weight:Int)
