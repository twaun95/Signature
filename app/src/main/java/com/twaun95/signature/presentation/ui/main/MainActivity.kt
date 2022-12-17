package com.twaun95.signature.presentation.ui.main

import android.Manifest
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.signature.R
import com.twaun95.signature.databinding.ActivityMainBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener
import com.twaun95.signature.presentation.model.ToggledState
import com.twaun95.signature.presentation.handler.ImageSaveHandler
import com.twaun95.signature.presentation.utils.dialog.*
import com.twaun95.signature.presentation.utils.toast.CustomToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


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
            viewModel.toggle(ToggledState.BACKGROUND_COLOR)
            CustomToast.show(this, getString(R.string.dialog_message_background_color), Toast.LENGTH_LONG)
            ColorPickerDialog.show(this,
                getString(R.string.dialog_title_background_color),
                { viewModel.toggle(ToggledState.IDLE) },
                { color ->  binding.viewDrawing.updateBackgroundColor(color) }
            )
        }

        binding.buttonUpdatePenColor.setOnSingleClickListener {
            viewModel.toggle(ToggledState.PEN_COLOR)
            ColorPickerDialog.show(this,
                getString(R.string.dialog_title_pen_color),
                { viewModel.toggle(ToggledState.IDLE) },
                { color -> binding.viewDrawing.updatePenColor(color) }
            )
        }

        binding.buttonUpdateWidth.setOnSingleClickListener {
            WidthPickerDialog.show(supportFragmentManager,
                binding.viewDrawing.getPenWidth(),
                binding.viewDrawing.getPenColor(),
                {},
                { binding.viewDrawing.updateStrokeWidth(it) }
            )
        }

        binding.buttonEraser.setOnSingleClickListener {
            if (viewModel.toggledState.value != ToggledState.ERASER) {
                viewModel.toggle(ToggledState.ERASER)
                EraserDialog.show(supportFragmentManager,
                    binding.viewDrawing.getPenWidth(),
                    { },
                    { binding.viewDrawing.onErasingMode(true, it) }
                )
            } else {
                viewModel.toggle(ToggledState.IDLE)
                binding.viewDrawing.onErasingMode(false, binding.viewDrawing.getEraserWidth())
            }
        }

        binding.buttonReset.setOnSingleClickListener {
            viewModel.toggle(ToggledState.IDLE)
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

// Firebase Crashlytics 테스트
//    private fun setFirebase() {
//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))
//    }
}