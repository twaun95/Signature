package com.twaun95.signature.presentation.handler

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.twaun95.signature.presentation.utils.toast.CustomToast
import java.io.File
import java.io.FileOutputStream

object ImageSaveHandler {

    private const val GALLERY_PATH = "낙서장"
    private const val MESSAGE_SUCCESS = "저장을 완료했습니다."
    private const val MESSAGE_FAIL = "저장을 실패했습니다."

    fun saveImageGallery(context: Context, bitmap: Bitmap) {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {

            val rootPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val dirName = "/$GALLERY_PATH"
            val fileName = System.currentTimeMillis().toString() + ".png"
            val savePath = File(rootPath + dirName)
            savePath.mkdirs()

            val file = File(savePath, fileName)
            if (file.exists()) file.delete()

            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()

                //갤러리 갱신
                context.sendBroadcast(
                    Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + Environment.getExternalStorageDirectory())
                    )
                )
                CustomToast.show(context, MESSAGE_SUCCESS)
                return
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        CustomToast.show(context, MESSAGE_FAIL)
    }

    fun checkPermission(activity: AppCompatActivity, permission: String): Boolean {
        val permissionChecker = ContextCompat.checkSelfPermission(activity.applicationContext, permission)

        if (permissionChecker == PackageManager.PERMISSION_GRANTED) return true

        ActivityCompat.requestPermissions(activity, arrayOf(permission), DownloadManager.Request.NETWORK_MOBILE)
        return false
    }
}