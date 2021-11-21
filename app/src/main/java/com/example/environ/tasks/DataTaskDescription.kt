package com.example.environ.tasks

interface DataTaskDescription {

    fun getTitle(position: Int): String{

        var taskDescTitleVal: String = ""

        taskDescTitleVal = when (position) {
            1 -> "Title 1" //Change these values
            2 -> "Title 2"
            3 -> "Title 3"
            4 -> "Title 4"
            5 -> "Title 5"
            6 -> "Title 6"
            else -> {
                "Task title not added yet."
            }
        }
        return taskDescTitleVal
    }
    fun getDescription(position: Int): String {

        var taskDescVal: String = ""

        taskDescVal = when (position) {
            1 -> "Desc 1" //Change these values
            2 -> "Desc 2"
            3 -> "Desc 3"
            4 -> "Desc 4"
            5 -> "Desc 5"
            6 -> "Desc 6"
            else -> {
                "Task description not added yet"
            }
        }
        return taskDescVal

    }
    fun getPoints(position: Int): Int{

        var taskPointVal: Int = 0

        taskPointVal = when (position) {
            1 -> 1 //Change these values
            2 -> 2
            3 -> 3
            else -> {
                0
            }
        }
        return taskPointVal

    }
}