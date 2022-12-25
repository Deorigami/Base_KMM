package com.eyedea.feature_config.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.eyedea.feature_config.router.GlobalRouterContract


abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment(), Binding {

    /**
     * DATA
     * */

    open var binding: VB? = null
    abstract val layout: Int
    abstract val TAG: String?
    private var rootView: View? = null
    protected var hasInitializedRootView = false
    open var useSaveViewState = false
    open var useDefaultThemeResolver = true
    open var customOnBackPressedCallback: (() -> Unit)? = null

    // Router
    open val router: GlobalRouterContract? = null

    /**
     * Lifecycles
     * */

    override fun onResume() {
        super.onResume()
        Log.d("PageClassName", "$TAG");
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCustomBackPressedCallback()
        didMount(view)
        didMount(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    /**
     * METHODS
     * */

    open fun didMount(view: View) {
        initBinding(view)
    }


    open fun didMount(view: View, savedInstanceState: Bundle?) {
        initBinding(view)
    }

    open fun showLoading(
        isCancelable: Boolean = false,
        onCanceled: (() -> Unit)? = null,
    ) {
        val loadingDialog = LoadingDialog(onCanceled = onCanceled)
        loadingDialog.isCancelable = isCancelable
        if (!loadingDialog.isVisible) loadingDialog.show(childFragmentManager, "loadingDialog")
    }

    open fun closeLoading() {
        val loadingDialog: LoadingDialog? = try {
            childFragmentManager.findFragmentByTag("loadingDialog") as LoadingDialog
        } catch (e: Exception) {
            null
        }
        loadingDialog?.dismiss()
    }

    private fun setCustomBackPressedCallback() {
        customOnBackPressedCallback?.let {
            requireActivity().onBackPressedDispatcher
                .addCallback(
                    viewLifecycleOwner,
                    object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            it.invoke()
                        }
                    },
                )
        }
    }

    fun closeKeyboard() {
        ViewCompat.getWindowInsetsController(requireView())?.hide(WindowInsetsCompat.Type.ime())
    }
}