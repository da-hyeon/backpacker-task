package com.hdh.backpacker_task.ui.main.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.data.model.data.Location
import kotlinx.android.synthetic.main.item_weather_info.view.*


class LocalWeatherListAdapter(
    private val localWeatherList: ArrayList<Location.ConsolidatedWeather>,
    private val parentView: MainFragmentView
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM = 1
    private val TYPE_HEADER = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val viewHolder: RecyclerView.ViewHolder
        if (viewType == TYPE_HEADER) {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_cartegory, parent, false)
            viewHolder = CategoryHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_weather_info, parent, false)
            viewHolder = LocalWeatherListHolder(view)
        }

//        view.setOnClickListener{
//            parentView.mActivity.click.run {
//
//            }
//        }

        return viewHolder
    }

    override fun getItemCount(): Int = localWeatherList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LocalWeatherListHolder -> {
                holder.bind(localWeatherList[position - 1], position - 1)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            else -> TYPE_ITEM
        }
    }


    private class CategoryHolder(view: View) : RecyclerView.ViewHolder(view)
    private inner class LocalWeatherListHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Location.ConsolidatedWeather, position: Int) {
            with(itemView) {
                text_location.text = item.title
                checkLastItem(position)
            }
        }

        private fun checkLastItem(position: Int) {
            itemView.view_last.visibility = if (position == localWeatherList.size - 1) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
