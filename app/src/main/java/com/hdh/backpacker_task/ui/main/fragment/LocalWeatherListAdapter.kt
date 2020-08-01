package com.hdh.backpacker_task.ui.main.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hdh.backpacker_task.R
import com.hdh.backpacker_task.data.model.data.Location
import com.hdh.backpacker_task.utils.ImageUtil
import kotlinx.android.synthetic.main.item_weather_info.view.*


class LocalWeatherListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Location> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val viewHolder: RecyclerView.ViewHolder

        when (viewType) {
            TYPE_HEADER -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_cartegory, parent, false)
                viewHolder = CategoryHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_info, parent, false)
                viewHolder = LocalWeatherListHolder(view)
            }
        }

        return viewHolder
    }

    override fun getItemCount(): Int = items.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LocalWeatherListHolder -> {
                holder.bind(items[position - 1], position - 1)
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
        fun bind(item: Location, position: Int) {
            with(itemView) {

                text_location.text = item.title

                with(item.consolidated_weather[0]){
                    ImageUtil.getImage(image_today_weather_state_abbr, getWeatherStateAbbr())
                    text_today_weather_state_name.text = weather_state_name
                    text_today_temp.text = context.getString(R.string.the_temp , getTheTemp())
                    text_today_humidity.text = context.getString(R.string.humidity , humidity)
                }

                with(item.consolidated_weather[1]){
                    ImageUtil.getImage(image_tomorrow_weather_state_abbr, getWeatherStateAbbr())
                    text_tomorrow_weather_state_name.text = weather_state_name
                    text_tomorrow_today_temp.text = context.getString(R.string.the_temp , getTheTemp())
                    text_tomorrow_today_humidity.text = context.getString(R.string.humidity , humidity)
                }

                checkLastItem(position)
            }
        }

        private fun checkLastItem(position: Int) {
            itemView.view_last.visibility = if (position == items.size - 1) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
