package com.ahmet.notpad.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmet.notpad.R
import com.ahmet.notpad.data.User
import com.ahmet.notpad.data.UserViewModel

class UpdateFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val editname = view.findViewById<TextView>(R.id.editName2)
        val editage = view.findViewById<TextView>(R.id.editAge2)

        editname.setText(args.currentUser.name)
        editage.setText(args.currentUser.age.toString())

        val insertButton = view.findViewById<Button>(R.id.updateButton)

        insertButton.setOnClickListener {
            var edit3=editname.text.toString()
            var edit4=editage.text.toString()

            if (edit3.equals("") || edit4.equals("")) {
                insertButton.isEnabled=true
                Toast.makeText(requireContext(), "Boş Bırakamazsın", Toast.LENGTH_SHORT).show()
            } else {

                val updateUser = User(args.currentUser.id, edit3,edit4.toInt())
                userViewModel.updateUser(updateUser)
                Toast.makeText(requireContext(), "Veri Güncellendi", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete) {
            val builder=AlertDialog.Builder(requireContext())
            builder.setTitle("${args.currentUser.name} mi silinecek ?")
            builder.setMessage("${args.currentUser.name} silmek istiyor musun ?")
            builder.setPositiveButton("Evet"){ _,_ ->
                userViewModel.deleteUser(args.currentUser)
                Toast.makeText(requireContext(), "Silme İşlemi Başarılı", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("Hayır"){_,_ -> }
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }

}