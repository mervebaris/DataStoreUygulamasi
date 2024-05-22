package com.example.datastoreuygulamasi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.datastoreuygulamasi.ui.theme.DataStoreUygulamasiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    val context = LocalContext.current
    val ads = AppDataStore(context)
    val sayac = remember { mutableStateOf(0) }
    
    LaunchedEffect(key1 = true) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            //KAYITLAR
            ads.kayitAd("ahmet")
            ads.kayitYas(23)
            ads.kayitBoy(1.78)
            ads.kayitBekarMi(true)

            val liste = HashSet<String>()
            liste.add("merve")
            liste.add("pınar")

            ads.kayitArkadasListe(liste)

            //OKUMA İŞLEMLERİ
            val gelenAd = ads.okuAd()
            val gelenYas = ads.okuYas()
            val gelenBoy = ads.okuBoy()
            val gelenBekarMi = ads.okuBekarMi()
            val gelenListe = ads.okuArkadasListe()

            Log.e("Gelen Ad", gelenAd)
            Log.e("Gelen Yas", gelenYas.toString())
            Log.e("Gelen Boy", gelenBoy.toString())
            Log.e("Gelen Bekar Mı", gelenBekarMi.toString())

            for (a in gelenListe!!){
                Log.e("Gelen Arkadaş", a)
            }

            //SAYAC UYGULAMASI
            var gelenSayac =  ads.okuSayac()
            sayac.value = ++gelenSayac
            ads.kayitSayac(gelenSayac)
        }
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Açılış Sayısı: ${sayac.value}", fontSize = 36.sp)

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DataStoreUygulamasiTheme {
        Sayfa()
    }
}