package com.example.keyoftravel

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MenuActivity : AppCompatActivity() {
    lateinit var dp_text: EditText
    private var _year = 2024
    private var _month = 0
    private var _day = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val createEventButton: View = findViewById(R.id.btn_creat)

        createEventButton.setOnClickListener {
            // Вызываем функцию создания события
            CreatEvent()
        }

        val spinnerKuda: Spinner = findViewById(R.id.btn_kuda)
        val dataKuda = arrayOf("Куда?", "Москва", "Нижний Новгород", "Казань", "Санкт-Петербург", "Бор")
        val adapterKuda = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dataKuda
        )
        adapterKuda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKuda.adapter = adapterKuda
        val myView = findViewById<WebView>(R.id.webView)
        myView.visibility = WebView.GONE
        val selectedItemId = spinnerKuda.selectedItemPosition
        spinnerKuda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (id == 1L) {
                    myView.settings.javaScriptEnabled = true
                    myView.loadUrl("https://yandex.ru/map-widget/v1/?um=constructor%3A9d903b563a67849bfd1f4070d8b2c0c2345cd146610b0ac2e52b924fbe277d21&amp;source=constructor")
                    myView.visibility = WebView.VISIBLE
                }
                else if (id == 2L) {
                    myView.settings.javaScriptEnabled = true
                    myView.loadUrl("https://yandex.ru/map-widget/v1/?um=constructor%3A0a30dc248f1c27140c786e72b4595cab1cab6b36bdb7fce037973acf6784caa3&amp;source=constructor")
                    myView.visibility = WebView.VISIBLE
                }
                else if (id == 3L) {
                    myView.settings.javaScriptEnabled = true
                    myView.loadUrl("https://yandex.ru/map-widget/v1/?um=constructor%3A07cbb951e5f7c527373a4c65da223a354538ad2a4b720a3823b6919339bef5f6&amp;source=constructor")
                    myView.visibility = WebView.VISIBLE
                }
                else if (id == 4L) {
                    myView.settings.javaScriptEnabled = true
                    myView.loadUrl("https://yandex.ru/map-widget/v1/?um=constructor%3A081f84ac4bb459b69dc61a6035b32763e7ec8580c76ab5852e0464eae2a688ba&amp;source=constructo")
                    myView.visibility = WebView.VISIBLE
                }
                else if (id == 5L) {
                    myView.settings.javaScriptEnabled = true
                    myView.loadUrl("https://yandex.ru/map-widget/v1/?um=constructor%3A7b446f1622c565fd779e930de927dada222f950c0fdb7bea690d1a6a6eba1461&amp;source=constructor")
                    myView.visibility = WebView.VISIBLE
                }
                else if (id == 0L) {
                    myView.visibility = WebView.GONE
                }

            }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

        }

        // Spinner для вида транспорта
        val spinnerVidTrs: Spinner = findViewById(R.id.btn_vidtrs)
        val dataVidTrs = arrayOf("На чём?", "Машина", "Самолет", "Поезд")
        val adapterVidTrs = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dataVidTrs
        )
        adapterVidTrs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerVidTrs.adapter = adapterVidTrs

        val veshi = findViewById<TextView>(R.id.TV1)
        veshi.visibility = TextView.GONE
        val veshi1 = findViewById<TextView>(R.id.TV2)
        veshi1.visibility = TextView.GONE
        val veshi2 = findViewById<TextView>(R.id.TV3)
        veshi2.visibility = TextView.GONE

        val selectedItemId2 = spinnerVidTrs.selectedItemPosition
        spinnerVidTrs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (id == 1L) {
                    veshi.visibility = TextView.VISIBLE
                    veshi1.visibility = TextView.GONE
                    veshi2.visibility = TextView.GONE
                } else if (id == 2L) {
                    veshi1.visibility = TextView.VISIBLE
                    veshi.visibility = TextView.GONE
                    veshi2.visibility = TextView.GONE
                } else if (id == 3L) {
                    veshi2.visibility = TextView.VISIBLE
                    veshi.visibility = TextView.GONE
                    veshi1.visibility = TextView.GONE
                } else if (id == 0L) {
                    veshi.visibility = TextView.GONE
                    veshi1.visibility = TextView.GONE
                    veshi2.visibility = TextView.GONE
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        dp_text = findViewById(R.id.editDP_text_create)
        dp_text.setOnClickListener {
            onClickChangeDB()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClickChangeDB() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.datepicker)

        val _dp: DatePicker = dialog.findViewById(R.id.date_picker)

        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        _dp.init(_year, _month, _day, DatePicker.OnDateChangedListener { picker, year, month, day ->
            val _thisDate = LocalDate.of(year, month + 1, day)
            _year = year
            _day = day
            _month = month
            dp_text.setText("" + _thisDate.format(formatter))
        })

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.AnimNav
        dialog.getWindow()?.setGravity(Gravity.BOTTOM);
    }

// Функция для создания события в календаре на заданный день
    fun CreatEvent() {
    // Устанавливаем дату и время начала события
    val startMillis: Long = Calendar.getInstance().run {
        set(2023, Calendar.JANUARY, 1, 10, 0)
        timeInMillis
    }

    // Устанавливаем дату и время окончания события
    val endMillis: Long = Calendar.getInstance().run {
        set(2023, Calendar.JANUARY, 1, 12, 0)
        timeInMillis
    }

    // Создаем намерение (Intent) для добавления события в календарь
    val intent = Intent(Intent.ACTION_INSERT)
        .setData(Events.CONTENT_URI)
        .putExtra(Events.TITLE, "Важное событие")
        .putExtra(Events.DESCRIPTION, "Описание события")
        .putExtra(Events.EVENT_LOCATION, "Место проведения")
        .putExtra(Events.ALL_DAY, false) // Событие не на весь день
        .putExtra(Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)

    try {
        // Пытаемся запустить приложение для добавления события в календарь
        startActivity(intent)
    } catch (e: Exception) {
        // Если произошла ошибка, выводим уведомление
        Toast.makeText(this, "Ошибка при открытии календаря", Toast.LENGTH_SHORT).show()
    }
}
}