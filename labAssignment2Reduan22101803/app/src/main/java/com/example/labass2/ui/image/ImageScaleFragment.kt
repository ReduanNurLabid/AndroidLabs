package com.example.labass2.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.labass2.R

class ImageScaleFragment : Fragment() {

    private lateinit var imageViewModel: ImageViewModel
    private lateinit var imageView: ImageView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageViewModel =
            ViewModelProvider(this).get(ImageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_image_scale, container, false)
        
        imageView = root.findViewById(R.id.image_view)
        
        Glide.with(this)
            .load("https://random-image-pepebigotes.vercel.app/api/random-image")
            .placeholder(R.drawable.ic_menu_gallery)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .into(imageView)
        
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
        
        imageView.setOnTouchListener { v, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }
        
        return root
    }
    
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            
            // Limit scaling
            scaleFactor = scaleFactor.coerceIn(0.5f, 3.0f)
            
            imageView.scaleX = scaleFactor
            imageView.scaleY = scaleFactor
            return true
        }
    }
}