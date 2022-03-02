import java.util.*

object Main {

    @JvmStatic

    fun main(args: Array<String>) {

        //Entrada de datos

            println("Ingresa una expresión infija: ")
            val leer = Scanner(System.`in`)

            //Depurar la expresion algebraica
            val expr = depurar(leer.nextLine())
            val arrayInfix = expr.split(" ".toRegex()).toTypedArray()

            //Declaración de las pilas
            val infija = Stack<String>() //Pila entrada
            val pila = Stack<String>() //Pila temporal para operadores
            val posfija = Stack<String>() //Pila salida

            //Añadir la array a la Pila de entrada (E)
            for (i in arrayInfix.indices.reversed()) {
                infija.push(arrayInfix[i])
            }
            try {
                //Algoritmo Infijo a Postfijo
                while (!infija.isEmpty()) {
                    when (pref(infija.peek())) {
                        1 -> pila.push(infija.pop())
                        3, 4 -> {
                            while (pref(pila.peek()) >= pref(infija.peek())) {
                                posfija.push(pila.pop())
                            }
                            pila.push(infija.pop())
                        }
                        2 -> {
                            while (pila.peek() != "(") {
                                posfija.push(pila.pop())
                            }
                            pila.pop()
                            infija.pop()
                        }
                        else -> posfija.push(infija.pop())
                    }
                }

                //Eliminacion de `impurezas´ en la expresiones algebraicas
                val infix = expr.replace(" ", "")
                val postfix = posfija.toString().replace("[\\]\\[,]".toRegex(), "")

                //Mostrar resultados:
                println("Expresion Infija: $infix")
                println("Expresion Postfija: $postfix")

                var comp = Comprobar(postfix)
                //comp.agregar()
                comp.Algoritmoevaluar()

                println("Evaluacion: " +comp.resultado())

            } catch (ex: Exception) {
                println("Error en la expresión algebraica")
                System.err.println(ex)
            }




    }

    //Depurar expresión algebraica
    private fun depurar(s: String): String {
        var s = s
        s = s.replace("\\s+".toRegex(), "") //Elimina espacios en blanco
        s = "($s)"
        val simbols = "+-*/()^"
        var str = ""

        //Deja espacios entre operadores
        for (i in 0 until s.length) {
            if (simbols.contains("" + s[i])) {
                str += " " + s[i] + " "
            } else str += s[i]
        }
        return str.replace("\\s+".toRegex(), " ").trim { it <= ' ' }
    }

    //Jerarquia de los operadores
    private fun pref(op: String): Int {
        var prf = 99
        if (op == "^") prf = 5
        if (op == "*" || op == "/") prf = 4
        if (op == "+" || op == "-") prf = 3
        if (op == ")") prf = 2
        if (op == "(") prf = 1
        return prf
    }


}