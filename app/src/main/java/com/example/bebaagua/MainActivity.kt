package com.example.bebaagua

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
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
    private lateinit var Lembrete: Button
    private lateinit var Alarme: Button
    private lateinit var hora: TextView
    private lateinit var Minutos : TextView

    private fun IniciarComponentes() {
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        buttonCalcular = findViewById(R.id.buttonCalcular)
        textCalculo = findViewById(R.id.textCalculo)
        redefinir = findViewById(R.id.redefinir)
        Lembrete = findViewById(R.id.Lembrete)
        Alarme = findViewById(R.id.Alarme)
        hora = findViewById(R.id.text_hora)
        Minutos = findViewById(R.id.Minutos)
    }
    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0

    lateinit var  timePickerDialog: TimePickerDialog
    lateinit var calendario : Calendar
    var horaAtual = 0
    var minutosAtuais = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()

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
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_descriçao)
                .setPositiveButton("ok", {dialogInterface, i ->
                    edit_peso.setText("")
                    edit_idade.setText("")
                    textCalculo.text = ""

                })

            alertDialog.setNegativeButton("cancelar", null)

            val dialog = alertDialog.create()
            dialog.show()

        }

        Lembrete.setOnClickListener {

            calendario = Calendar.getInstance()
            horaAtual = calendario.get(Calendar.HOUR_OF_DAY)
            minutosAtuais = calendario.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this, { timePicker: TimePicker, hourOfDay: Int, minutes: Int ->
                    hora.text = String.format("%02d", hourOfDay)
                    Minutos.text = String.format("%02d", minutes)

                }, horaAtual, minutosAtuais, true)
            timePickerDialog.show();
        }

        Alarme.setOnClickListener{

            if(!hora.text.toString().isEmpty() && !Minutos.text.toString().isEmpty()){
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, hora.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, Minutos.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.Hora_Agua))

                if (intent.resolveActivity(packageManager) !=null){
                    startActivity(intent)
                }
            }
        }


    }
}