package com.learn.sssiassignment.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learn.sssiassignment.adapters.RvAdapter
import com.learn.sssiassignment.data.remote.models.UserDataX
import com.learn.sssiassignment.databinding.LayoutTeachersBinding
import com.learn.sssiassignment.data.local.UserLocalModel
import com.learn.sssiassignment.presentation.viewModel.UserDataViewModel
import com.learn.sssiassignment.utils.Connectivity
import com.learn.sssiassignment.utils.Resource
import com.learn.sssiassignment.utils.cons.ApiConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL


/**
created by Rachit on 3/27/2024.
 */
class TeachersFragment : Fragment() {
    private var _binding: LayoutTeachersBinding? = null
    private lateinit var viewModel: UserDataViewModel
    private val binding get() = _binding!!
    private lateinit var rvAdapter: RvAdapter
    private var items: MutableList<UserDataX> = mutableListOf()
    private var itemsOriginal: List<UserDataX> = emptyList()

    private var currentPage = 0
    private val PAGE_SIZE = 5
    private var isLoading = false
    private var isLastPage = false

    private var itemsId: MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutTeachersBinding.inflate(inflater, container, false)


        if (isAdded) {
            val isInternetAvailable = Connectivity.isOnline(requireContext())
            if (isInternetAvailable) {
                binding.tv.visibility = View.GONE
                viewModel.getLocalData()
                initObserver()
            } else {
                binding.tv.visibility = View.VISIBLE
                binding.progress.visibility = View.GONE
                binding.recycler.visibility = View.GONE
            }
        }




        return binding.root
    }

    private fun initObserver() {
        if (!isAdded) {
            return
        }
        viewModel.localData.observe(viewLifecycleOwner) {
            it.forEach {
                itemsId.add(it.id)
            }

        }
        viewModel.data.observe(viewLifecycleOwner) { it ->
            when (it) {
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    binding.recycler.visibility = View.GONE
                    binding.tv.text = it.error
                }

                Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recycler.visibility = View.GONE
                }

                is Resource.Success -> {
                    binding.apply {
                        progress.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        recycler.apply {
                            layoutManager = LinearLayoutManager(context);

                            this.layoutManager = layoutManager
                            itemsOriginal = it.data.userData
                            items = it.data.userData.take(5).toMutableList()
                            rvAdapter = RvAdapter(items, itemsId) { userClicked ->
                                lifecycleScope.launch {

                                    if (!itemsId.contains(userClicked.id)) {
                                        saveImageToDatabase(userClicked)

                                    }


                                }
                            }
                            this.adapter = rvAdapter
                            addOnScrollListener(object :
                                RecyclerView.OnScrollListener() {
                                override fun onScrolled(
                                    recyclerView: RecyclerView,
                                    dx: Int,
                                    dy: Int,
                                ) {
                                    super.onScrolled(recyclerView, dx, dy)
                                    val visibleItemCount = layoutManager!!.childCount
                                    val totalItemCount = layoutManager!!.itemCount
                                    val firstVisibleItemPosition: Int =
                                        (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                                    if (!isLoading && !isLastPage && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                                        loadMoreData()
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }

    private suspend fun saveImageToDatabase(userDataX: UserDataX) {
        val imageData: ByteArray? = if (userDataX.profilePic != null) {
            downloadImage(ApiConstants.BASE_URL + userDataX.profilePic.substring(1))
        } else null
        viewModel.insertData(
            UserLocalModel(
                null,
                userDataX.coins,
                userDataX.id,
                userDataX.languageName,
                userDataX.name,
                imageData
            )
        )
    }

    private suspend fun downloadImage(imageUrl: String): ByteArray {
        return withContext(Dispatchers.IO) {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputStream = connection.inputStream
            inputStream.readBytes()
        }

    }

    private fun loadMoreData() {
        isLoading = true
        currentPage++

        // Simulate loading delay
        CoroutineScope(Dispatchers.Main).launch {

            delay(1000)
            // Add next set of data
            val start = currentPage * PAGE_SIZE
            val end = Math.min((currentPage + 1) * PAGE_SIZE, 25)
            for (i in start until end) {
                items.add(itemsOriginal[i])
            }

            // Notify adapter
            rvAdapter.notifyDataSetChanged()
            isLoading = false
            if (currentPage == 4 || end >= 25) { // Assuming 25 items in total
                isLastPage = true
            }
        } // Simulated loading time
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity())[UserDataViewModel::class.java]
    }
}
