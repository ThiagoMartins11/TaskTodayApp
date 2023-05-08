package com.example.tasktodayapp.model.Tarefa


import java.util.*

class Tarefa(val nome: String, val detalhes: String?, val createdDate: Date, val pzoFinal:Date, status:Double, val cor: String) {

    var status = "pendente"
        get() {return field}
        set(value){
            field = value
        }
}