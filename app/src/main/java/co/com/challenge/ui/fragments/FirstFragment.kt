package co.com.challenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import co.com.challenge.R
import co.com.challenge.databinding.FragmentFirstBinding
import co.com.challenge.presentation.state.State
import co.com.challenge.presentation.viewModels.ProductListViewModel
import co.com.challenge.ui.adapters.ListProductsAdapter

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ProductListViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.fragment_first,
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initView()

    }
    private fun initListeners() {
        ListProductsAdapter().onClickItem = {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun initView(){
        viewModel.stateGetProductsList.observe(viewLifecycleOwner, Observer {
            it.let { state ->
                when(state){
                    is State.Loading -> binding.cargando = true
                    is State.Success -> {
                        binding.cargando = false
                        viewModel.setProductsAdapter(state.responseTo())
                    }
                    is State.Failed -> {
                        binding.cargando = false
                        binding.error = true
                        println(state.failure)
                    }
                    is State.Empty -> Toast.makeText(
                        requireContext(),
                        getString(R.string.frag_fir_error_empty),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}