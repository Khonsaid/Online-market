package uz.gita.latizx.onlinemarketexam6.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object BitmapConverter {
    // Bitmap ni ByteArray ga aylantirish
//    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
//        val stream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//        return stream.toByteArray()
//    }
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val resizedBitmap = resizeBitmap(bitmap, 800, 800) // 800x800 dan katta bo'lmagan
        val stream = ByteArrayOutputStream()
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        return stream.toByteArray()
    }

    private fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val aspectRatio = bitmap.width.toFloat() / bitmap.height.toFloat()
        val width = if (aspectRatio > 1) maxWidth else (maxHeight * aspectRatio).toInt()
        val height = if (aspectRatio > 1) (maxWidth / aspectRatio).toInt() else maxHeight
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    // ByteArray ni Bitmap ga aylantirish
    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}