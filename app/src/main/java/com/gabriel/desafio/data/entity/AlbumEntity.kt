package com.gabriel.desafio.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AlbumEntity : RealmObject() {
    @PrimaryKey
    var id: Int = 0
        get() = field

    var userId: Int = 0
        get() = field

    var title: String = ""
        get() = field
}
