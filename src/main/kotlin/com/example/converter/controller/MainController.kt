package com.example.converter.controller


import com.example.converter.algorithm.Converter
import com.example.converter.algorithm.ConverterInputException
import com.example.converter.db.ConverterMessage
import com.example.converter.db.User
import com.example.converter.repos.ConverterMessageRepo
import com.google.gson.Gson
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Controller
class MainController(val converters: List<Converter>, private val converterMessageRepo: ConverterMessageRepo ) {

    private val config = ResourceBundle.getBundle("messages")
    private var converterMap : Map<String, Converter> = converters.associateBy {it.getLanguage()};

    @GetMapping("/")
    fun start(model: Map<String, Any>): String {
        return "main"
    }

    @GetMapping("/main")
    fun main(model: MutableMap<String, Any>): String {
        return "main";
    }

    @PostMapping("/addToHistory")
    @ResponseBody
    fun convert(@RequestParam inputText: String, @RequestParam outputText: String,converterMessage: ConverterMessage, ) {
        converterMessage.user = SecurityContextHolder.getContext().authentication.principal as User
        converterMessage.inputString = inputText
        converterMessage.convertedString = outputText

        converterMessageRepo.save(converterMessage)
    }

    @PostMapping("/convert")
    @ResponseBody
    fun addToHistory(@RequestParam convert: String): String {
        val locale = LocaleContextHolder.getLocale()
        lateinit var output: String

        converterMap[locale.language]?.let {
            output = try {
                it.gettingData(convert)
            } catch(exception: ConverterInputException) {
                exception.message
            }
        } ?: run {
            output = config.getString("error")
        }
        return output
    }

    @GetMapping("/history")
    fun history(): String
    {
        return "history"
    }

    @ResponseBody
    @GetMapping("/historyTable")
    fun historyTable(model: MutableMap<String, Any>): String
    {
        val current = SecurityContextHolder.getContext().authentication.principal as User
        return Gson().toJson(converterMessageRepo?.findByUser(current))
    }

    @ResponseBody
    @PostMapping("/deleteFromHistory")
    fun removeFromHistory(@RequestParam id: String)
    {
        converterMessageRepo.deleteById(id.toLong())
    }




}