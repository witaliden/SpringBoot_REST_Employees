package com.employees

val list: MutableList<Int>? = mutableListOf()

fun main() {
    val list = CustomList.myListOf(110, 5)
    for (i in 0..20){
        list.add(i)
    }

    for(i in 0 until list.size){
        print(i)
//        print("Moje $i.")
    }
}