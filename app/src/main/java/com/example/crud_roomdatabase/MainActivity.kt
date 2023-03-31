package com.example.crud_roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_roomdatabase.data.User
import com.example.crud_roomdatabase.data.UserAdapter
import com.example.crud_roomdatabase.data.UserDataBase

import com.example.crud_roomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var madapter:UserAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, Add_Activity::class.java)
            intent.putExtra("DATA", User())
            startActivity(intent)
        }


    }
    private fun setAdapter(list: List<User>){
        madapter?.setData(list)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val userList = UserDataBase(this@MainActivity).getUserDao().getAllUser()

            madapter=UserAdapter()
            binding.recyclerview.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter=madapter

                    setAdapter(userList)
                //adapter = UserAdapter().apply {
                    madapter?.setData(userList)
                    madapter?.setOnActionEditListner {
                        val intent = Intent(this@MainActivity, Add_Activity::class.java)
                        intent.putExtra("DATA", it)
                        startActivity(intent)
                    }

                    madapter?.setOnActionDeleteListner {
                        val builder = AlertDialog.Builder(this@MainActivity)
                        builder.setMessage("Are you sure you want to delete this item")
                        builder.setPositiveButton("Yes") { p0, p1 ->
                            lifecycleScope.launch {
                                UserDataBase(this@MainActivity).getUserDao().deleteUsers(it)
                                val list= UserDataBase(this@MainActivity).getUserDao().getAllUser()
                                setAdapter(list)
                            }
                        }
                            builder.setNegativeButton("No") { p0, p1 ->
                                p0.dismiss()
                            }
                            val dialog = builder.create()
                            dialog.show()

                    }
                //}
            }
        }


    }


}