package com.twaun95.signature.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.signature.R
import com.twaun95.signature.databinding.ActivityMainBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener
import com.twaun95.signature.presentation.model.DialogBody
import com.twaun95.signature.presentation.utils.*
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
            PenWidthDialog(
                BaseDialog.ButtonType.TWO,
                DialogBody(getString(R.string.dialog_title_background_color), getString(R.string.dialog_message_background_color)),
                {},
                {binding.viewDrawing.changeStrokeWidth(20f) }
            ).show(supportFragmentManager, null)
        }
        binding.button7.setOnSingleClickListener {
            ColorPickerDialog.show(this) { color -> binding.viewDrawing.changePenColor(color) }
        }
        binding.button8.setOnSingleClickListener {
            CommonDialog(
                BaseDialog.ButtonType.TWO,
                DialogBody(getString(R.string.dialog_title_background_color), getString(R.string.dialog_message_background_color)),
                {},
                { ColorPickerDialog.show(this) { color ->  binding.viewDrawing.changeBackgroundColor(color) } }
            ).show(supportFragmentManager, null)
        }
        binding.button5.setOnSingleClickListener {
            EraserDialog(
                BaseDialog.ButtonType.TWO,
                DialogBody(getString(R.string.dialog_title_background_color), getString(R.string.dialog_message_background_color)),
                {},
                { binding.viewDrawing.erasingMode() }
            ).show(supportFragmentManager, null)
        }
        binding.button3.setOnSingleClickListener {
            binding.viewDrawing.goBack()
        }
    }
}