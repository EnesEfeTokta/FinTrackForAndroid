package com.fintrack.fintrackforandroid.features.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fintrack.fintrackforandroid.R
import androidx.compose.foundation.BorderStroke

val MainBackground = Color(0xFF1a1b26)
val CardBackground = Color(0xFF24283b)
val TextPrimary = Color(0xFFc0caf5)
val TextSecondary = Color(0xFFa9b1d6)
val DividerColor = TextSecondary.copy(alpha = 0.5f)
val ProgressBarBackground = Color(0xFF414868)
val StatusGreen = Color(0xFF9ece6a)
val StatusRed = Color(0xFFf7768e)
val TotalBalanceGradient = Brush.verticalGradient(listOf(Color(0xFFff9e64), Color(0xFFdb4b4b)))
val MembershipAccentGradient = Brush.linearGradient(listOf(Color(0xFFbb9af7), Color(0xFF7dcfff)))
val DebtAccentGradient = Brush.linearGradient(listOf(Color(0xFF292e42), Color(0xFF24283b)))
val ReportFormatButtonColor = Color(0xFF3b4261)

@Composable
fun DashboardScreen() {
    // XAML'deki UserControl'ün arka planı ve padding'i
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(15.dp)
    ) {
        // XAML'deki ana Grid (3 sütunlu) -> Row ile taklit edilir.
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(15.dp) // Sütunlar arası boşluk
        ) {
            // SÜTUN 1 (SOL)
            Column(
                modifier = Modifier.weight(1.2f),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                BudgetsCard()
                CurrenciesCard()
            }

            // SÜTUN 2 (ORTA)
            Column(
                modifier = Modifier.weight(1.5f),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AccountsCard()
                TransactionsCard()
                MembershipCard()
            }

            // SÜTUN 3 (SAĞ)
            Column(
                modifier = Modifier.weight(1.3f),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DebtsCard()
                ReportsCard()
            }
        }
    }
}

// --- Yeniden Kullanılabilir Composable'lar (XAML'deki stillere benzer) ---

@Composable
fun DashboardCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        content = {
            Column(modifier = Modifier.padding(15.dp), content = content)
        }
    )
}

@Composable
fun CardTitle(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
    )
}

@Composable
fun CardDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 15.dp),
        color = DividerColor
    )
}

// --- SÜTUN 1 COMPOSABLE'LARI ---

@Composable
fun BudgetsCard() {
    DashboardCard {
        CardTitle("Budgets")
        CardDivider()

        // XAML: ItemsControl with UniformGrid -> LazyVerticalGrid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            // Yüksekliği içeriğe göre ayarla
            modifier = Modifier.height(180.dp) // Örnek yükseklik, dinamik olabilir
        ) {
            items(4) { // Örnek veri
                BudgetSubCard(statusColor = if (it % 2 == 0) StatusGreen else StatusRed)
            }
        }
    }
}

@Composable
fun BudgetSubCard(statusColor: Color) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, statusColor),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("Groceries", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("Due: 25.12.2024", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.padding(top = 5.dp))
            Text("₺1500.00", color = statusColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("10 days left", color = TextSecondary, fontSize = 12.sp, modifier = Modifier.padding(top = 5.dp))
        }
    }
}


