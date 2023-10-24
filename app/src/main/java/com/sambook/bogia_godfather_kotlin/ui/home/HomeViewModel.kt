package com.sambook.bogia_godfather_kotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun prepareListData(
        listDataHeader: ArrayList<String>,
        listDataChild: HashMap<String, List<String>>
    ) {

        listDataHeader.add("CUỐN I");
        listDataHeader.add("CUỐN II");
        listDataHeader.add("CUỐN III");
        listDataHeader.add("CUỐN IV");
        listDataHeader.add("CUỐN V");
        listDataHeader.add("CUỐN VI");
        listDataHeader.add("CUỐN VII");
        listDataHeader.add("CUỐN VIII");
        listDataHeader.add("CUỐN IX");

        // Adding child data

        // Adding child data
        val cuon1: MutableList<String> = ArrayList()
        cuon1.add("Chương 1")
        cuon1.add("Chương 2")
        cuon1.add("Chương 3")
        cuon1.add("Chương 4")
        cuon1.add("Chương 5")
        cuon1.add("Chương 6")
        cuon1.add("Chương 7")
        cuon1.add("Chương 8")
        cuon1.add("Chương 9")
        cuon1.add("Chương 10")
        cuon1.add("Chương 11")


        val cuon2: MutableList<String> = ArrayList()
        cuon2.add("Chương 12")
        cuon2.add("Chương 13")


        val cuon3: MutableList<String> = ArrayList()
        cuon3.add("Chương 14")

        val cuon4: MutableList<String> = ArrayList()
        cuon4.add("Chương 15")
        cuon4.add("Chương 16")
        cuon4.add("Chương 17")
        cuon4.add("Chương 18")
        cuon4.add("Chương 19")

        val cuon5: MutableList<String> = ArrayList()
        cuon5.add("Chương 20")
        cuon5.add("Chương 21")
        cuon5.add("Chương 22")

        val cuon6: MutableList<String> = ArrayList()
        cuon6.add("Chương 23")
        cuon6.add("Chương 24")

        val cuon7: MutableList<String> = ArrayList()
        cuon7.add("Chương 25")
        cuon7.add("Chương 26")
        cuon7.add("Chương 27")
        cuon7.add("Chương 28")

        val cuon8: MutableList<String> = ArrayList()
        cuon8.add("Chương 29")
        cuon8.add("Chương 30")
        cuon8.add("Chương 31")

        val cuon9: MutableList<String> = ArrayList()
        cuon9.add("Chương 32")

        listDataChild[(listDataHeader as ArrayList<String>)[0]] = cuon1 // Header, Child data

        listDataChild.put(listDataHeader.get(0), cuon1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), cuon2);
        listDataChild.put(listDataHeader.get(2), cuon3);
        listDataChild.put(listDataHeader.get(3), cuon4);
        listDataChild.put(listDataHeader.get(4), cuon5);
        listDataChild.put(listDataHeader.get(5), cuon6);
        listDataChild.put(listDataHeader.get(6), cuon7);
        listDataChild.put(listDataHeader.get(7), cuon8);
        listDataChild.put(listDataHeader.get(8), cuon9);
    }
}