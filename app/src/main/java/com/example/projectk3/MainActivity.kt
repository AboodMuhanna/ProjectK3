package com.example.projectk3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectk3.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var count :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference()

        binding.Sbutton.setOnClickListener {
            val name = binding.edName.text.toString()
            val id = binding.edID.text.toString()
            val age = binding.edAge.text.toString()

            // Create a new user with a first and last name
            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myRef.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()

        }

        binding.gbutton.setOnClickListener {
            // Read from the database
            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.getValue()
                    binding.txData.text = value.toString()
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()


                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"Failed",Toast.LENGTH_LONG).show()

                }

            })

        }



    }
}