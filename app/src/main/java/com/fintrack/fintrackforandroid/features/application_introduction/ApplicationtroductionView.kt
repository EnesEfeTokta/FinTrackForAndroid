package com.fintrack.fintrackforandroid.features.application_introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fintrack.fintrackforandroid.ui.theme.FinTrackForAndroidTheme
import com.fintrack.fintrackforandroid.R

data class SlideData(
    val imageRes: Int,
    val head: String,
    val body: String
)

val slides = listOf(
    SlideData(
        imageRes = R.drawable.ic_message,
        head = "FinTrack'e Hoş Geldiniz",
        body = "Tüm finansal işlemlerinizi tek bir yerden kolayca yönetin."
    ),
    SlideData(
        imageRes = R.drawable.ic_message,
        head = "Bütçenizi Planlayın",
        body = "Harcamalarınızı takip ederek hedeflerinize daha hızlı ulaşın."
    ),
    SlideData(
        imageRes = R.drawable.ic_message,
        head = "Raporları İnceleyin",
        body = "Anlaşılır grafiklerle finansal durumunuzu anında görün."
    )
)

@Composable
fun ApplicationtroductionScreen()
{
    var currentSlideIndex by remember { mutableStateOf(0) }
    val currentSlide = slides[currentSlideIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff0a0e14))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = currentSlide.imageRes),
                contentDescription = "Slide Illustration",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = currentSlide.head,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentSlide.body,
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    Button(
                        /* TODO: Burası iş mantığı katmamında olacak. */
                        onClick = {
                            if (currentSlideIndex > 0) {
                                currentSlideIndex--
                            }
                        },
                        enabled = currentSlideIndex > 0,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.LightGray
                        )
                    ) {
                        Text("Geri", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        /* TODO: Burası iş mantığı katmamında olacak. */
                        onClick = {
                            if (currentSlideIndex < slides.lastIndex) {
                                currentSlideIndex++
                            } else {
                                // İleri işlemi...
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1DB954),
                            contentColor = Color.White
                        )
                    ) {
                        val buttonText =
                            if (currentSlideIndex == slides.lastIndex) "Başla" else "İleri"
                        Text(buttonText, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                TextButton(
                    onClick = { /* TODO: Tanıtımı atla... */ },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(text = "Atla", color = Color(0xFF1E88E5), fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ApplicationIntroductionScreenPreview() {
    FinTrackForAndroidTheme {
        ApplicationtroductionScreen()
    }
}