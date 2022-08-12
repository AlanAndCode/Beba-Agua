package com.example.bebaagua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.PhoneAccount.builder
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.bebaagua.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder
import java.util.stream.LongStream.builder


class MainActivity : AppCompatActivity() {
    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var textCalculo: TextView
    private lateinit var redefinir: ImageButton
    private fun IniciarComponentes() {
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        buttonCalcular = findViewById(R.id.buttonCalcular)
        textCalculo = findViewById(R.id.textCalculo)
        redefinir = findViewById(R.id.redefinir)
    }
    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()
/*formula calculo agua
ate 17 anos 40 ml por cada kg
18 a 55 anos 35 ml por cada kg
56 a 65 anos 30 ml por cada kg
mais 66 25 ml por cada kg

multiplicar peso em kg por quantidade de ml para ober resultado em Ml
 */

        buttonCalcular.setOnClickListener {
            if (edit_peso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.Informe_Peso, Toast.LENGTH_SHORT).show()
            } else if (edit_idade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.Informe_Idade, Toast.LENGTH_SHORT).show()
            } else {
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
                calcularIngestaoDiaria.CalcularTotal(peso, idade)
                resultadoMl = calcularIngestaoDiaria.ResultadoMl()
                val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formatar.isGroupingUsed = false
                textCalculo.text = resultadoMl.toString() + " " + "ml"

            }

        }

        redefinir.setOnClickListener{

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dia)


        }





    }
}