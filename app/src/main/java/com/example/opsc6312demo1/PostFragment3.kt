package com.example.opsc6312demo1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.opsc6312demo1.databinding.FragmentPost3Binding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment3 : Fragment() {
    private var _binding: FragmentPost3Binding? = null
    private val binding get() = _binding!!
    private var currentPost: Post? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPost3Binding.inflate(inflater, container, false)
        return binding.root
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Fetch post data
            RetrofitClient.api.getPost(1, 5)
                .enqueue(object : Callback<List<Post>> {
                    override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                        if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                            currentPost = response.body()!![0]
                            binding.textUserId.text = "User ID: ${currentPost!!.userId}"
                            binding.textPostId.text = "Post ID: ${currentPost!!.id}"
                            binding.textTitle.text = currentPost!!.title
                            binding.textBody.text = currentPost!!.body
                        }
                    }

                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

        binding.btnJsonHandler.setOnClickListener {
            currentPost?.let {
                val jsonString = Gson().toJson(it)
                val intent = Intent(requireContext(), JsonDisplayActivity::class.java)
                intent.putExtra("json_data", jsonString)
                startActivity(intent)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
