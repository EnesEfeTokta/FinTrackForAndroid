package com.fintrack.fintrackforandroid.features.application_introduction

import com.fintrack.fintrackforandroid.R
import com.fintrack.fintrackforandroid.data.slide.SlideData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

val slides = listOf(
    SlideData(
        imageRes = R.drawable.ic_hello,
        head = "Welcome to FinTrack: Your First Step to Financial Freedom",
        body = """
                Discover that achieving your financial goals doesn't have to be complicated. 
                FinTrack unifies your entire financial life—from budgeting and investing to expense tracking and detailed analysis—into one powerful, intuitive platform. 
                Get ready to take control of your financial health.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_analytics,
        head = "Data-Driven Decisions: Understand Your Financial Landscape",
        body = """
                Uncover the "why" behind your financial habits. 
                Our advanced analytics tools present your income streams, spending categories, and budget performance in visually rich, easy-to-understand charts. 
                Shape your future more consciously with clear, actionable reports.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_document,
        head = "Your Data, Your Control: Export Financial Reports Freely",
        body = """
                Your financial data shouldn't be confined to the app. 
                With a single click, export your financial summaries, account statements, or budget reports in professional formats like PDF, Excel, and Word. 
                Your data is always at your fingertips for accounting, archiving, or personal analysis.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_increase,
        head = "Every Penny Accounted For: Effortless Expense Tracking",
        body = """
                Financial awareness begins with recording every income and expense. 
                With smart categorization and quick-entry features, easily log your daily spending and income sources. 
                See exactly where your money is going to eliminate waste and maximize savings.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_investment,
        head = "Achieve Your Dreams: Automate Your Savings Goals",
        body = """
                Saving is no longer a chore, but a motivating journey. 
                Whether it's for a vacation or a new home, create your goals and let FinTrack monitor your progress for you. 
                Gaining financial discipline has never been this straightforward.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_payment,
        head = "Premium Features, Accessible Price: An Investment in Your Financial Health",
        body = """
                We believe powerful financial management tools should be accessible to everyone. 
                We are proud to offer our comprehensive feature set through a transparent and competitive membership model that won't strain your budget. 
                No hidden fees, just a service focused on your financial growth.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_statistic,
        head = "Global Markets at Your Fingertips: Track Exchange Rates in Real-Time",
        body = """
                Stay informed while managing your investments or international spending. 
                Track real-time exchange rates for dozens of fiat and digital currencies, access historical data, and analyze market trends to make the most accurate decisions for your portfolio.
                """
    ),
    SlideData(
        imageRes = R.drawable.ic_chatbot,
        head = "Your Smart Financial Assistant: Unleash Your Potential with AI",
        body = """
                Don't get lost in piles of data. Our AI-powered assistant analyzes your financial records to provide personalized savings tips, detect unusual spending, and identify potential areas for improvement in your budget. 
                Your financial advisor is now available 24/7.
                """
    )
)

data class IntroductionUiState(
    val currentSlide: SlideData = slides.first(),
    val currentSlideIndex: Int = 0,
    val isBackButtonEnabled: Boolean = false,
    val isLastSlide: Boolean = false
)

class ApplicationIntroductionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(IntroductionUiState())

    val uiState: StateFlow<IntroductionUiState> = _uiState.asStateFlow()

    fun onNextClicked() {
        val currentIndex = _uiState.value.currentSlideIndex
        if (currentIndex < slides.lastIndex) {
            updateSlide(currentIndex + 1)
        } else {
            // TODO: Ana ekrana geçiş veya giriş yapma mantığını tetikle.
            println("Tanıtım bitti, ana ekrana yönlendiriliyor...")
        }
    }

    fun onBackClicked() {
        val currentIndex = _uiState.value.currentSlideIndex
        if (currentIndex > 0) {
            updateSlide(currentIndex - 1)
        }
    }

    fun onSkipClicked() {
        // TODO: Ana ekrana geçiş veya giriş yapma mantığını tetikle.
        println("Tanıtım atlandı, ana ekrana yönlendiriliyor...")
    }

    private fun updateSlide(newIndex: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentSlideIndex = newIndex,
                currentSlide = slides[newIndex],
                isBackButtonEnabled = newIndex > 0,
                isLastSlide = newIndex == slides.lastIndex
            )
        }
    }
}