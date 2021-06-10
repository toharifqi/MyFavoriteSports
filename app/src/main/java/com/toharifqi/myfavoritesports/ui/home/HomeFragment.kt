package com.toharifqi.myfavoritesports.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stone.vega.library.VegaLayoutManager
import com.toharifqi.myfavoritesports.R
import com.toharifqi.myfavoritesports.core.data.Resource
import com.toharifqi.myfavoritesports.core.ui.SportAdapter
import com.toharifqi.myfavoritesports.databinding.FragmentHomeBinding
import com.toharifqi.myfavoritesports.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()

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

        if (activity != null){
            val sportAdapter = SportAdapter()
            sportAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.sports.observe(viewLifecycleOwner, { sport ->
                if (sport != null){
                    when(sport){
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            sportAdapter.setData(sport.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = sport.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvHome){
                layoutManager = VegaLayoutManager()
                setHasFixedSize(true)
                adapter = sportAdapter
            }
        }

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
            binding.imgTime.setImageResource(R.drawable.morning)
        } else if (greeting > 11 && greeting <= 15) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Afternoon")
            binding.imgTime.setImageResource(R.drawable.afternoon)
        } else if (greeting > 15 && greeting <= 19) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Evening")
            binding.imgTime.setImageResource(R.drawable.evening)
        } else if (greeting > 19 && greeting <= 24) {
            binding.txtGreeting.text = getString(R.string.dummy_greeting, "Night")
            binding.imgTime.setImageResource(R.drawable.night)
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