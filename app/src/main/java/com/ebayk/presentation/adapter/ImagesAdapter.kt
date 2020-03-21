package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebayk.R
import kotlinx.android.synthetic.main.item_image.view.*

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
class ImagesAdapter(
    private val listUrl: List<String>
) :
    RecyclerView.Adapter<ImagesAdapter.ModelViewHolder>() {

    class ModelViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val url = listUrl[position]
        val newUrl = url.replace("{imageId}", position.toString(), true)
        Glide.with(holder.view.context)
            .load(newUrl)
            .into(holder.view.iv_picture)

        holder.view.tv_counter.text = "${position + 1}/${listUrl.size}"

        holder.view.iv_share.setOnClickListener {
            Toast.makeText(it.context, "Will be implemented!", Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount() = listUrl.size
}