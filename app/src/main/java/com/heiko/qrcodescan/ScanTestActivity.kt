package com.heiko.qrcodescan

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.heiko.qrcodescan.databinding.ActivityScanTestBinding

class ScanTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanTest1.setOnClickListener {
            val intent = Intent(this, ScanTest1Activity::class.java)
            startActivity(intent)
        }

        binding.btnScanTest2.setOnClickListener {
            val intent = Intent(this, ScanTest2Activity::class.java)
            startActivity(intent)
        }

        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
                1)
    }
}