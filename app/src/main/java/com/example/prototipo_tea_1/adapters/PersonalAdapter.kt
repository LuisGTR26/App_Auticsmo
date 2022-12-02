package com.example.prototipo_tea_1.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prototipo_tea_1.R
import com.example.prototipo_tea_1.model.data.database.entities.Rutina
import com.example.prototipo_tea_1.view.fragments.FamiliarFragmentDirections
import com.example.prototipo_tea_1.view.fragments.PersonalFragmentDirections
import com.example.prototipo_tea_1.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_rutina.view.*

class PersonalAdapter: RecyclerView.Adapter<PersonalAdapter.MyViewHolder>() {

    private var rutinaList = emptyList<Rutina>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rutina, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(holder.itemView.context)
        val admin = prefs.getBoolean("mode_admin", false )

        val currentItem = rutinaList[position]
        holder.itemView.txtRutina.text = currentItem.titleRutina
        holder.itemView.imgRutinaR.setImageBitmap(currentItem.imgRutina)
        //Verificar que es admin
        if (admin){
            holder.itemView.btnMore.visibility = View.VISIBLE
        }else{
            holder.itemView.btnMore.visibility = View.GONE
        }
        holder.itemView.btnMore.setOnClickListener { popupmenu(holder, it, currentItem) }
        holder.itemView.rowLayout.setOnClickListener{
            val action = PersonalFragmentDirections.actionPersonalFragmentToProcedimientosFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    private fun popupmenu(h: MyViewHolder, v:View, cI: Rutina) {
        val c = h.itemView.context
        val popupMenus = PopupMenu(c, v)
        popupMenus.inflate(R.menu.item_menu)
        popupMenus.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit -> {
                    val action = PersonalFragmentDirections.actionPersonalFragmentToEditarRutina(cI)
                    h.itemView.findNavController().navigate(action)
                    true
                }
                else -> true
            }
        }
        popupMenus.show()
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenus)
        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(menu, true)
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