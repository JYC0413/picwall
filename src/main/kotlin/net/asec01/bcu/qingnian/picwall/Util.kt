package net.asec01.bcu.qingnian.picwall

import org.springframework.http.HttpHeaders
import java.util.*

class Util {
    companion object {
        fun getRuntimePath(): String {
            return System.getProperty("user.dir")
        }

        fun getFilesStorePath(): String {
            return getRuntimePath() + "/files/"
        }

        fun getFilesSafePath(dir: String, filename: String): String {
            return getFilesStorePath() + dir + "/" + getSafeFileName(filename)
        }

        fun getSafeFileName(filename: String): String? {
            var safeName = ""
            val whiteList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-.()_"
            val filenameChars = filename.toCharArray()
            for (c: Char in filenameChars) {
                if (whiteList.contains(c)) {
                    safeName += c
                }
            }
            return safeName
        }

        fun genFileName(type: String, custom: String, suffix: String): String {
            var rand = UUID.randomUUID().toString().split("-")[4]
            return type + "_" + custom + "_" + System.currentTimeMillis().toString() + "_" + rand + "." + suffix
        }

        fun getRandom(min: Int, max: Int): Int {
            return (min..max).random()
        }

        fun getCookie(headers: HttpHeaders): HashMap<String, String>? {
            try {
                val l1 = headers["cookie"]!![0].toString().split(";")
                var hashmap: HashMap<String, String> = HashMap()
                for (i in l1) {
                    val t = i.split("=")
                    if (t.size==2){
                        hashmap.put(t[0].replace(" ", ""), t[1])
                    }
                }
                return hashmap
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }

    }
}