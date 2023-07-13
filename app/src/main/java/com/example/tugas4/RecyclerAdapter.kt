package com.example.tugas4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RecyclerAdapter (private val detailList: ArrayList<Detail>)  :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    var onItemClick : ((Detail) -> Unit)? = null




    fun addedListOgBook(list: List<Detail>) {
        this.detailList.clear()
        this.detailList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.titlelabel)
        val itemDetail: TextView = itemView.findViewById(R.id.deskripsilabel)
        val itemPicture: ImageView = itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detail = detailList[position]
        holder.itemTitle.text = detail.judul
        holder.itemDetail.text = "Detail"
        Picasso.get().load(detail.url).into(holder.itemPicture)
        holder.itemView.setOnClickListener{

            onItemClick?.invoke(detail)
        }

    }




}

