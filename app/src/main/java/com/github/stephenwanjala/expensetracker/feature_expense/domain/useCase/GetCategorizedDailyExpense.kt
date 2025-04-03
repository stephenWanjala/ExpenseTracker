package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import android.os.Parcelable
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toLocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GetCategorizedDailyExpense(private val repository: ExpenseRepository) {
    operator fun invoke(expenseOrder: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending)): Flow<List<CategorizedDailyExpense>> {
        return repository.getAllExpenses()
            .map { expenses ->
                // Group expenses by category and date
                val categorizedExpenses = expenses
                    .groupBy { it.category }
                    .flatMap { (category, categoryExpenses) ->
                        categoryExpenses
                            .groupBy { it.date.toLocalDateTime().dayOfWeek.value }
                            .map { (dayOfWeek, dateExpenses) ->
                                val date =  dateExpenses.firstOrNull()?.date?.toLocalDateTime() ?: LocalDateTime.now()
                                CategorizedDailyExpense(
                                    category = Category.valueOf(category),
                                    date = date,
                                    dayOfWeek=dayOfWeek,
                                    amount = dateExpenses.sumOf { it.amount }
                                )
                            }
                    }
                // Sort the result by date
                categorizedExpenses.sortedByDescending { it.date }
            }
            .map { categorizedDailyExpenseList ->
                when (expenseOrder) {
                    is ExpenseOrder.Date -> {
                        when (expenseOrder.orderType) {
                            is OrderType.Ascending -> categorizedDailyExpenseList.sortedBy { it.date }
                            is OrderType.Descending -> categorizedDailyExpenseList.sortedByDescending { it.date }
                        }
                    }

                    is ExpenseOrder.Amount -> {
                        when (expenseOrder.orderType) {
                            is OrderType.Ascending -> categorizedDailyExpenseList.sortedBy { it.amount }
                            is OrderType.Descending -> categorizedDailyExpenseList.sortedByDescending { it.amount }
                        }
                    }

                    is ExpenseOrder.Category -> {
                        when (expenseOrder.orderType) {
                            is OrderType.Ascending -> categorizedDailyExpenseList.sortedBy { it.category }
                            is OrderType.Descending -> categorizedDailyExpenseList.sortedByDescending { it.category }
                        }
                    }
                }
            }
            .flowOn(Dispatchers.IO)
    }
}


@Parcelize
data class CategorizedDailyExpense(
    val category: Category,
    val date: LocalDateTime,
    val amount: Double,
    val dayOfWeek: Int,
) : Parcelable

@OptIn(ExperimentalSerializationApi::class)
object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.format(formatter))
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val string = decoder.decodeString()
        return LocalDateTime.parse(string, formatter)
    }
}