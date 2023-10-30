package com.nalldev.collywood.data.repository

import com.ealva.welite.db.Database
import com.ealva.welite.db.expr.and
import com.ealva.welite.db.expr.eq
import com.ealva.welite.db.table.OnConflict
import com.ealva.welite.db.table.andWhere
import com.ealva.welite.db.table.selectAll
import com.ealva.welite.db.table.selectCount
import com.nalldev.collywood.data.db.UserTable
import com.nalldev.collywood.domain.model.UserModel
import com.nalldev.collywood.domain.repository.UserRepository

class UserRepositoryImpl (private val db : Database) : UserRepository {
    override suspend fun login(username: String, password: String): UserModel? {
        return db.query {
            UserTable.selectAll().andWhere { (UserTable.email eq username) and (UserTable.password eq password) }.sequence { UserModel(id = it[id], email = it[this.email], phone = it[phone], password = "") }.singleOrNull()
        }
    }

    override suspend fun register(user: UserModel) : Boolean {
        db.transaction {
            UserTable.insert(OnConflict.Ignore) {
                it[email] = user.email.trim()
                it[phone] = user.phone.trim()
                it[password] = user.password.trim()
            }
        }
        return db.query {
            UserTable.selectCount { email eq user.email.trim() }.count() > 0
        }
    }
}