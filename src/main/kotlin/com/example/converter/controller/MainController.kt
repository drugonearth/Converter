package com.example.converter.controller


import com.example.converter.algorithm.Converter
import com.example.converter.algorithm.ConverterInputException
import com.example.converter.db.ConverterMessage
import com.example.converter.db.User
import com.example.converter.repos.ConverterMessageRepo
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @PostMapping("/main")
    fun convert(@RequestParam convert: String, converterMessage: ConverterMessage,
                model: MutableMap<String, Any>): String {
        val locale = LocaleContextHolder.getLocale()

        converterMap[locale.language]?.let {
            try {
                model["output"] = it.gettingData(convert)
            } catch(exception: ConverterInputException) {
                model["output"] = exception.message
            }
        } ?: run {
            model["output"] = config.getString("error")
        }


        converterMessage.user = SecurityContextHolder.getContext().authentication.principal as User
        converterMessage.inputString = convert
        converterMessage.convertedString = model["output"] as String?
        converterMessageRepo.save(converterMessage)

        return "main"
    }

    @GetMapping("/history")
    fun history(model: MutableMap<String, Any>)
    {
        val current = SecurityContextHolder.getContext().authentication.principal as User
        model["historyList"] = converterMessageRepo?.findByUser(current) as Iterable<ConverterMessage>
    }


}