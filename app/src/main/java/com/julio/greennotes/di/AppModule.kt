package com.julio.greennotes.di

import com.julio.greennotes.TaskViewModel
import com.julio.greennotes.dao.GreenNotesDataBase
import com.julio.greennotes.repository.TaskRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ (taskRepository : TaskRepository) -> TaskViewModel(taskRepository) }
}