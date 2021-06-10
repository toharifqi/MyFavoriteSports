package com.toharifqi.myfavoritesports.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stone.vega.library.VegaLayoutManager
import com.toharifqi.myfavoritesports.core.ui.SportAdapter
import com.toharifqi.myfavoritesports.favorite.databinding.FragmentFavoriteBinding
import com.toharifqi.myfavoritesports.favorite.di.favoriteModule
import com.toharifqi.myfavoritesports.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val sportAdapter = SportAdapter()
            sportAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteSports.observe(viewLifecycleOwner, { sports ->
                sportAdapter.setData(sports)
                binding.viewEmpty.root.visibility = if (sports.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvFavorite){
                layoutManager = VegaLayoutManager()
                setHasFixedSize(true)
                adapter = sportAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}