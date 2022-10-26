package com.anku.githubtrending.ui.main.adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat

import androidx.recyclerview.widget.RecyclerView
import com.anku.githubtrending.R
import com.anku.githubtrending.databinding.ItemRepoListBinding
import com.anku.githubtrending.model.Repo
import com.bumptech.glide.Glide


class ReposAdapter(private val context: Context) :
    RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
    var repositories = mutableListOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repositories[position]
        holder.bind(repo, context)
        holder.binding.root.setOnClickListener {
            repositories.forEach { it.checked = false }
            repositories[position].checked = true
            notifyDataSetChanged()
            var bundle = Bundle()
            bundle.putParcelable("repo" , repo)

        }

    }
    override fun getItemCount() = repositories.size


    class ViewHolder(val binding: ItemRepoListBinding) :
        BaseViewHolder(binding) {
        override fun bind(repo: Repo, context: Context) {

            binding.apply {

                Glide.with(itemView)
                    .load(repo.owner?.avatar_url)
                    .centerCrop()
                    .error(android.R.drawable.stat_notify_error)
                    .into(avatar)

                val str = SpannableString(repo.name)
                str.setSpan(
                    StyleSpan(Typeface.BOLD),
                    repo.name.length,
                    str.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                name.text = str
                description.text = repo.description
                language.text = repo.language
                star.text = repo.stars.toString()

                if(repo.checked)
                 selectedImage.setImageResource(R.drawable.ic_baseline_check_circle)
                else selectedImage.setImageResource(0)

                ViewCompat.setTransitionName(this.avatar, "avatar_${repo.name}")
            }

        }
    }

}