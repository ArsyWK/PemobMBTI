package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

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

    private var currentIndex = 0
    private var BoolAns = true
    private var E = 0 // Extroversion
    private var I = 0 // Introversion
    private var S = 0 // Sensing
    private var N = 0 // Intuition
    private var T = 0 // Thinking
    private var F = 0 // Feeling
    private var J = 0 // Judging
    private var P = 0 // Perceiving

    private var DictPersona = mutableMapOf<String, String>()
    private var ResultPersonality: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        setupPersonalityDictionary()

        val progressContainer = findViewById<LinearLayout>(R.id.progressContainer)
        val questionText = findViewById<TextView>(R.id.questionText)
        val btnPrev = findViewById<ImageButton>(R.id.btnPrev)
        val btnNext = findViewById<ImageButton>(R.id.btnNext)
        val answerAButton = findViewById<MaterialButton>(R.id.btnYes)
        val answerBButton = findViewById<MaterialButton>(R.id.btnNo)

        fun updateUI() {
            if (currentIndex in questions.indices) {
                questionText.text = questions[currentIndex]
                answerAButton.text = Aanswer[currentIndex]
                answerBButton.text = Banswer[currentIndex]

                for (i in 0 until progressContainer.childCount) {
                    val iv = progressContainer.getChildAt(i) as ImageView
                    iv.setImageResource(if (i == currentIndex) R.drawable.dot_selected else R.drawable.dot_unselected)
                }
            }
        }

        fun calculateAndFinish() {
            val dimension1 = if (E >= I) "E" else "I"
            val dimension2 = if (S >= N) "S" else "N"
            val dimension3 = if (T >= F) "T" else "F"
            val dimension4 = if (J >= P) "J" else "P"
            val finalCode = "$dimension1$dimension2$dimension3$dimension4"
            Log.d("AppDebug", "Final Personality Code: $finalCode")


            ResultPersonality = DictPersona[finalCode] ?: "Tipe tidak ditemukan."
            Log.d("AppDebug", "Matched Personality: $ResultPersonality")


            val intent = Intent(this, ActivityEnd::class.java)
            intent.putExtra("RESULT_PERSONALITY", ResultPersonality)
            startActivity(intent)
            finish()
        }

        fun handleAnswer() {

            when (currentIndex) {
                0, 4, 8, 12 -> if (BoolAns) E++ else I++
                1, 5, 9, 13 -> if (BoolAns) S++ else N++
                2, 6, 10    -> if (BoolAns) T++ else F++
                3, 7, 11    -> if (BoolAns) J++ else P++
            }
            Log.d("AppDebug", "Answered Q#$currentIndex. Scores: E=$E, I=$I, S=$S, N=$N, T=$T, F=$F, J=$J, P=$P")



            if (currentIndex < questions.size - 1) {
                currentIndex++
                updateUI()
            } else {
                calculateAndFinish()
            }
        }


        answerAButton.setOnClickListener {
            BoolAns = true
            handleAnswer()
        }


        answerBButton.setOnClickListener {
            BoolAns = false
            handleAnswer()
        }


        btnPrev.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateUI()
            }
        }

        btnNext.setOnClickListener {
            Toast.makeText(this, "Silakan pilih jawaban untuk melanjutkan", Toast.LENGTH_SHORT).show()
        }


        for (i in questions.indices) {
            val dot = ImageView(this).apply {
                val size = dpToPx(12)
                layoutParams = LinearLayout.LayoutParams(size, size).also {
                    (it as LinearLayout.LayoutParams).setMargins(dpToPx(6), 0, dpToPx(6), 0)
                }


                setImageResource(if (i == currentIndex) R.drawable.dot_selected else R.drawable.dot_unselected)
            }
            progressContainer.addView(dot)
        }


        updateUI()
    }

    private fun setupPersonalityDictionary() {
        DictPersona["INTJ"] = "Sang Arsitek: Pemikir strategis yang imajinatif dan memiliki rencana untuk segala hal."
        DictPersona["INTP"] = "Sang Ahli Logika: Penemu inovatif dengan kehausan yang tak terpuaskan akan pengetahuan."
        DictPersona["ENTJ"] = "Sang Komandan: Pemimpin yang tegas dan berani, selalu menemukan atau menciptakan jalan."
        DictPersona["ENTP"] = "Sang Pendebat: Pemikir yang cerdas dan penasaran, tidak bisa menolak tantangan intelektual."
        DictPersona["INFJ"] = "Sang Advokat: Idealis yang pendiam, menginspirasi, dan tak kenal lelah."
        DictPersona["INFP"] = "Sang Mediator: Pribadi puitis dan baik hati, selalu ingin membantu orang lain."
        DictPersona["ENFJ"] = "Sang Protagonis: Pemimpin yang karismatik dan mampu menginspirasi pendengarnya."
        DictPersona["ENFP"] = "Sang Juru Kampanye: Jiwa bebas yang antusias, kreatif, dan mudah bergaul."
        DictPersona["ISTJ"] = "Sang Ahli Logistik: Individu yang praktis dan mengandalkan fakta, yang keandalannya tidak perlu diragukan."
        DictPersona["ISFJ"] = "Sang Pembela: Pelindung yang sangat berdedikasi dan berhati hangat, selalu siap membela orang yang dicintainya."
        DictPersona["ESTJ"] = "Sang Eksekutif: Administrator yang hebat, tak tertandingi dalam mengelola berbagai hal atau orang."
        DictPersona["ESFJ"] = "Sang Konsul: Pribadi yang sangat peduli, mudah bergaul, dan populer, selalu bersemangat untuk membantu."
        DictPersona["ISTP"] = "Sang Virtuoso: Seorang praktisi yang berani dan kreatif, menguasai semua jenis alat."
        DictPersona["ISFP"] = "Sang Petualang: Seniman yang fleksibel dan menawan, selalu siap untuk menjelajahi dan mengalami hal baru."
        DictPersona["ESTP"] = "Sang Pengusaha: Orang yang cerdas, bersemangat, dan sangat tanggap, senang hidup di ujung tanduk."
        DictPersona["ESFP"] = "Sang Penghibur: Penghibur yang spontan dan antusias, tidak pernah ada momen yang membosankan di sekitarnya."
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density + 0.5f).toInt()
    }
}