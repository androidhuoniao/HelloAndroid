package com.grass.helloandroid.accountsync

import android.accounts.Account
import android.accounts.AccountManager
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle

/**
 *
 * Created by grassswwang
 * on 2020/7/31
 * Email: grassswwang@tencent.com
 */
object AccountCreator {
    val PN = "com.grass.helloandroid"
    val ACCOUNT_TYPE: String = "$PN.account.sync"
    val ACCOUNT_NAME = "HelloAndroid-account"
    val CONTENT_AUTHORITY: String = "${PN}.account"
    val SYNC_PERIOD = 20L * 60

    fun createSyncAccount(context: Context) {
        val accountManager =
            context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        var account = Account(ACCOUNT_NAME, ACCOUNT_TYPE)
        var result = accountManager.addAccountExplicitly(account, "", null)
        if (result) {
            ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1)
            ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true)
        }
        ContentResolver.addPeriodicSync(account, CONTENT_AUTHORITY, Bundle(), SYNC_PERIOD)
    }
}