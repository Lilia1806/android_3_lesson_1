package com.example.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android_3_lesson_1.databinding.FragmentFirstBinding
import com.example.android_3_lesson_1.model.Model
import com.example.ui.adapter.FirstAdapter

class FirstFragment : Fragment() {

    private var viewModel: FirstViewModel? = null
    private lateinit var binding: FragmentFirstBinding
    private val adapter = FirstAdapter(this::onItemClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FirstViewModel::class.java]

        initialize()
        setUpListener()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel?.getListOfText()?.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }

    private fun setUpListener() {
        binding.btnMain.setOnClickListener {
            binding.btnMain.isInvisible = true
            binding.rvMain.isInvisible = false
        }
    }

    private fun initialize() {
        binding.rvMain.adapter = adapter
    }

    private fun onItemClick(model: Model) {
        findNavController().navigate(
            FirstFragmentDirections.actionFirstFragmentToDetailFragment(
                model.name,
                model.image
            )
        )
    }

}