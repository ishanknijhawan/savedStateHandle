package com.example.savedstatehandle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class FragmentA : Fragment(R.layout.fragment_a) {

    private lateinit var addButton: MaterialButton
    private lateinit var subtractButton: MaterialButton
    private lateinit var resultText: MaterialTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton = view.findViewById(R.id.btn_add)
        subtractButton = view.findViewById(R.id.btn_subtract)
        resultText = view.findViewById(R.id.tv_result)
        val bundle = bundleOf()

        findNavController().currentBackStackEntry
            ?.savedStateHandle?.let { handle ->
                handle.getLiveData<Int>(MainActivity.RESULT).observe(viewLifecycleOwner, {
                    resultText.text = it.toString()
                })
            }

        addButton.setOnClickListener {
            bundle.putString(MainActivity.TYPE, Calculation.ADD.value)
            findNavController().navigate(R.id.calculations, bundle)
        }
        subtractButton.setOnClickListener {
            bundle.putString(MainActivity.TYPE, Calculation.SUBTRACT.value)
            findNavController().navigate(R.id.calculations, bundle)
        }
    }
}