package net.wushilin.webhookdebug

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicLong

@Controller
class WebService {
    companion object {
        val baseFolder = getBase()
        val sequence = AtomicLong(0L);
        val randStart = Random().nextLong() % 10000;
        fun getBase():java.io.File {
            var baseFolder = System.getProperty("dump.dest.folder")
            if (baseFolder == null || baseFolder.trim().isEmpty()) {
                baseFolder = System.getenv("DUMP_DEST_FOLDER")
                if(baseFolder == null || baseFolder.trim().isEmpty()) {
                    baseFolder = ".";
                }
            }


            while(baseFolder.endsWith("/")) {
                baseFolder = baseFolder.substring(0, baseFolder.length - 1)
            }
            if (!java.io.File(baseFolder).exists() || !java.io.File(baseFolder).isDirectory || !java.io.File(baseFolder).canWrite() || !java.io.File(baseFolder).canExecute()) {
                throw IOException("Invalid folder ${baseFolder}")
            }
            return java.io.File(baseFolder)
        }
    }
    fun formatSuffix(now:Date):String {
        var nextId = sequence.addAndGet(1L);
        nextId %= 1000000L
        val idString = String.format("%06d", nextId)
        val df = SimpleDateFormat("yyyy/MM/dd/HH/mm");
        val dString = df.format(now)
        val tf = SimpleDateFormat("ss.SSS")
        val tString = tf.format(now)
        return "$dString/$tString-$idString.json"
    }

    @RequestMapping("/*")
    fun doWork(req: HttpServletRequest, resp: HttpServletResponse) {
        val now = Date()
        val destFile = java.io.File(baseFolder, formatSuffix(now))
        val parentFile = destFile.parentFile
        if(!parentFile.exists() ) {
            parentFile.mkdirs()
        }
        if(!parentFile.exists()) {
            resp.writer.write("Can't write the file. sorry")
            return
        }
        val os = FileOutputStream(destFile)
        os.use {
            Request(req).dumpTo(it)
            resp.writer.write("Thanks. It was saved.")
        }
    }
}