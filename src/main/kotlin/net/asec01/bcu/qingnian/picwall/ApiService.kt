package net.asec01.bcu.qingnian.picwall

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Service
import java.sql.Connection
import java.sql.Statement

@Service
class ApiService {

    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    fun addNew(obj: PicObject): Int {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val sql = "INSERT INTO `picwall` (`filename`, `session`, `useragent`) " +
                "VALUES (?, ?, ?)"
        jdbcTemplate!!.update(PreparedStatementCreator { connection: Connection ->
            val ps = connection
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ps.setString(1, obj.filename)
            ps.setString(2, obj.session)
            ps.setString(3, obj.useragent)
            ps
        }, keyHolder)
        return keyHolder.key!!.toInt()
    }

    fun getAllPic(): List<PicObject?>? {
        val sql = "SELECT * FROM `picwall`"
        return jdbcTemplate!!.query(sql, PicObjectMapper(true)) as List<PicObject?>
    }

    fun getRandomPic(count: Int): List<PicObject?> {
//    fun getRandomPic(count: Int): String {
        val maxCount = 50;
        var sql = "SELECT `id` FROM `picwall` WHERE `checked` = 1"
        val listForAllId = jdbcTemplate!!.query(sql, PicObjectIdMapper()) as MutableList<Int>
        var fCount = count
        if (fCount > maxCount) {
            fCount = maxCount
        }
        if (fCount > listForAllId.size) {
            fCount = listForAllId.size
        }
        val newList = mutableListOf<Int>()
        for (i in 0 until fCount) {
            newList.add(listForAllId.removeAt(Util.getRandom(0, listForAllId.size - 1)))
        }
        if (newList.size > 0) {
            val nums = newList.joinToString(",")
            sql = "SELECT * FROM `picwall` WHERE `id` IN ($nums)"
            return jdbcTemplate!!.query(sql, PicObjectMapper(true)) as List<PicObject?>
        }
        return listOf<PicObject>()
    }
}