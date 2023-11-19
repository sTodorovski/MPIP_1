package mk.ukim.lab_intents.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LabIntentsViewModel: ViewModel() {

    private val _rezultat: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getRezultat(): MutableLiveData<String> {
        return _rezultat
    }

    fun setRezultat(rezultat: String) {
        this._rezultat.value = rezultat
    }

    fun getRezultatValue(): String? {
        return _rezultat.value
    }
}