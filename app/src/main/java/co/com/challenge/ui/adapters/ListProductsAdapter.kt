package co.com.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.challenge.R
import co.com.challenge.databinding.HolderProductBinding
import co.com.challenge.domain.models.ProductModel
import co.com.challenge.presentation.viewModels.ProductListViewModel
import com.bumptech.glide.Glide
import okhttp3.internal.notifyAll

/**
 * Created by Elkin Fracica on 1/11/21.
 */
class ListProductsAdapter() : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>()  {

    private var listUsers = listOf<ProductModel>()

    var onClickItem: (ProductModel) -> Unit = {}

    override fun getItemCount(): Int = listUsers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.holder_product, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.product = listUsers[position]
        Glide.with(holder.itemView)
            .load(listUsers[position].thumbnail)
            .into(holder.binding.imgHoldProd)

        holder.itemView.setOnClickListener {
            onClickItem(listUsers[position])
        }
    }

    fun updateData(data: List<ProductModel>) {
        listUsers = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : HolderProductBinding = HolderProductBinding.bind(itemView)
    }
}