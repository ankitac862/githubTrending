package com.anku.githubtrending.ui.main.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anku.githubtrending.databinding.ItemRepoListBinding
import com.anku.githubtrending.model.Repo

abstract class BaseViewHolder(view: ItemRepoListBinding) : RecyclerView.ViewHolder(view.root) {
    abstract fun bind(repo: Repo, context: Context)
}