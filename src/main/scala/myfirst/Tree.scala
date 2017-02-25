package myfirst

/**
  * Implementation using case classes of an
  * arithmetic structure, basic (x + x) + (7 + x) as an
  * example.
  *
  * Case classes provide getter/equals/hashCode and a default toString, I believe
  * you have to specify 'var' within the parameters.
  *
  * They also don't require a new keyword
  */
abstract class Tree
case class Sum(var l: Tree, var r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree
