package co.com.challenge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.com.challenge.R
import co.com.challenge.databinding.FragmentSecondBinding
import co.com.challenge.presentation.viewModels.ProductListViewModel
import co.com.challenge.presentation.viewModels.SecondFragmentViewModel
import com.bumptech.glide.Glide

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val safeArgs: SecondFragmentArgs by navArgs()
    private val viewModel: SecondFragmentViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.fragment_second,
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        viewModel.product = safeArgs.product
        binding.viewModel = viewModel
        val nameSeller = viewModel.product.seller.permalink.split("/")
        binding.lbSellerName.text = nameSeller[3]
        Glide.with(this)
            .load(viewModel.product.thumbnail)
            .into(binding.imgProd)
    }
}