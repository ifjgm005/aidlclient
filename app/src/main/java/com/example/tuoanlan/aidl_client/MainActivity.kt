package com.example.tuoanlan.aidl_client

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.tuoanlan.servicebestdemo.ITree
import com.example.tuoanlan.servicebestdemo.ITreePlantCenter
import kotlinx.android.synthetic.main.activity_main.*


private val TAG = "MainActivity"
var center: ITreePlantCenter? = null

class MainActivity : AppCompatActivity() {
    private val serviceConnection: AidlServiceCon = AidlServiceCon()
    private var treeList:List<ITree> ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()

        //绑定service
        bindService()


    }

    private fun bindDataToRecycleview(treeList: List<ITree>?) {
        if (treeList != null) {
            recycle_list.layoutManager = LinearLayoutManager(this)
            recycle_list.adapter = MyAdapter(this, treeList)
        }
    }

    //设置监听器
    private fun setListener() {


        //种植，数据流向为双向inout
        btn_plant_tree.setOnClickListener {
            plantTreeInout()
        }


        btn_get_p.setOnClickListener {
            if (center != null) {
                treeList= center?.trees()
            }else{
                Log.e(TAG, "服务处于断开状态,无法读取列表")
            }
            bindDataToRecycleview(treeList)
        }

        btn_count.setOnClickListener{
            if (center!=null){
                val count= center?.treesCount
                Log.e(TAG,"森林中树木种类为$count")
            }else{
                Log.e(TAG, "服务处于断开状态,无法读取列表")
            }
        }

        //种植，数据流向为in
        btn_plan_in.setOnClickListener {
            plantTreeIn()
        }

        //种植，数据流向为out
        btn_plant_out.setOnClickListener {
            plantTreeOut()
        }
    }

    /**
     *
     * */
    private fun plantTreeInout() {
        if (center != null) {
            try {
                val tree = ITree("无花果")
                Log.e(TAG, "调用种植方法前树名称：${tree.name},哈希值：${tree.hashCode()}")
                center?.plantTreeInout(tree)
                Log.e(TAG, "调用种植方法后树名称：${tree.name},哈希值：${tree.hashCode()}")
            } catch (e: Exception) {

            }
        } else {
            Log.e(TAG, "服务处于断开状态，无法添加")
        }
    }

    //
    private fun plantTreeIn() {
        if (center != null) {
            try {
                val tree = ITree("无花果")
                Log.e(TAG, "调用种植方法前树名称：${tree.name},哈希值：${tree.hashCode()}")
                center?.plantTreeIn(tree)
                Log.e(TAG, "调用种植方法后树名称：${tree.name},哈希值：${tree.hashCode()}")
            } catch (e: Exception) {

            }
        } else {
            Log.e(TAG, "服务处于断开状态，无法添加")
        }
    }



    private fun plantTreeOut() {
        if (center != null) {
            try {
                val tree = ITree("无花果")
                Log.e(TAG, "调用种植方法前树名称：${tree.name},哈希值：${tree.hashCode()}")
                center?.plantTreeout(tree)
                Log.e(TAG, "调用种植方法后树名称：${tree.name},哈希值：${tree.hashCode()}")
            } catch (e: Exception) {

            }
        } else {
            Log.e(TAG, "服务处于断开状态，无法添加")
        }
    }




    private fun bindService() {
        val intent = Intent()
        intent.`package` = "com.example.tuoanlan.servicebestdemo"
        intent.action = "com.example.aidl"
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
    }


    class AidlServiceCon : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e(TAG, "从远程服务断开")

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e(TAG, "绑定到远程服务成功")
            center = ITreePlantCenter.Stub.asInterface(service)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }

}
