package com.example.myapplication
import android.content.Intent
import android.widget.ImageButton
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.util.Log


class QuestionnaireActivity : AppCompatActivity() {
    private val questions = listOf(
        "Ketika Anda menghadiri sebuah acara sosial, Anda cenderung",
        "Anda lebih mempercayai",
        "Saat memberikan kritik kepada teman, Anda lebih mengutamakan",
        "Rutinitas harian Anda cenderung",
        "Setelah minggu yang sangat sibuk, Anda memulihkan energi dengan cara",
        "Saat mempelajari sesuatu yang baru, Anda lebih suka",
        "Menurut Anda, mana yang lebih buruk",
        "Ketika mendekati sebuah tenggat waktu (deadline), Anda",
        "Di lingkungan kerja, Anda lebih dikenal sebagai orang yang",
        "Anda lebih tertarik pada percakapan tentang",
        "Ketika harus membuat keputusan yang sulit, Anda lebih mengandalkan",
        "Saat berlibur, Anda lebih menikmati",
        "Cara Anda memproses pikiran paling baik adalah dengan",
        "Anda melihat diri Anda sebagai orang yang lebih"

    )
    private val Aanswer = listOf(
        "Berkenalan dan berbicara dengan banyak orang, bahkan yang tidak Anda kenal.",
        "Pengalaman langsung dan fakta yang terbukti.",
        "Kejujuran dan objektivitas agar mereka bisa berkembang.",
        "Terjadwal dan terstruktur dengan baik.",
        "Pergi keluar dan melakukan aktivitas yang menyenangkan bersama teman-teman.",
        "Mendapatkan petunjuk praktis dan langkah-langkah yang jelas.",
        "Terlalu sentimental dan tidak logis.",
        "Telah menyelesaikan sebagian besar pekerjaan jauh-jauh hari.",
        "Mudah bergaul dan suka berdiskusi secara terbuka (brainstorming).",
        "Hal-hal yang nyata dan terjadi saat ini.",
        "Analisis pro dan kontra yang tidak memihak.",
        "Perjalanan dengan rencana dan jadwal yang sudah pasti.",
        "Mengucapkannya atau mendiskusikannya dengan orang lain.",
        "Realistis dan membumi."




        )
    private val Banswer = listOf(
        "Menghabiskan lebih banyak waktu dengan orang-orang yang sudah Anda kenal baik.",
        "Inspirasi, firasat, dan kemungkinan-kemungkinan tersembunyi.",
        "Menjaga perasaan mereka dan menyampaikannya dengan halus.",
        "Fleksibel dan berubah sesuai dengan suasana hati atau keadaan.",
        "Menikmati waktu tenang di rumah sendirian atau dengan beberapa orang terdekat.",
        "Memahami konsep dan teori dasarnya terlebih dahulu.",
        "Terlalu blak-blakan dan tidak memikirkan perasaan orang lain.",
        "Merasa lebih termotivasi dan produktif saat mendekati batas waktu.",
        "Lebih suka bekerja mandiri dan fokus pada tugas.",
        "Ide-ide filosofis dan masa depan.",
        "Nilai-nilai pribadi dan bagaimana dampaknya bagi orang-orang yang terlibat.",
        "Perjalanan yang spontan tanpa rencana yang kaku.",
        "Merenungkannya secara mendalam di dalam pikiran Anda sendiri.",
        "Imajinatif dan visioner."
        )

    var currentIndex = 0

    var Dimention1 = "E"//Extroversion vs Introversion (1,5,9,13)
    var Dimention2 = "S"//Sensing vs Intuition (2,6,10,14)
    var Dimention3= "T"// Thinking vs Feeling (3,7,11,15)
    var Dimention4 = "P"//Judging vs Perceiving (4,8,12)

    var E=0
    var Itro =0
    var S = 0
    var It = 0
    var T = 0
    var F = 0
    var J=0
    var P=0
    var DictPersona = mutableMapOf<String, String>()
    companion object {
        var ResultPersonality: String = ""
    }

    var BoolAns = true //true is A false B

    var arrA = Array<String?>(4) { null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)


        DictPersona["INTJ"] = "Sang Arsitek Pemikir strategis yang imajinatif dan memiliki rencana untuk segala hal."
        DictPersona["INTP"] = "Sang Ahli Logika Penemu inovatif dengan kehausan yang tak terpuaskan akan "
        DictPersona["ENTJ"] = "Sang Komandan Pemimpin yang tegas dan berani, selalu menemukan atau menciptakan jalan."
        DictPersona["ENTP"] = "Sang Pendebat Pemikir yang cerdas dan penasaran, tidak bisa menolak tantangan intelektual."

        DictPersona["INFJ"] = "Sang Advokat Idealis yang pendiam, menginspirasi, dan tak kenal lelah."
        DictPersona["INFP"] = "Pribadi puitis dan baik hati, selalu ingin membantu orang lain."
        DictPersona["ENFJ"] = "Pemimpin yang karismatik dan mampu menginspirasi pendengarnya."
        DictPersona["ENFP"] = "Sang Juru Kampanye Jiwa bebas yang antusias, kreatif, dan mudah bergaul.)"

