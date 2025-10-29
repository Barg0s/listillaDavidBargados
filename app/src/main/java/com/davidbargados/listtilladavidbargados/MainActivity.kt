package com.davidbargados.listtilladavidbargados

import android.app.AlertDialog
import android.widget.ImageView
import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {
    var records : ArrayList<Record> = ArrayList<Record>();


    var adapter : ArrayAdapter<Record>? = null;




    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){v,insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var button = findViewById<Button>(R.id.button);

        records.add(Record(25,"David"))
        records.add(Record(25,"David"))
        records.add(Record(25,"David"))


        adapter = object : ArrayAdapter<Record>(this,R.layout.itemview,records)
        {
            override fun getView(pos: Int, convertView: View?, container: ViewGroup): View {
                var convertView = convertView;
                if (convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.itemview, container, false)



                }


                val bitmap = BitmapFactory.decodeStream( assets.open("ieti_logo.png") )
                convertView.findViewById<ImageView>(R.id.imageView).setImageBitmap( bitmap )

                convertView.findViewById<TextView>(R.id.nom).text = getItem(pos)?.nom
                convertView.findViewById<TextView>(R.id.intents).text = getItem(pos)?.intents.toString()
                return convertView




            }
        }


            val lv = findViewById<ListView>(R.id.listilla)
            lv.setAdapter(adapter);

        button.setOnClickListener {
            mostrarDialog("RECORDS");
        }

        }


    private fun mostrarDialog(title: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("AFEGIR RECORD!")

        val dialogView = layoutInflater.inflate(R.layout.dialog, null)
        builder.setView(dialogView)

        builder.setPositiveButton("Registrar") { dialog, _ ->
            val nom = dialogView.findViewById<EditText>(R.id.name).text.toString()
            val numText = dialogView.findViewById<EditText>(R.id.puntacio).text.toString()

            if (nom.isNotBlank() && numText.isNotBlank()) {
                val intents = numText.toIntOrNull() ?: 0
                records.add(Record(intents, nom))
                adapter?.notifyDataSetChanged()
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }



}
