//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 2 October 2021

package com.example.parliamentmembersapp

import android.widget.RatingBar
import com.example.parliamentmembersapp.database.MemberOfParliament
import com.example.parliamentmembersapp.database.Rating
import java.util.*

// Format item.first and item.last into full name for displaying
fun setFullName(item: MemberOfParliament): String {
    return "${item.first} ${item.last}"
}

// Calculating age of a member
fun setAge(item: MemberOfParliament): String {
    return "Age: ${(Calendar.getInstance().get(Calendar.YEAR) - item.bornYear)}"
}

// Translate party acronym to full text
fun formatParties(item: MemberOfParliament): String {
    val formattedParty = when (item.party) {
        "ps" -> "Perussuomalaiset"
        "kesk" -> "Suomen Keskusta"
        "kok" -> "Kansallinen Kokoomus"
        "liik" -> "Liike Nyt"
        "r" -> "Ruotsalainen Kansanpuolue"
        "sd" -> "Suomen Sosialidemokraattinen puolue"
        "vas" -> "Vasemmistoliitto"
        "vihr" -> "Vihreä liitto"
        "kd" -> "Suomen Kristillisdemokraatit"
        else -> "No party"
    }
    return formattedParty
}

// calculating average
fun calculateAvg(list: List<Rating>): Float {
    var sum = 0.0F
    list.forEach { sum += it.rating }

    return sum / (list.size)
}

// translate rating point to stars
fun formatDisplayOfRatingBar(average : Float, rating_bar : RatingBar) {
    rating_bar.numStars = 5
    rating_bar.rating = average
}






