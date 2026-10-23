package com.example.kartuprofildigital

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0D0F12)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileCardDarkF1()
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileCardDarkF1() {
    val context = LocalContext.current
    val scrollState = rememberScrollState() // State untuk mengontrol scroll vertical
    val coroutineScope = rememberCoroutineScope()
    var bioPositionPx by remember { mutableFloatStateOf(0f) }
    var isScrollEnabled by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(565.dp) // Mengatur tinggi tetap kartu agar konten di dalamnya bisa di-scroll
            .padding(horizontal = 24.dp, vertical = 16.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF12161F)),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState,
                    enabled = isScrollEnabled
                )

        ) {
            // 1. BANNER HEADER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "F1 Background Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 2. AVATAR OVERLAP + STATUS BADGE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_profil_update),
                    contentDescription = "F1 Driver Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(y = (-40).dp)
                        .size(85.dp)
                        .clip(CircleShape)
                        .border(4.dp, Color(0xFF12161F), CircleShape)
                )

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0x2628C751),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4D28C751)),
                    modifier = Modifier.offset(y = (-10).dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF28C751))
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "#1 Classement | 340 PTS",
                            fontSize = 12.sp,
                            color = Color(0xFF28C751),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // 3. KONTEN PROFILE & STATISTIK
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Kimi Antonelli",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "F1 Driver | Mercedes AMG Petronas",
                            fontSize = 13.sp,
                            color = Color(0xFF8A8F9D)
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = "More Options",
                        tint = Color(0xFF8A8F9D)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // F1 Stats Grid
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0x0DFFFFFF))
                        .padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(label = "RACE WIN", value = "14")
                    StatItem(label = "PODIUM", value = "38")
                    StatItem(label = "POLE POS", value = "21")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // About Brief
                Text(
                    text = "About",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Competing at the highest level of motorsport. Pushing limits on every corner.",
                    fontSize = 13.sp,
                    color = Color(0xFFA0A5B5),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 📍 BARIS TOMBOL GET IN TOUCH
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            isScrollEnabled = true

                            coroutineScope.launch {
                                scrollState.animateScrollTo(bioPositionPx.toInt())
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
                    ) {
                        Text(
                            text = "Get in touch",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    IconButton(
                        onClick = {
                            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ArifMesi"))
                            context.startActivity(githubIntent)
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0x1AFFFFFF), CircleShape)
                            .border(1.dp, Color(0x1AFFFFFF), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PersonAdd,
                            contentDescription = "Github",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))


                // ... Di dalam komponen Text "Full Biography" ...
                Text(
                    text = "Full Biography & Career Highlights",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        // 📍 Menyimpan posisi koordinat Y dari judul Biography
                        bioPositionPx = coordinates.positionInParent().y
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Arif Mesi memulai kariernya di dunia balap dari kejuaraan gokart internasional sejak usia muda sebelum menembus Formula 1 bersama tim Scuderia Performance.\n\nDikenal dengan gaya membalap yang agresif namun presisi di sirkuit basah, ia telah mengantongi 14 kemenangan balapan, 38 kali naik podium, serta 21 pole position sepanjang kariernya.\n\nSelain fokus di lintasan balap, Arif juga aktif dalam pengembangan aerodinamis mobil balap serta kampanye keselamatan berkendara di tingkat global.",
                    fontSize = 13.sp,
                    color = Color(0xFFA0A5B5),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                //Dokumentasi
                Text(
                    text = "Knows the Limit",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.documentation_1),
                        contentDescription = "Career Documentation 1",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.documentation_2),
                        contentDescription = "Career Documentation 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.documentation_3),
                        contentDescription = "Career Documentation 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.documentation_4),
                        contentDescription = "Career Documentation 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.documentation_4),
                        contentDescription = "Career Documentation 1",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.documentation_3),
                        contentDescription = "Career Documentation 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.documentation_2),
                        contentDescription = "Career Documentation 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Image(
                        painter = painterResource(id = R.drawable.documentation_1),
                        contentDescription = "Career Documentation 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color(0xFF8A8F9D),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}