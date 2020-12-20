package net.asec01.bcu.qingnian.picwall

import net.asec01.bcu.qingnian.picwall.Util.Companion.getFilesStorePath
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

@Controller
@RequestMapping(value = ["/api"])
class ApiController {
    @RequestMapping(value = ["/uploadImage.do"], produces = ["application/json; charset=utf-8"])
    @ResponseBody
    fun uploadImage(@RequestHeader headers: HttpHeaders, @RequestParam(value = "file") file: MultipartFile): String? {
        val fileName = file.originalFilename
        val suffixName = fileName!!.substring(fileName.lastIndexOf(".")).replace(".", "")
        val saveFileName = Util.genFileName("upload", "test", suffixName)
        var filePath = getFilesStorePath() + "images/"
        val dest = File(filePath + saveFileName)
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs()
        }
        try {
            file.transferTo(dest)
            return ResponseObject(0, null, null).toJson()
        } catch (e: IllegalStateException) {
            return ResponseObject(1, e.message, null).toJson()
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseObject(2, e.message, null).toJson()
        }
    }
}