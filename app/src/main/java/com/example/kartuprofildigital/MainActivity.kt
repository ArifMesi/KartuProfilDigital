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
    Card(
        modifier = Modifier
            .width(320.dp)
            .wrapContentHeight()
            .padding(16.dp),
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
                    .height(280.dp)
//                    .align(Alignment.CenterHorizontally)
                    .clip(
                        RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp,
                            bottomStart = 24.dp,
                            bottomEnd = 24.dp
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // 📍 Padding khusus untuk Teks & Tombol saja
            ) {

                // 2. NAMA + BADGE VERIFIKASI
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Arif Mesi",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111111)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Badge Verifikasi Hijau
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF28C751)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                            Text(text = "👤", fontSize = 14.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "312",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF222222)
                            )
                        }

                        // Item 2: Projects / Posts
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🖼️", fontSize = 14.sp, color = Color.Gray)
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
                        onClick = { /* Aksi saat follow diklik */ },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1F3F5),
                            contentColor = Color(0xFF111111)
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Follow  +",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}