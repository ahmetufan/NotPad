package com.ahmet.notpad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ahmet.notpad.R
import com.ahmet.notpad.data.User
import com.ahmet.notpad.view.ListFragmentDirections

class RecyclerviewAdapter() : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textname = view.findViewById<TextView>(R.id.txtName)
        val textage = view.findViewById<TextView>(R.id.txtAge)
        fun bind(user: User) {
            textname.text = user.name
            textage.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.bind(currentUser)

        holder.itemView.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}