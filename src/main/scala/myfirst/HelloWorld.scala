package myfirst

import java.util.{Date, Locale}
import java.text.DateFormat._
import java.util

/**
  * Just a playground utilizing multiple features of Scala
  */
object HelloWorld {
  type Function = String => () => Unit
  type Environment = String => Int

  // A more concrete way imo of using a Map by using type which is essentially functions!
  val otherFunctions: Function = {
    case "func1" => () => println("FunctionOne")
    case "func2" => () => println("FunctionTwo")
  }

  // Define a function map utilizing Hashmap, we can see that functions are objects too
  val functionMap = new util.HashMap[String, (Int, String) => Unit]
  functionMap.put("func1", timeFlies)
  functionMap.put("func2", (arg1, arg2) => println(s"Another random func with $arg1 and $arg2"))

  /**
    * Print out the current timestamp in French, this is
    * just an example of utilizing Java packages in scala
    */
  def frenchDate(): Unit = {
    val now = new java.util.Date
    val df = getDateInstance(LONG, Locale.FRENCH)
    println(df format now)
  }

  /**
    * Every second execute either the callback or something from the
    * functionMap
    */
  def oncePerSecond(callback: (Int, String) => Unit): Unit = {
    var counter = 0
    while (counter != 6) {
      if (counter % 2 == 0) {
        if (counter == 4) {
          callback(counter, "Callback being called")
        } else {
          functionMap.get("func2")(counter, "WOOOT RANDOM")
        }
      } else {
        functionMap.get("func1")(counter, "Ermagawwd")
      }

      counter += 1
      Thread sleep 1000
    }
  }

  /**
    * The callback, this could be an anonymous function as well
    */
  def timeFlies(counter: Int, random: String): Unit = {
    println(s"time flies like an arrow...$counter")
    println(s"Random Message: $random")
  }

  /**
    * Execute the Functions type here
    */
  def executeFunction(func: String): Unit = {
    otherFunctions(func)()
  }

  /**
    * Just create and print out a ComplexNumber using toString and getters
    */
  def printComplexNumber(real: Double, imaginary: Double): Unit = {
    val complexNumber = new ComplexNumber(real, imaginary)
//    println("ComplexNumber -> real: " + complexNumber.getReal() + " imaginary: " + complexNumber.getImaginary())
    // no parenthesis
    println("ComplexNumber -> real: " + complexNumber.re + " imaginary: " + complexNumber.im)
    println(complexNumber)
  }

  /**
    * Using Pattern Matching to match the correct Tree with
    * how to execute the eval function.
    */
  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }

  /**
    * Using Pattern Matching to match the correct Tree with
    * how to execute the derive function.
    */
  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l,r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if v == n => Const(1)
    case _ => Const(0) // wildcard, match anything else
  }

  /**
    * Actual implementation of how the Tree works using all of the functions
    * defined here.
    */
  def doSomeTreeStuff(): Unit = {
    val exp: Tree = Sum(Sum(Var("x"), Var("x")), Sum(Const(7), Var("y")))
    val env: Environment = {
      case "x" => 5
      case "y" => 7
    }

    println("Expression: " + exp)
    println("Evaluation with x=5, y=7", eval(exp, env))
    println("Derivative relative to x:\n" + derive(exp, "x"))
    println("Derivative relative to y:\n" + derive(exp, "y"))
  }

  /**
    * Alternate way using the eval/derive defined within the Abstract class
    * of Tree
    */
  def doSomeAnotherTreeStuff(): Unit = {
    val exp: AnotherTree = AnotherSum(AnotherSum(AnotherVar("x"), AnotherVar("x")), AnotherSum(AnotherConst(7), AnotherVar("y")))
    val env: Environment = {
      case "x" => 5
      case "y" => 7
    }

    println("Expression: " + exp)
    println("Evaluation with x=5, y=7", exp.eval(exp, env))
    println("Derivative relative to x:\n" + exp.derive(exp, "x"))
    println("Derivative relative to y:\n" + exp.derive(exp, "y"))
  }

  /**
    * Using Generics, similar to Java
    */
  def doSomeReferences(): Unit = {
    val cell = new Reference[Int]
    cell.set(13)
    println("Reference contains the half of " + (cell.get * 2))
  }

  /**
    * The main source of execution
    */
  def main(args: Array[String]): Unit = {
    frenchDate()

    printComplexNumber(2.0, 5.2)

    oncePerSecond(timeFlies)

    executeFunction("func1")
    executeFunction("func2")

    doSomeTreeStuff()

    doSomeReferences()
  }

}
