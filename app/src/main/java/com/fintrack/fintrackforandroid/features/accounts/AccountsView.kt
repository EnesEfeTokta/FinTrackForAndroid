package com.fintrack.fintrackforandroid.features.accounts

import com.fintrack.fintrackforandroid.data.account.Account
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.fintrack.fintrackforandroid.features.dashboard.DarkBackground
import com.fintrack.fintrackforandroid.features.dashboard.PinkGradientStart
import com.fintrack.fintrackforandroid.features.dashboard.TextColor
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fintrack.fintrackforandroid.features.dashboard.CardBackground
import com.fintrack.fintrackforandroid.features.dashboard.GreenAccent
import com.fintrack.fintrackforandroid.features.dashboard.RedAccent
import com.fintrack.fintrackforandroid.features.dashboard.TextGray

// UI'ı test etmek için sahte hesap listesi
val dummyAccounts = listOf(
    Account(1, "Maaş Hesabım", 80000.0, "TRY", Icons.Default.AccountBalance),
    Account(2, "Kredi Kartı", -3500.50, "TRY", Icons.Default.CreditCard),
    Account(3, "Nakit Cüzdan", 1500.0, "TRY", Icons.Default.AccountBalanceWallet),
    Account(4, "Birikim Hesabı", 15000.0, "TRY", Icons.Default.Savings),
    Account(5, "Dolar Hesabı", 2500.0, "USD", Icons.Default.AttachMoney)
)

// --- Ana Ekran Composable ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen() {
    // Arama çubuğunun metnini tutmak için bir state
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("All Accounts", color = TextColor) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Geri gitme işlemi */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DarkBackground
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Yeni hesap oluşturma ekranını aç */ },
                containerColor = PinkGradientStart
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Account", tint = TextColor)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Arama Çubuğu
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Search accounts...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                singleLine = true,

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hesap Listesi
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dummyAccounts.filter { it.name.contains(searchQuery, ignoreCase = true) }) { account ->
                    AccountItemCard(account = account)
                }
            }
        }
    }
}

// --- Alt Bileşen: Hesap Kartı ---

@Composable
fun AccountItemCard(
    account: Account,
    onEditClick: () -> Unit = {}, // Düzenleme tıklandığında
    onDeleteClick: () -> Unit = {} // Silme tıklandığında
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = account.icon,
                contentDescription = account.name,
                modifier = Modifier.size(40.dp),
                tint = PinkGradientStart
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = account.name,
                    color = TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${account.balance} ${account.currency}",
                    color = if (account.balance >= 0) GreenAccent else RedAccent,
                    fontSize = 16.sp
                )
            }

            // Düzenle ve Sil Butonları
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Account", tint = TextGray)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Account", tint = RedAccent)
            }
        }
    }
}


// --- Önizleme ---

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun AccountsScreenPreview() {
    MaterialTheme {
        AccountsScreen()
    }
}