@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val ending: String
    val ageInStr = age.toString().reversed()
    if (ageInStr.length == 1 || ageInStr[1] !in "1") {
        if (ageInStr[0] in "1") {
            ending = " год"
        } else if (ageInStr[0] in "234") {
            ending = " года"
        } else {
            ending = " лет"
        }
    } else {
        ending = " лет"
    }
    return (age.toString() + ending)
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1: Double = t1 * v1
    val s2: Double = t2 * v2
    val s3: Double = t3 * v3
    val sHalf: Double = (s1 + s2 + s3) / 2
    return when {
        sHalf <= s1 -> sHalf * t1 / s1
        sHalf - s1 <= s2 -> t1 + (sHalf - s1) * t2 / s2
        else -> t1 + t2 + (sHalf - s1 - s2) * t3 / s3
    }
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if ((kingX == rookX1 || kingY == rookY1) && kingX != rookX2 && kingY != rookY2) return 1
    if ((kingX == rookX2 || kingY == rookY2) && kingX != rookX1 && kingY != rookY1) return 2
    if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) return 3
    else return 0
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    return when {
        ((kingX == rookX || kingY == rookY) && (kotlin.math.abs(kingX - bishopX) == kotlin.math.abs(kingY - bishopY))) -> 3
        ((kingX != rookX && kingY != rookY) && (kotlin.math.abs(kingX - bishopX) == kotlin.math.abs(kingY - bishopY))) -> 2
        ((kingX == rookX || kingY == rookY) && (kotlin.math.abs(kingX - bishopX) != kotlin.math.abs(kingY - bishopY))) -> 1
        else -> 0
    }

}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val cosA = (a * a - b * b + c * c) / (2 * a * c)
    val cosB = (a * a + b * b - c * c) / (2 * a * b)
    val cosC = (-a * a + b * b + c * c) / (2 * c * b)
    return when {
        (a + b <= c) || (c + b <= a) || (a + c <= b) -> -1
        (cosA > 0.0 && cosB > 0.0 && cosC > 0.0) -> 0
        (cosA == 0.0 || cosB == 0.0 || cosC == 0.0) -> 1
        else -> 2
    }
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (c >= a && c <= b && d >= a && d <= b) {
        return d - c
    } else if (c <= a && d >= a && d <= b) {
        return d - a
    } else if (c <= a && d >= b) {
        return b - a
    } else if (c >= a && c <= b && d >= b) {
        return b - c
    } else return -1
}
