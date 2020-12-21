package net.asec01.bcu.qingnian.picwall

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException

open class PicObjectMapper : RowMapper<PicObject> {
    constructor()

    var removeSensitiveInformation = false

    constructor(removeSensitiveInformation: Boolean) {
        this.removeSensitiveInformation = removeSensitiveInformation
    }

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): PicObject? {
        val picObject = PicObject()
        picObject.id = rs.getInt("id")
        picObject.filename = rs.getString("filename")
        if (!removeSensitiveInformation) {
            picObject.session = rs.getString("session")
            picObject.useragent = rs.getString("useragent")
            picObject.timestamp = rs.getString("timestamp")
            picObject.checked = rs.getBoolean("checked")
        }
        return picObject
    }
}

open class PicObjectIdMapper : RowMapper<Int> {
    constructor()

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Int? {
        return rs.getInt("id")
    }
}