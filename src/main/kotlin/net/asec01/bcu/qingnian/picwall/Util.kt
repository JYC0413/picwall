package net.asec01.bcu.qingnian.picwall

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
    }
}