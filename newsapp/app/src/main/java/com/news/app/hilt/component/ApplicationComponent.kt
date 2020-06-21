//package com.news.app.dagger.component
//
//import android.content.Context
//import com.news.app.*
//import com.news.app.dagger.module.*
//import dagger.BindsInstance
//import dagger.Component
//import dagger.android.AndroidInjectionModule
//import dagger.android.AndroidInjector
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [
//    AndroidInjectionModule::class,
//    ApplicationModule::class,
//    BindingModule::class,
//    MediaModule::class,
//    FragmentModule::class,
//    ViewModelModule::class
//])
//interface ApplicationComponent : AndroidInjector<NewsApplication> {
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance application: Context): ApplicationComponent
//    }
//}