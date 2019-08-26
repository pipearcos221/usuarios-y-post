package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.model.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.util.inflate
import io.reactivex.subjects.PublishSubject


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>(){

    val clickUserSubject: PublishSubject<User> = PublishSubject.create()

    var users: List<User> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder =
            UserHolder(parent.inflate(R.layout.user_list_item))


    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) =
            holder.bind(users[position], clickUserSubject)

    class UserHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: UserListItemBinding = DataBindingUtil.bind(itemView)!!
        fun bind(item: User, clickSubjet: PublishSubject<User>) = binding.run {
            user = item
            onCLick = clickSubjet
        }
    }
}