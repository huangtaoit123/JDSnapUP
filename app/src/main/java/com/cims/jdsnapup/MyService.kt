package com.cims.jdsnapup

import android.accessibilityservice.AccessibilityService
import android.os.TestLooperManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : AccessibilityService() {
    var currentClassName: String = ""
    var checkNotificationCount: Int = 0

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d(TAG, "event: $event")
        event?.let {
            handleClassName(event)
            when (currentClassName) {
                JD_ACTIVITY, JD_FRAMELAYOUT -> {
                    shopping(event)
                }
                JD_NEWFILLORDERACTIVITY -> {
                    placeOrder(event)
                }
            }
        }
    }

    private fun handleClassName(event: AccessibilityEvent) {
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
            currentClassName = event.className as String
            Log.d(TAG, "currentClassName: $currentClassName")
            if (currentClassName == JD_HOME_ACTIVITY) {
                checkNotificationCount = 0
            }
            if (currentClassName == JD_ACTIVITY) {
                checkNotificationCount = 0
            }
            if (currentClassName == JD_FRAMELAYOUT) {
                checkNotificationCount = 0
            }



    }






    private fun pay(event: AccessibilityEvent) {
        var nodes = event.source?.findAccessibilityNodeInfosByText("立即支付")
        nodes?.forEach { node ->
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            Log.d(TAG, "onClick pay button")
        }
    }


    private fun shopping(event: AccessibilityEvent) {
        val nodes2 = event.source?.findAccessibilityNodeInfosByText("立即抢购")
        nodes2?.forEach { node ->
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            Log.d(TAG, "onClick 立即抢购 button")
        }

    }

    private fun placeOrder(event: AccessibilityEvent) {
        val nodes = event.source?.findAccessibilityNodeInfosByText("提交订单")
        nodes?.forEach { node ->
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            Log.d(TAG, "onClick shopping button")
        }


    }



    override fun onInterrupt() {
    }

    companion object {
        const val TAG = "MyService"
        const val JD_HOME_ACTIVITY = "com.jingdong.app.mall.MainFrameActivity"
        const val JD_ACTIVITY="com.jd.lib.productdetail.ProductDetailActivity"
        const val JD_FRAMELAYOUT="android.widget.FrameLayout"
        const val JD_NEWFILLORDERACTIVITY="com.jd.lib.settlement.fillorder.activity.NewFillOrderActivity"
        const val UNBOTTOMDIALOG="com.jingdong.common.unification.dialog.UnBottomDialog"
    }
}