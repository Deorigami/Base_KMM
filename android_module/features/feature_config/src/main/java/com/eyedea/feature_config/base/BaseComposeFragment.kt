package com.eyedea.feature_config.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.eyedea.feature_config.router.GlobalRouterContract

abstract class BaseComposeFragment : Fragment(), SetContent {

    abstract val TAG : String

    open val router : GlobalRouterContract? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { MaterialTheme { ComposeContent() } }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("PageClassName", TAG);
    }
}