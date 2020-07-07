package com.dxtdkwt.zzh.appframework.ui.loadinterface

import android.content.Context
import dalvik.system.DexClassLoader

class DLClassLoader(dexPath: String, optimizedDirectory: String, librarySearchPath: String?, parent: ClassLoader) : DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, parent) {

    companion object {
        const val TAG = "DLClassLoader"
        val mPluginClassLoaders = HashMap<String, DLClassLoader>()

        fun getClassLoader(dexPath: String, context: Context, parentLoader: ClassLoader): DLClassLoader {
            var dLClassLoader = mPluginClassLoaders.get(dexPath)
            if (dLClassLoader != null) {
                return dLClassLoader
            }
            val dexOutputDir = context.getDir("dex", Context.MODE_PRIVATE)
            val dexOutputPath = dexOutputDir.absolutePath
            dLClassLoader = DLClassLoader(dexPath, dexOutputPath, null, parentLoader)
            mPluginClassLoaders.put(dexPath, dLClassLoader)
            return dLClassLoader
        }
    }
}