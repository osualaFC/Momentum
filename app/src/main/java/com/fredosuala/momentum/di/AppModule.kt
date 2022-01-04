package com.fredosuala.momentum.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fredosuala.momentum.data.Converter
import com.fredosuala.momentum.data.Moment2mDatabase
import com.fredosuala.momentum.data.dao.Moment2mDao
import com.fredosuala.momentum.data.repository.RepositoryImpl
import com.fredosuala.momentum.domain.repository.Repository
import com.fredosuala.momentum.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : Moment2mDatabase {
        return Room.databaseBuilder(
            app,
            Moment2mDatabase::class.java,
            Moment2mDatabase.DATABASE_NAME
            )
            .addTypeConverter(Converter())
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: Moment2mDatabase) : Repository {
        return RepositoryImpl(database.dao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository, app: Application) : UseCases {
        return UseCases(
            clearTable = ClearTable(repository),
            createHabit = CreateHabit(repository),
            deleteHabit = DeleteHabit(repository),
            generateTodayTasks = GenerateTodayTasks(repository, app),
            getHabit = GetHabit(repository),
            updateHabit = UpdateHabit(repository)
        )
    }
}