fun main() {

    val dataBase = DataBase()

    var command: Int

    println("База подключена\n")

    while (true) {
        println(
            """
        |Напишите
        |1 для создания пользователя
        |2 для редактирования пользователя 
        |3 для поиска пользователя
        |4 для вывода всех пользователей
        |5 для удаления пользователя
        |6 для закрытия программы
        |------------------------------------------------
        """.trimMargin()
        )
        command = try {
            readlnOrNull().toString().toInt()
        } catch (e: NumberFormatException) {
            0
        }
        when (command) {
            1 -> dataBase.addUser()
            2 -> dataBase.editUser(inputIDorLogin())
            3 -> dataBase.getUserByTarget(inputIDorLogin())
            4 -> dataBase.getAllUser()
            5 -> dataBase.deleteUser(inputIDorLogin())
            6 -> {
                println("До свидания!")
                break
            }

            else -> println("Неверная команда\n")
        }
    }
}

data class dataArgs(val id: Int, var login: String, var password: String)

fun inputIDorLogin(): Any? {
    do {
        println("Сначала найдем пользователя. Введите 1 для поиска по id или 2 для поиска по логину пользователя ПЕРЕДЕЛАТЬ ФРАЗУ под общую")
        when (readln()) {
            "1" -> {
                println("Введите id пользователя")
                return readlnOrNull()?.toInt()
            }

            "2" -> {
                println("Введите логин пользователя")
                //оно и так String, но пусть будет
                return readlnOrNull()?.toString()
            }

            else -> println("Повторите ввод(1 или 2)")
        }

    } while (true)
}

class DataBase {
    //Подключение базы данных
    private val data = mutableListOf<dataArgs>()
    private var id_user: Int = 0

    //методы для обработки данных
    //Добавление пользователя
    fun addUser() {
        var check: Boolean
        var login: String
        do {
            check = true
            println("Введите логин:")
            login = readlnOrNull().toString()

            for (it in data) {
                if (it.login == login) {
                    check = false
                    println("Пользователь с таким логином уже существует, аккаунт не будет создан.\n")
                    break
                }
            }
        } while (!check)
        println("Введите пароль:")
        val password: String = readlnOrNull().toString()
        if (check) {
            val add = data.add(dataArgs(++id_user, login, password))
            println("Аккаунт создан\n")
        }

    }

    //Перегруженный Удаление пользователя
    fun deleteUser(inputIDorLogin: Any?) {
        when (inputIDorLogin) {
            is Int -> deleteUserID(inputIDorLogin)
            is String -> deleteUserLogin(inputIDorLogin)
            else -> println("Ошибка 1001")
        }
    }

    private fun deleteUserID(delete_id: Int) {
        var check = false
        for (it in data) {
            if (it.id == delete_id) {
                check = true
                println("Пользователь ID: ${it.id}, login: ${it.login}, password: ${it.password} удален")
                data.remove(it)
                break
            }
        }
        if (!check)
            println("Пользователь для удаления не найден")
    }

    private fun deleteUserLogin(delete_login: String) {
        var check = false
        for (it in data) {
            if (it.login == delete_login) {
                check = true
                println("Пользователь ID: ${it.id}, login: ${it.login}, password: ${it.password} удален")
                data.remove(it)
                break
            }
        }
        if (!check)
            println("Пользователь для удаления не найден")
    }

    //Показ всех пользователей
    fun getAllUser() {
        if (data.isEmpty())
            println("Пользователей нет")
        else
            for (it in data)
                println("ID: ${it.id}, login: ${it.login}, password: ${it.password}")
    }

    //Перегруженный Вывод пользователя по ID по Login
    fun getUserByTarget(inputIDorLogin: Any?) {
        when (inputIDorLogin) {
            is Int -> getUserByTargetID(inputIDorLogin)
            is String -> getUserByTargetLogin(inputIDorLogin)
            else -> println("Ошибка 1002")
        }
    }

    private fun getUserByTargetID(find_id: Int) {
        var check = false
        for (it in data)
            if (it.id == find_id) {
                check = true
                println("Найденный пользователь:\nID: ${it.id}, login: ${it.login}, password: ${it.password}")
                break
            }
        if (!check)
            println("Пользователь не найден")
    }

    private fun getUserByTargetLogin(find_login: String) {
        var check = false
        for (it in data)
            if (it.login == find_login) {
                check = true
                println("Найденный пользователь:\nID: ${it.id}, login: ${it.login}, password: ${it.password}")
                break
            }
        if (!check)
            println("Пользователь не найден")
    }

    //Редактирование пользователя
    fun editUser(inputIDorLogin: Any?) {
        when (inputIDorLogin) {
            is Int -> editUserID(inputIDorLogin)
            is String -> editUserLogin(inputIDorLogin)
            else -> println("Ошибка 1003")
        }

    }

    private fun editUserID(edit_id: Int) {
        var check = false
        for (it in data)
            if (it.id == edit_id) {
                check = true
                println("Найденный пользователь:\nID: ${it.id}, login: ${it.login}, password: ${it.password}")
                println("Введите новый логин: ")
                it.login = readlnOrNull().toString()
                println("Введите новый пароль: ")
                it.password = readlnOrNull().toString()
                break
            }
        if (!check)
            println("Пользователь для редактирования не найден")
    }

    private fun editUserLogin(edit_login: String) {
        var check = false
        for (it in data)
            if (it.login == edit_login) {
                check = true
                println("Найденный пользователь:\nID: ${it.id}, login: ${it.login}, password: ${it.password}")
                println("Введите новый логин: ")
                it.login = readlnOrNull().toString()
                println("Введите новый пароль: ")
                it.password = readlnOrNull().toString()
                break
            }
        if (!check)
            println("Пользователь для редактирования не найден")
    }
}