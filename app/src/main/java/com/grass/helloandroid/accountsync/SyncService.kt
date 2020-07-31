package com.grass.helloandroid.accountsync

import android.accounts.Account
import android.app.Service
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log

/**
 *
 * Created by grassswwang
 * on 2020/7/31
 * Email: grassswwang@tencent.com
 */
class SyncService : Service() {

    private val sSyncAdapterLock = Any()
    private var sSyncAdapter: SyncAdapter? = null

    override fun onCreate() {
        super.onCreate()
        synchronized(sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = SyncAdapter(this, true)
            }
        }

        // 因该组件导致Push进程启动时，会自动拉起PushService.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        synchronized(sSyncAdapterLock) { return sSyncAdapter!!.syncAdapterBinder }
    }

    private class SyncAdapter : AbstractThreadedSyncAdapter {
        constructor(context: Context?, autoInitialize: Boolean) : super(
            context,
            autoInitialize
        )

        override fun onPerformSync(
            account: Account, extras: Bundle, authority: String,
            provider: ContentProviderClient, syncResult: SyncResult
        ) {
            Log.i(AccountTag.TAG, "SyncAdapter.onPerformSync: ")
        }
    }
}