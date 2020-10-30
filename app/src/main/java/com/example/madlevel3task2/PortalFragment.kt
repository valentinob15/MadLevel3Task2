package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_portal.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

val portals = arrayListOf<Portal>()
val portalAdapter = PortalAdapter(portals)

class FirstFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeAddReminderResult()

    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(PORTAL_KEY) {
                key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_KEY)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initViews() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = portalAdapter
        createItemTouchHelper().attachToRecyclerView(recyclerView)
    }


    // Touch helper for the portal cards for a right swipe
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Delete the card on swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                portals.removeAt(position)
                portalAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}