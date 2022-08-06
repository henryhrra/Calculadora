package com.hrra.calculadora
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.Statusbar)
        }
        val TextView_Ecuacion = findViewById<TextView>(R.id.ecu)
        val TextView_Respuesta = findViewById<TextView>(R.id.respuesta)
        val button_AC = findViewById<Button>(R.id.buttonAC)
        val button_Delet = findViewById<Button>(R.id.buttonDelet)
        val button_Porcentaje = findViewById<Button>(R.id.buttonPorcentaje)
        val button_Dividir = findViewById<Button>(R.id.buttonEntre)
        val button_Siete = findViewById<Button>(R.id.buttonSiete)
        val button_Ocho = findViewById<Button>(R.id.buttonOcho)
        val button_Nueve = findViewById<Button>(R.id.buttonNueve)
        val button_Multiplicacion = findViewById<Button>(R.id.buttonPor)
        val button_Cuatro = findViewById<Button>(R.id.buttonCuatro)
        val button_Cinco = findViewById<Button>(R.id.buttonCinco)
        val button_Seis = findViewById<Button>(R.id.buttonSeis)
        val button_Resta = findViewById<Button>(R.id.buttonMenos)
        val button_Uno = findViewById<Button>(R.id.buttonUno)
        val button_Dos = findViewById<Button>(R.id.buttonDos)
        val button_Tres = findViewById<Button>(R.id.buttonTres)
        val button_Suma = findViewById<Button>(R.id.buttonMas)
        val button_Cero = findViewById<Button>(R.id.buttonCero)
        val button_Punto = findViewById<Button>(R.id.buttonPunto)
        val button_Igual = findViewById<Button>(R.id.buttonIgual)
        var punto = false
        button_AC.setOnClickListener{
            TextView_Ecuacion.text = ""
            TextView_Respuesta.text = ""
            punto = false
        }
        button_Delet.setOnClickListener{
            if((TextView_Ecuacion.text as String).endsWith(".")){
                punto = false
            }
            TextView_Ecuacion.text = Borrar(TextView_Ecuacion)
            if((TextView_Ecuacion.text as String).isEmpty()){
                TextView_Respuesta.text = ""
            }
        }
        button_Porcentaje.setOnClickListener{
            var numeric = (TextView_Ecuacion.text as String).matches("-?\\d+(\\.\\d+)?".toRegex())
            if(numeric){
                TextView_Respuesta.text = ((TextView_Ecuacion.text as String).toDouble() / 100).toString()
            }else if((TextView_Ecuacion.text as String) == null || (TextView_Ecuacion.text as String).isEmpty()){
                TextView_Ecuacion.text = ""
            }else if((TextView_Ecuacion.text as String) == "+"||(TextView_Ecuacion.text as String) == "-" ||(TextView_Ecuacion.text as String) == "."){
                TextView_Ecuacion.text = igual(TextView_Ecuacion)
            }else{
                punto = false
                var Texto = igual(TextView_Ecuacion)
                numeric = Texto.matches("-?\\d+(\\.\\d+)?".toRegex())
                if(numeric){
                    TextView_Respuesta.text = (Texto.toDouble()/100).toString()
                    TextView_Respuesta.text = (((TextView_Respuesta.text as String).toDouble() * 100.0).roundToInt() / 100.0).toString()
                }else{
                    TextView_Respuesta.text = igual(TextView_Ecuacion)
                    TextView_Respuesta.text = (((TextView_Respuesta.text as String).toDouble() * 100.0).roundToInt() / 100.0).toString()
                }
            }
        }
        button_Dividir.setOnClickListener{
            if(!TextView_Ecuacion.text.contains("/")){
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"/")
            }else{
                punto = false
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"/")
            }
        }
        button_Siete.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Siete,TextView_Ecuacion)
        }
        button_Ocho.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Ocho,TextView_Ecuacion)
        }
        button_Nueve.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Nueve,TextView_Ecuacion)
        }
        button_Multiplicacion.setOnClickListener{
            if(!TextView_Ecuacion.text.contains("X")){
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"X")
            }else{
                punto = false
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"X")
            }
        }
        button_Cuatro.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Cuatro,TextView_Ecuacion)
        }
        button_Cinco.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Cinco,TextView_Ecuacion)
        }
        button_Seis.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Seis,TextView_Ecuacion)
        }
        button_Resta.setOnClickListener{
            if(!TextView_Ecuacion.text.contains("-")){
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"-")
            }else{
                punto = false
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"-")
            }
        }
        button_Uno.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Uno,TextView_Ecuacion)
        }
        button_Dos.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Dos,TextView_Ecuacion)
        }
        button_Tres.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Tres,TextView_Ecuacion)
        }
        button_Suma.setOnClickListener{
            if(!TextView_Ecuacion.text.contains("+")){
                punto = false
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"+")
            }else{
                punto = false
                TextView_Ecuacion.text = operar(TextView_Ecuacion,"+")
            }
        }
        button_Cero.setOnClickListener{
            TextView_Ecuacion.text = imprimir(button_Cero,TextView_Ecuacion)
        }
        button_Punto.setOnClickListener{
            if(!punto){
                TextView_Ecuacion.text = TextView_Ecuacion.text as String + "."
            }
            punto = true
        }
        button_Igual.setOnClickListener{
            TextView_Respuesta.text = igual(TextView_Ecuacion)
            if((TextView_Ecuacion.text as String).contains(".")){
                punto = true
            }
        }
    }
    private fun imprimir(Boton :Button, Pantalla : TextView ): String {
        return Pantalla.text as String + Boton.text as String
    }
    private fun Borrar(Pantalla : TextView ): String{
        var Texto : String = if(Pantalla.text as String == null || (Pantalla.text as String).isEmpty()){
            Pantalla.text as String
        }else{
            (Pantalla.text as String).substring(0, (Pantalla.text as String).length - 1)
        }
        return Texto
    }
    private fun operar(Pantalla : TextView, Operador: String ): String{
        var Resultado : String = Pantalla.text as String
        var Temporal : Double
        var primeraCifra: Double
        var segundaCifra: Double
        var numeric = true
        var arreglo : List<String>
        if((Pantalla.text as String) == null || (Pantalla.text as String).isEmpty()){
            if(Operador == "-"){
                Resultado = "-"
            }else{
                Resultado = Pantalla.text as String
            }
        }else{
            numeric = (Pantalla.text as String).matches("-?\\d+(\\.\\d+)?".toRegex())
            if((Pantalla.text as String).contains("+")){
                if((Pantalla.text as String).endsWith("+")){
                    Resultado = (Pantalla.text as String).replace("+", Operador)
                }else{
                    arreglo = (Pantalla.text as String).split("+")
                    primeraCifra = arreglo[0].toDouble()
                    segundaCifra = arreglo[1].toDouble()
                    Temporal = primeraCifra + segundaCifra
                    Temporal = (Temporal * 100.0).roundToInt() / 100.0
                    Resultado = "$Temporal" + Operador
                }
            }else if(numeric){
                Resultado = Pantalla.text as String + Operador
            }else if ((Pantalla.text as String).contains("/")){
                if((Pantalla.text as String).endsWith("/")){
                    Resultado = (Pantalla.text as String).replace("/", Operador)
                }else{
                    arreglo = (Pantalla.text as String).split("/")
                    primeraCifra = arreglo[0].toDouble()
                    segundaCifra = arreglo[1].toDouble()
                    Temporal = primeraCifra / segundaCifra
                    Temporal = (Temporal * 100.0).roundToInt() / 100.0
                    Resultado = "$Temporal"+ Operador
                }
            }else if ((Pantalla.text as String).contains("X")){
                if((Pantalla.text as String).endsWith("X")){
                    Resultado = (Pantalla.text as String).replace("X", Operador)
                }else{
                    arreglo = (Pantalla.text as String).split("X")
                    primeraCifra = arreglo[0].toDouble()
                    segundaCifra = arreglo[1].toDouble()
                    Temporal = primeraCifra * segundaCifra
                    Temporal = (Temporal * 100.0).roundToInt() / 100.0
                    Resultado = "$Temporal" + Operador
                }
            }else if ((Pantalla.text as String).contains("-")){
                if((Pantalla.text as String).endsWith("-")){
                    Resultado = (Pantalla.text as String).replace("-", Operador)
                }else{
                    if((Pantalla.text as String).startsWith("-")){
                        Resultado = (Pantalla.text as String).substring(1)
                        arreglo = Resultado.split("-")
                        primeraCifra = arreglo[0].toDouble()
                        segundaCifra = arreglo[1].toDouble()
                        Temporal = - primeraCifra - segundaCifra
                        Temporal = (Temporal * 100.0).roundToInt() / 100.0
                        Resultado = "$Temporal"+ Operador
                    }else{
                        arreglo = (Pantalla.text as String).split("-")
                        primeraCifra = arreglo[0].toDouble()
                        segundaCifra = arreglo[1].toDouble()
                        Temporal = primeraCifra - segundaCifra
                        Temporal = (Temporal * 100.0).roundToInt() / 100.0
                        Resultado = "$Temporal"+ Operador
                    }
                }
            }
        }
        return Resultado
    }
    private fun igual(Pantalla : TextView):String{
        var Resultado : String = ""
        var numeric = true
        var Temporal : Double
        var primeraCifra: Double
        var segundaCifra: Double
        var arreglo : List<String>
        if((Pantalla.text as String) == null || (Pantalla.text as String).isEmpty()){
            Resultado = Pantalla.text as String
        }else{
            numeric = (Pantalla.text as String).matches("-?\\d+(\\.\\d+)?".toRegex())
            if((Pantalla.text as String).contains("+")){
                if((Pantalla.text as String).endsWith("+")){
                    Resultado = (Pantalla.text as String)
                }else{
                    arreglo = (Pantalla.text as String).split("+")
                    primeraCifra = arreglo[0].toDouble()
                    segundaCifra = arreglo[1].toDouble()
                    Temporal = primeraCifra + segundaCifra
                    Temporal = (Temporal * 100.0).roundToInt() / 100.0
                    Resultado = "$Temporal"
                }
            }else if(numeric){
                Resultado = Pantalla.text as String
            }else if ((Pantalla.text as String).contains("/")){
                if((Pantalla.text as String).endsWith("/")){
                    Resultado = Pantalla.text as String
                }else{
                    arreglo = (Pantalla.text as String).split("/")
                    primeraCifra = arreglo[0].toDouble()
                    segundaCifra = arreglo[1].toDouble()
                    Temporal = primeraCifra / segundaCifra
                    Temporal = (Temporal * 100.0).roundToInt() / 100.0
                    Resultado = "$Temporal"
                }
            }else if ((Pantalla.text as String).contains("X")){
                if((Pantalla.text as String).endsWith("X")){
                    Resultado = Pantalla.text as String
                }else{
                    arreglo = (Pantalla.text as String).split("X")
                    primeraCifra = arreglo[0].toDouble()
                    segundaCifra = arreglo[1].toDouble()
                    Temporal = primeraCifra * segundaCifra
                    Temporal = (Temporal * 100.0).roundToInt() / 100.0
                    Resultado = "$Temporal"
                }
            }else if ((Pantalla.text as String).contains("-")){
                if((Pantalla.text as String).endsWith("-")){
                    Resultado = Pantalla.text as String
                }else{
                    if((Pantalla.text as String).startsWith("-")){
                        Resultado = (Pantalla.text as String).substring(1)
                        arreglo = Resultado.split("-")
                        primeraCifra = arreglo[0].toDouble()
                        segundaCifra = arreglo[1].toDouble()
                        Temporal = - primeraCifra - segundaCifra
                        Temporal = (Temporal * 100.0).roundToInt() / 100.0
                        Resultado = "$Temporal"
                    }else {
                        arreglo = (Pantalla.text as String).split("-")
                        primeraCifra = arreglo[0].toDouble()
                        segundaCifra = arreglo[1].toDouble()
                        Temporal = primeraCifra - segundaCifra
                        Temporal = (Temporal * 100.0).roundToInt() / 100.0
                        Resultado = "$Temporal"
                    }
                }
            }else{
                Resultado = Pantalla.text as String
            }
            Resultado = Resultado
        }
        return Resultado
    }
}