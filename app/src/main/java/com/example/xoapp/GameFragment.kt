package com.example.xoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xoapp.databinding.FargmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FargmentGameBinding

    private val myAdapter = MyAdapter()
    private var allItem: List<Item> = ITEMS_3

    private var clickNum = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FargmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val buttonRow = GameFragmentArgs.fromBundle(requireArguments()).buttonNumber

        binding.mainRecycleView.layoutManager =
            GridLayoutManager(context, buttonRow)
        binding.mainRecycleView.adapter = myAdapter

        updateAdapter(allItem)

        myAdapter.setCallBack(object : MyAdapter.CallBack {
            override fun onItemClick(item: Item) {
                if (clickNum % 2 == 0) {
                    
                    updateAdapter(allItem.toList())
                }
            }
        })
    }

    private fun updateAdapter(list: List<Item>) {
        myAdapter.submitList(list)
    }

}

val ITEMS_3 = listOf(
    Item(1, 0, 0),
    Item(2, 0, 0),
    Item(3, 0, 0),
    Item(4, 0, 0),
    Item(5, 0, 0),
    Item(6, 0, 0),
    Item(7, 0, 0),
    Item(8, 0, 0),
    Item(9, 0, 0),
)
