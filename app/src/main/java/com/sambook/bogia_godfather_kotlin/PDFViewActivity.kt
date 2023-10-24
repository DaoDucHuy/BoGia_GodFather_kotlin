package com.sambook.bogia_godfather_kotlin
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.util.FitPolicy
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class PDFViewActivity : AppCompatActivity() {

    lateinit var pdfView : PDFView

    lateinit var menu : Menu

    var isdark = false

    var _page = intArrayOf(0)

    var cuon1 = intArrayOf(4, 46, 62, 64, 69, 72, 77, 80, 86, 90, 99)
    var cuon2 = intArrayOf(112, 125)
    var cuon3 = intArrayOf(134)
    var cuon4 = intArrayOf(157, 163, 170, 175, 180)
    var cuon5 = intArrayOf(190, 208, 210)
    var cuon6 = intArrayOf(227, 248)
    var cuon7 = intArrayOf(251, 260, 273, 281)
    var cuon8 = intArrayOf(294, 302, 311)
    var cuon9 = intArrayOf(322)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)

        init()

        val bundle = intent.extras
        val pageContinue = bundle!!["PAGECONTINUE"] as String?

        if (pageContinue != null) {
            val a = intArrayOf(pageContinue.toInt())
            _page = a
            openChapter(_page, isdark)
        } else {
            val head = bundle["HEAD"] as Int
            val chapter = bundle["CHAPTER"] as Int
            getPage(head, chapter)
            openChapter(_page, isdark)
        }
    }

    private fun getPage(head: Int, chapter: Int) {
        val a: IntArray
        a = when (head) {
            0 -> cuon1
            1 -> cuon2
            2 -> cuon3
            3 -> cuon4
            4 -> cuon5
            5 -> cuon6
            6 -> cuon7
            7 -> cuon8
            8 -> cuon9
            else -> throw IllegalStateException("Unexpected value: $head")
        }

        _page[0] = a[chapter]
    }

    private fun openChapter(_page: IntArray, isdark: Boolean) {
        if (_page[0] == 0) {
            _page[0] = 1;
        }

        pdfView.fromAsset("bogia_converted.pdf")
            .enableSwipe(true) // allows to block changing pages using swipe
            .pageSnap(true)
            .autoSpacing(true)
            .pageFling(true)
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .defaultPage(_page[0] - 1)
            .onPageChange(object : OnPageChangeListener {
                override fun onPageChanged(page: Int, pageCount: Int) {
//                    Toast.makeText(this@PDFViewActivity, "Trang: " + page, Toast.LENGTH_SHORT).show()
                    val fileOutputStream : FileOutputStream
                    try {
                        val _page = page + 1
                        fileOutputStream = openFileOutput("CONTINUE.txt", Context.MODE_PRIVATE)
                        fileOutputStream.write(_page.toString().toByteArray())
                    } catch (e:Exception){
                        e.printStackTrace()
                    }
                }

            })
            // allows to draw something on the current page, usunally visible in the middle of the screen
            // allows to draw something on all pages, separately for every page. Called only for visible pages
            .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
            .password(null)
            .scrollHandle(null)
            .enableAntialiasing(true) // improve rendering a little bit on low-res screens
            // spacing between pages in dp. To define spacing color, set view background
            .spacing(0)
            .autoSpacing(true)
            .pageFling(true)
            .pageFitPolicy(FitPolicy.BOTH)
            .fitEachPage(true)
            .pageSnap(true) // snap pages to screen boundaries
            .pageFling(true) // make a fling change only a single page like ViewPager
            .nightMode(isdark)
            .load()
    }

    private fun init() {
        pdfView = findViewById(R.id.pdfView)
    }
}