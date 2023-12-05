package taller4

import org.scalameter.{Warmer, withWarmer}

object main {
  type Matriz = Vector[Vector[Int]]
  val obj = new Taller4()

  //IMPLEMENTACIONES DE FUNCIONES DE BENCHMARKING

  def main(args: Array[String]): Unit = {
    //Calentamiento
    println(
      withWarmer(new Warmer.Default) measure {
        (1 to 100000000).toArray
      }
    )

    print("Mutilplicacion de matrices recursivo y recurivo paralelo: \n")
    for {
    //la matriz debe ser de tamano 2^n hasta 1024x1024 osea 2^10
      i <- 1 to 10
      m1 = obj.matrizAlAzar(math.pow(2, i).toInt, 2)
      m2 = obj.matrizAlAzar(math.pow(2, i).toInt, 2)
    } yield (println("Tamano de la matriz: " + math.pow(2, i).toInt +
      "\nTiempo secuencial, Paralelo, aceleracion: " + obj.Benchmark.compararAlgoritmos2(obj.multMatrizRec, obj.multMatrizRecPar)(m1, m2)))

    /*
    print("\n")
    print("Mutilplicacion de matrices normal y paralelo: \n")
    for {
      i <- 1 to 10
      m1 = obj.matrizAlAzar(math.pow(2, i).toInt, 2)
      m2 = obj.matrizAlAzar(math.pow(2, i).toInt, 2)
    } yield (println("Tamano de la matriz: " + math.pow(2, i).toInt +
      "\nTiempo secuencial, Paralelo, aceleracion: " + obj.Benchmark.compararAlgoritmos2(obj.multMatriz, obj.multMatrizPar)(m1, m2)))



    print("\n")
    print("Mutilplicacion con el algoritmo de Strassen y Strassen Paralelo: \n")
    for {
      //la matriz debe ser de tamano 2^n hasta 1024x1024 osea 2^10
      i <- 1 to 10
      m1 = obj.matrizAlAzar(math.pow(2, i).toInt, 2)
      m2 = obj.matrizAlAzar(math.pow(2, i).toInt, 2)
    } yield (println("Tamano de la matriz: " + math.pow(2, i).toInt +
      "\nTiempo secuencial, Paralelo, aceleracion: " + obj.Benchmark.compararAlgoritmos2(obj.multStrassen, obj.multStrassenPar)(m1, m2)))

    print("Multiplicacion de productoPunto: \n")
    print(obj.Benchmark.compararProdPunto(100))

     */


  }
}
