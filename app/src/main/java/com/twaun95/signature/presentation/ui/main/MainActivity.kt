package com.twaun95.signature.presentation.ui.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.signature.R
import com.twaun95.signature.databinding.ActivityMainBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener
import com.twaun95.signature.presentation.model.DialogBody
import com.twaun95.signature.presentation.utils.ImageSaveHandler
import com.twaun95.signature.presentation.utils.dialog.*
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
        binding.buttonUpdateBackground.setOnSingleClickListener {
            CommonDialog.show(
                supportFragmentManager,
                BaseDialog.ButtonType.TWO,
                DialogBody(getString(R.string.dialog_title_background_color), getString(R.string.dialog_message_background_color)),
                {},
                { ColorPickerDialog.show(this) { color ->  binding.viewDrawing.updateBackgroundColor(color) } }
            )
        }

        binding.buttonUpdatePenColor.setOnSingleClickListener {
            ColorPickerDialog.show(this) { color -> binding.viewDrawing.updatePenColor(color) }
        }

        binding.buttonUpdateWidth.setOnSingleClickListener {
            WidthPickerDialog.show(supportFragmentManager, binding.viewDrawing.getPenWidth(), binding.viewDrawing.getPenColor()) { binding.viewDrawing.updateStrokeWidth(it) }
        }

        binding.buttonEraser.setOnSingleClickListener {
            viewModel.toggleEraser()
            if (viewModel.isErasingMode.value!!) {
                EraserDialog.show(supportFragmentManager, binding.viewDrawing.getPenWidth()) { binding.viewDrawing.onErasingMode(true, it) }
            } else {
                binding.viewDrawing.onErasingMode(false, binding.viewDrawing.getEraserWidth())
            }
        }

        binding.buttonReset.setOnSingleClickListener {
            binding.viewDrawing.reset()
        }

        binding.buttonSetting.setOnSingleClickListener {
            //권한 체크
            if(!ImageSaveHandler.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                return@setOnSingleClickListener
            }

            //그림 저장
            ImageSaveHandler.saveImageGallery(this, binding.viewDrawing.getBitmap())
        }
    }
}