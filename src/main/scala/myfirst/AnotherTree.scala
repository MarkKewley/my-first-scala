package myfirst

/**
  * Another implementation of Tree, less extensible and more
  * difficult to add new classes like say Multiply. Furthermore
  * a tad more difficult to add an eval that returns a Double no?
  */
abstract class AnotherTree {
  type Environment = String => Int
  def eval(t: AnotherTree, env: Environment): Int
  def derive(t: AnotherTree, v: String): AnotherTree
}
case class AnotherSum(l: AnotherTree, r: AnotherTree) extends AnotherTree {
  override def eval(t: AnotherTree, env: Environment): Int = l.eval(l, env) + r.eval(r, env)

  override def derive(t: AnotherTree, v: String): AnotherTree = AnotherSum(l.derive(l, v), r.derive(r, v))
}
case class AnotherVar(n: String) extends AnotherTree {
  override def eval(t: AnotherTree, env: Environment): Int = env(n)

  override def derive(t: AnotherTree, v: String): AnotherTree = if (v == n) AnotherConst(1) else AnotherConst(0)
}
case class AnotherConst(c: Int) extends AnotherTree {
  override def eval(t: AnotherTree, env: Environment): Int = c

  override def derive(t: AnotherTree, v: String): AnotherTree = AnotherConst(0)
}

