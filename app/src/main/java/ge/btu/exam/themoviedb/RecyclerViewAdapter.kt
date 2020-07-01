package ge.btu.exam.themoviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.btu.exam.themoviedb.models.ItemModel
import kotlinx.android.synthetic.main.movie_item.view.*
import java.util.*

class RecyclerViewAdapter(private val items: ArrayList<ItemModel>, private val activity: MoviesActivity) :

    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var model: ItemModel
        fun onBind() {
            model = items[adapterPosition]
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${model.image}").into(itemView.imageView)
            itemView.titleTextView.text = itemView.titleTextView.text.toString().replace("{title}", model.title)
            itemView.overviewTextView.text = itemView.overviewTextView.text.toString().replace("{overview}", model.description)
            itemView.releaseDateTextView.text = itemView.releaseDateTextView.text.toString().replace("{releaseDate}", model.date)

            itemView.setOnLongClickListener {
                activity.items.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
    )


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}