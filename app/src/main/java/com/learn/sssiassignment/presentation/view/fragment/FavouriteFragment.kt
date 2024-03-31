package com.learn.sssiassignment.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.sssiassignment.adapters.RvLocalAdapter
import com.learn.sssiassignment.databinding.LayoutFavBinding
import com.learn.sssiassignment.presentation.viewModel.UserDataViewModel

/**
created by Rachit on 3/27/2024.
 */
class FavouriteFragment : Fragment() {
    private var _binding: LayoutFavBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserDataViewModel

    private lateinit var rvLocalAdapter: RvLocalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutFavBinding.inflate(inflater, container, false)

        initObserver()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocalData()
    }

    private fun initObserver() {

        viewModel.localData.observe(viewLifecycleOwner) {
            it?.let {
                if(it.isNotEmpty()) {
                    binding.tv.visibility = View.GONE
                    binding.recycler.visibility = View.VISIBLE
                    binding.recycler.apply {
                        layoutManager = LinearLayoutManager(context);

                        this.layoutManager = layoutManager
                        rvLocalAdapter = RvLocalAdapter(it){uId->
                          viewModel.deleteData(uId)
                        }
                        this.adapter = rvLocalAdapter
                    }
                }
                else {
                    binding.recycler.visibility = View.GONE
                    binding.tv.visibility = View.VISIBLE
                }
            }


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocalData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[UserDataViewModel::class.java]
    }
}
