package com.example.bebaagua.model

class CalcularIngestaoDiaria {
    private  val ML_JOVEM = 40.0
    private val ML_ADULTO = 35.0
    private val ML_Idosinho = 30.0
    private val ML_Idoso = 25.0

    private var resutadoMl = 0.0
    private var resutado_total_ml = 0.0

    fun ResultadoMl (): Double{
        return  resutado_total_ml
    }

    fun  CalcularTotal(peso: Double, idade: Int){
   if(idade <=17 ){
       resutadoMl = peso * ML_JOVEM
       resutado_total_ml = resutadoMl
   }else if(idade <=55 ){
            resutadoMl = peso * ML_ADULTO
       resutado_total_ml = resutadoMl
        }else if(idade <=65 ){
       resutadoMl = peso * ML_Idosinho
       resutado_total_ml = resutadoMl
   }else{
       resutadoMl = peso * ML_Idoso
       resutado_total_ml = resutadoMl
   }


    }
}