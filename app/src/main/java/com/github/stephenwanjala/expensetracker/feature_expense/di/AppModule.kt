package com.github.stephenwanjala.expensetracker.feature_expense.di

import android.content.Context
import androidx.room.Room
import com.github.stephenwanjala.expensetracker.feature_expense.data.datasource.ExpenseDatabase
import com.github.stephenwanjala.expensetracker.feature_expense.data.datasource.ExpenseDatabase.Companion.DATABASE_NAME
import com.github.stephenwanjala.expensetracker.feature_expense.data.repositoryImpl.ExpenseRepositoryImpl
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.ExpenseScreenUseCase
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.GetCategorizedDailyExpense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.SaveExpense
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExpenseDatabase(@ApplicationContext context: Context): ExpenseDatabase =
        Room.databaseBuilder(context, ExpenseDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideExpenseRepository(db: ExpenseDatabase): ExpenseRepository =
        ExpenseRepositoryImpl(dao = db.dao)

    @Provides
    @Singleton
    fun provideExpenseScreenUseCase(repository: ExpenseRepository) = ExpenseScreenUseCase(
        categorizedDailyExpense = GetCategorizedDailyExpense(repository),
        saveExpense = SaveExpense(repository)
    )

}