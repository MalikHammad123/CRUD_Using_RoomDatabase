package com.example.crud_roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.crud_roomdatabase.data.User
import com.example.crud_roomdatabase.data.UserDataBase
import com.example.crud_roomdatabase.databinding.ActivityAddBinding
import kotlinx.coroutines.launch


class Add_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var user:User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user=intent.getSerializableExtra("DATA")as User

        if(user==null){
            binding.btnaddupdateuser.text="ADD USER"
        }else{
            binding.btnaddupdateuser.text="Update"
            binding.etfname.setText(user?.fname.toString())
            binding.etlname.setText(user?.lname.toString())
        }
            binding.btnaddupdateuser.setOnClickListener {
            addUsers()
        }

    }
    private fun addUsers(){
        val fname = binding.etfname.text.toString()
        val lname = binding.etlname.text.toString()

        lifecycleScope.launch {
            if (user==null){
                val user= User(fname = fname, lname = lname)
                UserDataBase(this@Add_Activity).getUserDao().addUser(user)
                finish()

            }else {
                val user = User(fname = fname, lname = lname)
                user.id=user?.id ?:0
                UserDataBase(this@Add_Activity).getUserDao().addUser(user)
                finish()
            }
        }
    }
}