package com.example.nativ.View.Pages.Home.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nativ.R

/**
 * A simple [Fragment] subclass.
 * Use the [PageHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class PageHome : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_home, container, false)
    }

}