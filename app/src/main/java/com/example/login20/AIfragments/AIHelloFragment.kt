package com.example.login20.AIfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login20.R

// TODO 1.1 creating AI fragment to interact with users, this first one should pop up and say hello!
class AIHelloFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_i_hello, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    //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
    override fun onDetach() {
        super.onDetach()

    }

}

