package com.app.user.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.networking.model.entity.Stadium
import com.app.networking.utils.ConstUtil
import com.app.networking.utils.ConstUtil.GETSTADIUMIMAGE
import com.app.user.R
import com.app.user.utils.ConstUtil.MAD
import com.app.user.utils.ConstUtil.PLAYERS
import com.app.user.utils.FunUtil.reformatDate
import com.app.user.utils.OnItemSelectedInterface
import com.app.user.utils.OnItemSelectedInterfaceWithArguments
import com.bumptech.glide.Glide

class ListStadiumAdapter(
    val context: Context,
    private val onItemSelected: OnItemSelectedInterfaceWithArguments
) :
    RecyclerView.Adapter<ListStadiumAdapter.ItemViewHolder>() {

    private var myList: ArrayList<Stadium> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Stadium>) {
        myList = data as ArrayList<Stadium>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.stadium_item_stadiums_fragment, parent, false)
        return ItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        myList[position].let {
            holder.id.text = it.id.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                holder.date.text =
                    reformatDate(it.disponibility_from.toString()) + " | " + reformatDate(
                        it.disponibility_to.toString()
                    )
            } else {
                holder.date.text = ""
            }
            holder.title.text = it.name
            holder.location.text = it.location
            try {
                holder.numberOfPlayer.text = if (it.seances?.first()?.nbreParticipant.toString()
                        .isEmpty()
                ) "" else "${it.seances?.first()?.nbreParticipant}${PLAYERS}"
            }catch (e: Exception){
                holder.numberOfPlayer.text = ""
            }

            holder.price.text = if (it.price.toString().isEmpty()) "" else "${it.price}${MAD}"

            holder.description.text = it.description
            val stadiumImage = GETSTADIUMIMAGE + it.imgFileName
            Glide.with(context)
                .load(stadiumImage)
                .error(R.drawable.event_stadium_default)
                .centerCrop()
                .into(holder.image)
        }
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        var id: TextView = view.findViewById(R.id.id)
        var date: TextView = view.findViewById(
            R.id
                .date
        )
        var title: TextView = view.findViewById(
            R.id
                .title
        )
        var location: TextView = view.findViewById(
            R.id
                .location
        )
        var numberOfPlayer: TextView = view.findViewById(
            R.id
                .numberOfPlayer
        )
        var price: TextView = view.findViewById(
            R.id
                .price
        )
        var description: TextView = view.findViewById(
            R.id
                .description
        )
        var image: ImageView = view.findViewById(R.id.image)
        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                myList[position].let {
                    onItemSelected.onItemClick(it.id.toString(), it.price.toString(), it.name)
                }
            }
        }
    }


    override fun getItemCount() = myList.size
}