package ir.company.project

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import ir.company.project.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter:ProductRecyclerAdapter


    val data = arrayListOf(
        DataProduct(1, "کیف چرمی", "240/000", R.drawable.img),
        DataProduct(2, "عکس پنگوئن", "500/000", R.drawable.img2),
        DataProduct(3, "عکس جگوار", "100/000", R.drawable.img3),
        DataProduct(4, "ارواح", "90/000", R.drawable.img4),
        DataProduct(5, "چلمبه", "12/000/000", R.drawable.img5),
        DataProduct(6, "اسکایریم", "8/000/000", R.drawable.img),
        DataProduct(7, "تابلو", "3/000/000", R.drawable.img2),
        DataProduct(8, "تابلو گران", "1/000/000", R.drawable.img3),
        DataProduct(9, "تابلو بزرگ", "18/000/000", R.drawable.img4),
        DataProduct(10, "تابلوارزان", "46/000/000", R.drawable.img5)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        title = "recycle view"

        adapter = ProductRecyclerAdapter(this, data)
        binding.recyclerView.layoutManager = GridLayoutManager(
            this,
            1,
            RecyclerView.VERTICAL,
            false
        )


        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            val name = binding.edtText.text.toString()
            if (name.isNotEmpty())
                adapter.addItem(name)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(binding.recyclerView)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu?.findItem(R.id.acrion_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                adapter.filter.filter(text)
                return false
            }

        })
        return true
    }


}


