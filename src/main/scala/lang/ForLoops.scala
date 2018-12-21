
package lang
import scala.util.control.Breaks
import scala.util.control.Breaks._

object ForLoops {
  def main(args: Array[String]): Unit = {
    println("Hello")
    var i=0
    while(i<10){
      println(i)
      i=i+3
    }

    println()

    for(i<- 1 to 4)
      println(i)

    println()

    for(i<- 1 until  4)
      println(i)

    println()

    for(i<- 1 to 10; n =i; if i%2 == 0 )
      println(n)

    for(i<- 1 to 3; j<- 7 to 9 ; n =i )
      println("i="+i + " j="+j)

    //single break
    breakable{
      for(i<-1 to 3){
        if(i==2){
          println(i)
          break
        }
      }
    }

    println("Inner and Outer Breaks")

  val outer = new Breaks
  val inner = new Breaks
  outer.breakable(
    for(i<-1 to 3 ){
      inner.breakable(
        for(j<-5 to 7 ){
          if(j==6){
            inner.break()
          }
          if(i==2 )
            outer.break()
          println("i="+i +" j="+j)
        }
      )
    }
  )

  }

}






