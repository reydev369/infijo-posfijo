import java.util.*

class Comprobar(
    val expresion :String,
) {
    val E = Stack<String>() //Pila entrada
    val P = Stack<String>() //Pila de operandos


    fun Algoritmoevaluar() {
        val expr = expresion // expresion posfija
        val post = expr.split(" ".toRegex()).toTypedArray()

        //Declaración de las pilas


        //Añadir post (array) a la Pila de entrada (E)
        for (i in post.indices.reversed()) {
            E.push(post[i])
        }
        val operadores = "+-*/%^"
        while (!E.isEmpty()) {
            if (operadores.contains("" + E.peek())) {
                P.push(evaluar(E.pop(), P.pop(), P.pop()).toString() + "")
            } else {
                P.push(E.pop())
            }
        }
    }
    fun evaluar(op: String, n2: String, n1: String): Int {
        val num1 = n1.toInt()
        val num2 = n2.toInt()
        if (op == "+") return num1 + num2
        if (op == "-") return num1 - num2
        if (op == "*") return num1 * num2
        if (op == "/") return num1 / num2
        if (op == "^") return Math.pow(num1.toDouble(),num2.toDouble()).toInt()
        return if (op == "%") num1 % num2 else 0
    }

fun resultado():String{
    return P.peek()
}

}