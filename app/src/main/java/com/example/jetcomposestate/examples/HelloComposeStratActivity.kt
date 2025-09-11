package com.example.jetcomposestate.examples

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetcomposestate.databinding.ActivityHelloComposeStratBinding
import kotlin.getValue

class HelloComposeStratActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHelloComposeStratBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.textInput.doAfterTextChanged {text ->
            updateHello(text.toString())
        }
    }

    private fun updateHello(text: String) {
        binding.HelloText.text = "Hello $text"
    }
}


class HelloViewModel : ViewModel(){
    private val _name = MutableLiveData("")
    val name : LiveData<String> = _name

    fun onNameChanged(newName : String){
        _name.value = newName
    }
}

class HelloComposeStratActivityWithViewModel : AppCompatActivity() {

    private val helloViewModel by viewModels<HelloViewModel>()
    private val binding by lazy {
        ActivityHelloComposeStratBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.textInput.doAfterTextChanged {text ->
            helloViewModel.onNameChanged(text.toString())
        }
        helloViewModel.name.observe(this){name ->
            binding.HelloText.text = "Hello $name"
        }
    }


}