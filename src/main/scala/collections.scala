object collections {
  object ex1 {
    // Из исходного листа с числами сделать новый лист, с условием: если это четное число, то превращаем его в строку и повторяем ее 3 раза, иначе заменять значение на "-1"
    List(1, 2, 3).flatMap {
      case i if i % 2 == 0 => List.fill(3)(i.toString)
      case i               => List(i.toString)
    }
  }

  object ex2 {
    // Отфильтровать из Map[Int, Int] значения с нечетными ключами и вернуть квадраты оставшихся значений

    Map(
        (1 -> 1),
        (2 -> 2),
        (3 -> 3)
    ).collect { case (key, value) if key % 2 == 0 => value*value }
  }

  object ex3 {
    // Свернуть Map[Int, Int] в сумму значений, у которых четные ключи
    Map(
        (1 -> 1),
        (2 -> 2),
        (3 -> 3)
    ).foldLeft(0) {
        case (acc, (key, value)) if key % 2 == 0 => acc + value
        case (acc, _) => acc
    }
  }
}
