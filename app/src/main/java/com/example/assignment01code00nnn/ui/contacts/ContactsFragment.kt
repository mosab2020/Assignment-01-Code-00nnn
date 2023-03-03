package com.example.assignment01code00nnn.ui.contacts

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.assignment01code00nnn.adapter.Contact
import com.example.assignment01code00nnn.adapter.ContactAdapter
import com.example.assignment01code00nnn.databinding.FragmentContactsBinding
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactsFragment : Fragment() {

    private val db = Firebase.firestore

    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactsViewModel =
            ViewModelProvider(this).get(ContactsViewModel::class.java)

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
        contactsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }

        var data = ArrayList<Contact>()

        db.collection("contacts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    Log.d(TAG, "${document.id} => ${document.data["name"]}")
                    data.add(
                        Contact(
                            document.data["name"].toString(),
                            document.data["phone"].toString(),
                            document.data["address"].toString()
                        )
                    )
                }
                val contactsAdapter = ContactAdapter(requireContext(), data)

                binding.listView.adapter = contactsAdapter
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
                Toast.makeText(requireContext(), "$exception", Toast.LENGTH_SHORT).show()
            }




        Log.d("AAAA", "${data.size.toString()}")
        Log.d("AAAA", "Ahmed")

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}