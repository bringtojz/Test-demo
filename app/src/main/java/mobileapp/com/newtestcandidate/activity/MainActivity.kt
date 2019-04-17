package mobileapp.com.newtestcandidate.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import mobileapp.com.newtestcandidate.R
import mobileapp.com.newtestcandidate.adapter.MenuAdapter

class MainActivity : AppCompatActivity() {

    private val menuList: List<String> by lazy { listOf("json", "alert", "image","camera") }
    val PERMISSIONS_REQUEST_CAMERA  = 0x2

    private lateinit var adapterList: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        adapterList = MenuAdapter(this, onClick)
        with(recycler_view) {
            adapter = adapterList
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        getData()
    }


    private fun getData() {
        adapterList.setData(menuList)
    }

    private val onClick: (positon: Int) -> Unit = { position ->
        when (position) {
            0 -> {
                startPage(DataListActivity::class.java)
            }
            1 -> {
                startPage(AlertDialogActivity::class.java)
            }
            2 -> {
                startPage(ImageActivity::class.java)
            }
            3->{
                openCamera()
            }
        }

    }

    private fun startPage(act: Class<*>) {
        val intentAct = Intent(this, act)
        startActivity(intentAct)
    }

    private fun openCamera() {
        //Build.VERSION.SDK_INT < 23
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            camera()
        }
        //Build.VERSION.SDK_INT >= 23
        else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), PERMISSIONS_REQUEST_CAMERA)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            PERMISSIONS_REQUEST_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    camera()
                }
            }

        }

    }
    fun camera(){
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }

}
