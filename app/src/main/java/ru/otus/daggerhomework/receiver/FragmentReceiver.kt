package ru.otus.daggerhomework.receiver

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import ru.otus.daggerhomework.MainActivity
import ru.otus.daggerhomework.R
import javax.inject.Inject

class FragmentReceiver : Fragment() {

    @Inject
    lateinit var viewModel: ViewModelReceiver

    private lateinit var frame: View

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        (activity as MainActivity).mainActivityComponent.fragmentReceiverComponent().build()
            .injectFragmentReceiver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frame = view.findViewById(R.id.frame)
        viewModel.event.observe(viewLifecycleOwner) {
            viewModel.observeColors()
            populateColor(it)
        }
    }

    fun populateColor(@ColorInt color: Int) {
        frame.setBackgroundColor(color)
    }
}