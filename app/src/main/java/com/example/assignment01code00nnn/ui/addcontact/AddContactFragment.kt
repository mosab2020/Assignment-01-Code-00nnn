package com.example.assignment01code00nnn.ui.addcontact

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignment01code00nnn.R
import com.example.assignment01code00nnn.databinding.FragmentAddContactBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddContactFragment : Fragment() {

    val db = Firebase.firestore

    private var _binding: FragmentAddContactBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addContactViewModel =
            ViewModelProvider(this).get(AddContactViewModel::class.java)

        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
        addContactViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }

        binding.btnSave.setOnClickListener {

            if (TextUtils.isEmpty(binding.etName.text.toString().trim()) ||
                TextUtils.isEmpty(binding.etAddress.text.toString().trim()) ||
                TextUtils.isEmpty(binding.etName.text.toString().trim())
            ) {
                Toast.makeText(context, "Please Fill All This Fields.", Toast.LENGTH_SHORT).show()
            } else {
                // Create a new user with a first and last name
                val contact = hashMapOf(
                    "name" to binding.etName.text.toString(),
                    "phone" to binding.etNumber.text.toString(),
                    "address" to binding.etAddress.text.toString()
                )

                // Add a new document with a generated ID
                db.collection("contacts")
                    .add(contact)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(context, "${documentReference.id}", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                    }
                binding.etName.setText("")
                binding.etNumber.setText("")
                binding.etAddress.setText("")
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}