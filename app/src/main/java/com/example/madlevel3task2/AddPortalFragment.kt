package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_portal.*
import kotlinx.android.synthetic.main.portal_layout.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
const val PORTAL_KEY = "portal_key"
const val BUNDLE_KEY = "bundle_key"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_add_portal).setOnClickListener {
            onAddPortal()
        }
    }


    private fun onAddPortal() {
        val titleTxt = input_portal_name.text.toString()
        val urlTxt = input_portal_url.text.toString()

        if (titleTxt.isNotBlank() && urlTxt.isNotBlank()) {
            setFragmentResult(PORTAL_KEY, bundleOf(Pair(BUNDLE_KEY, Portal(titleTxt, urlTxt))))
            findNavController().popBackStack()
        } else {
          //  Snackbar.make(input_portal_name, getString(R.string.error), Snackbar.LENGTH_LONG).show()
            Toast.makeText(context,R.string.error,Toast.LENGTH_SHORT).show()
        }
    }
}