package com.ebayk.presentation.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.R
import com.ebayk.domain.entity.Document
import kotlinx.android.synthetic.main.item_document.view.*

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */
class DocumentsAdapter(
    private val listDoc: List<Document>
) :
    RecyclerView.Adapter<DocumentsAdapter.ModelViewHolder>() {

    class ModelViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_document, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val doc = listDoc[position]
        holder.view.tv_title.text = doc.title
        holder.view.setOnClickListener {
            it.context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(doc.link))
            )
        }

    }

    override fun getItemCount() = listDoc.size
}