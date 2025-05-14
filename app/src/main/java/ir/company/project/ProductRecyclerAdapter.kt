package ir.company.project

import android.annotation.SuppressLint
import android.app.Activity
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import ir.company.project.databinding.ListItemBinding

class ProductRecyclerAdapter(
    private val context: Activity,
    private val products: ArrayList<DataProduct>
): RecyclerView.Adapter<ProductRecyclerAdapter.productViewHolder>(), Filterable {

    private val productsFull = ArrayList<DataProduct>()

    init {
        productsFull.addAll(products)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
        val binding = ListItemBinding.inflate(context.layoutInflater, parent, false)
        return productViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size


    fun addItem(name: String) {
        products.add(DataProduct(products.size + 1, name, "250/000", R.drawable.img))
        notifyItemInserted(products.size - 1)
//        product[1].name = "Test"
//        notifyItemChanged(1)

    }


    override fun onBindViewHolder(holder: productViewHolder, position: Int) {
        holder.setDataProduct(products[position])
    }


    fun removeItem(position: Int){
        products.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, products.size)

    }



    inner class productViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setDataProduct(product: DataProduct) {
            binding.txtName.text = product.name
            binding.txtPrice.text = product.price
            binding.imageView.setImageResource(product.imgAddress)

            binding.btnShow.setOnClickListener {
                removeItem(layoutPosition)
            }

//            binding.btnShow.setOnClickListener {
//                val intent = Intent(context, MainActivity2::class.java)
//                intent.putExtra("name", product.name)
//                intent.putExtra("price", product.price)
//                intent.putExtra("img", product.imgAddress)
//                context.startActivity(intent)
//            }

        }

    }

    override fun getFilter(): Filter =
        object : Filter() {
            override fun performFiltering(text: CharSequence?): FilterResults {
                val filterList = ArrayList<DataProduct>()
                if (text == null || text.isEmpty())
                    filterList.addAll(productsFull)
                else {
                    val filterPattern = text.toString().trim()
                    for (item in productsFull) {
                        if (item.name.contains(filterPattern))
                            filterList.add(item)
                    }
                }


                val results = FilterResults()
                results.values = filterList
                return results


            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, filter: FilterResults?) {
                products.clear()
                products.addAll(filter?.values as ArrayList<DataProduct>)
                notifyDataSetChanged()
            }

        }


}





