package com.grass.helloandroid.accountsync

import android.accounts.*
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.grass.helloandroid.accountsync.AccountCreator.ACCOUNT_NAME
import com.grass.helloandroid.accountsync.AccountCreator.ACCOUNT_TYPE

/**
 *
 * Created by grassswwang
 * on 2020/7/31
 * Email: grassswwang@tencent.com
 */
class AccountAuthService : Service() {

    var authenticator: AccountAuthenticator? = null

    override fun onCreate() {
        super.onCreate()
        authenticator = AccountAuthenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return authenticator!!.iBinder
    }

    class AccountAuthenticator(context: Context) : AbstractAccountAuthenticator(context) {

        var myContext: Context

        init {
            myContext = context
        }

        override fun editProperties(
            accountAuthenticatorResponse: AccountAuthenticatorResponse?,
            s: String?
        ): Bundle? {
            throw UnsupportedOperationException()
        }

        @Throws(NetworkErrorException::class)
        override fun addAccount(
            accountAuthenticatorResponse: AccountAuthenticatorResponse?,
            s: String?,
            s2: String?,
            strings: Array<String?>?,
            bundle: Bundle?
        ): Bundle? {
            Log.i(AccountTag.TAG, "addAccount:  ")
            AccountCreator.createSyncAccount(myContext) //创建帐户
            val b = Bundle()
            b.putString(AccountManager.KEY_ACCOUNT_NAME, ACCOUNT_NAME)
            b.putString(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE) //返回null会导致添加帐户页无反应
            return b
        }

        @Throws(NetworkErrorException::class)
        override fun confirmCredentials(
            accountAuthenticatorResponse: AccountAuthenticatorResponse?,
            account: Account?, bundle: Bundle?
        ): Bundle? {
            return null
        }

        @Throws(NetworkErrorException::class)
        override fun getAuthToken(
            accountAuthenticatorResponse: AccountAuthenticatorResponse?,
            account: Account?, s: String?, bundle: Bundle?
        ): Bundle? {
            throw UnsupportedOperationException()
        }

        override fun getAuthTokenLabel(s: String?): String? {
            throw UnsupportedOperationException()
        }

        @Throws(NetworkErrorException::class)
        override fun updateCredentials(
            accountAuthenticatorResponse: AccountAuthenticatorResponse?,
            account: Account?, s: String?, bundle: Bundle?
        ): Bundle? {
            throw UnsupportedOperationException()
        }

        @Throws(NetworkErrorException::class)
        override fun hasFeatures(
            accountAuthenticatorResponse: AccountAuthenticatorResponse?,
            account: Account?, strings: Array<String?>?
        ): Bundle? {
            throw UnsupportedOperationException()
        }
    }
}