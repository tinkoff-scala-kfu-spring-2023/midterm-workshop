import scala.util.Success
import scala.util.Failure
import scala.util.Try
object classes {
  object ex1 {
    // Создать класс Email, конструктор которого будет принимать имя учетной записи и домен, а в теле класса собирать это в один email с @

    class Email(name: String, domain: String) {
      val fullEmail = s"$name${Email.Sobaka}$domain"
    }
    object Email {
      val Sobaka: String = "@"
    }
    val email = new Email("nikita", "ru")
    // Вынести @ как константу в объект компаньон и использовать уже из него
  }
  object ex2 {
    // Дать доступ к полям конструктора класса
    class Email(val name: String, val domain: String) {

    }

    val email = new Email("nikita", "ru")
  }
  object ex3 {
    class Email(name: String, domain: String) {
      val fullEmail = s"$name${Email.Sobaka}$domain"
    }

    object Email {
      val Sobaka: String = "@"

      val allowedDomain = Set("com", "ru")

      def apply(name: String, domain: String): Option[Email] = {
        if (allowedDomain.contains(domain)) Some(new Email(name, domain))
        else None
      }

      class NotSupportedDomain(domain: String)

      def make(name: String, domain: String): Either[NotSupportedDomain, Email] = 
        if (allowedDomain.contains(domain)) Right(new Email(name, domain))
        else Left(new NotSupportedDomain(domain))

      def makeTry(name: String, domain: String): Try[Email] = 
        if (allowedDomain.contains(domain)) Success(new Email(name, domain))
        else Failure(new RuntimeException(domain))
    }
    // Задать внутри объекта компаньона смарт конструктор, допустимые домены, которые проверять в нем
  }

  object ex4 {
    case class Email(name: String, domain: String)
    // Перейти на использование кейс класса

    // Узнать, что будет в результате сравнения объектов классов и кейс классов
  }

  object ex5 {
    import ex4._
    // Патмат с кейс классом по доменам: если домен равен "com", вывести "загнивающий запад", "ru" - "родная гавань", "cn" - "друг-коммунист". Сначала пропустить один из доменов и запринтить
    // Запринтить результат
    // Использовать патмат в объявлении функции

    val i = 5
    i match {
      case 1 => "one"
      case 2 => "two"
    }

    val email = Email("name", "com")

    email match {
      case Email("Nikita", "com") => ???
      case Email(_, "com") => print("загнивающий запад")
      case Email(_, "ru") => print("родная гавань")
      case Email(_, "cn") => print("друг-коммунист")
      case _ => print("other")
    }

    val tuple = ("str", 1)
    val (str, i2) = tuple
  }

  object ex6 {
    // Разобрать кейс класс на значения через патмат
  }
}
