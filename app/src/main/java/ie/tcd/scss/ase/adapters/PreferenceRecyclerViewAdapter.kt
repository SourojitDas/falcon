package ie.tcd.scss.ase.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ie.tcd.scss.ase.poko.PreferenceModel

class PreferenceRecyclerViewAdapter(val prefList: ArrayList<PreferenceModel>,val context:Context): RecyclerView.Adapter<PreferenceRecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val preferenceItemVIew="";
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PreferenceRecyclerViewAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: PreferenceRecyclerViewAdapter.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

