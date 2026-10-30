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
    bio = "Andrea Kimi Antonelli lahir 25 Agustus 2006, yang lebih dikenal sebagai Kimi Antonelli, adalah seorang pembalap Italia yang saat ini bersaing di Kejuaraan Dunia Formula Satu dengan tim Mercedes. Antonelli telah memenangkan beberapa gelar di kategori mobil tunggal sepanjang kariernya, termasuk Kejuaraan F4 Italia dan Kejuaraan Formula 4 ADAC bersama Prema, serta Kejuaraan Formula Regional Timur Tengah 2023 dengan Mumbai Falcons dan Kejuaraan Formula Regional Eropa 2023 dengan Prema. Sejak tahun 2019, dia juga telah menjadi bagian dari Mercedes Junior Team."
)

val georgeRussell = F1Driver(
    rank = 4,
    points = 340,
    name = "George Russell",
    team = "F1 Driver | Mercedes AMG Petronas",
    raceWins = "8",
    podiums = "32",
    polePositions = "13",
    profileImg = R.drawable.foto_profil_russell,
    backgroundImg = R.drawable.background_russell,
    logoImg = R.drawable.mercedes_logo,
    accentColor = Color(0xFF27F4D2), // Toska Mercedes
    bio = "George William Russell lahir 15 Februari 1998, adalah seorang pembalap mobil profesional Inggris yang saat ini membalap di ajang Formula Satu dengan Mercedes. Pembalap dengan nomor 63 ini merupakan seorang juara Formula 2 musim 2018 dan juara Seri GP3 musim 2017. Setelah memenangkan gelar Formula 2, ia membalap untuk Williams pada 2019, membuat debutnya di F1 pada Grand Prix Australia 2019 bersama Robert Kubica. Russell menggantikan Lewis Hamilton pada Grand Prix Sakhir 2020. Ia memperoleh podium pertamanya di Grand Prix Belgia 2021 bersama Williams. Setelah 3 tahun dengan Williams, Russell bergabung dengan Mercedes pada musim 2022, menggantikan Valtteri Bottas."
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
    bio = "Sir Lewis Carl Davidson Hamilton MBE (lahir 7 Januari 1985) adalah seorang pembalap mobil profesional dari Britania Raya, yang pada saat ini membalap untuk Scuderia Ferrari di dalam ajang Formula Satu. Sekarang, dia memiliki gelar dunia terbanyak, yakni 7 (dipegang bersama dengan Michael Schumacher), dan juga kemenangan (105), posisi terdepan (104), dan finis podium terbanyak (203).\n" +
            "\n" +
            "Lahir dan dibesarkan di Stevenage, Hertfordshire, Inggris, Hamilton bergabung di program pembalap muda McLaren pada tahun 1998. Ini berujung pada Hamilton yang membalap untuk tim McLaren pada musim 2007 sampai dengan 2012, dan membuat Hamilton menjadi pembalap berkulit hitam yang pertama, dan sejauh ini satu-satunya yang membalap di seri tersebut. Di debutnya, dia finis di posisi kedua di belakang Kimi Räikkönen dengan jarak satu poin. Musim berikutnya, dia berhasil memenangkan gelar juara dunia pertamanya dengan kontroversial—dengan mendahului di putaran terakhir di balapan terakhir musim—dan pada waktu itu menjadi juara dunia Formula Satu yang termuda dalam sejarah. Setelah 6 tahun lagi bersama dengan tim McLaren, Hamilton bergabung bersama dengan tim Mercedes pada musim 2013. Sejak musim 2025, Hamilton membalap untuk tim Scuderia Ferrari dan bertandem dengan Charles Leclerc.\n" +
            "\n" + "Hamilton menikmati kesuksesan besar pada era mesin turbo-hibrida yang dimulai dari musim 2014, di mana dia berhasil memenangkan 6 gelar juara dunia. 2 gelar juara dunia secara berturut-turut datang pada musim 2014 dan 2015 dengan persaingan ketat dengan rekan setimnya, yaitu Nico Rosberg. Setelah Rosberg pensiun, pembalap Ferrari, yaitu Sebastian Vettel, menjadi saingan terdekat Hamilton dalam dua pertarungan kejuaraan dunia yang intens, dan Hamilton dua kali membalikkan defisit poin pada pertengahan musim untuk mengklaim gelar juara dunia secara berturut-turut lagi pada musim 2017 dan 2018. 2 gelar juara dunia lagi datang pada musim 2019 dan 2020, menyamai jumlah gelar juara dunia Michael Schumacher."
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
    bio = "Charles Marc Hervé Percival Leclerc lahir 16 Oktober 1997, merupakan seorang pembalap mobil profesional dari Monako yang saat ini membalap di arena Formula Satu untuk tim Scuderia Ferrari. Ia merupakan juara Seri GP3 musim 2016 dan Formula 2 musim 2017. Leclerc naik kelas ke ajang Formula Satu di musim 2018 dengan bergabung bersama tim Sauber. Debutnya di ajang F1 dimulai di Australia 2018. Selama musim 2018 berjalan, Leclerc meraih 39 poin dan mengantar tim Sauber bangkit dari sebelumnya posisi dasar klasemen di 2017 menjadi naik ke posisi 8. Atas prestasinya tersebut, Leclerc kemudian mendapat promosi naik kelas ke tim Ferrari pada tahun 2019 untuk menggantikan Kimi Räikkönen dan menjadi rekan setim Sebastian Vettel."
    )

