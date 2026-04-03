package eu.project.common.crashlytics

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CrashlyticsManagerModule {

    @Provides
    @Singleton
    fun provideCrashlyticsManager(): CrashlyticsManager =
        CrashlyticsManagerImpl()
}