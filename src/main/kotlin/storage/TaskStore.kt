package storage

import model.Task

class TaskStore {
    private val tasks = mutableListOf<Task>()

    fun getAll(): List<Task> = tasks.toList()
    fun getById(id: String): Task? = tasks.find { it.id == id }
    fun add(task: Task) = tasks.add(task)
    fun update(task: Task): Boolean {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index == -1) return false
        tasks[index] = task
        return true
    }
    fun delete(id: String) = tasks.removeIf { it.id == id }
}
