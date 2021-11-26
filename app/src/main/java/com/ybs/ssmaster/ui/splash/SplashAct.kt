package com.ybs.ssmaster.ui.splash

import android.Manifest
import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import com.ybs.base.data.AppDataManager
import com.ybs.base.util.getStr
import com.ybs.base.util.saveString
import com.ybs.reslib.databinding.ActSplashBinding
import com.ybs.ssmaster.ui.common.AppBaseAct
import com.ybs.ssmaster.ui.main.MainAct
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val KEY_PSW = "keyPsw"
const val KEY_NAME = "keyName"

class SplashAct : AppBaseAct<ActSplashBinding, SplashVm>() {

    override fun getVmClz() = SplashVm::class.java

    override fun getLayoutBinding() = ActSplashBinding.inflate(layoutInflater)

    override fun getPermissionArray() = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        super.onPermissionsDenied(requestCode, perms)
        lifecycleScope.launch {
            ToastUtils.showLong("无相关权限，2秒后APP退出")
            delay(2000)
            finish()
        }
    }


    private fun toMainAct() {
        MainAct.startAct(this@SplashAct)
//        finish()
    }


    override fun initView() {
        val n = getStr(KEY_NAME)
        if (!TextUtils.isEmpty(n)) {
            login(n, getStr(KEY_PSW))
        }
        binding.btnLogin.setOnClickListener {
//            val name = binding.etName.text.toString()
//            val psw = binding.etPsw.text.toString()
            val name = "admin"
            val psw = "admin123"
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psw)) {
                ToastUtils.showLong("请输入正确的用户名和密码！")
                return@setOnClickListener
            }

            login(name, psw)
        }
    }

    private fun login(name: String, psw: String) {
        vm.login(name, psw).observe(this, {
            if (it?.data?.isOk() == true) {
                AppDataManager.inst.token = it.data?.token ?: ""
                //                    ToastUtils.showLong("登录成功！")
                saveString(KEY_NAME, name)
                saveString(KEY_PSW, psw)
                toMainAct()
            } else {
                ToastUtils.showLong("登录失败！")
            }
        })
    }

}