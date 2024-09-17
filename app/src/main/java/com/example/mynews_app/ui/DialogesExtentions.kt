package com.example.mynews_app.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showMessage(
    message:String,
    posActionName :String? = null,
    posAction : DialogInterface.OnClickListener?=null,
    negActionName :String? = null,
    neAction : DialogInterface.OnClickListener?=null
): AlertDialog {
    val dialougeBuilder = AlertDialog.Builder(context)
    dialougeBuilder.setMessage(message)
    if (posActionName!=null){
        dialougeBuilder.setPositiveButton(posActionName,posAction)
    }
    if(negActionName!=null){
        dialougeBuilder.setNegativeButton(negActionName,neAction)
    }
    return dialougeBuilder.show()


}

fun Activity.showMessage(
    message:String ,
    posActionName :String? = null ,
    posAction : DialogInterface.OnClickListener?=null,
    negActionName :String? = null ,
    neAction : DialogInterface.OnClickListener?=null
):AlertDialog{
    val dialougeBuilder =AlertDialog.Builder(this)
    dialougeBuilder.setMessage(message)
    if (posActionName!=null){
        dialougeBuilder.setPositiveButton(posActionName,posAction)
    }
    if(negActionName!=null){
        dialougeBuilder.setNegativeButton(negActionName,neAction)
    }
    return dialougeBuilder.show()


}