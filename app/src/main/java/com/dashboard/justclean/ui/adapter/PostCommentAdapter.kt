package com.dashboard.justclean.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dashboard.justclean.R
import com.dashboard.justclean.data.Model.PostComment
import kotlinx.android.synthetic.main.row_comment_item.view.*

class PostCommentAdapter(var list: List<PostComment>, var itemClick:RecycleItemClick):RecyclerView.Adapter<PostCommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_comment_item,parent,
                false)
        )

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.textViewName.text = list[position].name
        holder.itemView.textViewBody.text = list[position].body
        holder.itemView.textViewEmail.text = list[position].email
        /*holder.itemView.imageViewBanner.context?.let {
            Glide.with(it).load(it.getDrawable(R.drawable.ic_launcher_background)).into(holder.itemView.imageViewBanner)
        }*/
        holder.itemView.setOnClickListener {

            itemClick.onItemClick(it)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface RecycleItemClick {
        fun onItemClick(view: View)
    }
}