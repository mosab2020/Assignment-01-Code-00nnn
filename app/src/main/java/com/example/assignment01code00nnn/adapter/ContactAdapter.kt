package com.example.assignment01code00nnn.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.assignment01code00nnn.MainActivity
import com.example.assignment01code00nnn.R
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.model.Document
import com.google.firebase.ktx.Firebase

class ContactAdapter(var context: Context, var data: ArrayList<Contact>) : BaseAdapter() {

    private val db = Firebase.firestore
    lateinit var documentReference: DocumentReference


    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var adapterView = p1

        if (p1 == null) {
            adapterView =
                LayoutInflater.from((context as MainActivity))
                    .inflate(R.layout.custom_list_view, null, false)
        }

        val btn_delete = adapterView!!.findViewById<Button>(R.id.btn_delete)
        val tv_name = adapterView.findViewById<TextView>(R.id.tv_name)
        val tv_number = adapterView.findViewById<TextView>(R.id.tv_number)
        val tv_address = adapterView.findViewById<TextView>(R.id.tv_address)
        val listView = adapterView.findViewById<ListView>(R.id.list_view)

        tv_name.text = data[p0].name.toString()
        tv_number.text = data[p0].phoneNumber.toString()
        tv_address.text = data[p0].address.toString()

        listView.setOnItemClickListener { adapterView, view, position, id -> }

        return adapterView
    }

    private fun delete() {


        db.collection("contacts").document(documentReference.id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}