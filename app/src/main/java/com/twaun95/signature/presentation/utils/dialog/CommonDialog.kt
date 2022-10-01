package com.twaun95.signature.presentation.utils.dialog

import androidx.fragment.app.FragmentManager
import com.twaun95.signature.R
import com.twaun95.signature.databinding.FragmentDialogCommonBinding
import com.twaun95.signature.presentation.extensions.setOnSingleClickListener
import com.twaun95.signature.presentation.extensions.visible
import com.twaun95.signature.presentation.model.DialogBody

class CommonDialog(
    private val buttonType: ButtonType,
    private val content: DialogBody,
    private var onDismissListener: (()->Unit)? = null,
    private var onCancelListener : (()->Unit)? = null,
    private var onConfirmListener : (()->Unit)? = null
) : BaseDialog<FragmentDialogCommonBinding>(R.layout.fragment_dialog_common){
    override fun initView() {
        super.initView()

        when(buttonType) {
            ButtonType.ONE -> {
                binding.layoutBottomTypeOne.visible()
            }
            ButtonType.TWO -> {
                binding.layoutBottomTypeTwo.visible()
            }
        }

        content.run {
            binding.tvTitle.text = title
            binding.tvMessage.text = message ?: ""
        }
    }

    override fun setEvent() {
        super.setEvent()

        binding.btnCancelTypeTwo.setOnSingleClickListener {
            onDismissListener?.invoke()
            onCancelListener?.invoke()
            dismiss()
        }
        binding.btnCompleteTypeTwo.setOnSingleClickListener {
            onDismissListener?.invoke()
            onConfirmListener?.invoke()
            dismiss()
        }
        binding.btnCompleteTypeOne.setOnSingleClickListener {
            onDismissListener?.invoke()
            onConfirmListener?.invoke()
            dismiss()
        }
    }

    companion object {
        private const val TAG = "CommonDialog"

        fun show(
            fragmentManager: FragmentManager,
            buttonType: ButtonType,
            content: DialogBody,
            onDismissListener: (()->Unit)? = null,
            onCancelListener : (()->Unit)? = null,
            onConfirmListener : (()->Unit)? = null
        ) {
            return CommonDialog(buttonType, content, onDismissListener, onCancelListener, onConfirmListener)
                .show(fragmentManager, TAG)
        }
    }
}