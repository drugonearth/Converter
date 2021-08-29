package com.example.converter.controller

import ConverterInputException
import RuConverter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class MainController {

    @GetMapping("/")
    fun start(model: Map<String, Any>): String {
        return "login"
    }

    @GetMapping("styles.css")
    fun css(model: Map<String, Any>): String {
        return "styles.css"
    }

    @GetMapping("/main")
    fun main(model: MutableMap<String, Any>): String {
        return "main"
    }

    @PostMapping("convert")
    fun filter(@RequestParam convert: String, model: MutableMap<String, Any>): String {

        var output: String?
        try {
            output = RuConverter().gettingData(convert)
            model["output"] = output
        }
        catch(exception: ConverterInputException)
        {
            model["output"] = "Error"
        }
        return "main"
    }


}