package com.twaun95.signature.presentation.ui.main

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twaun95.signature.R
import com.twaun95.signature.common.Logger
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
        binding.button4.setOnSingleClickListener {
            binding.viewDrawing.reset()
        }
        binding.button6.setOnSingleClickListener {
            WidthPickerDialog.show(supportFragmentManager, binding.viewDrawing.getPenWidth(), binding.viewDrawing.getPenColor()) {
                Logger.d(it)
                binding.viewDrawing.changeStrokeWidth(it)
            }
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
            viewModel.toggleEraser()
            if (viewModel.isErasingMode.value!!) {
                EraserDialog(
                    BaseDialog.ButtonType.TWO,
                    DialogBody(getString(R.string.dialog_title_background_color), getString(R.string.dialog_message_background_color)),
                    {},
                    { binding.viewDrawing.erasingMode(true) }
                ).show(supportFragmentManager, null)
            } else {
                binding.viewDrawing.erasingMode(false)
            }

        }
        binding.button3.setOnSingleClickListener {
            binding.viewDrawing.goBack()
        }

        binding.buttonSetting.setOnSingleClickListener {
            //권한 체크
            if(!ImageSaveHandler.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                !ImageSaveHandler.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                return@setOnSingleClickListener
            }
            //그림 저장
            if(!ImageSaveHandler.imageExternalSave(this, binding.viewDrawing.getBitmap(), getString(R.string.app_name))){
                Toast.makeText(this, "그림 저장을 실패하였습니다", Toast.LENGTH_SHORT).show()
                return@setOnSingleClickListener
            }
        }
    }
}