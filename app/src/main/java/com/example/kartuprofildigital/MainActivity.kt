package com.example.kartuprofildigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF2F3F5) // Warna background luar yang lembut
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileCardLeft()
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileCardLeft() {
    var context = LocalContext.current //buat buka link
    var isFollowing by remember {mutableStateOf(false)} //state nyimpen status follow
    var dynamicFollower by remember {mutableStateOf(312)} // state jumlah followers

    Card(
        modifier = Modifier
            .width(460.dp)
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)  // dihapus
        ) {
            // 1. FOTO PROFIL
            Image(
                painter = painterResource(id = R.drawable.foto_profil),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(500.dp)
//                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(24.dp))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp) // 📍 Padding khusus untuk Teks & Tombol saja
            ) {

                // 2. NAMA + BADGE VERIFIKASI
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Text(
                        text = "Arif Mesi",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111111)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Badge Verifikasi Hijau

                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "Verified Badge",
                        tint = Color(0xFF28C751), // Warna hijau centang (atau Color.Unspecified jika file xml sudah berwarna)
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 3. DESKRIPSI / BIO
                Text(
                    text = "Product Designer who focuses on simplicity & usability.",
                    fontSize = 14.sp,
                    color = Color(0xFF757575),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            // 📩 INTENT EMAIL DIJALANKAN SAAT DIKLIK
                            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:dhotaaa@gmail.com")
                            }
                            context.startActivity(emailIntent)
                        }
                        .padding(vertical = 4.dp, horizontal = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mail),
                        contentDescription = "Email",
                        tint = Color(0xFF1A73E8),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "contact me on email",
                        fontSize = 14.sp,
                        color = Color(0xFF1A73E8),
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                // 4. STATISTIK & TOMBOL FOLLOW
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ikon & Jumlah Pengikut / Postingan
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Item 1: User / Followers
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Followers",
                                tint = Color(0xFF757575),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = dynamicFollower.toString(),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF222222)
                            )
                        }

                        // Item 2: Projects / Posts
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ArifMesi"))
                                context.startActivity(intent)
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.github),
                                contentDescription = "GitHub",
                                tint = Color(0xFF111111),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "48",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF222222)
                            )
                        }
                    }

                    // Tombol "Follow +"
                    Button(
                        onClick = {
                            if (isFollowing) {
                                dynamicFollower--
                            } else {
                                dynamicFollower++
                            }
                            isFollowing = !isFollowing
                                  },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFollowing) Color(0xFF1A73E8) else Color(0xFFF1F3F5),
                            contentColor = if (isFollowing) Color.White else Color(0xFF111111)
//
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = if (isFollowing) "Following" else "Follow",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}