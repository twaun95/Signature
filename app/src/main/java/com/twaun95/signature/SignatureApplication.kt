package com.twaun95.signature

import android.app.Application
import com.twaun95.signature.di.modules.HandlerModule
import com.twaun95.signature.di.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SignatureApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SignatureApplication)
            modules(
                ViewModelModule.module,
                HandlerModule.module
            )
        }
    }
}