package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.R
import kotlinx.android.synthetic.main.item_feature.view.*

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
class FeaturesAdapter(
    private val list: List<String>
) :
    RecyclerView.Adapter<FeaturesAdapter.ModelViewHolder>() {

    class ModelViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feature, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val title = list[position]
        holder.view.tv_title.text = title

    }

    override fun getItemCount() = list.size
}