package com.fintrack.fintrackforandroid.features.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fintrack.fintrackforandroid.R

val DarkBackground = Color(0xFF121212)
val CardBackground = Color(0xFF1E1E1E)
val PurpleDark = Color(0xFF3A2E5B)
val TextColor = Color.White
val TextGray = Color.LightGray
val PinkGradientStart = Color(0xFFC86DD7)
val BlueGradientEnd = Color(0xFF3023AE)
val RedAccent = Color(0xFFE53935)
val GreenAccent = Color(0xFF43A047)


@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item { TopBarSection() }

        // Hoş Geldin Mesajı
        item {
            Text(
                text = "Welcome, Enes",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Bakiye Kartı
        item { BalanceCard() }

        item { ActionCard(title = "All Accounts", imagePainter = painterResource(id = R.drawable.bg_credit_card)) }
        item { ActionCard(title = "All Budgets", imagePainter = painterResource(id = R.drawable.bg_budget)) }
        item { ActionCard(title = "All Transactions", imagePainter = painterResource(id = R.drawable.bg_transaction)) }
        item { ActionCard(title = "All Debts", imagePainter = painterResource(id = R.drawable.bg_debts)) }
        item { ActionCard(title = "All Currencies", imagePainter = painterResource(id = R.drawable.bg_currency)) }

        // Bütçeler Bölümü
        item { BudgetsSection() }

        // İşlemler Bölümü
        item { TransactionsSection() }

        // Borçlar Bölümü
        item { DebtsSection() }

        // Kurlar Bölümü
        item { CurrenciesSection() }
    }
}

@Composable
fun TopBarSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_register),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "Enes E. Tokta", color = TextColor, fontWeight = FontWeight.Bold)
                Text(text = "enesefetokta009@gmail.com", color = TextGray, fontSize = 12.sp)
            }
        }
        Row {
            IconButton(onClick = { /* Ayarlar tıklandı */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = TextColor)
            }
            BadgedBox(
                badge = { Badge(modifier = Modifier.offset(x = (-6).dp, y = 4.dp)) { } }
            ) {
                IconButton(onClick = { /* Bildirimler tıklandı */ }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = TextColor)
                }
            }
        }
    }
}

@Composable
fun BalanceCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(BlueGradientEnd, PinkGradientStart)
                    )
                )
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Your Balance", color = TextColor.copy(alpha = 0.8f), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "95.000 TRY", color = TextColor, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(24.dp))

                AccountRow(accountName = "Account 1", amount = "80.000 TRY")
                Spacer(modifier = Modifier.height(12.dp))
                AccountRow(accountName = "Account 2", amount = "15.000 TRY")
            }
        }
    }
}

@Composable
fun AccountRow(accountName: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, TextColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Home, contentDescription = "Account Icon", tint = TextColor)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = accountName, color = TextColor, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = amount, color = TextColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun ActionCard(title: String, imagePainter: Painter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = imagePainter,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f)))

            Text(
                text = title,
                color = TextColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = TextColor,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun BudgetsSection() {
    Column {
        SectionTitle(title = "Budgets")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = PurpleDark)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Budget 1", color = TextColor, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(16.dp))
                LinearProgressIndicator(
                    progress = { 0.5f }, // 9000 / 18000 = 0.5
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = GreenAccent,
                    trackColor = RedAccent
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "9.000 TRY", color = TextGray)
                    Text(text = "18.000 TRY", color = TextGray)
                }
            }
        }
    }
}

@Composable
fun TransactionsSection() {
    Column {
        SectionTitle(title = "Transactions")
        Text(
            text = "A total of 4 transactions were found. Income: 5.500 TRY, Expenses: 3.500 TRY, Balance: 2.000 TRY",
            color = TextGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TransactionItem("03.01.2025", "Transaction 1", "2.000 TRY")
                Divider(color = TextGray.copy(alpha = 0.3f))
                TransactionItem("03.01.2025", "Transaction 2", "5.000 TRY")
                Divider(color = TextGray.copy(alpha = 0.3f))
                TransactionItem("03.01.2025", "Transaction 3", "1.500 TRY")
                Divider(color = TextGray.copy(alpha = 0.3f))
                TransactionItem("03.01.2025", "Transaction 4", "500 TRY")
            }
        }
    }
}

@Composable
fun TransactionItem(date: String, description: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = date, color = TextGray, modifier = Modifier.weight(1f))
        Text(text = description, color = TextColor, modifier = Modifier.weight(2f))
        Text(text = amount, color = TextColor, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
    }
}

@Composable
fun DebtsSection() {
    Column {
        SectionTitle(title = "Debts")
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = PurpleDark)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DebtParticipant(name = "Ramazan Tokta", avatar = painterResource(id = R.drawable.ic_register))
                    // TODO: İki kişi arasında bir ok veya ikon eklenebilir.
                    DebtParticipant(name = "Enes Efe Tokta", avatar = painterResource(id = R.drawable.ic_register))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Aktif", color = GreenAccent, fontSize = 14.sp)
                        Text(
                            text = "Final Payment",
                            color = TextGray,
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.Underline
                        )
                        Text(text = "07.05.2025", color = TextGray, fontSize = 12.sp)
                    }
                    Text(
                        text = "5.000 TRY",
                        color = TextColor,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun DebtParticipant(name: String, avatar: Painter) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = avatar,
            contentDescription = name,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, color = TextColor, fontSize = 12.sp)
    }
}


@Composable
fun CurrenciesSection() {
    Column {
        SectionTitle(title = "Currencies")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CurrencyCard(
                modifier = Modifier.weight(1f),
                baseCurrency = "1 USD",
                targetCurrency = "42 TRY",
                targetColor = RedAccent
            )
            CurrencyCard(
                modifier = Modifier.weight(1f),
                baseCurrency = "1 USD",
                targetCurrency = "0.8 EUR",
                targetColor = GreenAccent
            )
        }
    }
}

@Composable
fun CurrencyCard(modifier: Modifier = Modifier, baseCurrency: String, targetCurrency: String, targetColor: Color) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = baseCurrency,
                color = TextGray,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = targetCurrency,
                color = targetColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, heightDp = 2500)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}