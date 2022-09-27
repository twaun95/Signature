package com.twaun95.signature.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.signature.R
import com.twaun95.signature.databinding.ActivityMainBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener
import com.twaun95.signature.presentation.utils.ColorPickerDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel

        setEvent()
    }

    private fun setEvent() {
        binding.button4.setOnSingleClickListener {
            binding.viewDrawing.reset()
        }
        binding.button6.setOnSingleClickListener {
            binding.viewDrawing.changeStrokeWidth(20f)
        }
        binding.button7.setOnSingleClickListener {
            ColorPickerDialog.show(this) { color -> binding.viewDrawing.changePenColor(color) }
        }
        binding.button8.setOnSingleClickListener {
            binding.viewDrawing.changeBackgroundColor(R.color.purple_200)
        }
    }
}