package storage

import model.Task
import java.util.concurrent.ConcurrentHashMap

class TaskStore {

    private val tasks = ConcurrentHashMap<String, Task>()

    fun add(task: Task) {
        tasks[task.id] = task
    }

    fun getAll(): List<Task> =
        tasks.values.toList()

    fun getById(id: String): Task? =
        tasks[id]

    fun delete(id: String) {
        tasks.remove(id)
    }

    fun update(id: String, title: String): Task? {
        val task = tasks[id] ?: return null
        task.title = title
        return task
    }
}
