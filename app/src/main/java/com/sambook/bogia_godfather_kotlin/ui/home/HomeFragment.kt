package com.sambook.bogia_godfather_kotlin.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sambook.bogia_godfather_kotlin.PDFViewActivity
import com.sambook.bogia_godfather_kotlin.adapter.ExpandableListAdapter
import com.sambook.bogia_godfather_kotlin.databinding.FragmentHomeBinding
import java.io.File
import java.io.InputStream


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var listAdapter: ExpandableListAdapter? = null
    private var expListView: ExpandableListView? = null
    var listDataHeader: ArrayList<String> = arrayListOf()
    var listDataChild: HashMap<String, List<String>> = HashMap<String, List<String>>()

    var drawer: DrawerLayout? = null
    var btnContinue: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.prepareListData(listDataHeader, listDataChild)
        Log.i(TAG, listDataHeader.toString())

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {`
//            textView.text = it
//        }
        init()
        even()
        return root
    }

    private fun even() {
        btnContinue?.setOnClickListener {
            clickContinue()
        }

        expListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(context, listDataHeader[groupPosition] + " : " + listDataChild[listDataHeader[groupPosition]]!![childPosition], Toast.LENGTH_SHORT).show()
            val intent = Intent(context, PDFViewActivity::class.java)
            intent.putExtra("HEAD", groupPosition)
            intent.putExtra("CHAPTER", childPosition)
            startActivity(intent)
            false
        }
    }

    private fun init() {
        btnContinue = binding.btnContinue
        expListView = binding.lvExp

        listAdapter = ExpandableListAdapter(context, listDataHeader, listDataChild)

        expListView!!.setAdapter(listAdapter)
    }

    private fun clickContinue() {
        try {
//            val continuePage = File("pagecontinue.txt").readText()
//            val intent = Intent(context, PDFViewActivity::class.java)
//            intent.putExtra("PAGECONTINUE", continuePage)
//            startActivity(intent)
//            Toast.makeText(context, "Trang: $continuePage", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Bạn chưa đọc trang nào", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = HomeFragment::class.java.toString()
    }
}