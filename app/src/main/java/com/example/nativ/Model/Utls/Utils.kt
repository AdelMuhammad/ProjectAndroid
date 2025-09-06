package com.example.nativ.Model.Utls

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.nativ.R
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import org.json.JSONObject
import org.json.JSONArray

object Utils {
    /**
     * عرض مربع حوار مخصص
     */
    fun CustomDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonText: String,
        positiveButtonAction: () -> Unit,
        negativeButtonText: String,
        negativeButtonAction: () -> Unit
    ) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
//        val dialogTitle = view.findViewById<TextView>(R.id.dialog_title)
//        val dialogMessage = view.findViewById<TextView>(R.id.dialog_message)
//        dialogTitle.text = title
//        dialogMessage.text = message
        val builder = android.app.AlertDialog.Builder(context)
        builder.setView(view)
        builder.setPositiveButton(positiveButtonText) { _, _ -> positiveButtonAction() }
        builder.setNegativeButton(negativeButtonText) { _, _ -> negativeButtonAction() }
        val dialog = builder.create()
        dialog.show()
    }

    /**
     * الانتقال بين الشاشات
     */
    fun Navgation(activity: Activity, resId: Int, viewId: Int) {
        Navigation.findNavController(activity, viewId).navigate(resId)
    }

    /**
     * الانتقال بين الشاشات مع تمرير بيانات
     */
    fun Navgation(activity: Activity, resId: Int, viewId: Int, bundle: Bundle) {
        Navigation.findNavController(activity, viewId).navigate(resId, bundle)
    }

    /**
     * الرجوع إلى الشاشة السابقة
     */
    fun NavBack(View: View, viewId: Int) {
        Navigation.findNavController(View).popBackStack()
    }

    /**
     * الرجوع إلى شاشة محددة في المكدس
     */
    fun NavBack(View: View, viewId: Int, resId: Int) {
        Navigation.findNavController(View).popBackStack(resId, false)
    }

    /**
     * عرض رسالة Toast
     */
    fun Toast(context: Context, masseg: String) {
        Toast.makeText(context, masseg, Toast.LENGTH_LONG).show()
    }

    /**
     * عرض رسالة Toast
     */
    fun Toast(context: Context, masseg: Int) {
        Toast.makeText(context, masseg.toString(), Toast.LENGTH_LONG).show()
    }

    /**
     * عرض رسالة Toast مع تحديد مدة العرض
     */
    fun Toast(context: Context, masseg: Int, LENGTH_LONG: Int) {
        Toast.makeText(context, masseg.toString(), LENGTH_LONG).show()
    }

    /**
     * قراءة ملف من مجلد assets
     * @param context سياق التطبيق
     * @param fileName اسم الملف
     */
    fun assets(context: Context, fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }

    /**
     * قراءة ملف من مساحة التخزين الداخلية للتطبيق
     * @param context سياق التطبيق
     * @param fileName اسم الملف
     */
    fun ReadFile(context: Context, fileName: String): String {
        val inputStream = context.openFileInput(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }

    /**
     * طلب أذونات من المستخدم
     */
    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        val notGrantedPermissions = permissions.filter {
            activity.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
        }
        if (notGrantedPermissions.isNotEmpty()) {
            activity.requestPermissions(notGrantedPermissions.toTypedArray(), requestCode)
        }
    }

    /**
     * التحقق من منح الأذونات
     */
    fun arePermissionsGranted(activity: Activity, permissions: Array<String>): Boolean {
        return permissions.all {
            activity.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * بدء نشاط جديد
     */
    fun startActivity(activity: Activity, intent: Intent) {
        activity.startActivity(intent)
    }

    /**
     * بدء نشاط جديد مع انتظار نتيجة
     */
    fun startActivityForResult(activity: Activity, intent: Intent, requestCode: Int) {
        activity.startActivityForResult(intent, requestCode)
    }

    /**
     * إنهاء النشاط الحالي
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
    }

    /**
     * حذف مجلد ومحتوياته
     */
    fun DeletFolider(folderPath: String) {
        val folder = File(folderPath)
        if (folder.exists() && folder.isDirectory) {
            folder.deleteRecursively()
        }
    }

    /**
     * التحقق من وجود مجلد
     */
    fun isFolderExists(folderPath: String): Boolean {
        val folder = File(folderPath)
        return folder.exists() && folder.isDirectory
    }

    fun readJsonFile(context: Context, fileName: String): JSONObject? {
        return try {
            /**
             * قراءة ملف JSON من مساحة التخزين الداخلية للتطبيق
             */
            val jsonString = ReadFile(context, fileName)
            JSONObject(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * تحميل صورة من الهاتف
     * @param activity النشاط الحالي
     * @param requestCode رمز الطلب
     */
    fun pickImageFromGallery(activity: Activity, requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(activity, intent, requestCode)
    }

    /**
     * حفظ الصورة في مساحة التخزين الداخلية للتطبيق
     * @param context سياق التطبيق
     * @param uri مسار الصورة
     * @param fileName اسم الملف
     */
    fun saveImageToInternalStorage(context: Context, uri: Uri, fileName: String) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(File(context.filesDir, fileName))
        inputStream?.copyTo(outputStream)
    }

    /**
     * إضافة منتج إلى سلة التسوق
     * @param context سياق التطبيق
     * @param product المنتج المراد إضافته (يمكن أن يكون كائن JSON أو أي هيكل بيانات آخر)
     * @param cartFileName اسم ملف سلة التسوق (عادةً "cart.json")
     */
    fun addProductToCart(context: Context, product: JSONObject, cartFileName: String = "cart.json") {
        try {
            val cartFile = File(context.filesDir, cartFileName)
            val cartJsonArray: JSONArray

            if (cartFile.exists()) {
                // إذا كان الملف موجودًا، اقرأ محتواه
                val cartString = ReadFile(context, cartFileName)
                cartJsonArray = if (cartString.isNotEmpty()) JSONArray(cartString) else JSONArray()
            } else {
                // إذا لم يكن الملف موجودًا، قم بإنشاء مصفوفة JSON جديدة
                cartJsonArray = JSONArray()
            }

            // أضف المنتج الجديد إلى المصفوفة
            cartJsonArray.put(product)

            // اكتب المصفوفة المحدثة إلى الملف
            val outputStream = FileOutputStream(cartFile)
            outputStream.write(cartJsonArray.toString().toByteArray())
            outputStream.close()

            Toast(context, "تمت إضافة المنتج إلى السلة")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast(context, "حدث خطأ أثناء إضافة المنتج إلى السلة")
        }
    }

    fun getCartProducts(context: Context, cartFileName: String = "cart.json"): JSONArray? {
        return try {
            val cartString = ReadFile(context, cartFileName)
            if (cartString.isNotEmpty()) JSONArray(cartString) else JSONArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}