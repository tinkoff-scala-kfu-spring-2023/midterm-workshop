object typeSystem {
  object ex1 {
    // Задать через ADT тип Bool, у которого будет 3 метода and, or, negate

    sealed trait Bool {
      def and(other: Bool): Bool
      def or(other: Bool): Bool
      def negate(): Bool
    }
    object Bool {
      case object True extends Bool {

        override def and(other: Bool): Bool = other match {
          case True  => True
          case False => False
        }

        override def or(other: Bool): Bool = True

        override def negate(): Bool = False

      }

      case object False extends Bool {

        override def and(other: Bool): Bool = False

        override def or(other: Bool): Bool = other

        override def negate(): Bool = True

      }
    }
  }

  object ex2 {
    trait CanFly {
      def fly(): Unit = println("I'm flying!")
    }

    trait CanSwim {
      def swim(): Unit = println("I'm swimming!")
    }

    trait CanRun {
      def run(): Unit = println("I'm running!")
    }

    case class Bird(wingsLength: Int) extends CanFly with CanRun {
      override def fly(): Unit = println("I'm a bird, so I'm flying!")
      override def run(): Unit = println("I'm a bird, so I'm running!")
    }

    case class Fish(swimSpeed: Double) extends CanSwim {
      def swimDeep(): Unit = println("I'm a fish, so I'm swimming deep!")
    }

    case object Mammal extends CanRun with CanSwim {
      override def run(): Unit = println("I'm a mammal, so I'm running faster!")
    }

    val bird = Bird(123)
    val fish = Fish(0.5)
    val mammal = Mammal
    val animal1 = if (fish.swimSpeed < 1) mammal else fish
    val animal2 = if (fish.swimSpeed > 1) fish else bird

    // Какие типы будут у animal1 и animal2?
  }

  object ex3_1 {
    sealed trait Animal
    case class Cat() extends Animal
    case class Dog() extends Animal

    abstract class MyListExt[+T] {
      def contains[B >: T](elem: B): Boolean = this match {
        case ConsExt(x, _) if x == elem => true
        case ConsExt(x, xs)             => xs.contains(x)
        case NilExt                     => false
      }
    }

    case object NilExt extends MyListExt[Nothing]
    case class ConsExt[+T](head: T, tail: MyListExt[T]) extends MyListExt[T]

    val cat = Cat()
    val list: MyListExt[Animal] = ConsExt(cat, ConsExt(Dog(), NilExt))
  }

  object ex3_2 {
    // Модифицировать Check так, чтобы ое смог выполнять проверки имплементации, которая выше по иерархии, на имплементации которая ниже val checks: List[Check[Employee]] = List(Check[Person], Check[Employee])
    class Check[-A](expr: A => Boolean) {
      def check(a: A): Boolean = expr(a)
    }

    class Person(val name: String)
    class Employee(name: String, val salary: Int) extends Person(name)
    class Manager(name: String, salary: Int, val manages: List[Employee])
        extends Employee(name, salary)

    val checkPerson = new Check[Person](person => person.name.length() > 3) {
      override def check(a: Person): Boolean = ???
    }

    val checkEmpl = new Check[Employee](empl => empl.salary > 30000) {
      override def check(a: Employee): Boolean = ???
    }

    val checkManager = new Check[Manager](manager => !manager.manages.isEmpty) {
      override def check(a: Manager): Boolean = ???
    }

    val listCheck: List[Check[Manager]] = List(checkManager, checkEmpl, checkPerson)
  }
}
