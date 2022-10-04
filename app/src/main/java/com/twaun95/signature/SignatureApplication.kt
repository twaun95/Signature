package com.twaun95.signature

import android.app.Application
import com.twaun95.signature.di.modules.HandlerModule
import com.twaun95.signature.di.modules.ViewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class SignatureApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
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