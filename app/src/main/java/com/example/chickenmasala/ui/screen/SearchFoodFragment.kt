package com.example.chickenmasala.ui.screen


import android.annotation.SuppressLint
import android.view.*
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentSearchFoodBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class SearchFoodFragment : BaseFragment<FragmentSearchFoodBinding>() {


    override val LOG_TAG: String = "SearchFoodFragment"
    lateinit var dialog : BottomSheetDialog
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchFoodBinding =
        FragmentSearchFoodBinding::inflate

    override fun setup() {
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun addCallBacks() {
        dialog = BottomSheetDialog(binding.root.context)
        binding.editText.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                val drawableRight = 2
                if (motionEvent.rawX >= binding.editText.right - binding.editText.compoundDrawables[drawableRight].bounds.width()) {
                    createDialog()
                    dialog.show()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false

        }
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    private fun createDialog() {
        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null, false)
        dialog.dismiss()
        dialog.setContentView(view)


    }

}