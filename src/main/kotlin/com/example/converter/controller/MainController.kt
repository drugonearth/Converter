package com.example.converter.controller

import Converter
import ConverterInputException
import EnConverter
import RuConverter
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.util.*


@Controller
class MainController(val converters: List<Converter> ) {

    private val config = ResourceBundle.getBundle("messages")
    private var converterMap : Map<String, Converter> = converters.associateBy { it.getLanguage() };

    @GetMapping("/")
    fun start(model: Map<String, Any>): String {
        return "login"
    }

    @GetMapping("/login")
    fun login(model: Map<String, Any>): String {
        return "login"
    }

    @GetMapping("/main")
    fun main(model: MutableMap<String, Any>): String {
        return "main";
    }

    @PostMapping("/main")
    fun convert(@RequestParam convert: String, model: MutableMap<String, Any>): String {
        val locale = LocaleContextHolder.getLocale()
        converterMap[locale.language]?.let {
            try {
                model["output"] = it.gettingData(convert)
            } catch(exception: ConverterInputException) {
                model["output"] = exception.message
            }
        } ?: run {
            model["output"] = config.getString("language")
        }


        return "main"
    }


}