package com.employees

class CustomList<T> : MyList<T> {

    private var arrayList = arrayOfNulls<Any>(10)
    var size = 0

    companion object{
        fun<E> myListOf(vararg elements: E) : CustomList<E> {
            val list = CustomList<E>()
            for (element in elements) {
                list.add(element)
            }
            return list
        }
    }

    override fun get(index: Int): T {
        if (index in 0 until size) {
            arrayList[index]?.let {
                return it as T
            }
        }
        throw IndexOutOfBoundsException()
    }

    override fun add(string: T) {
        if (size >= arrayList.size) {
            arrayList = arrayList.copyOf(arrayList.size * 2)
        }
        arrayList[size] = string
        size++
    }

    override fun remove(element: T) {
        for ((index, i) in arrayList.withIndex()) {
            if (i == element) removeAt(index)
            return
        }
    }

    override fun removeAt(index: Int) {
        if (index in arrayList.indices) {
            for (i in arrayList.indices) {
                arrayList[i] = arrayList[i + 1]
            }
            size--
        } else throw java.lang.IndexOutOfBoundsException()
    }

    override fun size(): Int {
        return arrayList.size
    }
}