package myfirst

/**
  * Created by markkewley on 2/25/17.
  */
abstract class Tree
case class Sum(var l: Tree, var r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree
