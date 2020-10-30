package com.example.madlevel3task2

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portal_layout.view.*

class PortalAdapter(private val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun databind(portal: Portal) {
            itemView.portal_name.text = portal.name
            itemView.portal_url.text = portal.url
            itemView.setOnClickListener {
                openBrowser(itemView.context, Uri.parse(portal.url))
            }
        }

    }

    private fun openBrowser(context: Context, uri: Uri) {
        val customTabsIntent = CustomTabsIntent.Builder()
        customTabsIntent.build().launchUrl(context, uri)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portal_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.databind(portals[position])
            }

    override fun getItemCount(): Int {
            return portals.size
    }

}