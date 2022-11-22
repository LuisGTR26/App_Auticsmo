package com.example.prototipo_tea_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.view.fragments.FamiliarFragmentDirections
import kotlinx.android.synthetic.main.item_ambito.view.*

class FamiliarAdapter: RecyclerView.Adapter<FamiliarAdapter.MyViewHolder>() {

    private var rutinaList = emptyList<Rutina>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ambito, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = rutinaList[position]
        holder.itemView.txtRutina.text = currentItem.titleRutina
        holder.itemView.imgRutinaR.setImageBitmap(currentItem.imgRutina)

        holder.itemView.rowLayout.setOnClickListener{
            val action = FamiliarFragmentDirections.actionFamiliarFragmentToProcedimientosFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return rutinaList.size
    }

    //Para actualizar los datos
    fun setData(rutina: List<Rutina>){
        this.rutinaList = rutina
        notifyDataSetChanged()
    }
}