@Composable
fun CurrenciesCard() {
    DashboardCard(modifier = Modifier.fillMaxHeight()) {
        CardTitle("Currencies")
        CardDivider()

        // XAML: ScrollViewer with ItemsControl -> LazyColumn
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(5) { // Örnek veri
                CurrencyItem()
                if (it < 4) { // Son elemandan sonra çizgi koyma
                    HorizontalDivider(color = DividerColor.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}

@Composable
fun CurrencyItem() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // From Currency
        Image(painter = painterResource(id = R.drawable.ic_flag_try), contentDescription = "Turkish Flag", modifier = Modifier.size(30.dp, 20.dp), contentScale = ContentScale.FillBounds)
        Spacer(Modifier.width(10.dp))
        Column {
            Text("Türkiye", color = TextSecondary, fontSize = 12.sp)
            Text("TRY", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("100.00", color = TextSecondary, fontSize = 12.sp)
        }
        Spacer(Modifier.weight(1f))
        // To Currency
        Column(horizontalAlignment = Alignment.End) {
            Text("United States", color = TextSecondary, fontSize = 12.sp)
            Text("USD", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text("3.05", color = TextSecondary, fontSize = 12.sp)
        }
        Spacer(Modifier.width(10.dp))
        Image(painter = painterResource(id = R.drawable.ic_flag_usd), contentDescription = "US Flag", modifier = Modifier.size(30.dp, 20.dp), contentScale = ContentScale.FillBounds)
    }
}

// --- SÜTUN 2 COMPOSABLE'LARI ---

@Composable
fun AccountsCard() {
    DashboardCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                CardTitle("Accounts")
                CardDivider()
                // XAML: ItemsControl -> Column (veya LazyColumn)
                Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    AccountItem(progress = 0.7f, color = StatusGreen)
                    AccountItem(progress = 0.4f, color = Color.Yellow)
                }
            }
            Spacer(Modifier.width(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Total:", color = TextSecondary, fontSize = 14.sp)
                Text(
                    "₺24,580.12",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    // XAML: Foreground="{StaticResource TotalBalanceGradientBrush}"
                    style = LocalTextStyle.current.copy(
                        brush = TotalBalanceGradient,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Composable
fun AccountItem(progress: Float, color: Color) {
    // XAML: Grid -> Box
    Box(contentAlignment = Alignment.CenterStart) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Vakıfbank", color = TextPrimary, fontSize = 14.sp)
                Spacer(Modifier.weight(1f))
                Text("₺18,500.00", color = TextPrimary, fontSize = 14.sp)
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                color = color,
                trackColor = ProgressBarBackground
            )
        }
    }
}


@Composable
fun TransactionsCard() {
    DashboardCard(modifier = Modifier.fillMaxHeight(0.6f)) { // Yüksekliği ayarla
        CardTitle("Transactions")
        CardDivider()
        Text("Last 5 transactions are shown.", color = TextSecondary, fontSize = 12.sp)
        Spacer(Modifier.height(10.dp))

        // XAML: ListView -> LazyColumn
        LazyColumn {
            items(5) {
                TransactionItem()
            }
        }
    }
}

@Composable
fun TransactionItem() {
    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date Badge
        Box(
            modifier = Modifier
                .background(StatusGreen, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 10.dp)
        ) {
            Text("25\nDEC", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, textAlign = TextAlign.Center, lineHeight = 12.sp)
        }
        Spacer(Modifier.width(15.dp))
        Text("Spotify Subscription", modifier = Modifier.weight(1f), color = TextPrimary, overflow = TextOverflow.Ellipsis, maxLines = 1)
        Spacer(Modifier.width(15.dp))
        Text("- ₺99.99", color = StatusRed, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.width(15.dp))
        // Category Tag
        Box(
            modifier = Modifier
                .border(1.dp, TextSecondary.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text("Entertainment", color = TextSecondary, fontSize = 12.sp)
        }
    }
}


@Composable
fun MembershipCard() {
    DashboardCard {
        CardTitle("Membership")
        CardDivider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(MembershipAccentGradient, RoundedCornerShape(15.dp))
                    .padding(horizontal = 30.dp, vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("PRO", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            }
            Spacer(Modifier.width(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MembershipInfoRow("Membership Start:", "01.01.2023")
                MembershipInfoRow("Membership Renewal:", "01.01.2025")
                MembershipInfoRow("Membership Fee:", "₺299.99/year")
            }
        }
    }
}

@Composable
fun MembershipInfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = TextPrimary, fontSize = 13.sp)
        Text(value, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

// --- SÜTUN 3 COMPOSABLE'LARI ---

@Composable
fun DebtsCard() {
    DashboardCard {
        CardTitle("Debts")
        CardDivider()

        // Bu bölüm XAML'de veri yoksa gizleniyor, burada da bir koşulla yapılabilir.
        // if (currentDebt != null) { ... }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DebtAccentGradient, RoundedCornerShape(15.dp))
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DebtParticipant(name = "John Doe", iconRes = R.drawable.ic_person_lender, color = StatusGreen)
                    DebtParticipant(name = "You", iconRes = R.drawable.ic_person_borrower, color = StatusRed)
                }

                HorizontalDivider(
                    color = StatusGreen.copy(alpha = 0.8f),
                    modifier = Modifier.padding(vertical = 15.dp)
                )

                Row(verticalAlignment = Alignment.Bottom) {
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        DebtDateInfo("Creation:", "15.08.2024")
                        DebtDateInfo("Final Payment:", "15.02.2025", isBold = true)
                        DebtDateInfo("Review:", "15.11.2024")
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            "Pending Payment",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            "₺2,500.00",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DebtParticipant(name: String, iconRes: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = iconRes), contentDescription = name, modifier = Modifier.size(30.dp))
        Text(
            name,
            color = color,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun DebtDateInfo(label: String, date: String, isBold: Boolean = false) {
    Row {
        Text("$label ", color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
        Text(date, color = Color.White, fontSize = 11.sp, fontWeight = if(isBold) FontWeight.SemiBold else FontWeight.Normal)
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReportsCard() {
    DashboardCard(modifier = Modifier.fillMaxHeight()) {
        CardTitle("Reports")
        CardDivider()

        // ScrollViewer -> verticalScroll
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            ReportItem("Annual Income Report")
            Spacer(Modifier.height(15.dp))
            ReportItem("Expense by Category")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReportItem(name: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground.copy(alpha=0.4f)) // Farklı bir arka plan
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_report),
                contentDescription = "Report Icon",
                modifier = Modifier.size(60.dp)
            )
            Spacer(Modifier.width(25.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // XAML: WrapPanel -> FlowRow
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ReportFormatButton("PDF")
                    ReportFormatButton("Excel")
                    ReportFormatButton("CSV")
                }
            }
        }
    }
}

@Composable
fun ReportFormatButton(format: String) {
    Button(
        onClick = { /* Rapor oluşturma komutu burada tetiklenir */ },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ReportFormatButtonColor),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(format, color = TextSecondary)
    }
}

// --- PREVIEW ---
@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}