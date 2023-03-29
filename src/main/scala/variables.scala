import scala.util.Random
import scala.annotation.tailrec
object variables {
  object ex1 {
    // Задать несколько переменных со строками и попробовать переприсвоить им значения.
    var a1 = 5
    var a2 = 3
    a1 = a1 + a2
  }

  object ex2 {
    // Задать несколько значений с числами и попробовать переприсвоить им значения.
    val a1 = 5
    val a2 = 3
    val a3 = a1 + a2
  }

  object ex4 {
    def sum(str1: String, str2: String): String = s"$str1 $str2"
    // Задать функцию для конката строк через пробел через val

    val f1: String => String = inStr => inStr + "1"
    val sum1: (String, String) => String = (inStr1, inStr2) => s"$inStr1 $inStr2"
  }

  object ex5 {
    // Задать функцию высшего порядка, которая будет принимать бинарный оператор над двумя числами и возвращать Double
    def func(f: (Int, Int) => Double): Double = ???

    // А теперь попоробовать задать ее через val
    val func1: (Int, Int) => Double => Double = ???
  }

  object ex6 {
    // В чем проблема данной функции?
    def func(n: Long): Long = {
      if (n == 1) 1
      else if (n % 2 == 0) 1 + func(n / 2)
      else 5 + func(3 * n + 1)
    }

    def func1(n: Long): Long = {
      @tailrec
      def inner(n1: Long, acc: Long): Long = {
        if (n == 1) 1
        else if (n % 2 == 0) inner(n / 2, acc + 1)
        else inner(3 * n + 1, acc + 5)
      }

      inner(n, 0)
    }
  }

  object ex7 {
    // Перепишите функцию через val используя каррирование, при этом сгрупировав b и c в один список аргументов
    def func(a: Int, b: Int, c: String, d: Double): String =
      s"${(a + b) / d} != c"

    def func1(a: Int)(b: Int, c: String)(d: Double): String = ???

    val func2: (Int, String) => Double => String = func1(3)

    // Каким образом можно получить из этой функции частично примененную?
  }
}
