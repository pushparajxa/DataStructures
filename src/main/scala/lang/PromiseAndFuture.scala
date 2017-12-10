
package lang

import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object PromiseAndFuture {

  def main(args: Array[String]): Unit = {
    println("hello")
    val promise: Promise[String] = Promise()

    val future1 = promise.future

    val future2 = promise.future

    future1 onComplete {
      case Success(result) => println("From future1 " + result)
      case Failure(t) => println("Failure from future1 +"+t)
    }

    future1 onComplete {
      case Success(result) => println("From future2 " + result)
      case Failure(t) => println("Failure from future1 +"+t)
    }

    println("Fulfilling the promise")
    promise success("Promise fulfilled")


  }
}
