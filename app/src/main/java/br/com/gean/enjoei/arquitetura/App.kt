package br.com.gean.enjoei.arquitetura

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import android.util.Log
import br.com.gean.enjoei.BuildConfig
import br.com.gean.enjoei.utils.FontsOverride
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.facebook.stetho.Stetho
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        CaocConfig.Builder.create().apply()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        } else {
            Timber.plant(CrashReportingTree())
        }

        // Carregando as fontes da aplicação
        FontsOverride(this).loadFonts();
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    companion object {
        lateinit var instance: App private set
    }

    class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            //Crashlytics.log(priority, tag, message)

            if (t != null) {
                //Crashlytics.logException(t)
            }
        }
    }
}
