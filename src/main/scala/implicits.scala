object implicits {
    implicit val str: String = ???
  object ex1 {
    def pow(base: Int, exponent: Int): Int =
      Math.pow(base.toDouble, exponent.toDouble).toInt

    // e.g. `concat(123, 456) == 123456`.
    def concat(a: Int, b: Int): Int = s"$a$b".toInt

    def mean(list: List[Int]): Double = list.sum / list.size

    // Что общего у всех этих методов? Они все работают с Int
    def func(implicit context: Int) = ???

    object a {
      implicit val ctx: Int = ???
    }
    object b {
      implicit val ctx2: Int = ???
    }
    object e {
      implicit def abc(str: String): BigDecimal = BigDecimal(str)
      implicit def abcd(str: String): BigDecimal = BigDecimal(str)

      def func(int: BigDecimal): Int = ???

      def func1(implicit int: Int): Int = ???

    }

    implicit class RichInt(i: Int) {
      def pow(exponent: Int): Int =
        Math.pow(i.toDouble, exponent.toDouble).toInt

      // e.g. `concat(123, 456) == 123456`.
      def concat(b: Int): Int = s"$i$b".toInt

      def mean(tail: List[Int]): Double = (i :: tail).sum / (tail.size + 1)
    }

    3 pow 2
  }

  object ex2 {
    import ex1.a._

    def func1(implicit int: String): Int = ???

    func1
  }
}
