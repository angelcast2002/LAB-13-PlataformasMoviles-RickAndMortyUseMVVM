package com.example.lab8_plataformas.ui.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab8_plataformas.data.local.entity.Character
import com.example.lab8_plataformas.databinding.FragmentPlaceListBinding
import com.example.lab8_plataformas.ui.character_list.recylcerView.adapter.PlaceAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment(), PlaceAdapter.PlaceListener{

    private lateinit var binding: FragmentPlaceListBinding
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        viewModel.getCharacters()
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: CharacterListUiState) {
        when(state){

            CharacterListUiState.Default -> {

                binding.progressUsers.visibility = View.GONE
                binding.recyclerRecyclerActivity.visibility = View.GONE

            }
            is CharacterListUiState.Error -> {

                binding.progressUsers.visibility = View.GONE
                binding.recyclerRecyclerActivity.visibility = View.GONE

                Toast.makeText(
                    requireContext(),
                    state.message,
                    Toast.LENGTH_LONG
                ).show()

            }
            CharacterListUiState.Loading -> {

                binding.progressUsers.visibility = View.VISIBLE
                binding.recyclerRecyclerActivity.visibility = View.GONE

            }
            is CharacterListUiState.Succes -> {

                setRecycler(state.characters)

                binding.progressUsers.visibility = View.GONE
                binding.recyclerRecyclerActivity.visibility = View.VISIBLE



            }
        }
    }

    private fun setRecycler(characters: List<Character>) {

        val dataRecycler = characters.toMutableList()
        binding.recyclerRecyclerActivity.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerRecyclerActivity.setHasFixedSize(true)
        binding.recyclerRecyclerActivity.adapter = PlaceAdapter(dataRecycler,this)

    }

    override fun onPlaceClicked(data: Character, position: Int) {
        TODO("Not yet implemented")
    }
}