package com.example.nycschools.ui.main.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Horizontal Divider for our RecyclerView Lists
 * @param context to be used
 * before the first item in the list, false otherwise.
 */
class SchoolsListItemDividerDecoration(
    context: Context,
) : RecyclerView.ItemDecoration() {
    private val bounds = Rect()
    private val paint = Paint()

    init {
        paint.strokeWidth =
            context.resources.getDimension(com.google.android.material.R.dimen.material_divider_thickness)
        paint.style = Paint.Style.STROKE
        paint.color =
            ContextCompat.getColor(context, androidx.appcompat.R.color.material_blue_grey_800)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            parent.layoutManager!!.getDecoratedBoundsWithMargins(parent.getChildAt(i), bounds)
            canvas.drawLine(
                0f,
                bounds.bottom.toFloat(),
                canvas.width.toFloat(),
                bounds.bottom.toFloat(),
                paint
            )
        }
    }
}