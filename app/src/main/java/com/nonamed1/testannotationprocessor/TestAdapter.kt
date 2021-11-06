package com.nonamed1.testannotationprocessor

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nonamed1.annotation.ViewHolderState

class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<ViewHolderState<*>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonViewHolderStateViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (item is PersonViewHolderState) {
            (holder as PersonViewHolderStateViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items : List<ViewHolderState<*>>) {
        this.items.clear()
        this.items.addAll(items)
    }

}