package myfirst

import java.util.{Date, Locale}
import java.text.DateFormat._
import java.util

/**
  * Created by markkewley on 2/24/17.
  */
object HelloWorld {
  type Function = String => (Int, String) => Unit
  type Environment = String => Int

  val functionMap = new util.HashMap[String, (Int, String) => Unit]
  functionMap.put("func1", timeFlies)
  functionMap.put("func2", (arg1, arg2) => println(s"Another random func with $arg1 and $arg2"))

  def frenchDate(): Unit = {
    val now = new Date()
    val df = getDateInstance(LONG, Locale.FRENCH)
    println(df format now)
  }

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

      counter match {
        case 0 => println("Yes")
      }

      counter += 1
      Thread sleep 1000
    }
  }

  def timeFlies(counter: Int, random: String): Unit = {
    println(s"time flies like an arrow...$counter")
    println(s"Random Message: $random")
  }

  def printComplexNumber(real: Double, imaginary: Double): Unit = {
    val complexNumber = new ComplexNumber(real, imaginary)
//    println("ComplexNumber -> real: " + complexNumber.getReal() + " imaginary: " + complexNumber.getImaginary())
    // no parenthesis
    println("ComplexNumber -> real: " + complexNumber.re + " imaginary: " + complexNumber.im)
    println(complexNumber)
  }

  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }

  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l,r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if v == n => Const(1)
    case _ => Const(0)
  }

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

  def main(args: Array[String]): Unit = {
    frenchDate()
    printComplexNumber(2.0, 5.2)
    oncePerSecond(timeFlies)
    doSomeTreeStuff()
    println("ANOTHER TREE -----")
    doSomeAnotherTreeStuff()
  }

}
