//Name: Huynh Thanh Hang
//Student number: 2012185
//File created on 2 October 2021

package com.example.parliamentmembersapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.parliamentmembersapp.database.MemberOfParliament

// This is the binding adapter for displaying the images of each party on the AllMembersFragment
@BindingAdapter("partyImage")
fun ImageView.setPartyImage(item: MemberOfParliament) {
    item?.let {
        setImageResource(when (item.party) {
            "ps" -> R.drawable.ps
            "kesk" -> R.drawable.kes
            "kok" -> R.drawable.kok
            "liik" -> R.drawable.liik
            "r" -> R.drawable.rkp
            "sd" -> R.drawable.sdp
            "vas" -> R.drawable.vas
            "vihr" -> R.drawable.vihr
            "kd" -> R.drawable.kd
            else -> R.drawable.political_party
        })
    }
}



