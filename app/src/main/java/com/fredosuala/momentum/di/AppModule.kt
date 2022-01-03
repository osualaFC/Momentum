package com.fredosuala.momentum.di

import android.app.Application
import androidx.room.Room
import com.fredosuala.momentum.data.Moment2mDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
            Moment2mDatabase.DATABASE_NAME)
            .build()
    }
}