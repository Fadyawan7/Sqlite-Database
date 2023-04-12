package com.example.sqlite
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class MyAdapter(val context: Context, private val mList:List<ViewModal>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cutom_list,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Items = mList[position]
        holder.NameText.text =Items.Text

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder (ItemView: View) :RecyclerView.ViewHolder(ItemView){
        val NameText  = ItemView.findViewById<TextView>(R.id.textview)
    }


}