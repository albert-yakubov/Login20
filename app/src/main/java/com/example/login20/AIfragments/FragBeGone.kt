package com.example.login20.AIfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login20.R

class FragBeGone : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_be_gone, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    override fun onDetach() {
        super.onDetach()

    }

}