package br.com.gean.enjoei.arquitetura

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import br.com.gean.enjoei.R
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

class Util {

    fun substituirFragment(fragmentManager: FragmentManager?,
                           fragmentClass: Class<*>,
                           idLayout: Int) {
        if (fragmentManager == null) {
            return
        }

        val nomeFragment = fragmentClass.canonicalName

        if (isFragmentInBackstack(fragmentManager, nomeFragment)) {
            fragmentManager.popBackStackImmediate(nomeFragment, 0)
        } else {
            try {
                val fragment = fragmentClass.newInstance() as Fragment
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(idLayout, fragment, nomeFragment).addToBackStack(nomeFragment).commitAllowingStateLoss()
            } catch (e: Exception) {
                Timber.e(e, "Erro ao instanciar o fragment")
            }

        }
    }

    fun isFragmentInBackstack(fragmentManager: FragmentManager, fragmentTagName: String): Boolean {
        for (entry in 0 until fragmentManager.backStackEntryCount) {
            if (fragmentTagName == fragmentManager.getBackStackEntryAt(entry).name) {
                return true
            }
        }
        return false
    }

    fun getRequestPadrao(): RequestOptions {
        val options = RequestOptions()
                .centerInside()
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_placeholder)
                .fallback(R.drawable.default_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .centerInside()
                .priority(Priority.HIGH)
        return options
    }

}