package com.ebayk.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.R
import com.ebayk.domain.entity.Attribute
import com.ebayk.domain.entity.Document
import com.ebayk.domain.entity.Response
import com.ebayk.presentation.adapter.AttributesAdapter
import com.ebayk.presentation.adapter.DocumentsAdapter
import com.ebayk.presentation.adapter.FeaturesAdapter
import com.ebayk.presentation.adapter.ImagesAdapter
import com.ebayk.presentation.util.gone
import com.ebayk.presentation.util.visible
import com.ebayk.presentation.viewmodel.*
import kotlinx.android.synthetic.main.fragment_data.*

/**
 * Created by S.Nur Uysal on 2020-03-02.
 */

class DataFragment : Fragment() {

    private var isLoading = false

    private val viewModel: DataViewModel by lazy {
        ViewModelProviders.of(this, context?.let { DataViewModelFactory() }).get(
            DataViewModel::class.java
        )
    }
    private val stateObserver = Observer<DataViewModelState> { state ->
        state?.let {
            when (state) {
                is LoadedState -> {
                    isLoading = false
                    cv_loading.gone()
                    cv_data.visible()
                    if(state.loadedAllItems){
                      fillPageData(state.response)
                    }
                }
                is LoadingState -> {
                    isLoading = true
                    cv_data.gone()
                    cv_loading.visible()
                }
                is ErrorState -> {
                    isLoading = false
                    cv_data.gone()
                    cv_loading.gone()
                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        viewModel.getData()

    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    private fun fillPageData(response: Response) {
        response.title.let {
            tv_title.text=it
        }
        response.address.let {
            tv_address.text="${it?.street} ${it?.city}"
        }
        response.id.let {
            tv_id.text="ID: $it"
        }
        response.price.let {
            tv_price.text="${it?.amount} ${it?.currency}"
        }
        response.postedDateTime.let {
            tv_date.text=it
        }
        response.visits.let {
            tv_view_count.text=it.toString()
        }

        response.description.let {
            tv_description_detail.text=it
        }

        response.pictures.let {
            if(it!!.isNotEmpty()){
                fillPicturesList(it)
            }
        }

        response.attributes.let {
            if(it!!.isNotEmpty()){
                fillAttributesList(it)
            }
        }

        response.features.let {
            if(it!!.isNotEmpty()){
                fillFeaturesList(it)
            }
        }

        response.documents.let {
            if(it!!.isNotEmpty()){
                fillDocumentsList(it)
            }
        }

    }
    private fun fillPicturesList(list: List<String>) {

        rv_images.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_images.adapter = ImagesAdapter(list)
        cv_images.visible()
    }

    private fun fillAttributesList(list: List<Attribute>) {

        rc_details.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rc_details.adapter = AttributesAdapter(list)
        cv_details.visible()
    }

    private fun fillFeaturesList(list: List<String>) {

        rc_features.layoutManager = GridLayoutManager(context, 2)
        rc_features.adapter = FeaturesAdapter(list)
        cv_features.visible()
    }

    private fun fillDocumentsList(list: List<Document>) {

        rc_documents.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rc_documents.adapter = DocumentsAdapter(list)
        cv_documents.visible()
    }
    
}