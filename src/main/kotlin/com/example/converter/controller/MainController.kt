package com.example.converter.controller

import ConverterInputException
import EnConverter
import RuConverter
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*


@Controller
class MainController {

    @GetMapping("/")
    fun start(model: Map<String, Any>): String {
        return "login"
    }

    @GetMapping("/login")
    fun login(model: Map<String, Any>): String {
        return "main"
    }

    @GetMapping("/main")
    fun main(model: MutableMap<String, Any>): String {
        return "main"
    }

    @PostMapping("convert")
    fun convert(@RequestParam convert: String, model: MutableMap<String, Any>): String {
        val locale = LocaleContextHolder.getLocale()
        var output: String?
        try {
            if(locale == Locale("ru")) {
                output = RuConverter().gettingData(convert)
                model["output"] = output
            }
            else
            {
                output = EnConverter().gettingData(convert)
                model["output"] = output
            }
        }
        catch(exception: ConverterInputException)
        {
            if(locale == Locale("ru")) {
                model["output"] = "Ошибка"
            }
            else
            {
                output = EnConverter().gettingData(convert)
                model["output"] = "Error"
            }
        }
        return "main"
    }


}