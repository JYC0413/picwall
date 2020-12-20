package net.asec01.bcu.qingnian.picwall

import net.asec01.bcu.qingnian.picwall.Util.Companion.getFilesStorePath
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.tasks.UnsupportedFormatException
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
    fun uploadImage(@RequestHeader headers: HttpHeaders, @RequestParam(value = "file") file: MultipartFile?): String? {
        val saveFormat = "jpg"
        if (file == null) {
            return ResponseObject(2, "参数错误(0F)", null).toJson()
        }
        //16 * 1024 * 1024 = 16777216
        if (file.size >= 16777216) {
            return ResponseObject(1, "图片大小不能超过16M", null).toJson()
        }
        val saveFileName = Util.genFileName("upload", "test", saveFormat)
        val filePath = getFilesStorePath() + "images/"
        val oriFile: File = File.createTempFile(saveFileName,".tmp")
        try {
            file.transferTo(oriFile)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            return ResponseObject(2, e.message, null).toJson()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseObject(2, e.message, null).toJson()
        }
        val targetFile = File(filePath + saveFileName)
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs()
        }
        try {
            return ResponseObject(0, null, ua).toJson()
            Thumbnails.of(oriFile).size(512, 512).outputFormat(saveFormat).toFile(targetFile)
        } catch (e: UnsupportedFormatException){
            return ResponseObject(1, "图片处理错误(不支持的图片格式)", null).toJson()
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseObject(1, "图片处理错误(${e.message})", null).toJson()
        }
    }
}