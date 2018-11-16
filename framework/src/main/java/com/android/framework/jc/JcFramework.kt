package com.android.framework.jc

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.*

/**
 *@author   Mr.Hu(Jc) KtFramework
 *@create   2018/11/16 14:11
 *@describe
 *@update
 */
class JcFramework private constructor(){
    val mActivityList = LinkedList<Activity>()
    var mApplication:Application?=null;
    var mTopActivity:Activity?=null

    private object Holder {
        @SuppressLint("StaticFieldLeak")
        val INSTANCE = JcFramework()
    }

    companion object {
        fun getInstance(): JcFramework {
            return Holder.INSTANCE;
        }
    }

    private fun initialize(application: Application){
        mApplication=application;
        mApplication?.registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
                mTopActivity=activity
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
                if (activity!=null){
                    mActivityList-=activity
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
               if (activity!=null) {
                   mActivityList += activity
               }
            }

        })
    }

     fun getTopActivity(): Activity? {
       return mTopActivity
    }
}