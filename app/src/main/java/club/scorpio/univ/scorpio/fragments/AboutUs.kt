package club.scorpio.univ.scorpio.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import club.scorpio.univ.scorpio.R

class AboutUs : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.frag_about_us, container, false)
    }

    // TODO: make it an activity not a fragment
}