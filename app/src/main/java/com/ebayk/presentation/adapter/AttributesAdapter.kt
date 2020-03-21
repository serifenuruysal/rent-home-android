package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.R
import com.ebayk.domain.entity.Attribute
import kotlinx.android.synthetic.main.item_attribute.view.*

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
class AttributesAdapter(
    private val list: List<Attribute>
) :
    RecyclerView.Adapter<AttributesAdapter.ModelViewHolder>() {

    class ModelViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attribute, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val attribute = list[position]
        holder.view.tv_label.text = attribute.label
        holder.view.tv_value.text = attribute.value
        holder.view.tv_unit.text = attribute.unit

    }

    override fun getItemCount() = list.size
}