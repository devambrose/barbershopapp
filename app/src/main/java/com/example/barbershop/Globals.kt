package com.example.barbershop

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cn.pedant.SweetAlert.SweetAlertDialog
import java.util.*


class Globals : Application(){
    companion object {
        val LOGGIN_STATUS: String="APP_LOGIN_STATUS"
        const val USER_ID: String="USER_ID"
        val USER_NAME: String="APP_USER_NAME"
        const val APP_NAME: String = "BARBERS_APP"

    }
}
fun switchFragment(fragmentManager: FragmentManager, layout: Int, fragment: Fragment, bundle: Bundle, pos:Int=0){

    val transaction=fragmentManager.beginTransaction()

    fragment.arguments=bundle

    transaction.replace(layout, fragment)

    if(pos==0){

        transaction.addToBackStack(null)

    }

    transaction.commit()
}

fun changeFragment(fragmentManager: FragmentManager,fragment: Fragment,bundle: Bundle){
    val transaction=fragmentManager.beginTransaction()

    fragment.arguments=bundle

    transaction.replace(R.id.frameDynamicLayout, fragment)

    transaction.addToBackStack(null)

    transaction.commit()
}

fun showMessage(str:String){
    Log.d("Application_Error", str)
}

fun showErrorDialog(context: Context, text:String){
    val dialog= SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);

    dialog.setTitle("Error!")

    dialog.setCancelable(false)

    dialog.contentText = text

    dialog.show()
}
fun showSuccessDialog(context: Context, text: String, runnable: (dialog: SweetAlertDialog)->Unit){
    val dialog= SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);

    dialog.setTitle("SUCESS!")

    dialog.contentText = text

    dialog.show()

    dialog.setCancelable(false)

    dialog.setConfirmClickListener {
        it.hide()
        runnable(dialog)
    }
}
fun showProgressDialog(context: Context, text: String, confirm: (dialog: SweetAlertDialog) -> Unit): SweetAlertDialog {
    val dialog= SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)

    dialog.setTitle("PROCESSING ..")

    dialog.contentText=text

    dialog.setCancelable(false)

    dialog.setConfirmClickListener {
        confirm(dialog)
    }
    return dialog
}

fun getToday(): String {
    val cal= Calendar.getInstance()

    return "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)+1}-${cal.get(Calendar.DAY_OF_MONTH)}"
}