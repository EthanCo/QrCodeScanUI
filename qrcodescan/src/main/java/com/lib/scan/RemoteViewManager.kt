package com.lib.scan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.huawei.hms.hmsscankit.RemoteView

/**
 * 华为扫码库 RemoteView 管理类
 *
 * @author Heiko
 * @date 2021/8/14 0014
 */
class RemoteViewManager(val remoteView: RemoteView?) : DefaultLifecycleObserver {
    fun onCreate(activity: AppCompatActivity, savedInstanceState: Bundle?) {
        onCreate(activity.lifecycle, savedInstanceState)
    }

    fun onCreate(fragment: Fragment, savedInstanceState: Bundle?) {
        onCreate(fragment.lifecycle, savedInstanceState)
    }

    fun onCreate(lifecycle: Lifecycle, savedInstanceState: Bundle?) {
        lifecycle.addObserver(this)
        remoteView?.onCreate(savedInstanceState)
    }

    override fun onResume(owner: LifecycleOwner) {
        remoteView?.onResume()
    }

    override fun onStart(owner: LifecycleOwner) {
        remoteView?.onStart()
    }

    override fun onPause(owner: LifecycleOwner) {
        remoteView?.onPause()
    }

    override fun onStop(owner: LifecycleOwner) {
        remoteView?.onStop()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        remoteView?.onDestroy()
    }
}