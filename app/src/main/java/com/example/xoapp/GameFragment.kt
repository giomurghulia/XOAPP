package com.example.xoapp

import android.R.attr.y
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xoapp.databinding.FargmentGameBinding


class GameFragment : Fragment() {
    private lateinit var binding: FargmentGameBinding
    private val myAdapter = MyAdapter()

    private lateinit var itemList: MutableList<Item>
    private var clickNum = 0

    private var buttonRow = 0
    private var buttonNumber = 0

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
        buttonRow = GameFragmentArgs.fromBundle(requireArguments()).buttonNumber
        buttonNumber = buttonRow * buttonRow

        itemList = MutableList(buttonNumber) { Item(it, 0) }

        binding.mainRecycleView.layoutManager =
            GridLayoutManager(context, buttonRow)
        binding.mainRecycleView.adapter = myAdapter

        updateAdapter(itemList)

        myAdapter.setCallBack(object : MyAdapter.CallBack {
            override fun onItemClick(itemPosition: Int) {
                if (clickNum < buttonNumber) {
                    when (itemList[itemPosition].player) {
                        1 -> binding.resultText.text = "chose other button"
                        2 -> binding.resultText.text = "chose other button"
                        0 -> {
                            var player = ""
                            val item = itemList[itemPosition]

                            if (clickNum % 2 == 0) {
                                itemList[itemPosition] = item.copy(player = 1)
                                player = "X"
                            } else {
                                itemList[itemPosition] = item.copy(player = 2)
                                player = "O"
                            }
                            clickNum++
                            val x = itemPosition / buttonRow
                            val y = itemPosition % buttonRow
                            val result = checkWinner(x, y, itemList[itemPosition].player, clickNum)
                            when (result) {
                                1 -> {
                                    binding.resultText.text = "WIN PLAYER -> $player"
                                    clickNum = buttonNumber
                                }
                                2 -> {
                                    binding.resultText.text = "WIN PLAYER -> $player"
                                    clickNum = buttonNumber
                                }
                                3 -> {
                                    binding.resultText.text = "WIN PLAYER -> $player"
                                    clickNum = buttonNumber
                                }
                                4 -> {
                                    binding.resultText.text = "WIN PLAYER -> $player"
                                    clickNum = buttonNumber
                                }
                                5 -> binding.resultText.text = "DRAW & FINISH GAME"
                                else -> binding.resultText.text = "GAME ON PROGRESS"

                            }
                        }
                    }
                }
                updateAdapter(itemList)
            }
        }
        )
    }

    private fun updateAdapter(list: List<Item>) {
        myAdapter.submitList(list.toList())
    }

    private fun checkWinner(x: Int, y: Int, item: Int, clickNum: Int): Int {
        val itemArray = itemList.chunked(buttonRow).toTypedArray()
        for (i in 0 until buttonRow) {
            if (itemArray[x][i].player != item) break
            if (i == buttonRow - 1) {
                return 1
            }
        }
        for (i in 0 until buttonRow) {
            if (itemArray[i][y].player != item) break
            if (i == buttonRow - 1) {
                return 2
            }
        }
        if (x == y) {
            for (i in 0 until buttonRow) {
                if (itemArray[i][i].player != item) break
                if (i == buttonRow - 1) {
                    return 3
                }
            }
        }
        for (i in 0 until buttonRow) {
            if (itemArray[i][(buttonRow - 1) - i].player != item) break
            if (i == buttonRow - 1) {
                return 4
            }
        }
        if (clickNum == buttonRow * buttonRow) {
            return 5
        }
        return 0
    }

}
