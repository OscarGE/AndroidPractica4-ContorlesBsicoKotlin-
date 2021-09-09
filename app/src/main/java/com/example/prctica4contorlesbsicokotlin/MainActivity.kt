package com.example.prctica4contorlesbsicokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Integer.parseInt
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var calculo: String = "" //Almacena el operador ha realizar
    private var banNum: Boolean = false //Bandera que inica cuando usar num1 o num2
    private var num1: String = ""
    private var num2: String = ""
    private var resultado: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculadora)
        /*Declaración de variables*/
        val btn_cero: Button = findViewById(R.id.btn_cero)
        val btn_uno: Button = findViewById(R.id.btn_uno)
        val btn_dos: Button = findViewById(R.id.btn_dos)
        val btn_tres: Button = findViewById(R.id.btn_tres)
        val btn_cuatro: Button = findViewById(R.id.btn_cuatro)
        val btn_cinco: Button = findViewById(R.id.btn_cinco)
        val btn_seis: Button = findViewById(R.id.btn_seis)
        val btn_siete: Button = findViewById(R.id.btn_siete)
        val btn_ocho: Button = findViewById(R.id.btn_ocho)
        val btn_nueve: Button = findViewById(R.id.btn_nueve)
        val btn_divi: Button = findViewById(R.id.btn_divi)
        val btn_multi: Button = findViewById(R.id.btn_multi)
        val btn_rest: Button = findViewById(R.id.btn_rest)
        val btn_suma: Button = findViewById(R.id.btn_suma)
        val btn_igual: Button = findViewById(R.id.btn_igual)
        val btn_ac: Button = findViewById(R.id.btn_ac)
        val btn_mm: Button = findViewById(R.id.btn_mm)
        val btn_porcen: Button = findViewById(R.id.btn_porcen)
        val btn_punto: Button = findViewById(R.id.btn_punto)
        val txt_dispaly: TextView = findViewById(R.id.txt_dispaly)
        //Asignación de eventos
        //Números
        btn_cero.setOnClickListener{(pressNum(btn_cero.text.toString(), txt_dispaly))}
        btn_uno.setOnClickListener{(pressNum(btn_uno.text.toString(), txt_dispaly))}
        btn_dos.setOnClickListener{(pressNum(btn_dos.text.toString(), txt_dispaly))}
        btn_tres.setOnClickListener{(pressNum(btn_tres.text.toString(), txt_dispaly))}
        btn_cuatro.setOnClickListener{(pressNum(btn_cuatro.text.toString(), txt_dispaly))}
        btn_cinco.setOnClickListener{(pressNum(btn_cinco.text.toString(), txt_dispaly))}
        btn_seis.setOnClickListener{(pressNum(btn_seis.text.toString(), txt_dispaly))}
        btn_siete.setOnClickListener{(pressNum(btn_siete.text.toString(), txt_dispaly))}
        btn_ocho.setOnClickListener{(pressNum(btn_ocho.text.toString(), txt_dispaly))}
        btn_nueve.setOnClickListener{(pressNum(btn_nueve.text.toString(), txt_dispaly))}
        //Operadores
        btn_divi.setOnClickListener{(pressOper(btn_divi.text.toString(), txt_dispaly))}
        btn_multi.setOnClickListener{(pressOper(btn_multi.text.toString(), txt_dispaly))}
        btn_rest.setOnClickListener{(pressOper(btn_rest.text.toString(), txt_dispaly))}
        btn_suma.setOnClickListener{(pressOper(btn_suma.text.toString(), txt_dispaly))}
        btn_igual.setOnClickListener{(pressIgual(txt_dispaly))}
        //Otros
        btn_ac.setOnClickListener{(clean(txt_dispaly))}
        btn_punto.setOnClickListener{(pressPunto(txt_dispaly))}
    }

    fun pressNum(digito: String, txt_dispaly: TextView){
        //Si se presionan los números después de haber dado el resultado
        if(resultado!=null){
            clean(txt_dispaly)
        }
        //Para reemplazar el cero
        if(txt_dispaly.text=="0"){
            txt_dispaly.text = ""
        }
        //Si auno no se ha almacenado ningún número
        if(banNum==false){
            num1 += digito
        }else{//Si ya se ha almacenado un número
            if(num2.isEmpty()){
                txt_dispaly.text=""
            }
            num2 += digito
        }
        //Muestra los digitos en pantalla
        txt_dispaly.text= "${txt_dispaly.text}$digito"

    }
    fun pressPunto(txt_dispaly: TextView){
        if(!(txt_dispaly.text.contains("."))){
            //Para determinar si se esta ingresado el num1 o num2
            if(banNum==false){
                num1 += "."
            }else{
                num2 += "."
            }
            //Muestra los digitos en pantalla
            txt_dispaly.text= "${txt_dispaly.text}."
        }
    }
    fun pressOper(oper: String, txt_dispaly: TextView){
        //Se determina el operador que se presiono
        if(!num1.isEmpty()){
            when(oper){
                "÷" -> calculo="÷"
                "×" -> calculo="×"
                "−" -> calculo="−"
                "+" -> calculo="+"
            }
            //Si se presionan los operadores despues de haber dado el resultado
            if(resultado!=null){
                num1=resultado.toString()
                resultado=null
                num2=""
            }
            banNum=true
        }
    }
    fun pressIgual(txt_dispaly: TextView){
        //Se determina el operador para realizar la operación
        if(!num1.isEmpty() || !num2.isEmpty()){
            when(calculo){
                "÷" -> resultado= num1.toDouble() / num2.toDouble()
                "×" -> resultado= num1.toDouble() * num2.toDouble()
                "−" -> resultado= num1.toDouble() - num2.toDouble()
                "+" -> resultado= num1.toDouble() + num2.toDouble()
                else -> resultado= 0.0
            }
            txt_dispaly.text= String.format("%.4f", resultado)
        }
    }
    fun clean(txt_dispaly: TextView){
        txt_dispaly.text="0"
        num1=""
        num2=""
        resultado=null
        banNum=false
    }
}