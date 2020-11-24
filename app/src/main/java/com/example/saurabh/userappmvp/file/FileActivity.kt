package com.example.saurabh.userappmvp.file

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.Toast
import com.example.saurabh.userappmvp.BuildConfig
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseActivity
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_file.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import java.io.*
import java.util.*
import javax.inject.Inject

class FileActivity : BaseActivity() , ProgressListener {

    override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
        if(progressBar.isIndeterminate){
            progressBar.isIndeterminate = false
            textView.text = "Downloading.."
        }
        progressBar.progress = (100 * bytesRead / contentLength).toInt()
    }


    @Inject
    lateinit var repository : UserRepository

    init {
        DaggerUserComponent.create().inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        ProgressListenerHelper.progressListener = this
        selectButton.setOnClickListener {
            pickFile(requestCode = 100)
        }
    }


    private fun pickFile(fileType: String = "*/*", requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!packageManager.canRequestPackageInstalls()) {

                startActivityForResult(Intent(
                        Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).setData(Uri.parse(String.format("package:%s",
                        packageName
                ))), 1234);
            } else {
                requestPermission(fileType, requestCode)
            }
        } else {
            requestPermission(fileType, requestCode)
        }

    }

    private fun requestPermission(fileType: String = "*/*", requestCode: Int) {
        Permissions.check(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, null /*options*/,
                object : PermissionHandler() {
                    override fun onGranted() {
                        val intent = Intent(Intent.ACTION_GET_CONTENT);
                        intent.type = fileType;
                        startActivityForResult(intent, requestCode);
                    }

                    override fun onDenied(
                            context: Context?, deniedPermissions: ArrayList<String>?
                    ) {
                        Toast.makeText(
                                context,
                                "Storage permission is required to perform this operation",
                                Toast.LENGTH_SHORT
                        ).show()
                        super.onDenied(context, deniedPermissions)
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    var disposable : Disposable ?= null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.data?.let {
            val path: String? = FileLocalUtils.getPath(this, it)
            if (path?.substringAfterLast(".").equals("AAB", true)) {
                if (path != null && FileLocalUtils.isLocal(path)) {
                    val file = FileLocalUtils.getFile(baseContext,it)

                    textView.text = "Uploading...."
                    var lastValue = -1
                    val bodyX = UploadRequestBody(file, "*", object  : UploadRequestBody.UploadCallback {
                        @RequiresApi(Build.VERSION_CODES.N)
                        override fun onProgressUpdate(percentage: Int) {
                            if(percentage > lastValue) {
                                lastValue = percentage
                                progressBar.progress = lastValue


                                if(percentage == 99){
                                    progressBar.progress = 100
                                    progressBar.isIndeterminate = true
                                    textView.text = "Converting.."
                                }
                                Log.i("XXXXX Percent", percentage.toString())
                            }

                        }
                    })

                    val temp =
                            MultipartBody.Part.createFormData("imageFile", file.name,
                                    bodyX)

                    disposable = repository.remote.convertBundleToApk(bundleFile = temp).subscribe(
                            {
                                //progressBar.progress = (100)
                                saveFile(it,"baidu",file)
                                Toast.makeText(baseContext, it.toString(), Toast.LENGTH_SHORT).show()
                            },{
                        //progressBar.progress = (100)
                        Toast.makeText(baseContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                    )

                } else {
                    Toast.makeText(this@FileActivity, "Non Local Path", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@FileActivity, "Please select an AAB (App Bundle) file!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun saveFile(body: ResponseBody?, pathWhereYouWantToSaveFile: String,path : File):String{
        if (body==null)
            return ""



        var input: InputStream? = null
        try {
            input = body.byteStream()

            val root: String = Environment.getExternalStorageDirectory().toString()
            val file = File(root+"/bundle2apk/"+path.name.substringBefore(".aab")+"${System.currentTimeMillis()}.apk")
            val fos = FileOutputStream(file)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int = 0
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }

            installApk(file)
            return pathWhereYouWantToSaveFile
        }catch (e:Exception){
            Log.e("saveFile",e.toString())
        }
        finally {
            input?.close()
        }
        return ""
    }

    fun installApk(toInstall : File){
        val intent: Intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val apkUri = FileProvider.getUriForFile(
                    this, BuildConfig.APPLICATION_ID + ".fileprovider", toInstall
            )
            intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
            intent.data = apkUri
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        } else {
            val apkUri = Uri.fromFile(toInstall)
            intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        progressBar.progress = 0

        startActivity(intent)
    }
}