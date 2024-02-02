package com.example.cse227.fetchAndSave

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse227.R
import com.google.firebase.database.*

class Fetch : AppCompatActivity() {
    private lateinit var empRecyclerView : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var empList : ArrayList<EmployeeModel>
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)
        empRecyclerView=findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager=LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        empList=arrayListOf<EmployeeModel>()
        getEmployeesData()
    }

    private fun getEmployeesData() {
        Toast.makeText(this , "on fetch" , Toast.LENGTH_SHORT).show()
        empRecyclerView.visibility=View.GONE
        tvLoadingData.visibility=View.VISIBLE
        dbRef=FirebaseDatabase.getInstance().getReference("Employees")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                empList.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children) {
                        val empData=empSnap
                            .getValue(EmployeeModel::class.java)
                        empList.add(empData !!)
                    }
                    val mAdapter=EmpAdapter(empList)
                    empRecyclerView.adapter=mAdapter

                    mAdapter.setOnItemClickListener(object : EmpAdapter.OnItemClickListener {
                        override fun onItemClick(position : Int) {
                            val intent=Intent(
                                    this@Fetch ,
                                    EmployeeDetailsActivity::class.java
                            )
//put extras
                            intent.putExtra("empId" , empList[position].empId)
                            intent.putExtra("empName" , empList[position].empName)
                            intent.putExtra("empAge" , empList[position].empAge)
                            intent.putExtra("empSalary" , empList[position].empSalary)
                            startActivity(intent)
                        }

                    })

                    empRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error : DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}