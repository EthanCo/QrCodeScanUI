package com.heiko.qrcodescan

import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.heiko.qrcodescan.databinding.ActivityScanTest1Binding
import com.huawei.hms.hmsscankit.RemoteView
import com.huawei.hms.ml.scan.HmsScan
import com.heiko.scan.RemoteViewManager

/**
 * 扫码库样式一
 *
 * @author Heiko
 * @date 2021/8/14 0014
 */
class ScanTest1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityScanTest1Binding
    private var remoteView: RemoteView? = null
    private val remoteViewManager: RemoteViewManager by lazy {
        RemoteViewManager(remoteView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initScanView(savedInstanceState)
    }

    private fun initScanView(savedInstanceState: Bundle?) {
        val dm = resources.displayMetrics
        //2.get screen size
        val density = dm.density
        val mScreenWidth = dm.widthPixels
        val mScreenHeight = dm.heightPixels
        val scanFrameSize = (SCAN_FRAME_SIZE * density)

        val rect = Rect()
        apply {
            rect.left = (mScreenWidth / 2 - scanFrameSize / 2).toInt()
            rect.right = (mScreenWidth / 2 + scanFrameSize / 2).toInt()
            rect.top = (mScreenHeight / 2 - scanFrameSize / 2).toInt()
            rect.bottom = (mScreenHeight / 2 + scanFrameSize / 2).toInt()
        }

        remoteView = RemoteView.Builder()
                .setContext(this)
                .setBoundingBox(rect)
                //.setFormat(HmsScan.ALL_SCAN_TYPE)
                .setFormat(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE)
                .build()
        remoteView?.setOnResultCallback { result ->
            if (result != null && result.isNotEmpty() && result[0] != null && !TextUtils.isEmpty(
                            result[0].getOriginalValue()
                    )
            ) {
                onScanResultCallback(result[0].originalValue)
            }
        }
        remoteViewManager.onCreate(this,savedInstanceState)

        // Add the defined RemoteView to the page layout.
        val params = FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        )
        binding.rim1.addView(remoteView, params)
    }

    private fun onScanResultCallback(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        finish()
    }

    companion object {
        const val SCAN_FRAME_SIZE = 300
    }
}