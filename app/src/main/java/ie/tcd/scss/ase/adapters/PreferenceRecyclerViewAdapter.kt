package ie.tcd.scss.ase.adapters

import android.content.Context
import android.preference.Preference
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.activites.PreferencesActivity
import ie.tcd.scss.ase.poko.PreferedMode
import ie.tcd.scss.ase.utilities.ModePreferenceInterface

class PreferenceRecyclerViewAdapter(val prefList: ArrayList<PreferedMode>,val listener:ModePreferenceInterface,
                                    val context: Context): RecyclerView.Adapter<PreferenceRecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val preferenceItemVIew=itemView.findViewById<TextView>(R.id.single_row_preference_mode)
        val preferenceItemSwitch=itemView.findViewById<Switch>(R.id.single_row_preference_toggle)
//        var selectedMode: MutableList<String> = mutableListOf<String>()

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PreferenceRecyclerViewAdapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.single_row_preference,p0,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return prefList.size
    }

    override fun onBindViewHolder(viewHolder: PreferenceRecyclerViewAdapter.ViewHolder, i: Int) {
        viewHolder.preferenceItemVIew.text = prefList[i].mode
        viewHolder.preferenceItemSwitch.setOnClickListener {
            prefList[i].seleceted = viewHolder.preferenceItemSwitch.isChecked
            listener.selectedPrefernceMode(prefList)
        }

    }

}

