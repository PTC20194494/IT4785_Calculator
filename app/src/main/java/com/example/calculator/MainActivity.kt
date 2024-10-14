package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textWorking: TextView
    lateinit var textTemp: TextView

    var state: Int = 1
    var op = ""
    var op1: Int = Int.MIN_VALUE
    var op2: Int = Int.MIN_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textWorking = findViewById(R.id.tvDisplay)
        textTemp = findViewById(R.id.temp)

        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.buttonPlus).setOnClickListener(this)
        findViewById<Button>(R.id.buttonMinus).setOnClickListener(this)
        findViewById<Button>(R.id.buttonDivide).setOnClickListener(this)
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener(this)
        findViewById<Button>(R.id.buttonEqual).setOnClickListener(this)
        findViewById<Button>(R.id.buttonC).setOnClickListener(this)
        findViewById<Button>(R.id.buttonCE).setOnClickListener(this)
    }

    fun  numberAction(view: View){
        if(view is Button){
            textWorking.append(view.text)
        }
    }

    fun operationAction(view: View){
        if(view is Button){
            textWorking.append(view.text)
            state = 2
        }
    }

    fun allClearAction(){
        textWorking.text = "0"
        textTemp.text = ""
        op = ""
        op1 = Int.MIN_VALUE
        op2 = Int.MIN_VALUE
        state = 1
    }

    fun backSpaceAction(){
        if(state == 1){
            op1 = op1 / 10
            textWorking.text = "$op1"
        }
        else if(state == 2){
            op2 = op2 / 10
            textWorking.text = "$op2"
        }
        else{
            allClearAction()
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {
        val id = p0?.id

        if (id == R.id.button0) {
            addDigit(0)
        } else if (id == R.id.button1) {
            addDigit(1)
        } else if (id == R.id.button2) {
            addDigit(2)
        } else if (id == R.id.button3) {
            addDigit(3)
        } else if (id == R.id.button4) {
            addDigit(4)
        } else if (id == R.id.button5) {
            addDigit(5)
        } else if (id == R.id.button6) {
            addDigit(6)
        } else if (id == R.id.button7) {
            addDigit(7)
        } else if (id == R.id.button8) {
            addDigit(8)
        } else if (id == R.id.button9) {
            addDigit(9)
        } else if (id == R.id.buttonPlus) {
            if(state == 2 && op2 != Int.MIN_VALUE){
                getResult(true)
            }
            op = "+"
            state = 2
            textTemp.text = "$op1 +"
        } else if (id == R.id.buttonMinus) {
            if(state == 2&& op2 != Int.MIN_VALUE){
                getResult(true)
            }
            op = "-"
            state = 2
            textTemp.text = "$op1 -"
        } else if (id == R.id.buttonMultiply) {
            if(state == 2&& op2 != Int.MIN_VALUE){
                getResult(true)
            }
            op = "x"
            state = 2
            textTemp.text = "$op1 x"
        } else if (id == R.id.buttonDivide) {
            if(state == 2&& op2 != Int.MIN_VALUE){
                getResult(true)
            }
            op = "/"
            state = 2
            textTemp.text = "$op1 /"
        } else if (id == R.id.buttonEqual) {
            getResult(false)
        }
        else if(id == R.id.buttonC){
            allClearAction()
        }
        else if(id == R.id.buttonCE){
            backSpaceAction()
        }

    }

    fun addDigit(c: Int) {
        if(state == 0){
            allClearAction()
        }

        if (state == 1) {
            if(op1 == Int.MIN_VALUE)
                op1 = 0
            op1 = op1 * 10 + c
            textWorking.text = "$op1"
        }
        else if(state == 2) {
            if(op2 == Int.MIN_VALUE)
                op2 = 0
            op2 = op2 * 10 + c
            textWorking.text = "$op2"
        }
    }

    fun getResult(isFast: Boolean){

        if(op == "")
            return

        var result = 0

        if(op2 == Int.MIN_VALUE)
            op2 = op1

        if(op2 == 0 && op == "/"){
            textWorking.text = "Cannot divide by zero"
            return
        }

        if (op == "+") {
            result = op1 + op2
        }
        else if(op == "-"){
            result = op1 - op2
        }
        else if(op == "x"){
            result = op1 * op2
        }
        else if(op == "/"){
            result = op1 / op2
        }
        textWorking.text = "$result"
        textTemp.text = "$op1 $op $op2 ="
        state = 0
        op1 = result
        if(isFast)
            op2 = Int.MIN_VALUE
    }
}