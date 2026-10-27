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
import androidx.compose.material.icons.filled.Menu  //fitur sidebar
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState

// 🏎️ DATA CLASS DRIVER F1
data class F1Driver(
    val rank: Int,
    val points: Int,
    val name: String,
    val team: String,
    val raceWins: String,
    val podiums: String,
    val polePositions: String,
    val profileImg: Int,
    val backgroundImg: Int,
    val logoImg: Int,
    val accentColor: Color,
    val bio: String
)

// DATA PEMBALAP (URUTAN KLASEMEN)
val kimiAntonelli = F1Driver(
    rank = 1,
    points = 340,
    name = "Kimi Antonelli",
    team = "F1 Driver | Mercedes AMG Petronas",
    raceWins = "9",
    podiums = "15",
    polePositions = "7",
    profileImg = R.drawable.foto_profil_update,
    backgroundImg = R.drawable.background,
    logoImg = R.drawable.mercedes_logo,
    accentColor = Color(0xFF27F4D2), // Toska Mercedes
    bio = "Kimi Antonelli memulai kariernya di dunia balap dari kejuaraan gokart internasional sebelum menembus Formula 1 bersama tim Mercedes AMG Petronas."
)

val lewisHamilton = F1Driver(
    rank = 2,
    points = 315,
    name = "Lewis Hamilton",
    team = "F1 Driver | Scuderia Ferrari HP",
    raceWins = "105",
    podiums = "201",
    polePositions = "104",
    profileImg = R.drawable.foto_profil_lewis, // Ganti foto Lewis
    backgroundImg = R.drawable.background_lewis_update, // Ganti background Ferrari
    logoImg = R.drawable.ferrari_logo, // Pastikan ferrari_logo.xml sudah di-import
    accentColor = Color(0xFFE80020), // Merah Ferrari
    bio = "Sir Lewis Hamilton adalah Juara Dunia F1 7 kali yang kini membela Scuderia Ferrari. Memegang rekor kemenangan, podium, dan pole position terbanyak dalam sejarah Formula 1."
)

val charlesLeclerc = F1Driver(
    rank = 3,
    points = 295,
    name = "Charles Leclerc",
    team = "F1 Driver | Scuderia Ferrari HP",
    raceWins = "9",
    podiums = "55",
    polePositions = "27",
    profileImg = R.drawable.foto_profil_charles, // Ganti foto Lewis
    backgroundImg = R.drawable.background_charles, // Ganti background Ferrari
    logoImg = R.drawable.ferrari_logo, // Pastikan ferrari_logo.xml sudah di-import
    accentColor = Color(0xFFE80020), // Merah Ferrari
    bio = ""
    )

val maxVerstappen = F1Driver(
    rank = 4,
    points = 290,
    name = "Max Verstappen",
    team = "F1 Driver | Red Bull Racing",
    raceWins = "73",
    podiums = "136",
    polePositions = "50",
    profileImg = R.drawable.foto_profil_max,
    backgroundImg = R.drawable.background_max,
    logoImg = R.drawable.redbullracing_logo,
    accentColor = Color(0xFF3F51B5),
    bio = ""
)

val landoNorris = F1Driver(
    rank = 5,
    points = 275,
    name = "Lando Norris",
    team = "F1 Driver | Mclaren",
    raceWins = "13",
    podiums = "49",
    polePositions = "19",
    profileImg = R.drawable.foto_profil_lando,
    backgroundImg = R.drawable.background_lando,
    logoImg = R.drawable.mclaren_logo,
    accentColor = Color(0xFFFF5722),
    bio = ""
)

val driverList = listOf(kimiAntonelli, lewisHamilton, charlesLeclerc, maxVerstappen, landoNorris)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0D0F12)
                ) {
                    F1DriverDrawerScreen()
                }
            }
        }
    }
}

@Composable
fun F1DriverDrawerScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var currentDriver by remember { mutableStateOf(lewisHamilton) }

    // 📍 SIDEBAR NAVIGATION DRAWER (Slide dari Kiri)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFF12161F),
                modifier = Modifier.width(280.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Header Sidebar
                Text(
                    text = "F1 2026 Standings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )

                Divider(color = Color(0x1AFFFFFF), modifier = Modifier.padding(vertical = 12.dp))

                // List Pembalap Berdasarkan Klasemen
                driverList.forEach { driver ->
                    NavigationDrawerItem(
                        label = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "${driver.rank}. ${driver.name}",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "${driver.points} PTS",
                                        color = driver.accentColor,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                Icon(
                                    painter = painterResource(id = driver.logoImg),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        selected = currentDriver.name == driver.name,
                        onClick = {
                            currentDriver = driver
                            coroutineScope.launch { drawerState.close() }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = driver.accentColor.copy(alpha = 0.15f),
                            unselectedContainerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }
        }
    ) {
        // Konten Utama Kartu Profil
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProfileCardDarkF1(
                driver = currentDriver,
                onOpenDrawer = {
                    coroutineScope.launch { drawerState.open() }
                }
            )
        }
    }
}

@Composable
fun ProfileCardDarkF1(
    driver: F1Driver,
    onOpenDrawer: () -> Unit
) {
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
                    painter = painterResource(id = driver.backgroundImg),
                    contentDescription = "F1 Background Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = onOpenDrawer,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(Color(0x80000000), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Open Standings Sidebar",
                        tint = Color.White
                    )
                }
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
                    painter = painterResource(id = driver.profileImg),
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
                    color = driver.accentColor.copy(alpha = 0.15f), // ⚡ Warna aksen driver
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
                            text = "#${driver.rank} Classement | ${driver.points} PTS", // ⚡ Dinamis
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = driver.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.width(8.dp)) // Jarak antara nama dan logo

                            Icon(
                                painter = painterResource(id = driver.logoImg),
                                contentDescription = "Mercedes Team Logo",
                                tint = Color.Unspecified, // ⚡ PENTING: Supaya warna asli SVG tidak tertutup/terganti tint
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = driver.team,
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
                    StatItem(label = "RACE WIN", driver.raceWins)
                    StatItem(label = "PODIUM", driver.podiums)
                    StatItem(label = "POLE POS", driver.polePositions)
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
                                val customOffsetPx = 40 // Ubah angka ini sesuai kebutuhan presisi kamu (dalam pixel)
                                val targetPosition = (bioPositionPx.toInt() - customOffsetPx).coerceAtLeast(0)

                                scrollState.animateScrollTo(targetPosition)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = driver.accentColor)
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
                    color = Color.White

                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = driver.bio,
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
                    color = Color.White,
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        // 📍 Menyimpan posisi koordinat Y dari judul Biography
                        bioPositionPx = coordinates.positionInParent().y
                    }
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