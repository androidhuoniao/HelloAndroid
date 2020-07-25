package com.grass.helloandroid.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.BaseMvRxFragment
import com.grass.helloandroid.IFragmentSwitcher
import com.grass.helloandroid.R
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*


private class BaseEpoxyController(
    private val buildModelsCallback: EpoxyController.() -> Unit
) : AsyncEpoxyController() {
    override fun buildModels() {
        buildModelsCallback()
    }
}

abstract class BaseEpoxyFragment : BaseMvRxFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_base, container, false).apply {
            recyclerView.setController(BaseEpoxyController { buildModels() })
        }

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

    abstract fun EpoxyController.buildModels()

    fun getFragmentSwitcher(): IFragmentSwitcher {
        var iFragmentSwitcher = activity as? IFragmentSwitcher
        if (iFragmentSwitcher == null) {
            iFragmentSwitcher = object : IFragmentSwitcher {
                override fun showFragment(action: Int, fragment: Fragment) {
                    Log.i("grass", "showFragment: ")
                }
            }
        }
        return iFragmentSwitcher!!
    }

}