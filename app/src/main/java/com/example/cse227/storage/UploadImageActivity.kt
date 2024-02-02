package com.example.cse227.storage

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.cse227.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class UploadImageActivity : AppCompatActivity() {
    private lateinit var uploadBtn : Button
    private lateinit var chooseBtn : Button
    lateinit var ivPic : ImageView
    private var fileUri : Uri?=null
    private lateinit var getImage : ActivityResultLauncher<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        uploadBtn=findViewById(R.id.uploadBtn)
        chooseBtn=findViewById(R.id.chooseBtn)
        ivPic=findViewById(R.id.ivPic)

        getImage=registerForActivityResult(
                ActivityResultContracts.GetContent() ,
                ActivityResultCallback {
                    if (it != null) {
                        fileUri=it
                    }

                    ivPic.setImageURI(it)
                }
        )

        chooseBtn.setOnClickListener {
            getImage.launch("image/*")
        }

        uploadBtn.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        if (fileUri != null) {
            val progressDialog=ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.setMessage("Uploading your image..")
            progressDialog.show()

            val ref : StorageReference=FirebaseStorage.getInstance().getReference()
                .child(UUID.randomUUID().toString())
            ref.putFile(fileUri !!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext , "Image uploaded" , Toast.LENGTH_SHORT)
                    .show()

            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(
                        applicationContext ,
                        "Fail to upload Image" + it.message ,
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}