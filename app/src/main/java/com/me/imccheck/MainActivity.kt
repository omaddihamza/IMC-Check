package com.me.imccheck

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightInput = findViewById<EditText>(R.id.weightInput)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val categoryImage = findViewById<ImageView>(R.id.categoryImage)
        val categoryText = findViewById<TextView>(R.id.categoryText)

        calculateButton.setOnClickListener {
            val weightStr = weightInput.text.toString()
            val heightStr = heightInput.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                try {
                    val weight = weightStr.toFloat()
                    val heightCm = heightStr.toFloat()

                    // Validation des valeurs
                    if (weight <= 0 || heightCm <= 0) {
                        Toast.makeText(this, "Veuillez entrer des valeurs positives.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    val heightM = heightCm / 100
                    val imc = weight / (heightM * heightM)
                    val imcRounded = String.format("%.2f", imc)

                    resultText.text = "Votre IMC est : $imcRounded"

                    // Affichage de la catégorie selon le tableau BMI
                    when {
                        imc < 18.5 -> {
                            categoryText.text = "Maigreur"
                            categoryImage.setImageResource(R.drawable.maigre)
                        }
                        imc < 25 -> {
                            categoryText.text = "Normal"
                            categoryImage.setImageResource(R.drawable.normal)
                        }
                        imc < 30 -> {
                            categoryText.text = "Surpoids"
                            categoryImage.setImageResource(R.drawable.surpoids)
                        }
                        imc < 40 -> {
                            categoryText.text = "Obésité modérée"
                            categoryImage.setImageResource(R.drawable.obese)
                        }
                        imc >= 40 -> {
                            categoryText.text = "Obésité sévère"
                            categoryImage.setImageResource(R.drawable.t_obese)
                        }
                    }

                    categoryText.visibility = View.VISIBLE
                    categoryImage.visibility = View.VISIBLE

                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Veuillez entrer des nombres valides.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Veuillez entrer le poids et la taille.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}