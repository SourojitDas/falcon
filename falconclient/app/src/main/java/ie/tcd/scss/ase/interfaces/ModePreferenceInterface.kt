package ie.tcd.scss.ase.interfaces

import ie.tcd.scss.ase.dataclasses.Preferences

interface ModePreferenceInterface {
    fun selectedPrefernceMode(list:ArrayList<Preferences>)
}