val maxVerstappen = F1Driver(
    rank = 5,
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
    bio = "Max Emilian Verstappen lahir 30 September 1997, adalah seorang pembalap mobil profesional Belgia-Belanda, yang pada saat ini bersaing di bawah bendera Belanda di dalam ajang balap Formula Satu bersama dengan tim Red Bull Racing. Ia adalah putra dari mantan pembalap F1, yaitu Jos Verstappen.\n" +
            "\n" +
            "Di Grand Prix Australia 2015, dalam usia 17 tahun dan 166 hari, ia menjadi pembalap termuda yang berkompetisi di dalam ajang Formula Satu. Setelah menghabiskan musim 2015 bersama dengan tim Toro Rosso, ia memulai musim 2016 bersama dengan tim asal Italia ini sebelum dipromosikan ke tim induk Toro Rosso, yaitu Red Bull, sebagai pengganti Daniil Kvyat. Ia kemudian berhasil memenangkan Grand Prix Spanyol 2016 dalam lomba pertamanya untuk tim Red Bull pada usia 18 tahun, dan menjadi pemenang termuda sebuah Grand Prix, dan menjadi pemenang balapan F1 yang paling pertama di bawah bendera Belanda. Setelah berhasil memenangkan Grand Prix Abu Dhabi 2021, ia menjadi pembalap Belanda yang pertama yang berhasil memenangkan gelar kejuaraan dunia Formula Satu, dan merupakan juara dunia pembalap Formula Satu yang ke-34."
)

val isackHadjar = F1Driver(
    rank = 7,
    points = 278,
    name = "Isack Hadjar",
    team = "F1 Driver | Red Bull Racing",
    raceWins = "0",
    podiums = "3",
    polePositions = "0",
    profileImg = R.drawable.foto_profil_hadjar,
    backgroundImg = R.drawable.background_hadjar,
    logoImg = R.drawable.redbullracing_logo,
    accentColor = Color(0xFF3F51B5),
    bio = "Isack Alexandre Hadjar (lahir 28 September 2004) adalah seorang pembalap mobil profesional asal Prancis dan Aljazair, yang berlomba di ajang Formula Satu untuk tim Racing Bulls.\n" +
            "\n" +
            "Lahir dan dibesarkan di Paris dalam keluarga Aljazair, Hadjar mulai balapan go-kart kompetitif pada usia tujuh tahun. Setelah debut di balap mobil sport pada Ginetta Junior Winter Series, Hadjar melanjutkan ke formula junior pada tahun 2019. Ia finis di posisi ketiga pada Kejuaraan F4 Prancis 2020 bersama FFSA Academy, sebelum melanjutkan ke Kejuaraan Formula Regional Eropa pada tahun 2021, meraih beberapa kemenangan dan finis di posisi kelima pada musim debutnya. Ia kemudian mengikuti Formula Regional Asia dan Formula 3 FIA pada tahun 2022 bersama Hitech, meraih posisi ketiga di Formula Regional Asia dan mencatatkan beberapa kemenangan di keduanya. Hadjar naik ke Formula 2 FIA pada tahun 2023, finis sebagai runner-up di belakang Gabriel Bortoleto pada musim berikutnya bersama Campos.\n" +
            "\n" +
            "Sebagai anggota Red Bull Junior Team sejak 2022, Hadjar melakukan debut sesi latihan bebas di Formula Satu pada Grand Prix Kota Meksiko 2023. Ia berkompetisi pada musim 2025 bersama dengan Racing Bulls, memulai debutnya pada Grand Prix Australia 2025."
)