        DictPersona["ISTJ"] = "Sang Ahli Logistik Individu yang praktis dan mengandalkan fakta, yang keandalannya tidak perlu diragukan "
        DictPersona["ISFJ"] = "Sang Pembela Pelindung yang sangat berdedikasi dan berhati hangat, selalu siap membela orang yang dicintainya."
        DictPersona["ESTJ"] = "Sang Eksekutif Administrator yang hebat, tak tertandingi dalam mengelola berbagai hal atau orang."
        DictPersona["ESFJ"] = "Sang Konsul Pribadi yang sangat peduli, mudah bergaul, dan populer, selalu bersemangat untuk membantu. "

        DictPersona["ISTP"] = "Sang Virtuoso Seorang praktisi yang berani dan kreatif, menguasai semua jenis alat."
        DictPersona["ISFP"] = "Sang Petualang Seniman yang fleksibel dan menawan, selalu siap untuk menjelajahi dan mengalami hal baru."
        DictPersona["ESTP"] = "Sang Pengusaha Orang yang cerdas, bersemangat, dan sangat tanggap, senang hidup di ujung tanduk."
        DictPersona["ESFP"] = "Sang Penghibur Penghibur yang spontan dan antusias, tidak pernah ada momen yang membosankan di sekitarnya."

        val progressContainer = findViewById<LinearLayout>(R.id.progressContainer)
        val questionText = findViewById<TextView>(R.id.questionText)
        val btnnext = findViewById<ImageButton>(R.id.btnNext)
        val Answer= findViewById<MaterialButton>(R.id.btnYes)
        val Bnswer= findViewById<MaterialButton>(R.id.btnNo)

        questionText.text = questions[currentIndex]
        Answer.text = Aanswer[currentIndex]
        Bnswer.text = Banswer[currentIndex]


        progressContainer.removeAllViews()
        for (i in questions.indices) {
            val dot = ImageView(this).apply {
                val size = if (i == currentIndex) dpToPx(14) else dpToPx(12)
                layoutParams = LinearLayout.LayoutParams(size, size).also {
                    (it as LinearLayout.LayoutParams).setMargins(dpToPx(6), 0, dpToPx(6), 0)
                }
                setImageResource(if (i == currentIndex) R.drawable.dot_selected else R.drawable.dot_unselected)
            }
            progressContainer.addView(dot)
        }

        fun updateUI() {

            questionText.text = questions[currentIndex]
            Answer.text = Aanswer[currentIndex]
            Bnswer.text = Banswer[currentIndex]
            when(currentIndex)
            {
                1,5,9,13->
                {

                if(BoolAns == true)
                {
                    E +=1
                }
                else
                {
                    Itro += 1
                }

                }
                2,6,10,14->
                {
                    if(BoolAns == true)
                    {
                        S +=1
                    }
                    else
                    {
                        It += 1
                    }
                }
                3,7,11->
                {
                    if(BoolAns == true)
                    {
                        T +=1
                    }
                    else
                    {
                        F += 1
                    }
                }
                4,8,12->
                {
                    if(BoolAns == true)
                    {
                        J +=1
                    }
                    else
                    {
                        P += 1
                    }
                }
            }
            for (i in 0 until progressContainer.childCount) {
                val iv = progressContainer.getChildAt(i) as ImageView
                iv.setImageResource(if (i == currentIndex) R.drawable.dot_selected else R.drawable.dot_unselected)
            }
        }

        fun CalculateDomain() {
            Dimention1 = if (E > Itro) "E" else "I"
            Dimention2 = if (S > It) "S" else "N"
            Dimention3 = if (T > F) "T" else "F"
            Dimention4 = if (J > P) "J" else "P"

            arrA[0] = Dimention1
            arrA[1] = Dimention2
            arrA[2] = Dimention3
            arrA[3] = Dimention4
        }

        fun PersonalityResult() {
            for ((key, value) in DictPersona) {
                if (arrA.joinToString("") == key) {
                    ResultPersonality = value
                    Log.d("AppDebug", "Matched personality: $ResultPersonality")
                    break
                }
            }


            if (ResultPersonality.isEmpty()) {
                Log.d("Error Result Cause of", "No match found for ${arrA.joinToString("")}")
            }
        }







        Answer.setOnClickListener {
            BoolAns = true
        }
        Bnswer.setOnClickListener {
            BoolAns = false
        }
        findViewById<ImageButton>(R.id.btnPrev).setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }

        }
        findViewById<ImageButton>(R.id.btnNext).setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                updateUI()
            } else {
                CalculateDomain()
                PersonalityResult()
                val intent = Intent(this, ActivityEnd::class.java)
                startActivity(intent)
                finish()
            }
        }



        findViewById<MaterialButton>(R.id.btnYes).setOnClickListener {
            if (currentIndex < questions.size-1) {
                currentIndex++
                updateUI()
            }
        }

        findViewById<MaterialButton>(R.id.btnNo).setOnClickListener {
            if (currentIndex < questions.size-1) {
                currentIndex++
                updateUI()
            }
        }

    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density + 0.5f).toInt()
    }
}