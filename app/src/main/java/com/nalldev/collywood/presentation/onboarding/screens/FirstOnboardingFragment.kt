package com.nalldev.collywood.presentation.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.nalldev.collywood.R
import com.nalldev.collywood.databinding.FragmentFirstOnboardingBinding
import eightbitlab.com.blurview.RenderScriptBlur

class FirstOnboardingFragment : Fragment() {
    private var _binding : FragmentFirstOnboardingBinding? = null
    private val binding get() = _binding!!
    private var title : String = ""
    private var subtitle : String = ""
    private var image : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = requireArguments().getString(ARG_PARAM1, "")
            subtitle = requireArguments().getString(ARG_PARAM2, "")
            image = requireArguments().getInt(ARG_PARAM3, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            context?.let {
                bvContent.setupWith(binding.root, RenderScriptBlur(it))
                    .setBlurRadius(20f)
            }
            tvSubtitle.text = subtitle
            if (tvSubtitle.text.equals(getString(R.string.onboarding_subtitle_1))) {
                context?.let {
                    tvSubtitle.setTextColor(ContextCompat.getColor(it, R.color.black))
                }
            }
            tvTitle.text = title
            ivContent.setImageResource(image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val ARG_PARAM3 = "param3"
        @JvmStatic
        fun newInstance(param1: String, param2: String, @DrawableRes param3: Int) =
            FirstOnboardingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }
}