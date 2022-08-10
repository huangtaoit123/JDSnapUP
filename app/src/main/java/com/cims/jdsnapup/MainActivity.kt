package com.cims.jdsnapup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt).setOnClickListener {
            try {
                val accessibleIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                startActivity(accessibleIntent)
            } catch (e: Exception) {
                it?.let {
                    Snackbar.make(it, "跳转失败，请前往系统设置>无障碍服务>插件助手", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }
}