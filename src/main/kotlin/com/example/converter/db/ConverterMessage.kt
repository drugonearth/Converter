package com.example.converter.db

import javax.persistence.*


@Entity
data class ConverterMessage(@Id
                            @GeneratedValue(strategy = GenerationType.AUTO)
                            val id: Long = 0L,
                            @ManyToOne(fetch = FetchType.EAGER)
                            @JoinColumn(name = "user_id")
                            var user: User?,
                            var inputString: String?,
                            var convertedString: String?) {

}
