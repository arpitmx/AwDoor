 package com.ncs.awdoor.ProgressiveTextLayout

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.ncs.awdoor.R
import com.ncs.awdoor.databinding.FragmentProgressiveTextBinding
import java.text.SimpleDateFormat
import java.util.*

 class ProgressiveTextFragment : Fragment() {

    companion object {
        fun newInstance() = ProgressiveTextFragment()
    }

    private lateinit var viewModel: ProgressiveTextViewModel
    private var _binding : FragmentProgressiveTextBinding? = null
    private val binding get()  = _binding!!

     lateinit var date: String
     lateinit var destination : String
     lateinit var nodays : String
     lateinit var tripType : String
     lateinit var transport : String
     lateinit var noOfpeople : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProgressiveTextBinding.inflate(inflater,container,false)

        val languages = resources.getStringArray(R.array.triptype)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item , languages)
        val autocompleteTV =binding.autoCompleteTextView
        autocompleteTV.setAdapter(arrayAdapter)


        val transports = resources.getStringArray(R.array.transport)
        val transportAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item , transports)
        val transportView =binding.transportView
        transportView.setAdapter(transportAdapter)

        val will = resources.getStringArray(R.array.will)
        val willAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item , will)
        val willView =binding.hotelwill
        willView.setAdapter(willAdapter)

        val star = resources.getStringArray(R.array.stars)
        val starAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item , star)
        val starView =binding.hotelstars
        starView.setAdapter(starAdapter)


        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        binding.dateInp.setOnClickListener{
            datePicker.show(requireActivity().supportFragmentManager, "ewf")
            datePicker.addOnPositiveButtonClickListener{

//                val timeZoneUTC: TimeZone = TimeZone.getDefault()
//
//                val offsetFromUTC: Int = timeZoneUTC.getOffset(Date().getTime()) * -1
//
//
//                val simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
//                val date = Date(it + offsetFromUTC)
//
//
//                val text =

                date= outputDateFormat.format(it).toString()
                Toast.makeText(requireContext(),date,Toast.LENGTH_SHORT).show()
                binding.dateInp.text = date


            }
        }

        return binding.root
    }

     private val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
         timeZone = TimeZone.getTimeZone("UTC")
     }


     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener{
            var tempDestination = binding.destination.text.toString().trim()
            if(!tempDestination.equals("")){

                binding.detail2.visibility = View.VISIBLE
                binding.detail1.alpha = 0.5f
                destination = tempDestination

                binding.b.p1.setImageResource(R.drawable.ic_se)
                binding.b.l1.setBackgroundColor(resources.getColor(R.color.primary))
                binding.b.p2.setImageResource(R.drawable.ic_do)

                if (!binding.noofdays.toString().trim().equals("") && !binding.dateInp.text.toString().trim().equals("") ){

                    binding.detail3.visibility = View.VISIBLE
                    binding.detail2.alpha = 0.5f
                    nodays= binding.noofdays.toString().trim()

                    binding.b.p2.setImageResource(R.drawable.ic_se)
                    binding.b.l2.setBackgroundColor(resources.getColor(R.color.primary))
                    binding.b.p3.setImageResource(R.drawable.ic_do)




                }else {
                    Toast.makeText(requireContext(),"Some field still left", Toast.LENGTH_SHORT).show()
                }


            }else {
                binding.destination.error = "Empty"
            }
        }



     }



     fun handleGroupType(){
         if(!binding.autoCompleteTextView.toString().trim().equals("") ){
             tripType = binding.autoCompleteTextView.toString().trim()

             var group = false
             if (tripType == "group"){
                 group = true
                 binding.detail45.visibility = View.VISIBLE
             }
             if (group && !binding.noofpeople.toString().trim().equals("") || !group){

                 noOfpeople = binding.noofpeople.toString().trim()
                 binding.b.p3.setImageResource(R.drawable.ic_se)
                 binding.b.l3.setBackgroundColor(resources.getColor(R.color.primary))
                 binding.b.p4.setImageResource(R.drawable.ic_do)

                 binding.detail4.visibility = View.VISIBLE
                 binding.detail3.alpha = 0.5f
                 binding.detail45.alpha = 0.5f

             }

         }else {
             binding.autoCompleteTextView.error = "Empty"
         }
     }

     override fun onAttach(context: Context) {
         super.onAttach(context)
         viewModel = ViewModelProvider(this).get(ProgressiveTextViewModel::class.java)





     }

}