val oscarPiastri = F1Driver(
    rank = 6,
    points = 282,
    name = "Oscar Piastri",
    team = "F1 Driver | Mclaren",
    raceWins = "9",
    podiums = "28",
    polePositions = "6",
    profileImg = R.drawable.foto_profil_piastri,
    backgroundImg = R.drawable.background_piastri,
    logoImg = R.drawable.mclaren_logo,
    accentColor = Color(0xFFFF5722),
    bio = "Oscar Jack Piastri (lahir 6 April 2001) adalah seorang pembalap asal Australia yang berkompetisi pada ajang Formula Satu bersama dengan McLaren, setelah sebelumnya menjadi pembalap cadangan untuk Alpine F1 Team dan anggota akademi pembalap tim tersebut.\n" +
            "\n" +
            "Piastri memenangi Formula Renault Eurocup 2019 dengan R-ace GP, lalu memenangi Formula 3 musim 2020 dan Formula 2 musim 2020 dengan Prema Racing. Piastri adalah salah satu dari enam pembalap yang memenangi kejuaraan GP2/Formula 2 dalam musim pertama mereka."
)

val landoNorris = F1Driver(
    rank = 8,
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
    bio = "Lando Norris lahir 13 November 1999, merupakan seorang pembalap mobil profesional berkewarganegaraan Inggris-Belgia. Ia sekarang berkompetisi di Formula Satu bersama tim McLaren. Kariernya di ajang junior cukup gemilang dengan berhasil memenangi gelar juara MSA Formula championship, Toyota Racing Series, Eurocup Formula Renault 2.0 dan Formula Renault 2.0 Northern European Cup di periode 2015-2016. Ia pernah bergabung dengan McLaren Young Driver Programme.\n" +
            "\n" +
            "Debutnya dalam ajang Formula Satu dimulai pada Grand Prix Australia 2019. Sejak saat itu, ia meraih podium pertamanya saat finis ketiga pada Grand Prix Austria 2020, disusul kemudian posisi pole pertamanya pada Grand Prix Rusia 2021. Pada Grand Prix Miami 2024, Norris berhasil meraih kemenangan F1 pertamanya.\n" +
            "\n" +
            "Pada musim 2025 di Formula Satu (F1), Lando Norris berhasil meraih gelar juara dunia pembalap (World Drivers’ Championship) untuk pertama kalinya. Pembalap tim McLaren F1 Team ini memastikan gelar tersebut di seri penutup, 2025 Abu Dhabi Grand Prix — meskipun finis di posisi ketiga, itu sudah cukup untuk mengumpulkan total 423 poin, unggul 2 poin atas pesaingnya, Max Verstappen. Kemenangan ini mengakhiri dominasi Verstappen selama empat tahun berturut-turut dan menjadi kemenangan juara dunia pertama untuk McLaren sejak 2008, serta menandai pertama kalinya pembalap asal Inggris jadi juara dunia sejak 2020. Prestasi itu menghiasi musim di mana Norris sering tampil kompetitif, meskipun sempat menghadapi tantangan termasuk diskualifikasi di 2025 Las Vegas Grand Prix."
)

val driverList = listOf(kimiAntonelli, lewisHamilton, charlesLeclerc, georgeRussell, maxVerstappen, oscarPiastri, isackHadjar, landoNorris)

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
    var currentDriver by remember { mutableStateOf(kimiAntonelli) }

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