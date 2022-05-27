package com.ahmet.notpad.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.TextUtilsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmet.notpad.R
import com.ahmet.notpad.data.User
import com.ahmet.notpad.data.UserViewModel

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val editname = view.findViewById<TextView>(R.id.editName)
        val editage = view.findViewById<TextView>(R.id.editAge)
        val insertButton = view.findViewById<Button>(R.id.insertButton)


        insertButton.setOnClickListener {
            var edit1=editname.text.toString()
            var edit2=editage.text.toString()

            if (TextUtils.isEmpty(edit1) || TextUtils.isEmpty(edit2)) {
                insertButton.isEnabled=true
                Toast.makeText(requireContext(), "Boş Bırakamazsın", Toast.LENGTH_SHORT).show()
            } else {

                val user = User(0, edit1, edit2.toInt())

                userViewModel.addUser(user)
                Toast.makeText(requireContext(), "Kullanıcı Eklendi", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }

        }



        return view
    }


}