package com.toharifqi.myfavoritesports.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toharifqi.myfavoritesports.R
import com.toharifqi.myfavoritesports.databinding.FragmentHomeBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateDateGreeting()

    }

    private fun populateDateGreeting() {
        val c: Date = Calendar.getInstance().getTime()

        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("dd MMM yyyy")
        val tz: TimeZone = TimeZone.getTimeZone("Asia/Jakarta")
        tz.getDisplayName(false, TimeZone.SHORT, Locale.ENGLISH)
        df.setTimeZone(tz)
        val formattedDate: String = df.format(c)

        @SuppressLint("SimpleDateFormat") val dateFormat: DateFormat = SimpleDateFormat("k")
        val greeting: Int = java.lang.String.valueOf(dateFormat.format(c)).toInt()

        if (greeting > 0 && greeting <= 11) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Morning")
        } else if (greeting > 11 && greeting <= 15) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Afternoon")
        } else if (greeting > 15 && greeting <= 19) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Evening")
        } else if (greeting > 19 && greeting <= 24) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Good night")
        } else {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Hi")
        }

        binding.txtDate.text = formattedDate
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}