package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.model.Post
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.util.inflate
import java.util.concurrent.RecursiveAction

class PostAdater : RecyclerView.Adapter<PostAdater.PostHolder>() {

    var posts: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder =
            PostHolder(parent.inflate(R.layout.post_list_item))

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) =
            holder.bind(posts[position])


    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: PostListItemBinding = DataBindingUtil.bind(itemView)!!
        fun bind(item:Post) = binding.run {
            post = item
        }
    }
}