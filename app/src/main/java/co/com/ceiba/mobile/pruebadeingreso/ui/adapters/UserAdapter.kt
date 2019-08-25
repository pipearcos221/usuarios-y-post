package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.util.inflate

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var users: List<User> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                0 -> UserHolder(parent.inflate(R.layout.user_list_item))
                else -> EmptyViewHolder(parent.inflate(R.layout.empty_view))
            }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (holder) {
                is UserHolder -> holder.bind(users[position])
                else -> Unit
            }


    class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: UserListItemBinding = DataBindingUtil.bind(itemView)!!
        fun bind(item: User) = binding.run {
            user = item
        }
    }

    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}