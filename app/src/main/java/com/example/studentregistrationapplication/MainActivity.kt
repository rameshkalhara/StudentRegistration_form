package com.example.studentregistrationapplication
import com.example.studentregistrationapplication.models.validations.FormData
import com.example.studentregistrationapplication.models.validations.ValidationResult


import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog




import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var edtStudentId:EditText
        lateinit var spnYear: Spinner
        lateinit var spnSemester:Spinner
        lateinit var cbAgree:CheckBox
        lateinit var tvYear:TextView
        lateinit var tvSemester:TextView

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtStudentId = findViewById(R.id.edtStudentId)
        tvYear = findViewById(R.id.tvYear)
        spnYear = findViewById(R.id.spnYear)
        tvSemester = findViewById(R.id.tvSemester)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)

        fun displayAlert(title:String, message:String){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK") { dialog, which ->
                // Do something when the "OK" button is clicked
            }
            val dialog = builder.create()
            dialog.show()
        }

        fun submit(view: View){

            var count = 0
            val myForm = FormData(
                edtStudentId.text.toString(),
                spnYear.selectedItem.toString(),
                spnSemester.selectedItem.toString(),
                cbAgree.isChecked
            )
            val studentIdValidation = myForm.validateStudentId()
            val spnYearValidation = myForm.validateYear()
            val spnSemesterValidation = myForm.validateSemester()
            val cbAgreeValidation = myForm.validateAgreement()
            when(studentIdValidation){
                is ValidationResult.Valid ->{
                    count ++
                }
                is ValidationResult.Invalid ->{
                    edtStudentId.error = studentIdValidation.errorMessage
                }
                is ValidationResult.Empty ->{
                    edtStudentId.error = studentIdValidation.errorMessage
                }
            }
            when(spnYearValidation){
                is ValidationResult.Valid ->{
                    count ++
                }
                is ValidationResult.Invalid ->{
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error =""
                    tv.text = spnYearValidation.errorMessage
                }
                is ValidationResult.Empty ->{
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error =""
                    tv.text = spnYearValidation.errorMessage
                }
            }
            when(spnSemesterValidation){
                is ValidationResult.Valid ->{
                    count ++
                }
                is ValidationResult.Invalid ->{
                    val tv:TextView = spnSemester.selectedView as TextView
                    tv.error =""
                    tv.text = spnSemesterValidation.errorMessage
                }
                is ValidationResult.Empty ->{
                    val tv:TextView = spnSemester.selectedView as TextView
                    tv.error =""
                    tv.text = spnSemesterValidation.errorMessage
                }
            }
            when(cbAgreeValidation){
                is ValidationResult.Valid ->{
                    count ++
                }
                is ValidationResult.Invalid ->{
                    displayAlert("Error", cbAgreeValidation.errorMessage)
                }
                is ValidationResult.Empty ->{
                }
            }
            if(count==4){
                displayAlert("Success","You have successfully registered")
            }
        }




    }
}