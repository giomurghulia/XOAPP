package com.example.xoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.xoapp.databinding.FragmentConfigurationBinding

class ConfigurationFragment : Fragment() {
    private lateinit var binding: FragmentConfigurationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.startButton.setOnClickListener {
            if (submitButtonNumber() == 0) {
                binding.configurationTitleText.text = "Input 9 or 16 or 25"
            } else {
                findNavController().navigate(
                    ConfigurationFragmentDirections.actionConfigurationFragmentToGameFragment(
                        submitButtonNumber()
                    )
                )
            }
        }

    }

    private fun submitButtonNumber(): Int {
        try {
            when (binding.buttonQuantityEditText.text.toString().toInt()) {
                9 -> return 3
                16 -> return 4
                25 -> return 5
            }
//            if (buttonNumber.isNotEmpty()) {
//                if (buttonNumber == "9") return 3
//                else if (buttonNumber == "16") return 4
//                else if (buttonNumber == "25") return 9
//            }
        } catch (e: Exception) {
            return 0
        }
        return 0
    }
}