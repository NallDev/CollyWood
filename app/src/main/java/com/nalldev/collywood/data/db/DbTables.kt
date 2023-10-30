package com.nalldev.collywood.data.db

import com.ealva.welite.db.table.Table

object UserTable : Table() {
    val id = integer("id") { primaryKey().autoIncrement() }
    val email = text("email") { unique() }
    val phone = text("phone") { unique() }
    val password = text("password") { collateNoCase() }
}