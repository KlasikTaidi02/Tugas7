package com.example.tugas4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.tugas4.Helper.showContentState
import com.example.tugas4.Helper.showEmptyState
import com.example.tugas4.databinding.ActivityMainBinding

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    
    private lateinit var  binding: ActivityMainBinding
    private lateinit var  dataBase: DatabaseReference
    private lateinit var  detailList: ArrayList<Detail>
    private lateinit var  detailadapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        detailList = arrayListOf<Detail>()
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.rvRecyclerView.setHasFixedSize(true)
        showLoading(true)
        getDetailBuku()

        detailadapter.onItemClick = {

            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("detail", it )
            startActivity(i)
        }

        binding.createbtnmain.setOnClickListener{

            val i = Intent(this,CreateActivity::class.java)
            startActivity(i)
        }

    }

    private fun getDetailBuku() {

        dataBase = FirebaseDatabase.getInstance().getReference("Detail")

         dataBase.addValueEventListener(object: ValueEventListener{

             override fun onDataChange(snapshot: DataSnapshot) {

                 if(snapshot.exists())
                 {
                     for(detailSnapshot in snapshot.children)
                     {
                         val detail = detailSnapshot.getValue(Detail::class.java)
                         if (detail != null) {
                            detailList.add(detail)
                         }

                     }

                     detailadapter = RecyclerAdapter(detailList)
                     binding.rvRecyclerView.adapter =  detailadapter
                     showLoading(false)
                 }
                 else
                 {
                     binding.msvListGithubUser.showEmptyState("Data tidak ditemukan")
                 }


             }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })
    }

    private fun showLoading(isShow : Boolean) {
        if (isShow) {

            binding.shimmerEffectGithubUsers.visibility = View.VISIBLE
            binding.shimmerEffectGithubUsers.startShimmer()
//            binding.msvListGithubUser.showLoadingState()
        } else {

            binding.shimmerEffectGithubUsers.stopShimmer()
            binding.shimmerEffectGithubUsers.visibility = View.GONE
            binding.msvListGithubUser.showContentState()
        }
    }

}


