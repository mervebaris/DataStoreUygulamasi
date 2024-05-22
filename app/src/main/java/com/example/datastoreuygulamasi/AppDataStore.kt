package com.example.datastoreuygulamasi

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


//var context: Context kayıt işlemi, okuma, silme işlemi yapabileceğiz
class AppDataStore(var context: Context) {

    //kayıt yapacağımız alanı belirleyeceğiz
    val Context.ds : DataStore<Preferences> by preferencesDataStore("bilgiler")

    //key tanımlıyoruz hangi key ile kayıt edeceğiz bunları tanımlıyoruz
    //Sınıf ismiylede bu keylere direkt erişmek isteyebiliriz diye companion object dedik
    companion object{
        val AD_KEY = stringPreferencesKey("AD")
        val YAS_KEY = intPreferencesKey("YAS")
        val BOY_KEY = doublePreferencesKey("BOY")
        val BEKAR_MI_KEY = booleanPreferencesKey("BEKAR_MI")
        val ARKADAS_LISTE_KEY = stringSetPreferencesKey("ARKADAS_LISTE")
        val SAYAC_KEY = intPreferencesKey("SAYAC")
    }


    //kayıt işlemi olduğundan asenkron şekilde çalışmamız lazım coroutine metotlarıyla çalışacağımızdan suspend fun dedik
    suspend fun kayitAd(ad:String){
        context.ds.edit {//ds kullandık çünkü oraya kayıt etmek istiyoruz
            it[AD_KEY] = ad  //buraya dışarıdan veri olarak aldığımız ad'ı kaydet diyoruz
        }
    }


    //okuma işlemi yapıyoruz dışarıdan veri almıyoruz. Okuyacağım ve sonucu bu fonk. kullanan kişiye ileteceğiz sana :String türünde veri gönderiyorum diyoruz p nesnesi yukarıdaki bu yapıyı temsil ediyor ----> val Context.ds : DataStore<Preferences> by preferencesDataStore("bilgiler")
    suspend fun okuAd():String{
        val p = context.ds.data.first()
        return p[AD_KEY]?:"isim yok"
    }



    //silme işlemi yaptık
    suspend fun silAd(){
        context.ds.edit {
            it.remove(AD_KEY)
        }
    }


    suspend fun kayitYas(yas:Int){
        context.ds.edit {//ds kullandık çünkü oraya kayıt etmek istiyoruz
            it[YAS_KEY] = yas  //buraya dışarıdan veri olarak aldığımız yas'ı kaydet diyoruz
        }
    }

    suspend fun okuYas():Int{
        val p = context.ds.data.first()
        return p[YAS_KEY]?: 0
    }

    suspend fun kayitBoy(boy:Double){
        context.ds.edit {//ds kullandık çünkü oraya kayıt etmek istiyoruz
            it[BOY_KEY] = boy  //buraya dışarıdan veri olarak aldığımız boy'u kaydet diyoruz
        }
    }

    suspend fun okuBoy(): Double {
        val p = context.ds.data.first()
        return p[BOY_KEY]?:0.0
    }

    suspend fun kayitBekarMi(bekarMi:Boolean){
        context.ds.edit {//ds kullandık çünkü oraya kayıt etmek istiyoruz
            it[BEKAR_MI_KEY] = bekarMi  //buraya dışarıdan veri olarak aldığımız bekarMi kaydet diyoruz
        }
    }

    suspend fun okuBekarMi(): Boolean {
        val p = context.ds.data.first()
        return p[BEKAR_MI_KEY]?: false
    }

    suspend fun kayitArkadasListe(liste:Set<String>){
        context.ds.edit {//ds kullandık çünkü oraya kayıt etmek istiyoruz
            it[ARKADAS_LISTE_KEY] = liste  //buraya dışarıdan veri olarak aldığımız liste'yi kaydet diyoruz
        }
    }

    suspend fun okuArkadasListe(): Set<String>?{ //? ile nullable kontrolünü yapıyoruz
        val p = context.ds.data.first()
        return p[ARKADAS_LISTE_KEY]
    }

    suspend fun kayitSayac(sayac:Int){
        context.ds.edit {//ds kullandık çünkü oraya kayıt etmek istiyoruz
            it[SAYAC_KEY] = sayac  //buraya dışarıdan veri olarak aldığımız sayaç'ı kaydet diyoruz
        }
    }

    suspend fun okuSayac():Int{
        val p = context.ds.data.first()
        return p[SAYAC_KEY]?: 0
    }




}