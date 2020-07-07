package com.dxtdkwt.zzh.appframework.ui.activity

import android.app.AlertDialog
import android.app.ListActivity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.ShortcutInfo
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dxtdkwt.zzh.appframework.R
import com.dxtdkwt.zzh.appframework.utils.ShortcutHelper
import java.util.*

/**
 * 复制google
 */
class ShortcutActivity : ListActivity(), View.OnClickListener {

    companion object {
        //todo   引用kotlin的变量为什么要用这个，还不知道对错。。。。
        @kotlin.jvm.JvmField
        val TAG: String = "ShortcutSample"
    }

    private val ID_ADD_WEBSITE = "add_website"

    private val ACTION_ADD_WEBSITE = "com.example.android.shortcutsample.ADD_WEBSITE"

    private var mAdapter: MyAdapter? = null

    private var mHelper: ShortcutHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortcut)
        mHelper = ShortcutHelper(this)

        mHelper?.maybeRestoreAllDynamicShortcuts()

        mHelper?.refreshShortcuts( /*force=*/false)

        if (ACTION_ADD_WEBSITE == intent.action) { // Invoked via the manifest shortcut.
            addWebSite()
        }

        mAdapter = MyAdapter(this.applicationContext)
        setListAdapter(mAdapter)
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    /**
     * Handle the add button.
     */
    fun onAddPressed(v: View?) {
        addWebSite()
    }

    private fun addWebSite() {
        Log.i(TAG, "addWebSite")
        // This is important.  This allows the launcher to build a prediction model.
        mHelper!!.reportShortcutUsed(ID_ADD_WEBSITE)
        val editUri = EditText(this)
        editUri.hint = "http://www.android.com/"
        editUri.inputType = EditorInfo.TYPE_TEXT_VARIATION_URI
        AlertDialog.Builder(this)
                .setTitle("Add new website")
                .setMessage("Type URL of a website")
                .setView(editUri)
                .setPositiveButton("Add") { dialog: DialogInterface?, whichButton: Int ->
                    val url = editUri.text.toString().trim { it <= ' ' }
                    if (url.length > 0) {
                        addUriAsync(url)
                    }
                }
                .show()
    }

    private fun addUriAsync(uri: String) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                mHelper!!.addWebSiteShortcut(uri)
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                refreshList()
            }
        }.execute()
    }

    private fun refreshList() {
        mAdapter!!.setShortcuts(mHelper!!.shortcuts)
    }

    override fun onClick(v: View) {
        val shortcut = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            (v.parent as View).tag as ShortcutInfo
        } else {
            TODO("VERSION.SDK_INT < N_MR1")
        }
        when (v.id) {
            R.id.disable -> {
                if (shortcut.isEnabled) {
                    mHelper!!.disableShortcut(shortcut)
                } else {
                    mHelper!!.enableShortcut(shortcut)
                }
                refreshList()
            }
            R.id.remove -> {
                mHelper!!.removeShortcut(shortcut)
                refreshList()
            }
        }
    }

    private val EMPTY_LIST: List<ShortcutInfo> = ArrayList()

    public fun getType(shortcut: ShortcutInfo): String? {
        val sb = StringBuilder()
        var sep = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            if (shortcut.isDynamic) {
                sb.append(sep)
                sb.append("Dynamic")
                sep = ", "
            }
            if (shortcut.isPinned) {
                sb.append(sep)
                sb.append("Pinned")
                sep = ", "
            }
            if (!shortcut.isEnabled) {
                sb.append(sep)
                sb.append("Disabled")
                sep = ", "
            }
        }
        return sb.toString()
    }

    inner private class MyAdapter(private val mContext: Context) : BaseAdapter() {
        private lateinit var mInflater: LayoutInflater
        private var mList: List<ShortcutInfo> = EMPTY_LIST
        override fun getCount(): Int {
            return mList.size
        }

        override fun getItem(position: Int): Any {
            return mList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        override fun areAllItemsEnabled(): Boolean {
            return true
        }

        override fun isEnabled(position: Int): Boolean {
            return true
        }

        fun setShortcuts(list: List<ShortcutInfo>) {
            mList = list
            notifyDataSetChanged()
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            val view: View
            if (convertView != null) {
                view = convertView
            } else {
                view = mInflater.inflate(R.layout.list_item, null)
            }
            bindView(view, position, mList[position])
            return view
        }

        fun bindView(view: View, position: Int, shortcut: ShortcutInfo) {
            view.tag = shortcut
            val line1 = view.findViewById<View>(R.id.line1) as TextView
            val line2 = view.findViewById<View>(R.id.line2) as TextView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                line1.text = shortcut.longLabel
                line2.setText(getType(shortcut))
                val remove = view.findViewById<View>(R.id.remove) as Button
                val disable = view.findViewById<View>(R.id.disable) as Button
                disable.setText(
                        if (shortcut.isEnabled) "Disable" else "Enable")
                remove.setOnClickListener(this@ShortcutActivity)
                disable.setOnClickListener(this@ShortcutActivity)
            }
        }

        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mInflater = mContext.getSystemService(LayoutInflater::class.java)
            }
        }
    }


}
