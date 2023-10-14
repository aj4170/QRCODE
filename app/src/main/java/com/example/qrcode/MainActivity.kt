package com.example.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {

    private lateinit var ivqrcode : ImageView
    private lateinit var edittext : EditText
    private lateinit var btnforgenerate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivqrcode = findViewById(R.id.qr_code)
        edittext = findViewById(R.id.esit_text)
        btnforgenerate = findViewById(R.id.button)


        btnforgenerate.setOnClickListener{

            val data = edittext.text.toString()

            if(data.isEmpty()){
                Toast.makeText(this, "enter some data", Toast.LENGTH_SHORT).show()
            }
            else{

                var writer = QRCodeWriter()

                  try{

                      val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE,512,512)
                      val width = bitMatrix.width
                      val height = bitMatrix.height
                      val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
                      for(x in 0 until width){
                          for(y in 0 until height){
                              bmp.setPixel(x,y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                          }
                      }
                      ivqrcode.setImageBitmap(bmp)

                  }catch (e : WriterException){
                      e.printStackTrace()
                  }
            }
        }
    }
}