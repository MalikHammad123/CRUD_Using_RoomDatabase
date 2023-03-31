package com.example.crud_roomdatabase.data

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_roomdatabase.R

class UserAdapter:RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var list= mutableListOf<User>()
    private var actionUpdate:((User)->Unit)?=null
    private var actionDelete:((User)->Unit)?=null



    fun setOnActionEditListner(callback: (User)->Unit){
        this.actionUpdate=callback
    }
    fun setOnActionDeleteListner(callback: (User)->Unit){
        this.actionDelete=callback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_adapter,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount():Int{
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.fname.text=user.fname
        holder.lname.text=user.lname
        holder.actionupdate.setOnClickListener { actionUpdate?.invoke(user) }
        holder.actiondelete.setOnClickListener { actionDelete?.invoke(user) }

    }
    fun setData(data:List<User>){
        list.apply{
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
    class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val fname=itemView.findViewById<TextView>(R.id.tvfname)
        val lname=itemView.findViewById<TextView>(R.id.tvlname)
        val actionupdate=itemView.findViewById<ImageView>(R.id.imgupdate)
        val actiondelete=itemView.findViewById<ImageView>(R.id.imgdelete)


    }
}